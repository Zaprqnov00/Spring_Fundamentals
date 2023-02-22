package com.example.shoppinglist.service.impl;

import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.enums.CategoryNameEnum;
import com.example.shoppinglist.repository.CategoryRepository;
import com.example.shoppinglist.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategories() {

        if (this.categoryRepository.count() == 0){

            for (CategoryNameEnum currentCategoryName :CategoryNameEnum.values()) {
                Category category = new Category();
                category.setName(currentCategoryName);

                switch (currentCategoryName){
                    case Food -> category.setDescription("category -> Food");
                    case Drink -> category.setDescription("category -> Drink");
                    case Household -> category.setDescription("category -> Household");
                    case Other -> category.setDescription("category -> Other");
                }

                this.categoryRepository.save(category);
            }
        }
    }

    @Override
    public Category findByName(CategoryNameEnum name) {

        return this.categoryRepository.findByName(name).orElse(null);
    }
}
