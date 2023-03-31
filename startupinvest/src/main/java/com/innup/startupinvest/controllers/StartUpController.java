package com.innup.startupinvest.controllers;

import com.innup.startupinvest.models.StartUp;
import com.innup.startupinvest.services.StartUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StartUpController {
    private final StartUpService startUpService;

    @GetMapping("/startup/{id}")
    public String startUpInfo(@PathVariable Long id, Model model){
        model.addAttribute("start-up-info", startUpService.getStartupById(id));
        return "start-up-info";
    }

    @GetMapping("/startup")
    public String startUps(@RequestParam (name= "name", required = false, defaultValue = "World") String name, Model model){
        model.addAttribute("name", name);
        model.addAttribute("startups", startUpService.list());
        return "startups";
    }
    @PostMapping("/startup/create")
    public String createStartup(StartUp startUp){
        startUpService.saveStartUp(startUp);
        return "redirect:/";
    }
    @PostMapping("/startup/delete/{id}")
    public String deleteStartUp(@PathVariable Long id){
        startUpService.deleteStartUp(id);
        return "redirect:/";
    }
}
