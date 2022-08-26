package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.*;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import com.stsetsevich.smartpoker.repos.StatRepo;
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
    @Autowired
    StatRepo statRepo;
    @Autowired
    PreflopStatsCalc preflopStatsCalc;
    @Autowired
    ExtraStatsCalc extraStatsCalc;

    @GetMapping("/hud")
    public String hud(String nickname, Map<String, Object> model, String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5) {

        ArrayList<String> playerStats = SetPlayersAtTable.getPlayerStats(playerRepo, "Franshtik (PS)");

        ArrayList<Player> pl = SetPlayersAtTable.getAllPlayerStats(playerRepo, addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        modelInfo(model, pl, statRepo, preflopStatsCalc);
        return "hud";
    }


    @PostMapping("/hud")
    public String addHud(Map<String, Object> model, String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5) {





        ArrayList<Player> pl = SetPlayersAtTable.getAllPlayerStats(playerRepo, addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);

        modelInfo(model, pl, statRepo, preflopStatsCalc);

        return "hud";
    }

    @PostMapping("addPlayer")
    public String addPlayer(@RequestParam String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5, Map<String, Object> model) {



        ArrayList<Player> pl = SetPlayersAtTable.getAllPlayerStats(playerRepo, addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);

        modelInfo(model, pl, statRepo, preflopStatsCalc);

        return "hud";

    }

    @PostMapping("addPlayer2")
    public String addPlayer2(@RequestParam String addPlayer2, String addPlayer, String addPlayer3
            , String addPlayer4, String addPlayer5, Map<String, Object> model) {


        ArrayList<Player> pl = SetPlayersAtTable.getAllPlayerStats(playerRepo, addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);

        modelInfo(model, pl, statRepo, preflopStatsCalc);

        return "hud";

    }

    @PostMapping("addPlayer3")
    public String addPlayer3(@RequestParam String addPlayer3, String addPlayer2, String addPlayer
            , String addPlayer4, String addPlayer5, Map<String, Object> model) {


        ArrayList<Player> pl = SetPlayersAtTable.getAllPlayerStats(playerRepo, addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);

        modelInfo(model, pl, statRepo, preflopStatsCalc);

        return "hud";

    }

    @PostMapping("addPlayer4")
    public String addPlayer4(@RequestParam String addPlayer4, String addPlayer2, String addPlayer3
            , String addPlayer, String addPlayer5, Map<String, Object> model) {


        ArrayList<Player> pl = SetPlayersAtTable.getAllPlayerStats(playerRepo, addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);

        //Основная информация о столе на основе игоков за столом
        modelInfo(model, pl, statRepo, preflopStatsCalc);

        return "hud";

    }

    @PostMapping("addPlayer5")
    public String addPlayer5(@RequestParam String addPlayer5, String addPlayer2, String addPlayer3
            , String addPlayer, String addPlayer4, Map<String, Object> model) {


        ArrayList<Player> pl = SetPlayersAtTable.getAllPlayerStats(playerRepo, addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);

        modelInfo(model, pl, statRepo, preflopStatsCalc);


        return "hud";

    }
    @PostMapping("addStat")
    public String addStat(@RequestParam String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5, String addStat, String player, Map<String, Object> model) {
        //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
        //  List<Message> messages = messageRepo.findByTag(filter);


        ArrayList<Player> pl = SetPlayersAtTable.getAllPlayerStats(playerRepo, addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);


        Player playerNick = SetPlayersAtTable.checkPlayer(playerRepo, player);
        ArrayList<StatValue> statValues = extraStatsCalc.extraStatsCalc(playerNick, addStat, statRepo);

        //Информация для всплывающих сообщений с доп. статами
        model.put("statExtra", addStat);
        model.put("statPlayer", player);
        model.put("statValues", statValues);



        modelInfo(model, pl, statRepo, preflopStatsCalc);
        return "hud";

    }
    private static void modelInfo(Map<String, Object> model, ArrayList<Player> pl, StatRepo statRepo, PreflopStatsCalc preflopStatsCalc){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.put("name", username);

        model.put("tableinfo", TableInfoCalc.extraStatsCalc(pl));

        //Информация о цвете таблиц
        model.put("seats", preflopStatsCalc.hudSeatsColor(pl, statRepo));

        //Информация о префлопе для таблиц со статами
        model.put("plStatsLine1", preflopStatsCalc.hudStatsCalcLine1(pl, statRepo));
        model.put("plStatsLine2", preflopStatsCalc.hudStatsCalcLine2(pl, statRepo));
        model.put("plStatsLine3", preflopStatsCalc.hudStatsCalcLine3(pl, statRepo));
        model.put("plStatsLine4", preflopStatsCalc.hudStatsCalcLine4(pl, statRepo));

        //Информация об игроках за столом
        model.put("player", pl.get(0));
        model.put("player2", pl.get(1));
        model.put("player3", pl.get(2));
        model.put("player4", pl.get(3));
        model.put("player5", pl.get(4));
    }


}
