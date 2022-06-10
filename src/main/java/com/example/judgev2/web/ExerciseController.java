package com.example.judgev2.web;

import com.example.judgev2.models.binding.ExerciseBindingModel;
import com.example.judgev2.security.CurrentUser;
import com.example.judgev2.service.ExerciseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final CurrentUser currentUser;

    public ExerciseController(ExerciseService exerciseService, CurrentUser currentUser) {
        this.exerciseService = exerciseService;
        this.currentUser = currentUser;
    }

    @GetMapping("/exercises/add")
    public String exercise(Model model){
        if(!currentUser.isAdmin() || currentUser.isAnonymous()){
            return "redirect:/";
        }
        if(!model.containsAttribute("exerciseBindingModel")){
            model.addAttribute("exerciseBindingModel",new ExerciseBindingModel());
            model.addAttribute("alreadyExist",false);
        }
        return "exercise-add";
    }

    @PostMapping("/exercises/add")
    public String exerciseAdd(@Valid ExerciseBindingModel exerciseBindingModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("exerciseBindingModel",exerciseBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.exerciseBindingModel",bindingResult);
            return "redirect:/exercises/add";
        }
        boolean addSuccess = exerciseService.add(exerciseBindingModel);
        if(!addSuccess){
            redirectAttributes.addFlashAttribute("exerciseBindingModel",exerciseBindingModel);
            redirectAttributes.addFlashAttribute("alreadyExist",true);
            return "redirect:/exercises/add";
        }
        return "redirect:/home";
    }

}
