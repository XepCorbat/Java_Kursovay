package com.example.ComputerShop.security;

import com.example.ComputerShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity //Включаем Spring Security
@Configuration
public class WebConfigure extends WebSecurityConfigurerAdapter { // Наследуемся от файла конфигурации SpringSecurity

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception { // Переопределяем метод настройки SpringSecurity
        http
                .antMatcher("/**").authorizeRequests() // Указываем, что SpringSecurity будет работать с корня сайта (/)
                .antMatchers("/", "/login", "/auth/registration", "/css/**").permitAll() // Перечисляем эндпоинты и устанавливаем им доступ для всех
                .antMatchers("/shop/new").hasAuthority("ADMIN") // К данному эндпоинту доступ будет только у админов
                .anyRequest().authenticated().and() // Для остальных эндпоинтов нужна будет авторизация
                .formLogin().and() // Подключаем стандартную форму логина
                .logout().permitAll().and() // Даём всем доступ к логауту
                .csrf().disable(); // Отключаем CSRF(нам оно не нужно, много лишней возни с ним)
    }

    @Bean
    public DaoAuthenticationProvider authProvider() { // Создаём бин с провайдером, в котором указываем userService и PasswordEncoder, дабы спринг знал откуда брать пользователей для авторизации
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) { // Подключаем провайдер к SpringSecurity
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public PasswordEncoder encoder() { // Создаём бин с кодировщиком паролей
        return new BCryptPasswordEncoder(10);
    }

}
