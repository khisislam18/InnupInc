package com.innup.startupinvest.repositories;

import com.innup.startupinvest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepositories extends JpaRepository<User, Long> {
    User findByEmail(String email);
}