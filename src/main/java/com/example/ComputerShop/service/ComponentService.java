package com.example.ComputerShop.service;

import com.example.ComputerShop.model.Component;
import com.example.ComputerShop.model.ComponentType;
import com.example.ComputerShop.repo.ComponentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.ComputerShop.model.ComponentType.*;

@Service
public class ComponentService {

    @Autowired
    private ComponentRepo componentRepo;

    public Component save(Component component) {
        return componentRepo.save(component);
    }

    public Component findComponentById(Long id) {
        return componentRepo.findById(id).orElse(null);
    }

    public List<Component> getComponentsByParam(String param) { // В зависимости от параметра возвращаем тот или иной список компонентов
        switch (param) {
            case "motherboard":
                return findAllByType(MOTHERBOARD);
            case "hdd":
                return findAllByType(HDD);
            case "ssd":
                return findAllByType(SSD);
            case "memory":
                return findAllByType(MEMORY);
            case "cpu":
                return findAllByType(CPU);
            case "video":
                return findAllByType(VIDEO_CARD);
            case "ethernet":
                return findAllByType(ETHERNET_CARD);
            default:
                return componentRepo.findAll();
        }
    }

    private List<Component> findAllByType(ComponentType type) {
        return componentRepo.findAllByType(type);
    }
}
