package com.innup.startupinvest.controllers;


import com.innup.startupinvest.models.User;
import com.innup.startupinvest.repositories.StartupRepositories;
import com.innup.startupinvest.repositories.UserRepositories;
import com.innup.startupinvest.services.CustomUserDetailsService;
import com.innup.startupinvest.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final StartupRepositories startupRepositories;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepositories userRepositories;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/login_failed")
    public String login(Model model){
        model.addAttribute("loginFailed", "Неверный логин или пароль");
        return "login";
    }
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String createUser(User user, Model model){
        if(!userService.createUser(user)){
            model.addAttribute(
                    "errorMessage",
                    "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        userService.createUser(user);
        return "redirect:/";
    }
    @GetMapping("/user/my_profile")
    public String myProfile(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("startups", startupRepositories.findByUser(userService.getUserByPrincipal(principal)));
        return "my-profile";
    }
    @GetMapping("/user/edit_menu")
    public String profileEditMenu(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("userStartups", startupRepositories.findByUser(userService.getUserByPrincipal(principal)));
        return "my-profile-edit";
    }
    @PostMapping("/user/edit_menu/edit")
    public String profileEditAction(Principal principal, @RequestParam("username") String username, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("email") String email){
        userService.updateUser(userService.getUserByPrincipal(principal).getId(), username, phoneNumber, email);
        return "redirect:/my-profile";
    }
    @GetMapping("/user/{id}")
    public String userInfo(@PathVariable("id") Long userId, Model model){
        User user = userRepositories.findById(userId).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("startups", startupRepositories.findByUser(user));
        return "user-info";
    }
    @GetMapping("/hello")
    public String securityUrl(){
        return "hello";
    }
}
