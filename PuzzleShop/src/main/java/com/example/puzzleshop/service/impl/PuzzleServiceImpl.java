package com.example.puzzleshop.service.impl;

import com.example.puzzleshop.model.entity.Puzzle;
import com.example.puzzleshop.model.entity.enums.CategoryNameEnum;
import com.example.puzzleshop.model.service.PuzzleServiceModel;
import com.example.puzzleshop.model.view.PuzzleViewModel;
import com.example.puzzleshop.repository.PuzzleRepository;
import com.example.puzzleshop.security.CurrentUser;
import com.example.puzzleshop.service.CategoryService;
import com.example.puzzleshop.service.PuzzleService;
import com.example.puzzleshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PuzzleServiceImpl implements PuzzleService {

    private final ModelMapper modelMapper;
    private final PuzzleRepository puzzleRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final CurrentUser currentUser;

    public PuzzleServiceImpl(ModelMapper modelMapper, PuzzleRepository puzzleRepository, CategoryService categoryService, UserService userService, CurrentUser currentUser) {
        this.modelMapper = modelMapper;
        this.puzzleRepository = puzzleRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @Override
    public void addPuzzle(PuzzleServiceModel puzzleServiceModel) {
        Puzzle puzzle = modelMapper.map(puzzleServiceModel, Puzzle.class);
        puzzle.setCategory(this.categoryService.findByName(puzzleServiceModel.getCategory()));
        puzzle.setUser(this.userService.findById(currentUser.getId()));

        this.puzzleRepository.save(puzzle);
    }

    @Override
    public Integer getTotalSum() {
        return this.puzzleRepository.findAllByTotalSum();
    }

    @Override
    public List<PuzzleViewModel> findAllProductsByCategoryName(CategoryNameEnum categoryNameEnum) {

        return this.puzzleRepository
                .findAllByCategory_Name(categoryNameEnum)
                .stream()
                .map(puzzle -> modelMapper.map(puzzle, PuzzleViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void buyById(String id) {

        this.puzzleRepository.deleteById(id);
    }

    @Override
    public void buyAll() {
        this.puzzleRepository.deleteAll();
    }
}
