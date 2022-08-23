package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.PreflopStatsCalc;
import com.stsetsevich.smartpoker.engine.SetPlayersAtTable;
import com.stsetsevich.smartpoker.engine.StatValue;
import com.stsetsevich.smartpoker.engine.TableInfoCalc;
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
import java.util.HashMap;
import java.util.Map;

@Controller
public class HudController {

    @Autowired
    PlayerRepo playerRepo;
    @Autowired
    StatRepo statRepo;
    @Autowired
    PreflopStatsCalc preflopStatsCalc;

    @GetMapping("/hud")
    public String hud(String nickname, Map<String, Object> model, String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.put("name", username);
        ArrayList<String> playerStats = SetPlayersAtTable.getPlayerStats(playerRepo, "Franshtik (PS)");
        model.put("playerstats", playerStats);

        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);
        ArrayList<Player> pl = SetPlayersAtTable.getAllPlayerStats(playerRepo, addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);

      /*  HashMap<Integer, ArrayList<StatValue>> sv = HudCalc.hudStatsCalcLine1(pl, statRepo);

        for(ArrayList<StatValue> sAl : sv.values())
        {
            for (StatValue s : sAl)
            {
                System.out.println(s.getStat());
            }
        }
*/
        model.put("plStatsLine1", preflopStatsCalc.hudStatsCalcLine1(pl, statRepo));
        model.put("plStatsLine2", preflopStatsCalc.hudStatsCalcLine2(pl, statRepo));
        model.put("plStatsLine3", preflopStatsCalc.hudStatsCalcLine3(pl, statRepo));
        model.put("plStatsLine4", preflopStatsCalc.hudStatsCalcLine4(pl, statRepo));

        model.put("seats", preflopStatsCalc.hudSeatsColor(pl, statRepo));
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
        String username = auth.getName();
        model.put("name", username);

        Iterable<Player> players = playerRepo.findAll();

        ArrayList<Player> pl = SetPlayersAtTable.getAllPlayerStats(playerRepo, addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);

        model.put("plStatsLine1", preflopStatsCalc.hudStatsCalcLine1(pl, statRepo));
        model.put("plStatsLine2", preflopStatsCalc.hudStatsCalcLine2(pl, statRepo));
        model.put("plStatsLine3", preflopStatsCalc.hudStatsCalcLine3(pl, statRepo));
        model.put("plStatsLine4", preflopStatsCalc.hudStatsCalcLine4(pl, statRepo));
        model.put("tableinfo", TableInfoCalc.extraStatsCalc(pl));
        model.put("players", players);
        model.put("seats", PreflopStatsCalc.hudSeatsColor(pl, statRepo));
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
        String username = auth.getName();
        model.put("name", username);


        ArrayList<Player> pl = SetPlayersAtTable.getAllPlayerStats(playerRepo, addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);
        model.put("tableinfo", TableInfoCalc.extraStatsCalc(pl));

        model.put("seats", preflopStatsCalc.hudSeatsColor(pl, statRepo));

