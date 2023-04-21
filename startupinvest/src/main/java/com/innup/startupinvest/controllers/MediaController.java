package com.innup.startupinvest.controllers;

import com.innup.startupinvest.models.Media;
import com.innup.startupinvest.repositories.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class MediaController {
    private final MediaRepository mediaRepository;

    @GetMapping("/medias/{id}")
    private ResponseEntity<?> getMediaById(@PathVariable Long id){
        Media media = mediaRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", media.getMediaPath())
                .contentType(MediaType.valueOf(media.getContentType()))
                .contentLength(media.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(media.getBytes())));
    }
}
