package com.example.puzzleshop.web;

import com.example.puzzleshop.model.entity.enums.CategoryNameEnum;
import com.example.puzzleshop.security.CurrentUser;
import com.example.puzzleshop.service.PuzzleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final PuzzleService puzzleService;

    public HomeController(CurrentUser currentUser, PuzzleService puzzleService) {
        this.currentUser = currentUser;
        this.puzzleService = puzzleService;
    }

    @GetMapping("/")
    public String index(Model model){

        if (this.currentUser.getId() == null) {
            return "index";
        }

        model.addAttribute("totalSum", this.puzzleService.getTotalSum());
        model.addAttribute("animal", this.puzzleService.findAllProductsByCategoryName(CategoryNameEnum.ANIMAL));
        model.addAttribute("nature", this.puzzleService.findAllProductsByCategoryName(CategoryNameEnum.NATURE));
        model.addAttribute("car",this.puzzleService.findAllProductsByCategoryName(CategoryNameEnum.CAR));
        model.addAttribute("fantasy",this.puzzleService.findAllProductsByCategoryName(CategoryNameEnum.FANTASY));

        return "home";
    }

    @GetMapping("/about")
    private String about(){

        return "about";
    }
}
