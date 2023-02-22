package com.example.puzzleshop.model.entity;

import com.example.puzzleshop.model.entity.enums.CategoryNameEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private CategoryNameEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(targetEntity = Puzzle.class, mappedBy = "category")
    private List<Puzzle> puzzles;

    public Category() {
    }

    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    public void setPuzzles(List<Puzzle> puzzles) {
        this.puzzles = puzzles;
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
