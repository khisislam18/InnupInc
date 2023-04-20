package com.innup.startupinvest.repositories;

import com.innup.startupinvest.models.StartUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StartupRepositories extends JpaRepository<StartUp, Long> {
    List<StartUp> findByTitle(String title);
}
