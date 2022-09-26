package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.ExtraStatsCalc;
import com.stsetsevich.smartpoker.engine.SetPlayersAtTable;
import com.stsetsevich.smartpoker.engine.StatInfo;
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
    @Autowired
    ExtraStatsCalc extraStatsCalc;
    @Autowired
    SetPlayersAtTable setPlayersAtTable;

    @GetMapping("/extrastats")
    public String threeBet(Map<String, Object> model, String player, String stat) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.put("name", username);

        Player playerNick = setPlayersAtTable.checkPlayer(player);
        ArrayList<StatInfo> statInfos = extraStatsCalc.extraStatsCalc(playerNick, stat);
        model.put("statValues", statInfos);

        return "extrastats";
    }


    @PostMapping("/extrastats")
    public String addThreeBet(Map<String, Object> model, String player, String stat) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.put("name", username);
        Player playerNick = setPlayersAtTable.checkPlayer(player);
        ArrayList<StatInfo> statInfos = extraStatsCalc.extraStatsCalc(playerNick, stat);
        model.put("statValues", statInfos);

        return "extrastats";
    }

}
