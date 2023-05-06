package com.innup.startupinvest.services;

import com.innup.startupinvest.models.StartUp;
import com.innup.startupinvest.models.User;
import com.innup.startupinvest.models.enums.Role;
import com.innup.startupinvest.repositories.UserRepositories;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

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
        user.getRoles().add(Role.ROLE_ADMIN);
        log.info("Saving new User with email: {}", email);
        userRepositories.save(user);
        return true;
    }
    public User getUserByPrincipal(Principal principal) {
        if(principal == null){
            return null;
        }
        return userRepositories.findByEmail(principal.getName());
    }
    public List<User> list(){
        return userRepositories.findAll();
    }

    public void ban(Long id) {
        User user = userRepositories.findById(id).orElse(null);
        if(user != null){
            if(user.isActive()){
                user.setActive(false);
                log.info("Ban user with id = {}, email = {}", user.getId(),user.getEmail());
            }else{
                user.setActive(true);
                log.info("User with id = {}, email = {} is active now", user.getId(), user.getEmail());
            }
        }
        assert user != null;
        userRepositories.save(user);
    }

    public void updateUser(Long id, String username, String phoneNumber, String email) {
        User userChange = userRepositories.findById(id).orElse(null);
        if(userChange == null){
            log.info("user is null");
        }
        assert userChange != null;
        userChange.setName(username);
        userChange.setPhoneNumber(phoneNumber);
        userChange.setEmail(email);
        log.info("Saved changes for user id = {}", id);
        userRepositories.save(userChange);
    }
    public User getUserById(Long id) {
        return userRepositories.findById(id).orElse(null);
    }
}
