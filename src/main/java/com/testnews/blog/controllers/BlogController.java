package com.testnews.blog.controllers;


import com.testnews.blog.models.Form;
import com.testnews.blog.models.Post;

import com.testnews.blog.repo.FormRepository;
import com.testnews.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller

public class BlogController {

    @Autowired
    private PostRepository postRepository;



    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-Main";
    }

    @GetMapping("/blog/addPost")
    public String blogAdd(Model model){
        model.addAttribute("title", "Добавить статью");
        return "blog-addPost";
    }

    @PostMapping("/blog/addPost")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String fullText, Model model){
        Post post = new Post(title, anons, fullText);//модель
        postRepository.save(post);//добавление статьи сохранение объекта post
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")//динамический параметр
    public String blogDetails(@PathVariable(value = "id") Long id, Model model){
        if (!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);// поместили в объект модель post
        ArrayList<Post> res = new ArrayList<>();//
        post.ifPresent(res::add);//объект Optional переводим в ArrayList
        model.addAttribute("post", res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")//динамический параметр
    public String blogEdit(@PathVariable(value = "id") Long id, Model model){
        if (!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);// поместил в объект модель post
        ArrayList<Post> res = new ArrayList<>();//
        post.ifPresent(res::add);//объект Optional переводим в ArrayList
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") Long id,@RequestParam String title, @RequestParam String anons, @RequestParam String fullText, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFullText(fullText);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") Long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }



}
