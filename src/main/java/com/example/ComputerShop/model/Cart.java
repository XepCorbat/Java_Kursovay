package com.example.ComputerShop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @OneToMany
    private List<ComponentBundle> components;

    public Cart() {
        this.components = new ArrayList<>();
    }

    public Cart(User user) {
        this.user = user;
        this.components = new ArrayList<>();
    }

    public void addComponent(ComponentBundle componentBundle) {
        components.add(componentBundle);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
