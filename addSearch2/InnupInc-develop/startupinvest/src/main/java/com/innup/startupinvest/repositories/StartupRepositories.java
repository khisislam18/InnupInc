package com.innup.startupinvest.repositories;

import com.innup.startupinvest.models.StartUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StartupRepositories extends JpaRepository<StartUp, Long> {
    List<StartUp> findByTitle(String title);
    Iterable<StartUp> findByTitleContainingIgnoreCase (String title);
}
