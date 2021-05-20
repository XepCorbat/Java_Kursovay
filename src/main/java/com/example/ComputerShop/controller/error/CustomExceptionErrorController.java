package com.example.ComputerShop.controller.error;

import com.example.ComputerShop.exceptions.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionErrorController { // Контроллер, который ловит выброшенные нами ошибки

    @ExceptionHandler(ComponentNotFoundException.class) // В параметре аннотации указываем класс ошибки, которую будем ловить
    public String componentNotFound(Model model) {
        model.addAttribute("error", "Component not found");
        return "error/error";
    }

    @ExceptionHandler(AccessForbiddenException.class)
    public String accessForbidden(Model model) {
        model.addAttribute("error", "Access forbidden");
        return "error/error";
    }

    @ExceptionHandler(MappingNotFoundException.class)
    public String mappingNotFound(Model model) {
        model.addAttribute("error", "Mapping not found");
        return "error/error";
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public String notEnoughMoney(Model model) {
        model.addAttribute("error", "Not enough money to buy components");
        return "error/error";
    }

    @ExceptionHandler(DefaultException.class)
    public String defaultError(Model model) {
        model.addAttribute("error", "Something wrong");
        return "error/error";
    }

    @ExceptionHandler(NotEnoughComponentsException.class)
    public String notEnoughComponents(Model model) {
        model.addAttribute("error", "No enough components on stock");
        return "error/error";
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String notValidData(Model model) {
        model.addAttribute("error", "Field's can't be null");
        return "error/error";
    }

}
