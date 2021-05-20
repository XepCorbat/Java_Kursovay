package com.example.ComputerShop.controller;

import com.example.ComputerShop.model.Component;
import com.example.ComputerShop.model.User;
import com.example.ComputerShop.model.UserDetail;
import com.example.ComputerShop.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/shop")
public class ShopController { // Контроллер, который отвечает за операции с компонентами(товарами)

    @Autowired
    private ComponentService componentService;

    @GetMapping("/new")
    public String addPage(Model model) { // Возвращает страницу добавления компанента
        model.addAttribute("component", new Component());

        return "components/new";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute("component") @Valid Component component) { // Добавляет комнонент в БД
        Component entity = componentService.save(component);

        return "redirect:/shop/" + entity.getId().intValue();
    }

    @GetMapping
    public String shopPage(Model model, @RequestParam(value = "type", defaultValue = "all") String param, @AuthenticationPrincipal UserDetail userDetail) { // Возвращает страницу со списком компанентов
        UserDetail user = Optional.ofNullable(userDetail).orElse(new UserDetail("USER"));

        model.addAttribute("user", user);
        model.addAttribute("components", componentService.getComponentsByParam(param));

        return "components/list";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Long id, @AuthenticationPrincipal UserDetail userDetail) {  // Возвращает страницу с информацией об отдельном компоненте
        model.addAttribute("component", componentService.findComponentById(id));
        model.addAttribute("amount", 1);
        model.addAttribute("user", userDetail);

        return "components/one";
    }

}
