package com.stsetsevich.smartpoker.controller;


import com.stsetsevich.smartpoker.domain.Message;
import com.stsetsevich.smartpoker.engine.StatsParse;
import com.stsetsevich.smartpoker.repos.MessageRepo;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Controller
public class ParsePlayerController {

    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private PlayerRepo playerRepo;


    @GetMapping("/search")
    public String searchPlayer(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "search";
    }

    @PostMapping("/search")
    public String addPlayer(@RequestParam String addFile, Map<String, Object> model) {
        if (addFile !="") {
            StatsParse statsParse = new StatsParse(addFile);
            if (statsParse.getStats() != null) {
                if (playerRepo.findByNickname(statsParse.searchNickname()) == null) {
                    playerRepo.save(statsParse.getStats());
                } else System.out.println("That player excists in the DataBase");
            }
        }

        return "search";
    }
}