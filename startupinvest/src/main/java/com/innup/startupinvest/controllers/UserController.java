package com.innup.startupinvest.controllers;


import com.innup.startupinvest.models.User;
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

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
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
        return "redirect:/mainpage";
    }
    @GetMapping("/user/{id}")
    public String userInfo(@PathVariable("user") User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("startups", user.getStartUpList());
        return "user-info";
    }
    @GetMapping("/hello")
    public String securityUrl(){
        return "hello";
    }
}
