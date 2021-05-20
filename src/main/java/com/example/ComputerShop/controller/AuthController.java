package com.example.ComputerShop.controller;

import com.example.ComputerShop.model.User;
import com.example.ComputerShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller // Объявляем класс как контроллер
@RequestMapping("/auth") // Все эндпоинты данного контроллера будут начинаться с /auth
public class AuthController { // Контроллер, отвечающий за регистрацию

    @Autowired
    private UserService userService;

    @GetMapping("/registration") // Указывает, что по гет запросу на эндпоинт /registration (А поскольку над классом есть @RequestMapping то /auth/registration) будет срабатывать этот метод
    public String regPage(Model model) { // Метод контроллера получает модель, которая будет внедрена в метод спрингом
        model.addAttribute("user", new User()); // Добавляем аттрибут user, и ложим напротив него объект пользователя, дабы мы могли из HTML к нему обратиться
        return "auth/registration"; // Возвращаем HTML документ, который лежит в папке templates/auth/registration.html
    }

    @PostMapping("/registration") // Аналогична аннотации @GetMapping, только реагирует на POST запрос
    public String reg(@ModelAttribute("user") @Valid User user) { // @ModelAttribute вытаскивает модель из HTML страницы, если она там есть, а она там есть, ибо в методе выше мы её туда добавили
        userService.save(user);
        return "redirect:/login"; // Делаем редирект на эндпоинт /login
    }

}
