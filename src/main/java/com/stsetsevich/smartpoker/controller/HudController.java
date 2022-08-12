package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.Message;
import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.HudCalc;
import com.stsetsevich.smartpoker.repos.MessageRepo;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class HudController {

    @Autowired
    PlayerRepo playerRepo;

    @GetMapping("/hud")
    public String hud(String nickname, Map<String, Object> model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.put("name", username);
        HudCalc hudCalc = new HudCalc();
        ArrayList<String> playerStats = hudCalc.getPlayerStats(playerRepo, "Franshtik (PS)");
        model.put("playerstats", playerStats);

        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);
        Player player=new Player();
        if (nickname==null)
        {
            player=playerRepo.findByNickname("Franshtik (PS)");
            model.put("player", player);
        }
        else {
            player=player=playerRepo.findByNickname(nickname);
            model.put("player", player);
        }
        return "hud";
    }


    @PostMapping("/hud")
    public String addHud(Map<String, Object> model) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.put("name", username);

        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);


        return "hud";
    }

    @PostMapping("addPlayer")
    public String addPlayer(@RequestParam String addPlayer, Map<String, Object> model) {
        //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
        //  List<Message> messages = messageRepo.findByTag(filter);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.put("name", username);
        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);
        Player player=new Player();
        if(!addPlayer.isEmpty() && addPlayer !=null) {
            player = playerRepo.findByNickname(addPlayer);
            model.put("player", player);
        }
        return "hud";

    }
}
