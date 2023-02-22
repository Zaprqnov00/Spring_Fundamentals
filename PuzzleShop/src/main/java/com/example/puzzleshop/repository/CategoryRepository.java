package com.example.puzzleshop.repository;

import com.example.puzzleshop.model.entity.Category;
import com.example.puzzleshop.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Category findByName(CategoryNameEnum name);
}
