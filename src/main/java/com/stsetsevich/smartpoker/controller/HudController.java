package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.Message;
import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.HudCalc;
import com.stsetsevich.smartpoker.engine.TableInfoCalc;
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
    public String hud(String nickname, Map<String, Object> model, String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.put("name", username);
        ArrayList<String> playerStats = HudCalc.getPlayerStats(playerRepo, "Franshtik (PS)");
        model.put("playerstats", playerStats);

        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);
        ArrayList<Player> pl = HudCalc.getAllPlayerStats(playerRepo,addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        model.put("players", players);
        model.put("tableinfo", TableInfoCalc.extraStatsCalc(pl));
        model.put("player", pl.get(0));
        model.put("player2", pl.get(1));
        model.put("player3", pl.get(2));
        model.put("player4", pl.get(3));
        model.put("player5", pl.get(4));
        return "hud";
    }


    @PostMapping("/hud")
    public String addHud(Map<String, Object> model, String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.put("name", username);

        Iterable<Player> players = playerRepo.findAll();

        ArrayList<Player> pl = HudCalc.getAllPlayerStats(playerRepo,addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);

        model.put("tableinfo", TableInfoCalc.extraStatsCalc(pl));
        model.put("players", players);

        model.put("player", pl.get(0));
        model.put("player2", pl.get(1));
        model.put("player3", pl.get(2));
        model.put("player4", pl.get(3));
        model.put("player5", pl.get(4));


        return "hud";
    }

    @PostMapping("addPlayer")
    public String addPlayer(@RequestParam String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5, Map<String, Object> model) {
        //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
        //  List<Message> messages = messageRepo.findByTag(filter);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.put("name", username);


        ArrayList<Player> pl = HudCalc.getAllPlayerStats(playerRepo,addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);
        model.put("tableinfo", TableInfoCalc.extraStatsCalc(pl));



            model.put("player", pl.get(0));
            model.put("player2", pl.get(1));
            model.put("player3", pl.get(2));
            model.put("player4", pl.get(3));
            model.put("player5", pl.get(4));

        return "hud";

    }
    @PostMapping("addPlayer2")
    public String addPlayer2(@RequestParam String addPlayer2, String addPlayer, String addPlayer3
            , String addPlayer4, String addPlayer5, Map<String, Object> model) {
        //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
        //  List<Message> messages = messageRepo.findByTag(filter);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        System.out.println(addPlayer);
        model.put("name", username);
        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);


        ArrayList<Player> pl = HudCalc.getAllPlayerStats(playerRepo,addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        model.put("player", pl.get(0));
        model.put("player2", pl.get(1));
        model.put("player3", pl.get(2));
        model.put("player4", pl.get(3));
        model.put("player5", pl.get(4));
        model.put("tableinfo", TableInfoCalc.extraStatsCalc(pl));
            return "hud";

        }
    @PostMapping("addPlayer3")
    public String addPlayer3(@RequestParam String addPlayer3, String addPlayer2, String addPlayer
            , String addPlayer4, String addPlayer5, Map<String, Object> model) {
        //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
        //  List<Message> messages = messageRepo.findByTag(filter);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.put("name", username);

        System.out.println(addPlayer);
        System.out.println(addPlayer2);
        System.out.println(addPlayer3 + "+++++++++++++++");

        ArrayList<Player> pl = HudCalc.getAllPlayerStats(playerRepo,addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);
        model.put("tableinfo", TableInfoCalc.extraStatsCalc(pl));



        model.put("player", pl.get(0));
        model.put("player2", pl.get(1));
        model.put("player3", pl.get(2));
        model.put("player4", pl.get(3));
        model.put("player5", pl.get(4));

        return "hud";

    }
    @PostMapping("addPlayer4")
    public String addPlayer4(@RequestParam String addPlayer4, String addPlayer2, String addPlayer3
            , String addPlayer, String addPlayer5, Map<String, Object> model) {
        //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
        //  List<Message> messages = messageRepo.findByTag(filter);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.put("name", username);

        ArrayList<Player> pl = HudCalc.getAllPlayerStats(playerRepo,addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);


        model.put("tableinfo", TableInfoCalc.extraStatsCalc(pl));

        model.put("player", pl.get(0));
        model.put("player2", pl.get(1));
        model.put("player3", pl.get(2));
        model.put("player4", pl.get(3));
        model.put("player5", pl.get(4));

        return "hud";

    }
    @PostMapping("addPlayer5")
    public String addPlayer5(@RequestParam String addPlayer5, String addPlayer2, String addPlayer3
            , String addPlayer, String addPlayer4,  Map<String, Object> model) {
        //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
        //  List<Message> messages = messageRepo.findByTag(filter);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.put("name", username);

        ArrayList<Player> pl = HudCalc.getAllPlayerStats(playerRepo,addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);


        model.put("tableinfo", TableInfoCalc.extraStatsCalc(pl));

        model.put("player", pl.get(0));
        model.put("player2", pl.get(1));
        model.put("player3", pl.get(2));
        model.put("player4", pl.get(3));
        model.put("player5", pl.get(4));

        return "hud";

    }
}
