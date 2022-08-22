package com.stsetsevich.smartpoker.controller;


import com.stsetsevich.smartpoker.engine.StatsParse;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
public class ParsePlayerController {


    @Autowired
    private PlayerRepo playerRepo;


    @GetMapping("/search")
    public String searchPlayer(Map<String, Object> model) {

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