        model.put("plStatsLine1", preflopStatsCalc.hudStatsCalcLine1(pl, statRepo));
        model.put("plStatsLine2", preflopStatsCalc.hudStatsCalcLine2(pl, statRepo));
        model.put("plStatsLine3", preflopStatsCalc.hudStatsCalcLine3(pl, statRepo));
        model.put("plStatsLine4", preflopStatsCalc.hudStatsCalcLine4(pl, statRepo));
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
        String username = auth.getName();
        System.out.println(addPlayer);
        model.put("name", username);
        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);


        ArrayList<Player> pl = SetPlayersAtTable.getAllPlayerStats(playerRepo, addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);

        HashMap<Integer, ArrayList<StatValue>> sv = preflopStatsCalc.hudStatsCalcLine1(pl, statRepo);

        for (ArrayList<StatValue> sAl : sv.values()) {
            for (StatValue s : sAl) {
                System.out.println(s.getStat());
            }
        }

        model.put("plStatsLine1", preflopStatsCalc.hudStatsCalcLine1(pl, statRepo));
        model.put("plStatsLine2", preflopStatsCalc.hudStatsCalcLine2(pl, statRepo));
        model.put("plStatsLine3", preflopStatsCalc.hudStatsCalcLine3(pl, statRepo));
        model.put("plStatsLine4", preflopStatsCalc.hudStatsCalcLine4(pl, statRepo));
        model.put("seats", preflopStatsCalc.hudSeatsColor(pl, statRepo));
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
        String username = auth.getName();
        model.put("name", username);

        System.out.println(addPlayer);
        System.out.println(addPlayer2);
        System.out.println(addPlayer3 + "+++++++++++++++");

        ArrayList<Player> pl = SetPlayersAtTable.getAllPlayerStats(playerRepo, addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);
        model.put("tableinfo", TableInfoCalc.extraStatsCalc(pl));

        model.put("seats", preflopStatsCalc.hudSeatsColor(pl, statRepo));
        model.put("plStatsLine1", preflopStatsCalc.hudStatsCalcLine1(pl, statRepo));
        model.put("plStatsLine2", preflopStatsCalc.hudStatsCalcLine2(pl, statRepo));
        model.put("plStatsLine3", preflopStatsCalc.hudStatsCalcLine3(pl, statRepo));
        model.put("plStatsLine4", preflopStatsCalc.hudStatsCalcLine4(pl, statRepo));
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
        String username = auth.getName();
        model.put("name", username);

        ArrayList<Player> pl = SetPlayersAtTable.getAllPlayerStats(playerRepo, addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);

        model.put("seats", preflopStatsCalc.hudSeatsColor(pl, statRepo));
        model.put("tableinfo", TableInfoCalc.extraStatsCalc(pl));
        model.put("plStatsLine1", preflopStatsCalc.hudStatsCalcLine1(pl, statRepo));
        model.put("plStatsLine2", preflopStatsCalc.hudStatsCalcLine2(pl, statRepo));
        model.put("plStatsLine3", preflopStatsCalc.hudStatsCalcLine3(pl, statRepo));
        model.put("plStatsLine4", preflopStatsCalc.hudStatsCalcLine4(pl, statRepo));
        model.put("player", pl.get(0));
        model.put("player2", pl.get(1));
        model.put("player3", pl.get(2));
        model.put("player4", pl.get(3));
        model.put("player5", pl.get(4));

        return "hud";

    }

    @PostMapping("addPlayer5")
    public String addPlayer5(@RequestParam String addPlayer5, String addPlayer2, String addPlayer3
            , String addPlayer, String addPlayer4, Map<String, Object> model) {
        //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
        //  List<Message> messages = messageRepo.findByTag(filter);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.put("name", username);

        ArrayList<Player> pl = SetPlayersAtTable.getAllPlayerStats(playerRepo, addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        Iterable<Player> players = playerRepo.findAll();
        model.put("players", players);

        model.put("seats", preflopStatsCalc.hudSeatsColor(pl, statRepo));
        model.put("tableinfo", TableInfoCalc.extraStatsCalc(pl));
        model.put("plStatsLine1", preflopStatsCalc.hudStatsCalcLine1(pl, statRepo));
        model.put("plStatsLine2", preflopStatsCalc.hudStatsCalcLine2(pl, statRepo));
        model.put("plStatsLine3", preflopStatsCalc.hudStatsCalcLine3(pl, statRepo));
        model.put("plStatsLine4", preflopStatsCalc.hudStatsCalcLine4(pl, statRepo));
        model.put("player", pl.get(0));
        model.put("player2", pl.get(1));
        model.put("player3", pl.get(2));
        model.put("player4", pl.get(3));
        model.put("player5", pl.get(4));

        return "hud";

    }
}
