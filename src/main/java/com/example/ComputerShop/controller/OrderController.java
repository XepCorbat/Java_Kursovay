package com.example.ComputerShop.controller;

import com.example.ComputerShop.model.UserDetail;
import com.example.ComputerShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController { // Контроллер, для формирования заказа

    @Autowired
    private OrderService orderService;

    @PostMapping
    public String order(@AuthenticationPrincipal UserDetail user, Model model) {
        model.addAttribute("order", orderService.makeAPurchase(user.getId()));
        return "order/order";
    }

}
