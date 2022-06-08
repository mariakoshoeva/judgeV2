package com.example.judgev2.web;

import com.example.judgev2.models.binding.UserRegisterBindingModel;
import com.example.judgev2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegisterBindingModel")
    public void initUserRegisterForm(Model model){
        model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
    }


    @GetMapping("/login")
    public String login(){
     return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(){

        return "/";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            FieldError notEqualPassword = new FieldError("userRegisterBindingModel","confirmPassword","Password not match");
            bindingResult.addError(notEqualPassword);
        }

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",bindingResult);
            return "redirect:register";
        }
        userService.register(userRegisterBindingModel);
        return "redirect:login";
    }
}
