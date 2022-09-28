package uz.pdp.clickup.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickup.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    Optional<User> findByEmailAndEmailCode(String email, String emailCode);
}
