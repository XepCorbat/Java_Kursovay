package com.example.ComputerShop.repo;

import com.example.ComputerShop.model.Cart;
import com.example.ComputerShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(User user);

}
