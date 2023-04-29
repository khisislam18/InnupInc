package com.innup.startupinvest.services;

import com.innup.startupinvest.models.User;
import com.innup.startupinvest.models.enums.Role;
import com.innup.startupinvest.repositories.UserRepositories;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepositories userRepositories;
    private final PasswordEncoder passwordEncoder;


    public boolean createUser(User user){
        String email = user.getEmail();
        if (userRepositories.findByEmail(email) != null) {
            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}", email);
        userRepositories.save(user);
        return true;
    }
}
