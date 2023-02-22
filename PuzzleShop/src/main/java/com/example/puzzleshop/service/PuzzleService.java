package com.example.puzzleshop.service;

import com.example.puzzleshop.model.entity.enums.CategoryNameEnum;
import com.example.puzzleshop.model.service.PuzzleServiceModel;
import com.example.puzzleshop.model.view.PuzzleViewModel;

import java.util.List;

public interface PuzzleService {
    void addPuzzle(PuzzleServiceModel puzzleServiceModel);

    Integer getTotalSum();

    List<PuzzleViewModel> findAllProductsByCategoryName(CategoryNameEnum categoryNameEnum);

    void buyById(String id);

    void buyAll();
}
