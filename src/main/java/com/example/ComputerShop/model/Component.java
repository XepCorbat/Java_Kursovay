package com.example.ComputerShop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "components")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @NotNull
    private String name;
    @Basic
    @NotNull
    private String description;
    @Enumerated(EnumType.STRING)
    @NotNull
    private ComponentType type;
    @Basic
    @NotNull
    private Integer cost;
    @Basic
    @NotNull
    private String image;
    @Column(name = "amount")
    @NotNull
    private Integer amountOnStock;

    public Component() {
    }

    public Integer getAmountOnStock() {
        return amountOnStock;
    }

    public void setAmountOnStock(Integer amountOnStock) {
        this.amountOnStock = amountOnStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
