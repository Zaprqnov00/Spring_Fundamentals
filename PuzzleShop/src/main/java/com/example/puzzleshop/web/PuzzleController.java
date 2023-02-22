package com.example.puzzleshop.web;

import com.example.puzzleshop.model.binding.PuzzleAddBindingModel;
import com.example.puzzleshop.model.service.PuzzleServiceModel;
import com.example.puzzleshop.service.PuzzleService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/puzzles")
public class PuzzleController {

    private final ModelMapper modelMapper;
    private final PuzzleService puzzleService;

    public PuzzleController(ModelMapper modelMapper, PuzzleService puzzleService) {
        this.modelMapper = modelMapper;
        this.puzzleService = puzzleService;
    }

    @ModelAttribute
    public PuzzleAddBindingModel puzzleAddBindingModel(){
        return new PuzzleAddBindingModel();
    }

    @GetMapping("/add")
    public String add(){

        return "puzzle-add";
    }

    @PostMapping("/add")
    public String confirmAdd(@Valid PuzzleAddBindingModel puzzleAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("puzzleAddBindingModel", puzzleAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.puzzleAddBindingModel",
                    bindingResult);

            return "redirect:add";
        }

        PuzzleServiceModel puzzleServiceModel = modelMapper.map(puzzleAddBindingModel, PuzzleServiceModel.class);

        this.puzzleService.addPuzzle(puzzleServiceModel);

        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public String buyPuzzle(@PathVariable String id){

        this.puzzleService.buyById(id);

        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String buyAllPuzzles(){

        this.puzzleService.buyAll();
        return "redirect:/";
    }
}
