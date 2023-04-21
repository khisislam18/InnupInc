package com.innup.startupinvest.controllers;

import com.innup.startupinvest.models.StartUp;
import com.innup.startupinvest.services.StartUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class StartUpController {
    private final StartUpService startUpService;

    @GetMapping("/startup/{id}")
    public String startUpInfo(@PathVariable Long id, Model model){
        model.addAttribute("startup", startUpService.getStartupById(id));
        return "start-up-info";
    }
    @GetMapping("/startup/creation_menu")
    public String creationMenu(){
        return "startups";
    }

    @GetMapping("/")
    public String startUps(@RequestParam(name = "title", required = false) String title, Model model){
        model.addAttribute("startups", startUpService.startUpList(title));
        return "mainpage";
    }
    @PostMapping("/img")
    public String usageImage(StartUp startUp){
        return "redirect:/";
    }
    @PostMapping("/startup/create")
    public String createStartup(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3, StartUp startUp) throws IOException {
        startUpService.saveStartUp(startUp, file1, file2, file3);
        return "redirect:/";
    }
    @PostMapping("/startup/delete/{id}")
    public String deleteStartUp(@PathVariable Long id){
        startUpService.deleteStartUp(id);
        return "redirect:/";
    }
}
