package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.repos.PlayerRepo;
import com.stsetsevich.smartpoker.repos.StatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Controller
public class MainController {


    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private StatRepo statRepo;


    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.put("name", username);

        return "greeting";
    }


    @GetMapping("/main")
    public String main(Model model1) {
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Model model1) {








        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Model model1) {
        //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
      //  List<Message> messages = messageRepo.findByTag(filter);

        return "main";

    }
    @GetMapping("/login")
    public String login(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.put("name", username);
        return "login.html";
    }

}