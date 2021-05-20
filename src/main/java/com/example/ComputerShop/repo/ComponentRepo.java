package com.example.ComputerShop.repo;

import com.example.ComputerShop.model.Component;
import com.example.ComputerShop.model.ComponentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComponentRepo extends JpaRepository<Component, Long> {
    List<Component> findAllByType(ComponentType type);
}
