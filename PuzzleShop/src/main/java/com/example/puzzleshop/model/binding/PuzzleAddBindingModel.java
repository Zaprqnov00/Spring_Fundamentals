package com.example.puzzleshop.model.binding;

import com.example.puzzleshop.model.entity.Category;
import com.example.puzzleshop.model.entity.enums.CategoryNameEnum;
import com.example.puzzleshop.model.entity.enums.PiecesEnum;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PuzzleAddBindingModel {

    @Size(min = 3, max = 10, message = "Name length must be between 3 and 10 characters!")
    @NotNull
    private String name;

    @Size(min = 5,message = "Description length must be more than 5 characters!")
    private String description;

    @PastOrPresent(message = "Date cannot be in the future!")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Positive(message = "Price must be positive!")
    @NotNull
    private BigDecimal price;

    @NotNull(message = "Select pieces!")
    private PiecesEnum pieces;

    @NotNull
    private CategoryNameEnum category;

    public PuzzleAddBindingModel() {
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
