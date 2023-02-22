package com.example.puzzleshop.model.service;

import com.example.puzzleshop.model.entity.Category;
import com.example.puzzleshop.model.entity.enums.CategoryNameEnum;
import com.example.puzzleshop.model.entity.enums.PiecesEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PuzzleServiceModel {

    private String id;

    private String name;

    private String description;

    private LocalDate date;

    private BigDecimal price;

    private PiecesEnum pieces;

    private CategoryNameEnum category;

    public PuzzleServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PiecesEnum getPieces() {
        return pieces;
    }

    public void setPieces(PiecesEnum pieces) {
        this.pieces = pieces;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryNameEnum category) {
        this.category = category;
    }
}
