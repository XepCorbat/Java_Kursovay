package com.example.ComputerShop.controller.error;

import com.example.ComputerShop.exceptions.AccessForbiddenException;
import com.example.ComputerShop.exceptions.DefaultException;
import com.example.ComputerShop.exceptions.MappingNotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RequestErrorStatusInterceptController implements ErrorController { // Контроллер, который будет ловить все запросы, статус которых != 200
                                                                                // Делает он это благодаря настройке в application.yml

    @RequestMapping("/error")
    public String intercept(HttpServletRequest request) { // Единственный метод контроллера, который принимает запрос
        int status = Integer.parseInt(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString()); // Вытаскиваем код статуса
        try {
            switch (HttpStatus.valueOf(status)) { // Выбрасываем ошибки в зависимости от статуса
                case FORBIDDEN: // Если вдруг доступ запрещен
                    throw new AccessForbiddenException();
                case NOT_FOUND: // Если вдруг эндпоинт, по которому пытается перейти человек, не найден
                    throw new MappingNotFoundException();
                default: // Если не то и не другое, то выбрасываем "стандартную" ошибку
                    throw new DefaultException();
            }
        } catch (Exception e) {
            throw new DefaultException(); // Если вдруг мы не смогли получить статус, или статус каким то образом отличается от стандартов, то выбрасываем стандартную ошибку
        }
    }

    @Override
    public String getErrorPath() { // Метод, который прилетает вместе с интерфейсом ErrorController, но он помечен как устаревший, поэтому нет смысла на него внимания обращать
        return "/error";
    }
}