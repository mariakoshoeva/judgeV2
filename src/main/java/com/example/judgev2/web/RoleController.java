package com.example.judgev2.web;

import com.example.judgev2.security.CurrentUser;
import com.example.judgev2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoleController {
    private final CurrentUser currentUser;
    private final UserService userService;

    public RoleController(CurrentUser currentUser, UserService userService) {
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @GetMapping("/roles/add")
    public String role(Model model) {
        if (!currentUser.isAdmin() || currentUser.isAnonymous()) {
            return "redirect:/";
        }
        model.addAttribute("allUsersNames", userService.getAllUserNames());
        return "role-add";
    }

    @PostMapping("/roles/add")
    public String roleAdd(@RequestParam String username,@RequestParam String role) {
        userService.changeRole(username,role);
        return "redirect:/";
    }
}
