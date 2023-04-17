package com.innup.startupinvest.controllers;

import com.innup.startupinvest.models.StartUp;
import com.innup.startupinvest.repositories.StartupRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    @Autowired
    StartupRepositories startupRepositories;

    @GetMapping("/search")
    public String searchPage(){
        return "/search";
    }

    @PostMapping("search")
    public String searchPage(@RequestParam("searchString") String searchString, Model model){
        if (searchString != null){
            try {
                Iterable<StartUp> searchResult = startupRepositories.findByTitleContainingIgnoreCase(searchString);
                model.addAttribute("searchResult", searchResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "search";
    }
}
