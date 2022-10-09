package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.*;
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
    public String hud(Map<String, Object> model) {
        //первичная инициализация пустых таблиц
        playersAtTable.initPlayers();
        List<Player> pl = playersAtTable.getAllPlayers();

        //добавляет в модель общую для всех методов в контроллере информацию
        modelPutGeneralInfo(model, pl);

        return "hud";
    }

    //добавляет нового игрока за стол
    @PostMapping("addPlayer")
    public String addPlayer(@RequestParam String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5, Map<String, Object> model) {

        List<Player> pl = playersAtTable.getAllPlayers(addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        modelPutGeneralInfo(model, pl);

        return "hud";
    }

    //Выводит всплывающее окно с дополнительными статами
    @PostMapping("addStat")
    public String extraStats(String addStat, String player, Map<String, Object> model) {

        List<Player> pl = playersAtTable.getAllPlayers();
        modelPutGeneralInfo(model, pl);

        ArrayList<StatInfo> statInfos = extraStatsCalc.extraStatsCalc(playerRepo.findByNickname(player), addStat);

        //Информация для всплывающих сообщений с доп. статами
        model.put("statExtra", addStat);
        model.put("statPlayer", player);
        model.put("statValues", statInfos);

        return "hud";

    }

    //добавляет в модель общую для всех методов информацию
    private void modelPutGeneralInfo(Map<String, Object> model, List<Player> pl) {

        //Статы игроков для всех улиц
        model.put("plStats", playersAtTable.getPreflopStats());
        model.put("flopStats", playersAtTable.getFlopStats());
        model.put("turnStats", playersAtTable.getTurnStats());
        model.put("riverStats", playersAtTable.getRiverStats());

        //Игроки
        model.put("player", pl.get(0));
        model.put("player2", pl.get(1));
        model.put("player3", pl.get(2));
        model.put("player4", pl.get(3));
        model.put("player5", pl.get(4));

        //основная информация в правом верхнем углу
        model.put("tableinfo", tableInfoCalc.generalStatsCalc(pl));

        //Информация о цвете таблиц игроков
        model.put("seats", tableInfoCalc.hudSeatsColor(pl));

        //Показать сообщение о возникшем исключении
        if (playersAtTable.getPlayerNotFoundException() != null) {
            model.put("playerError", playersAtTable.getPlayerNotFoundException());
            playersAtTable.setPlayerNotFoundException(null);
        }
    }


}
