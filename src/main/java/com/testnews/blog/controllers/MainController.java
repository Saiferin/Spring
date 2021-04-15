package com.testnews.blog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller

public class MainController {

    @GetMapping("/")//главная страница
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/about")//о нас
    public String aboutController(Model model) {
        model.addAttribute("title", "О нас");
        return "about";
    }

}
