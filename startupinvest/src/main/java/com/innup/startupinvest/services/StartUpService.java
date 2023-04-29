package com.innup.startupinvest.services;

import com.innup.startupinvest.models.Media;
import com.innup.startupinvest.models.StartUp;
import com.innup.startupinvest.models.User;
import com.innup.startupinvest.repositories.StartupRepositories;
import com.innup.startupinvest.repositories.UserRepositories;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class StartUpService {
    private final StartupRepositories startupRepositories;
    private final UserRepositories userRepositories;
    public List<StartUp> startUpList(String title){
        List<StartUp> startUps = startupRepositories.findAll();
        if(title != null){
            return startupRepositories.findByTitle(title);
        }
        return startUps;
    }
    public void saveStartUp(StartUp startUp, MultipartFile file1, MultipartFile file2, MultipartFile file3, Principal principal) throws IOException {
        startUp.setUser(getUserByPrincipal(principal));
        Media media1;
        Media media2;
        Media media3;
        if(file1.getSize() != 0){
            media1 = toMediaEntity(file1);
            media1.setPreview(true);
            startUp.addMediaToProduct(media1);
        }
        if(file2.getSize() != 0){
            media2 = toMediaEntity(file2);
            startUp.addMediaToProduct(media2);
        }
        if(file3.getSize() != 0){
            media3 = toMediaEntity(file3);
            startUp.addMediaToProduct(media3);
        }
        log.info("Saving new Startup. Title: {}; Email:{}", startUp.getTitle(), startUp.getUser().getEmail());
        StartUp startUpFromDB = startupRepositories.save(startUp);
        startUpFromDB.setPreviewMediaId(startUpFromDB.getMedias().get(0).getId());
        startupRepositories.save(startUp);
    }

    public User getUserByPrincipal(Principal principal) {
        if(principal == null){
            return null;
        }
        return userRepositories.findByEmail(principal.getName());
    }


    private Media toMediaEntity(MultipartFile file1) throws IOException {
       Media media = new Media();
       media.setName(file1.getName());
       media.setMediaPath(file1.getOriginalFilename());
       media.setContentType(file1.getContentType());
       media.setSize(file1.getSize());
       media.setBytes(file1.getBytes());
       return media;
    }

    public void deleteStartUp(Long id){
        log.info("Saving new Startup. Startup Id: {};", id);
        startupRepositories.deleteById(id);
    }

    public StartUp getStartupById(Long id) {
        return startupRepositories.findById(id).orElse(null);
    }
}
