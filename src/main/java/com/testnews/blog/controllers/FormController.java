package com.testnews.blog.controllers;

import com.testnews.blog.models.Form;
import com.testnews.blog.repo.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {
    @Autowired
    private FormRepository formRepository;

    @GetMapping("/form")
    public String blogForm(Model model){
        Iterable<Form> forms = formRepository.findAll();
        model.addAttribute("forms", forms);
        return "form";
    }

    @PostMapping("/form")
    public String blogForm(@RequestParam String user, @RequestParam String userMail, Model model){
        Form form = new Form(user, userMail);//модель
        formRepository.save(form);//добавление статьи сохранение объекта post
        return "redirect:/form";
    }
}
