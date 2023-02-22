package com.example.coffeeshop.model.entity;

import com.example.coffeeshop.model.entity.enums.CategoryName;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private CategoryName name;

    @Column(name = "needed_time", nullable = false)
    private Integer neededTime;

    @OneToMany(targetEntity = Order.class, mappedBy = "category")
    private List<Order> orders;

    public Category() {
    }

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    public Integer getNeededTime() {
        return neededTime;
    }

    public void setNeededTime(Integer neededTime) {
        this.neededTime = neededTime;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
