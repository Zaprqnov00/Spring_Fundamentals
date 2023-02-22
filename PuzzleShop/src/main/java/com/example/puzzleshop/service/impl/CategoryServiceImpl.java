package com.example.puzzleshop.service.impl;

import com.example.puzzleshop.model.entity.Category;
import com.example.puzzleshop.model.entity.enums.CategoryNameEnum;
import com.example.puzzleshop.repository.CategoryRepository;
import com.example.puzzleshop.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() == 0) {
            for (CategoryNameEnum currentCategory :CategoryNameEnum.values()) {
                Category category = new Category();
                category.setName(currentCategory);

                switch (currentCategory){
                    case CAR -> category.setDescription("Puzzles with car images.");
                    case ANIMAL -> category.setDescription("Puzzles with animal images.");
                    case NATURE -> category.setDescription("Puzzles with nature, mountain, river, sea, fly images.");
                    case FANTASY -> category.setDescription("Puzzles with fantasy girls, dragon, movies and popular movie actor images.");
                }
                this.categoryRepository.save(category);
            }
        }

    }

    @Override
    public Category findByName(CategoryNameEnum category) {

        return this.categoryRepository.findByName(category);
    }
}
