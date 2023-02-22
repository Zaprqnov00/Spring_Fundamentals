package com.example.shoppinglist.service;

import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    void addCategories();

    Category findByName(CategoryNameEnum name);
}
