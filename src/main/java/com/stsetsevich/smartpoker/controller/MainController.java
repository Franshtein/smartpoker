package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.Message;
import com.stsetsevich.smartpoker.engine.StatsParse;
import com.stsetsevich.smartpoker.repos.MessageRepo;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private PlayerRepo playerRepo;


    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.put("name", username);
        StatsParse statsParse = new StatsParse();
        if(statsParse.getStats()!=null)
        {
        if(playerRepo.findByNickname(statsParse.getStats().getNickname())==null)
        {
            playerRepo.save(statsParse.getStats());
        }
        else System.out.println("That player excists in the DataBase");
}
        return "greeting";
    }


    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("mesages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
      //  List<Message> messages = messageRepo.findByTag(filter);

        Iterable<Message> messages;

        if(filter==null || filter.isEmpty())
        {
            messages = messageRepo.findAll();
        }
        else messages = messageRepo.findByTag(filter);
        model.put("messages", messages);
        return "main";

    }
    @GetMapping("/login")
    public String login(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.put("name", username);
        StatsParse statsParse = new StatsParse();
        // stats.getText();
        // stats.getWiki();
        //  stats.getStats();
       // stats.getStatsLocal();
        return "login";
    }
}