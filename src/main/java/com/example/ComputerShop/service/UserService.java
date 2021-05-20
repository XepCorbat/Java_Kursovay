package com.example.ComputerShop.service;

import com.example.ComputerShop.model.PaymentCallback;
import com.example.ComputerShop.model.User;
import com.example.ComputerShop.model.UserDetail;
import com.example.ComputerShop.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) { // Сохраняем пользователя(при регистрации)
        user.setRoles(List.of("USER"));
        user.setMoney(0);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);
    }

    public void increaseMoney(Long userId, PaymentCallback callback) { // Увеличиваем кол-во денег пользователя
        User userById = findUserById(userId);
        userById.setMoney(userById.getMoney() + callback.getSum());

        userRepo.save(userById);
    }

    public User findUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // Метод, который пришёл вместе с интерфейсом UserDetailsService, нужен для SpringSecurity, что бы тот мог пользователей в БД искать
        return UserDetail.from(userRepo.findByUsername(username));
    }

}
