package com.example.ComputerShop.service;

import com.example.ComputerShop.exceptions.NotEnoughComponentsException;
import com.example.ComputerShop.exceptions.NotEnoughMoneyException;
import com.example.ComputerShop.model.*;
import com.example.ComputerShop.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CartService cartService;

    public Order makeAPurchase(Long userId) { // Метод формирования заказа
        Cart cartByUser = cartService.findCartByUser(userId);
        List<ComponentBundle> components = cartByUser.getComponents();
        User user = cartByUser.getUser();

        int componentSumCost = productSumCost(components); // Считаем сумму всех компонентов в корзине
        int userMoney = user.getMoney();

        if (userMoney - componentSumCost < 0) { // Если у пользователя не хватает денег на покупку то выбрасываем ошибку
            throw new NotEnoughMoneyException();
        }

        withdrawComponentsFromStock(components); // Списываем компоненты со склада
        withdrawMoneyWithUser(user, componentSumCost); // Списываем деньги со счёта пользователя

        cartService.deleteCart(userId); // Удаляем корзину пользователя

        return orderRepo.save(new Order(user, new ArrayList<>(components))); // Создаём сущность заказа и сохраняем её в БД
    }

    private void withdrawComponentsFromStock(List<ComponentBundle> components) {
        for (ComponentBundle componentBundle : components) {
            Component component = componentBundle.getComponent();

            if (component.getAmountOnStock() < componentBundle.getAmount()) { // Если количество компонента на складе меньше чем этого же компонента в корзине то выбрасываем ошибку
                throw new NotEnoughComponentsException();
            }

            component.setAmountOnStock(component.getAmountOnStock() - componentBundle.getAmount()); // Уменьшаем количество компонента на складе
        }
    }

    private void withdrawMoneyWithUser(User user, int money) {
        user.setMoney(user.getMoney() - money);
    }

    private int productSumCost(List<ComponentBundle> components) {
        return components.stream().mapToInt(comp -> comp.getAmount() * comp.getComponent().getCost()).sum();
    }

}
