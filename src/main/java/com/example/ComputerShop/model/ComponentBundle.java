package com.example.ComputerShop.model;

import javax.persistence.*;

@Entity
@Table(name = "cb")
public class ComponentBundle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Component component;
    @Basic
    private int amount;

    public ComponentBundle() {
    }

    public ComponentBundle(Component component, int amount) {
        this.component = component;
        this.amount = amount;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
