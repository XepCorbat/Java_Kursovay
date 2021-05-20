package com.example.ComputerShop.service;

import com.example.ComputerShop.model.Cart;
import com.example.ComputerShop.model.Component;
import com.example.ComputerShop.model.ComponentBundle;
import com.example.ComputerShop.model.User;
import com.example.ComputerShop.repo.CartRepo;
import com.example.ComputerShop.repo.CompBundleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private ComponentService componentService;
    @Autowired
    private CompBundleRepo compBundleRepo;

    public void addToCart(Long userId, Long componentId, int count) { // Добавляем предмет в корзину
        Cart cartByUser = findCartByUser(userId);
        Component componentById = componentService.findComponentById(componentId);

        cartByUser.addComponent(compBundleRepo.save(new ComponentBundle(componentById, count))); // Добавляем в корзину уже сохранённую в БД сущность ComponentBundle

        cartRepo.save(cartByUser);
    }

    public void deleteComponentFromCart(Long userId, Long componentId) { // Удаляем компонент из корзины
        Cart cartByUser = findCartByUser(userId);
        cartByUser.getComponents().removeIf(componentBundle -> componentBundle.getComponent().getId().equals(componentId));

        cartRepo.save(cartByUser);
    }

    public void deleteCart(Long userId) { // Удаляем корзину
        cartRepo.findByUser(userService.findUserById(userId)).ifPresent(cartRepo::delete);
    }

    public Cart findCartByUser(Long userId) {
        User user = userService.findUserById(userId);
        return cartRepo.findByUser(user).orElseGet(() -> cartRepo.save(new Cart(user)));
    }

}
