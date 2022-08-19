package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.domain.Stat;
import com.stsetsevich.smartpoker.engine.HudCalc;
import com.stsetsevich.smartpoker.engine.SetPlayersAtTable;
import com.stsetsevich.smartpoker.engine.StatValue;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import com.stsetsevich.smartpoker.repos.StatRepo;
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
        @Autowired
        StatRepo statRepo;

        @GetMapping("/extrastats")
        public String threeBet(Map<String, Object> model, String player, int stat) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username=auth.getName();
            model.put("name", username);

            Player playerNick= SetPlayersAtTable.checkPlayer(playerRepo, player);
            ArrayList <StatValue> statValues =HudCalc.extraStatsCalc(playerNick, stat, statRepo);
            model.put("statValues", statValues);

            return "extrastats";
        }


        @PostMapping("/extrastats")
        public String addThreeBet(Map<String, Object> model, String player, int stat) {


            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username=auth.getName();
            model.put("name", username);
            Player playerNick=SetPlayersAtTable.checkPlayer(playerRepo, player);
            ArrayList <StatValue> statValues =HudCalc.extraStatsCalc(playerNick, stat, statRepo);
            model.put("statValues", statValues);

            return "extrastats";
        }

}
