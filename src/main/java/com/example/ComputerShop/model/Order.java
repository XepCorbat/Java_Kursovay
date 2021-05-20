package com.example.ComputerShop.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @ManyToOne
    private User user;
    @OneToMany
    private List<ComponentBundle> components;

    public Order() {
        this.components = new ArrayList<>();
    }

    public Order(User user, List<ComponentBundle> components) {
        this.orderDate = LocalDate.now();
        this.user = user;
        this.components = components;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ComponentBundle> getComponents() {
        return components;
    }

    public void setComponents(List<ComponentBundle> components) {
        this.components = components;
    }
}
