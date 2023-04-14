package com.innup.startupinvest.services;

import com.innup.startupinvest.models.StartUp;
import com.innup.startupinvest.repositories.StartupRepositories;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class StartUpService {
    private final StartupRepositories startupRepositories;
    public List<StartUp> startUpList(String title){
        List<StartUp> startUps = startupRepositories.findAll();
        if(title != null){
            return startupRepositories.findByTitle(title);
        }
        return startUps;
    }
    public void saveStartUp(StartUp startUp){
        log.info("Saving new {}", startUp);
        startUp.setCreationDate(LocalDate.now());
        startupRepositories.save(startUp);
    }
    public void deleteStartUp(Long id){
        System.out.println(id);
        startupRepositories.deleteById(id);
    }

    public StartUp getStartupById(Long id) {
        return startupRepositories.findById(id).orElse(null);
    }
}
