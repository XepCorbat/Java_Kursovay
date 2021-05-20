package com.example.ComputerShop.repo;

import com.example.ComputerShop.model.ComponentBundle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompBundleRepo extends JpaRepository<ComponentBundle, Long> {
}
