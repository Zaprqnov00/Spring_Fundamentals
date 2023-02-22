package com.example.shoppinglist.web;

import com.example.shoppinglist.model.entity.enums.CategoryNameEnum;
import com.example.shoppinglist.security.CurrentUser;
import com.example.shoppinglist.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final ProductService productService;

    public HomeController(CurrentUser currentUser, ProductService productService) {
        this.currentUser = currentUser;
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model){

        if (currentUser.getId() == null) {
            return "index";
        }

        model.addAttribute("totalSum", this.productService.getTotalSum());
        model.addAttribute("drinks", this.productService.findAllProductsByCategoryName(CategoryNameEnum.Drink));
        model.addAttribute("foods", this.productService.findAllProductsByCategoryName(CategoryNameEnum.Food));
        model.addAttribute("household",this.productService.findAllProductsByCategoryName(CategoryNameEnum.Household));
        model.addAttribute("other",this.productService.findAllProductsByCategoryName(CategoryNameEnum.Other));

        return "home";
    }
}
