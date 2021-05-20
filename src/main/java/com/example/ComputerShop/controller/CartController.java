package com.example.ComputerShop.controller;

import com.example.ComputerShop.model.ComponentBundle;
import com.example.ComputerShop.model.UserDetail;
import com.example.ComputerShop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController { // Контроллер отвечающий за взаимодействие с корзиной

    @Autowired
    private CartService cartService;

    @GetMapping
    public String cart(Model model, @AuthenticationPrincipal UserDetail userDetail) { // Если пользователь залогинен, то @AuthenticationPrincipal вернёт данные этого пользователя, если нет то пользователь будет NULL
        List<ComponentBundle> components = userDetail == null ? new ArrayList<>() : cartService.findCartByUser(userDetail.getId()).getComponents();
        model.addAttribute("components", components.stream().map(ComponentBundle::getComponent).collect(Collectors.toList())); // Клепаем лист компонентов из корзины пользователя, и пихаем его в модель
        return "cart/cart";
    }

    @PostMapping("/{id}")
    public String addToCart(@AuthenticationPrincipal UserDetail userDetail, @PathVariable("id") Long id, @RequestParam(value = "amount", defaultValue = "1") int amount) { // @PathVariable("id") выхватывает кусок URL, и пихает его в переменную, идущую за аннотацией, @RequestParam выхватывает параметр из URL
        cartService.addToCart(userDetail.getId(), id, amount);
        return "redirect:/cart";
    }

}
