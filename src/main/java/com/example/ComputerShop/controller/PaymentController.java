package com.example.ComputerShop.controller;

import com.example.ComputerShop.model.PaymentCallback;
import com.example.ComputerShop.model.User;
import com.example.ComputerShop.model.UserDetail;
import com.example.ComputerShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public class PaymentController { // Контроллер, который отвечает за пополнение счёта пользователя

    @Autowired
    private UserService userService;

    @GetMapping
    public String payPage(Model model) {
        model.addAttribute("payment", new PaymentCallback());
        return "payment";
    }

    @PostMapping
    public String pay(@AuthenticationPrincipal UserDetail user, @ModelAttribute("payment") PaymentCallback callback) {
        userService.increaseMoney(user.getId(), callback);
        return "redirect:/profile";
    }

}
