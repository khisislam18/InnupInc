package com.innup.startupinvest.controllers;

import com.innup.startupinvest.models.StartUp;
import com.innup.startupinvest.models.User;
import com.innup.startupinvest.repositories.StartupRepositories;
import com.innup.startupinvest.repositories.UserRepositories;
import com.innup.startupinvest.services.StartUpService;
import com.innup.startupinvest.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AdminController {
    private final UserService userService;
    private final UserRepositories userRepositories;
    private final StartupRepositories startupRepositories;
    private final StartUpService startupService;

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("users", userService.list());
        return "adminPanel/admin";
    }
    @PostMapping("/admin/ban/{id}")
    public String userBan(@PathVariable("id") Long id){
        userService.ban(id);
        return "redirect:/admin";
    }
    @GetMapping("/admin/user/edit-form/{id}")
    public String adminUserEditMenu(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("userStartups", startupRepositories.findByUser(userRepositories.findById(id).orElse(null)));
        return "adminPanel/user-edit";
    }
    @PostMapping("/admin/user/edit/{id}")
    public String adminUserEdit(@PathVariable("id") Long id, @RequestParam("name") String username, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("email") String email){
        userService.updateUser(id, username, phoneNumber, email);
        return "redirect:/admin";
    }
    @GetMapping("/admin/{userId}/startup/edit-form/{startupId}")
    public String adminStartupEditMenu(@PathVariable("startupId") Long startupId, @PathVariable("userId") Long userId, Model model){
        model.addAttribute("userId", userId);
        model.addAttribute("startup", startupService.getStartupById(startupId));
        return "adminPanel/startup-edit";
    }
    @PostMapping("/admin/{userId}/startup/edit/{startupId}")
    public String adminStartupEdit(@PathVariable("startupId") Long startupId,  @PathVariable("userId") Long userId,@RequestParam("title") String title, @RequestParam("price") Integer price, @RequestParam("description") String description){
        startupService.updateStartup(startupId, title, price, description);
        return "redirect:/admin/user/edit-form/" + userId;
    }
}
