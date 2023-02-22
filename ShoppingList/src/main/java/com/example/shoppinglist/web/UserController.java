package com.example.shoppinglist.web;

import com.example.shoppinglist.model.binding.UserLoginBindingModel;
import com.example.shoppinglist.model.binding.UserRegisterBindingModel;
import com.example.shoppinglist.model.service.UserServiceModel;
import com.example.shoppinglist.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }
    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
    }

    @GetMapping("/register")
    public String register(){

        return "register";
    }

    @PostMapping("/register")
    public String confirmRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                    bindingResult);

            return "redirect:register";
        }

        UserServiceModel userServiceModel = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);

        this.userService.register(userServiceModel);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model){

        if (!model.containsAttribute("isFound")){
            model.addAttribute("isFound", true);
        }

        return "login";
    }

    @PostMapping("/login")
    public String confirmLogin(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                    bindingResult);

            return "redirect:login";
        }

        UserServiceModel userServiceModel =
                this.userService.findByUsernameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());

        if (userServiceModel == null){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("isFound", false);

            return "redirect:login";
        }

        this.userService.login(userServiceModel.getId(), userLoginBindingModel.getUsername());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){

        httpSession.invalidate();

        return "redirect:/";
    }
}
