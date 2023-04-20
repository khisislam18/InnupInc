package com.innup.startupinvest.services;

import com.innup.startupinvest.models.User;
import com.innup.startupinvest.repositories.UserRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepositories userRepositories;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepositories.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException(email);
        }
        return user;
    }
}
