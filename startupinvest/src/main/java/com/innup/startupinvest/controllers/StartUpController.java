package com.innup.startupinvest.controllers;

import com.innup.startupinvest.models.StartUp;
import com.innup.startupinvest.repositories.StartupRepositories;
import com.innup.startupinvest.repositories.UserRepositories;
import com.innup.startupinvest.services.StartUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class StartUpController {
    private final StartUpService startUpService;
    private StartupRepositories startupRepositories;

    @GetMapping("/startup/{id}")
    public String startUpInfo(@PathVariable Long id, Model model, Principal principal){
        model.addAttribute("principalCheck", startUpService.startupReferenceToPrincipalCheck(principal, id));
        model.addAttribute("startup", startUpService.getStartupById(id));
        model.addAttribute("medias", startUpService.getStartupById(id).getMedias());
        model.addAttribute("user", startUpService.getUserByPrincipal(principal));
        return "start-up-info";
    }
    @GetMapping("/startup/creation_menu")
    public String creationMenu(){
        return "startups";
    }

    @GetMapping("/")
    public String startUps(@RequestParam(name = "title", required = false) String title, Model model, Principal principal){
        model.addAttribute("startups", startUpService.startUpList(title));
        model.addAttribute("user", startUpService.getUserByPrincipal(principal));
        return "mainpage";
    }
    @PostMapping("/startup/create")
    public String createStartup(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3, StartUp startUp, Principal principal) throws IOException {
        startUpService.saveStartUp(startUp, file1, file2, file3, principal);
        return "redirect:/";
    }
    @PostMapping("/startup/delete/{id}")
    public String deleteStartUp(@PathVariable Long id){
        startUpService.deleteStartUp(id);
        return "redirect:/";
    }
    @GetMapping("/startup/edit-menu/{id}")
    public String startupEditMenu(@PathVariable("id") Long id, Model model){
        model.addAttribute("startup", startUpService.getStartupById(id));
        return "start-up-edit";
    }
    @PostMapping("/startup/edit/{id}")
    public String editStartUp(@PathVariable Long id, @RequestParam(name = "title") String title, @RequestParam(name = "price") Integer price, @RequestParam(name = "description") String description){
        startUpService.updateStartup(id, title, price, description);
        return "redirect:/startup/" + id;
    }
}
