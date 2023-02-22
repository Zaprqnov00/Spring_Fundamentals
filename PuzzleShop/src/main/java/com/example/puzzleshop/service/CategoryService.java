package com.example.puzzleshop.service;

import com.example.puzzleshop.model.entity.Category;
import com.example.puzzleshop.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    Category findByName(CategoryNameEnum category);
}
