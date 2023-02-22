package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.Category;
import com.example.coffeeshop.model.entity.enums.CategoryName;
import com.example.coffeeshop.repository.CategoryRepository;
import com.example.coffeeshop.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() != 0){
            return;
        }

        for (CategoryName currentName: CategoryName.values()) {
                Category category = new Category();
                category.setName(currentName);

                switch (currentName){
                    case Cake -> category.setNeededTime(10);
                    case Drink -> category.setNeededTime(1);
                    case Coffee -> category.setNeededTime(2);
                    case Other -> category.setNeededTime(5);
                }

                this.categoryRepository.save(category);
        }

    }

    @Override
    public Category findByCategoryName(CategoryName categoryName) {

        return this.categoryRepository.findByName(categoryName).orElse(null);
    }
}
