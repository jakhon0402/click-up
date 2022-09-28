package uz.pdp.clickup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.clickup.entity.User;
import uz.pdp.clickup.payload.ApiResponse;
import uz.pdp.clickup.payload.LoginDto;
import uz.pdp.clickup.payload.RegisterDto;
import uz.pdp.clickup.repository.UserRepo;
import uz.pdp.clickup.security.JwtProvider;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Lazy
    @Autowired
    JavaMailSender javaMailSender;

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public ApiResponse register(RegisterDto registerDto) {
        boolean existsByEmail = userRepo.existsByEmail(registerDto.getEmail());
        if(existsByEmail){
            return new ApiResponse("Bunday email avval ro'yhatdan o'tgan!",false);
        }
        User user = new User(
                registerDto.getFullName(),
                registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword()));
        int emailCode = new Random().nextInt(999999);
        user.setEmailCode(String.valueOf(emailCode).substring(0,4));
        userRepo.save(user);
        sendEmail(user.getEmail(), user.getEmailCode());
        return new ApiResponse("User ro'yhatdan o'tdi iltimos aktivatsiya qiling!",true);
    }

    public boolean sendEmail(String sendingEmail, String emailCode){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("jahon99king@gmail.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Akkountni tasdiqlash");
            mailMessage.setText("<a href='http://localhost:8080/api/auth/verifyEmail?emailCode="+emailCode+"&email="+sendingEmail+"'></a>");
            javaMailSender.send(mailMessage);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public ApiResponse verifyEmail(String email, String emailCode) {
        Optional<User> optionalUser = userRepo.findByEmailAndEmailCode(email, emailCode);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setEmailCode(null);
            user.setEnabled(true);
            userRepo.save(user);
            return new ApiResponse("Akkount tasdiqlandi!",true);
        }

        return new ApiResponse("Akkount allaqachon tasdiqlangan!",false);
    }

    public ApiResponse login(LoginDto loginDto){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword()));
            User user = (User) authentication.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getEmail());
            return new ApiResponse(token,true);
        }
        catch (BadCredentialsException badCredentialsException){
            return new ApiResponse("Parol yoki login xato",false);

        }
    }
}
