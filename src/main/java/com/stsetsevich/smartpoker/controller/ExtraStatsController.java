package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.HudCalc;
import com.stsetsevich.smartpoker.engine.StatValue;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class ExtraStatsController {

        @Autowired
        PlayerRepo playerRepo;

        @GetMapping("/extrastats")
        public String threeBet(Map<String, Object> model, String player, int stat) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username=auth.getName();
            model.put("name", username);



            Player playerNick=HudCalc.checkPlayer(playerRepo, player);
            ArrayList <StatValue> statValues =HudCalc.extraStatsCalc(playerNick, stat);
            System.out.println(statValues.get(4).getValue());
            model.put("statValues", statValues);
            model.put("test", playerNick);
            model.put("player", playerNick);

            return "extrastats";
        }


        @PostMapping("/extrastats")
        public String addThreeBet(Map<String, Object> model, String player, int stat) {


            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username=auth.getName();
            model.put("name", username);
            Player playerNick=HudCalc.checkPlayer(playerRepo, player);
            ArrayList <StatValue> statValues =HudCalc.extraStatsCalc(playerNick, stat);
            model.put("statValues", statValues);
            model.put("test", playerNick);
            model.put("player", playerNick);
            System.out.println(stat);

            return "extrastats";
        }

        @PostMapping("extrastatsfiter")
        public String addThreeBetFilter(Map<String, Object> model, String player, int stat) {
            //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
            //  List<Message> messages = messageRepo.findByTag(filter);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username=auth.getName();
            model.put("name", username);
            Player playerNick=HudCalc.checkPlayer(playerRepo, player);
            System.out.println(stat);
            ArrayList <StatValue> statValues =HudCalc.extraStatsCalc(playerNick, stat);
            model.put("statValues", statValues);
            model.put("test", playerNick);
            model.put("player", playerNick);

            return "extrastats";

        }
}
