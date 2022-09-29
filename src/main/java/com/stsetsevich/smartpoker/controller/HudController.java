package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.*;
import com.stsetsevich.smartpoker.engine.hud.PreflopStatsCalc;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class HudController {

    @Autowired
    PlayerRepo playerRepo;
    @Autowired
    ExtraStatsCalc extraStatsCalc;

    @Autowired
    PlayersAtTable playersAtTable;

    @Autowired
    TableInfoCalc tableInfoCalc;


    @GetMapping("/hud")
    public String hud(String nickname, Map<String, Object> model, String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5) {

        List<Player> pl = new ArrayList<>(Arrays.asList(playerRepo.findByNickname("Empty Seat"), playerRepo.findByNickname("Empty Seat"), playerRepo.findByNickname("Empty Seat"), playerRepo.findByNickname("Empty Seat"), playerRepo.findByNickname("Empty Seat")));
        playersAtTable.playersInit(pl);


        modelPutGeneralInfo(model, pl);

        return "hud";
    }


    @PostMapping("/hud")
    public String addHud(Map<String, Object> model, String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5) {


        List<Player> pl = playersAtTable.getAllPlayers(addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        modelPutGeneralInfo(model, pl);


        return "hud";
    }

    @PostMapping("addPlayer")
    public String addPlayer(@RequestParam String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5, Map<String, Object> model) {


        List<Player> pl = playersAtTable.getAllPlayers(addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        modelPutGeneralInfo(model, pl);


        return "hud";

    }


    @PostMapping("addStat")
    public String addStat(@RequestParam String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5, String addStat, String player, Map<String, Object> model) {
        //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
        //  List<Message> messages = messageRepo.findByTag(filter);


        List<Player> pl = playersAtTable.getAllPlayers(addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        modelPutGeneralInfo(model, pl);

        Player playerNick = playersAtTable.checkPlayer(player);
        ArrayList<StatInfo> statInfos = extraStatsCalc.extraStatsCalc(playerNick, addStat);

        //Информация для всплывающих сообщений с доп. статами
        model.put("statExtra", addStat);
        model.put("statPlayer", player);
        model.put("statValues", statInfos);


        return "hud";

    }

    private void modelPutGeneralInfo(Map<String, Object> model, List<Player> pl) {


        model.put("plStats", playersAtTable.getPreflopStats());
        model.put("flopStats", playersAtTable.getFlopStats());
        model.put("turnStats", playersAtTable.getTurnStats());
        model.put("riverStats", playersAtTable.getRiverStats());

        model.put("player", pl.get(0));
        model.put("player2", pl.get(1));
        model.put("player3", pl.get(2));
        model.put("player4", pl.get(3));
        model.put("player5", pl.get(4));

        model.put("tableinfo", tableInfoCalc.generalStatsCalc(pl));

        //Информация о цвете таблиц
        model.put("seats", tableInfoCalc.hudSeatsColor(pl));

        if (playersAtTable.getPlayerNotFoundException() != null) {
            model.put("playerError", playersAtTable.getPlayerNotFoundException());
            playersAtTable.setPlayerNotFoundException(null);
        }

    }


}
