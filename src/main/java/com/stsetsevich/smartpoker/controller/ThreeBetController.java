package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.HudCalc;
import com.stsetsevich.smartpoker.engine.SetPlayersAtTable;
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
public class ThreeBetController {

        @Autowired
        PlayerRepo playerRepo;

        @GetMapping("/3bet")
        public String threeBet(String nickname, Map<String, Object> model, String addPlayer, String addPlayer2, String addPlayer3
                , String addPlayer4, String addPlayer5, String player, int stat) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username=auth.getName();
            model.put("name", username);

            System.out.println(stat);

            Player playerNick=SetPlayersAtTable.checkPlayer(playerRepo, player);
            model.put("test", playerNick);
            model.put("player", playerNick);

            return "3bet";
        }


        @PostMapping("/3bet")
        public String addThreeBet(Map<String, Object> model, String addPlayer, String addPlayer2, String addPlayer3
                , String addPlayer4, String addPlayer5, String player, int stat) {


            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username=auth.getName();
            model.put("name", username);
            Player playerNick=SetPlayersAtTable.checkPlayer(playerRepo, player);
            model.put("test", playerNick);
            model.put("player", playerNick);
            System.out.println(stat);

            return "hud";
        }

        @PostMapping("3betfilter")
        public String addThreeBetFilter(@RequestParam String addPlayer, String addPlayer2, String addPlayer3
                , String addPlayer4, String addPlayer5, Map<String, Object> model, String player, int stat) {
            //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
            //  List<Message> messages = messageRepo.findByTag(filter);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username=auth.getName();
            model.put("name", username);
            Player playerNick= SetPlayersAtTable.checkPlayer(playerRepo, player);
            System.out.println(stat);
            model.put("test", playerNick);
            model.put("player", playerNick);
            return "hud";

        }
}
