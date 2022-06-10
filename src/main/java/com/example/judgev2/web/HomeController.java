package com.example.judgev2.web;

import com.example.judgev2.security.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CurrentUser currentUser;

    public HomeController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String index(){
        if(currentUser.isAnonymous()){
            return "index";
        }
        return "home";
    }

    @GetMapping("/home")
    public String home(){
        if(currentUser.isAnonymous()){
            return "index";
        }
        return "home";
    }
}
