package com.example.ComputerShop.controller;

import com.example.ComputerShop.model.UserDetail;
import com.example.ComputerShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController { // Контроллер, который отвечает за профиль

    @Autowired
    private UserService userService;

    @GetMapping
    public String profile(Model model, @AuthenticationPrincipal UserDetail userDetail) {
        model.addAttribute("user", userService.findUserById(userDetail.getId()));
        return "profile/profile";
    }

}
