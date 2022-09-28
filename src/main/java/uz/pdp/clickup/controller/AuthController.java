package uz.pdp.clickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickup.AuthService;
import uz.pdp.clickup.payload.ApiResponse;
import uz.pdp.clickup.payload.LoginDto;
import uz.pdp.clickup.payload.RegisterDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Lazy
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody RegisterDto registerDto){
        ApiResponse apiResponse = authService.register(registerDto);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDto loginDto){
        ApiResponse apiResponse = authService.login(loginDto);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PutMapping("/verifyEmail")
    public HttpEntity<?> verifyEmail(@RequestParam String email,@RequestParam String emailCode){
        ApiResponse apiResponse = authService.verifyEmail(email,emailCode);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }
}
