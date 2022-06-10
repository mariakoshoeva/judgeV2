package com.example.judgev2.web;

import com.example.judgev2.models.binding.UserLoginBindingModel;
import com.example.judgev2.models.binding.UserRegisterBindingModel;
import com.example.judgev2.models.entity.UserEntity;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegisterBindingModel")
    public void initUserRegisterForm(Model model) {
        model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
    }


    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("notFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               HttpSession session) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }
        boolean loginSuccess = userService.login(userLoginBindingModel);

        if (!loginSuccess) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notFound", true);
            return "redirect:login";
        }
//        UserEntity user = userService.find(userLoginBindingModel);
//        if(user == null){
//            redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
//            redirectAttributes.addFlashAttribute("notFound",true);
//            return "redirect:login";
//        }
//        session.setAttribute("user",user);
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            FieldError notEqualPassword = new FieldError("userRegisterBindingModel", "confirmPassword", "Password not match");
            bindingResult.addError(notEqualPassword);
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }
        userService.register(userRegisterBindingModel);
        return "redirect:login";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }
}
