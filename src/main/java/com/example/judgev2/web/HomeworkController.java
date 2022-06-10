package com.example.judgev2.web;

import com.example.judgev2.models.binding.HomeworkAddBindingModel;
import com.example.judgev2.security.CurrentUser;
import com.example.judgev2.service.ExerciseService;
import com.example.judgev2.service.HomeworkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/homework")
public class HomeworkController {
    private final ExerciseService exerciseService;
    private final HomeworkService homeworkService;
    private final CurrentUser currentUser;

    public HomeworkController(ExerciseService exerciseService, HomeworkService homeworkService, CurrentUser currentUser) {
        this.exerciseService = exerciseService;
        this.homeworkService = homeworkService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String addHomework(Model model) {
        if(currentUser.isAnonymous()){
            return "redirect:/";
        }
        if (!model.containsAttribute("homework")) {
            model.addAttribute("homework", new HomeworkAddBindingModel());
            model.addAttribute("isLate", false);
        }
        model.addAttribute("allExerciseName", exerciseService.findAllName());
        return "homework-add";
    }

    @PostMapping("/add")
    public String addHomeworkConfirm(@Valid HomeworkAddBindingModel homeworkAddBindingModel,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("homework", homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homework", bindingResult);
            return "redirect:add";
        }
       //todo: check if user choose a exercise from dropdownMenu;
        boolean isLate = exerciseService.checkHomeworkIsOutOfDate(homeworkAddBindingModel.getExercise());
        if(isLate){
            redirectAttributes.addFlashAttribute("homework",homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("isLate", isLate);
            return "redirect:add";
        }

       homeworkService.add(homeworkAddBindingModel);

        return "redirect:/";
    }

}
