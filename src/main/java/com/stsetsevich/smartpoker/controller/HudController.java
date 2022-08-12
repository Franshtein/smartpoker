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
        Player player2=new Player();
        if (nickname==null)
        {
            player2=playerRepo.findByNickname("szkeg (PS)");
            model.put("player2", player2);
        }
        else {
            player=player=playerRepo.findByNickname(nickname);
            model.put("player2", player2);
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
    public String addPlayer(@RequestParam String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5, String addPlayer6, Map<String, Object> model) {
        //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
        //  List<Message> messages = messageRepo.findByTag(filter);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.put("name", username);

        HudCalc hudCalc = new HudCalc();
        hudCalc.getAllPlayerStats(addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5, addPlayer6);
        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);
        Player player=playerRepo.findByNickname(addPlayer);
        if(!addPlayer.isEmpty() && addPlayer !=null) {
                if(player==null) {
                    System.out.println("Игрок не найден, установлено значение по умолчанию");
                    player = player = playerRepo.findByNickname("Franshtik (PS)");
                }
            Player player2 = playerRepo.findByNickname(addPlayer2);

                if (player2 == null) {
                    System.out.println("Игрок не найден, установлено значение по умолчанию");
                    player2 = playerRepo.findByNickname("Franshtik (PS)");
                }




            model.put("player", player);
            model.put("player2", player2);
        }
        return "hud";

    }
    @PostMapping("addPlayer2")
    public String addPlayer2(@RequestParam String addPlayer2, String addPlayer, Map<String, Object> model) {
        //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
        //  List<Message> messages = messageRepo.findByTag(filter);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        System.out.println(addPlayer);
        model.put("name", username);
        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);
        Player player=playerRepo.findByNickname(addPlayer);

            if (player == null) {
                System.out.println("Игрок не найден, установлено значение по умолчанию");
                player = playerRepo.findByNickname("Franshtik (PS)");
            }
            Player player2 = playerRepo.findByNickname(addPlayer2);
            if (!addPlayer2.isEmpty() && addPlayer2 != null) {
                if (player2 == null) {
                    System.out.println("Игрок не найден, установлено значение по умолчанию");
                    player2 = playerRepo.findByNickname("Franshtik (PS)");
                }

            }
                model.put("player", player);
                model.put("player2", player2);

            return "hud";

        }
}
