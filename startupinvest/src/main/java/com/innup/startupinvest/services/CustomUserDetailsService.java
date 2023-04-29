package com.innup.startupinvest.services;

import com.innup.startupinvest.repositories.UserRepositories;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
    private final UserRepositories userRepositories;


    @Override
    public UserDetails loadUserByUsername(String email) {
        try {
            return userRepositories.findByEmail(email);
        }catch (InternalAuthenticationServiceException internalAuthenticationServiceException){
            throw new InternalAuthenticationServiceException("Invalid username, not found in database");
        }catch(UsernameNotFoundException usernameNotFoundException){
            throw new UsernameNotFoundException("Invalid username, not found in database");
        }
    }
}
