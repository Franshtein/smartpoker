package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.Message;
import com.stsetsevich.smartpoker.domain.Stat;
import com.stsetsevich.smartpoker.engine.StatsParse;
import com.stsetsevich.smartpoker.repos.MessageRepo;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;
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
        Iterable<Message> messages = messageRepo.findAll();
        model1.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Model model1) {
        Message message = new Message(text, tag);
        messageRepo.save(message);





        Iterable<Message> messages = messageRepo.findAll();
        model1.addAttribute("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Model model1) {
        //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
      //  List<Message> messages = messageRepo.findByTag(filter);

        Iterable<Message> messages;

        if(filter==null || filter.isEmpty())
        {
            messages = messageRepo.findAll();
        }
        else messages = messageRepo.findByTag(filter);
        model1.addAttribute("messages", messages);
        return "main";

    }
    @GetMapping("/login")
    public String login(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.put("name", username);
        return "login";
    }

}