package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.*;
import com.stsetsevich.smartpoker.engine.hud.FlopStatsCalc;
import com.stsetsevich.smartpoker.engine.hud.PreflopStatsCalc;
import com.stsetsevich.smartpoker.engine.hud.RiverStatsCalc;
import com.stsetsevich.smartpoker.engine.hud.TurnStatsCalc;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import com.stsetsevich.smartpoker.repos.StatRepo;
import com.stsetsevich.smartpoker.repos.UserRepo;
import com.stsetsevich.smartpoker.repos.UserSmarthandAccountAndCookiesRepo;
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
    FlopStatsCalc flopStatsCalc;
    @Autowired
    TurnStatsCalc turnStatsCalc;
    @Autowired
    RiverStatsCalc riverStatsCalc;
    @Autowired
    ExtraStatsCalc extraStatsCalc;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserSmarthandAccountAndCookiesRepo userSmarthandAccountAndCookiesRepo;
    @Autowired
    SetPlayersAtTable setPlayersAtTable;
   // @Autowired
   // EntityManager entityManager;
    @Autowired
   StatInfoWORK statInfo;


    @GetMapping("/hud")
    public String hud(String nickname, Map<String, Object> model, String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5) {

      //  ArrayList<String> playerStats = SetPlayersAtTable.getPlayerStats(playerRepo, "Franshtik (PS)");
        addPlayer = "Empty Seat";
        addPlayer2 = "Empty Seat";
        addPlayer3 = "Empty Seat";
        addPlayer4 = "Empty Seat";
        addPlayer5 = "Empty Seat";
        ArrayList<Player> pl = setPlayersAtTable.getAllPlayers(addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);
        modelPutGeneralInfo(model, pl);
        System.out.println(playerRepo.findSomething("Vot blin"));;
       /* String stat="total3bet";
        String nick="Franshtik (PS)";
        Query query = entityManager.createNativeQuery("SELECT "+stat+" FROM player where nickname='"+nick+"'");
        List list=query.getResultList();
        System.out.println(list);

        */

       // statInfo.setInfo("total3bet", playerRepo.findByNickname("Franshtik (PS)"));


/*
        statInfo.setInfo("total_hands");
        ;
StatInfo statInfo1 = statInfo.getStatInfo();
statInfo1.setInfo("total3bet");
        StatInfo statInfo2 = statInfo.getStatInfo();
        statInfo2.setInfo("afq_flop");
        System.out.println(statInfo);
        System.out.println(statInfo1);
        System.out.println(statInfo2);

*/
        return "hud";
    }


    @PostMapping("/hud")
    public String addHud(Map<String, Object> model, String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5) {


        ArrayList<Player> pl = setPlayersAtTable.getAllPlayers(addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);

        modelPutGeneralInfo(model, pl);

        return "hud";
    }

    @PostMapping("addPlayer")
    public String addPlayer(@RequestParam String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5, Map<String, Object> model) {


        ArrayList<Player> pl = setPlayersAtTable.getAllPlayers(addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);

        modelPutGeneralInfo(model, pl);

        return "hud";

    }



    @PostMapping("addStat")
    public String addStat(@RequestParam String addPlayer, String addPlayer2, String addPlayer3
            , String addPlayer4, String addPlayer5, String addStat, String player, Map<String, Object> model) {
        //можно только одной строкой, вот так, только будет фильтровать и при пустом поле
        //  List<Message> messages = messageRepo.findByTag(filter);


        ArrayList<Player> pl = setPlayersAtTable.getAllPlayers(addPlayer, addPlayer2, addPlayer3, addPlayer4, addPlayer5);


        Player playerNick = SetPlayersAtTable.checkPlayer(playerRepo, player);
        ArrayList<StatInfo> statInfos = extraStatsCalc.extraStatsCalc(playerNick, addStat);

        //Информация для всплывающих сообщений с доп. статами
        model.put("statExtra", addStat);
        model.put("statPlayer", player);
        model.put("statValues", statInfos);


        modelPutGeneralInfo(model, pl);
        return "hud";

    }

    private void modelPutGeneralInfo(Map<String, Object> model, ArrayList<Player> pl) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.put("name", username);

        model.put("plStatsLine1", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("plStatsLine2", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("plStatsLine3", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("plStatsLine4", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("plStatsLine5", preflopStatsCalc.hudStatsCalcLine1(pl));

        model.put("flopStatsLine1", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("flopStatsLine2", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("flopStatsLine3", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("flopStatsLine4", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("flopStatsLine5", preflopStatsCalc.hudStatsCalcLine1(pl));

        model.put("turnStatsLine1", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("turnStatsLine2", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("turnStatsLine3", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("turnStatsLine4", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("turnStatsLine5", preflopStatsCalc.hudStatsCalcLine1(pl));

        model.put("riverStatsLine1", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("riverStatsLine2", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("riverStatsLine3", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("riverStatsLine4", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("riverStatsLine5", preflopStatsCalc.hudStatsCalcLine1(pl));


        //Информация о префлопе для таблиц со статами
        /*
        model.put("plStatsLine1", preflopStatsCalc.hudStatsCalcLine1(pl));
        model.put("plStatsLine2", preflopStatsCalc.hudStatsCalcLine2(pl));
        model.put("plStatsLine3", preflopStatsCalc.hudStatsCalcLine3(pl));
        model.put("plStatsLine4", preflopStatsCalc.hudStatsCalcLine4(pl));
        model.put("plStatsLine5", preflopStatsCalc.hudStatsCalcLine5(pl));

        model.put("flopStatsLine1", flopStatsCalc.hudStatsCalcLine1(pl));
        model.put("flopStatsLine2", flopStatsCalc.hudStatsCalcLine2(pl));
        model.put("flopStatsLine3", flopStatsCalc.hudStatsCalcLine3(pl));
        model.put("flopStatsLine4", flopStatsCalc.hudStatsCalcLine4(pl));
        model.put("flopStatsLine5", flopStatsCalc.hudStatsCalcLine5(pl));

        model.put("turnStatsLine1", turnStatsCalc.hudStatsCalcLine1(pl));
        model.put("turnStatsLine2", turnStatsCalc.hudStatsCalcLine2(pl));
        model.put("turnStatsLine3", turnStatsCalc.hudStatsCalcLine3(pl));
        model.put("turnStatsLine4", turnStatsCalc.hudStatsCalcLine4(pl));
        model.put("turnStatsLine5", turnStatsCalc.hudStatsCalcLine5(pl));

        model.put("riverStatsLine1", riverStatsCalc.hudStatsCalcLine1(pl));
        model.put("riverStatsLine2", riverStatsCalc.hudStatsCalcLine2(pl));
        model.put("riverStatsLine3", riverStatsCalc.hudStatsCalcLine3(pl));
        model.put("riverStatsLine4", riverStatsCalc.hudStatsCalcLine4(pl));
        model.put("riverStatsLine5", riverStatsCalc.hudStatsCalcLine5(pl));


         */

        //Информация об игроках за столом
        //if (pl.get(0).getNickname().equals("Franshtik (PS)")) pl.get(0).setNickname("Empty Seat");

        model.put("player", pl.get(0));
        model.put("player2", pl.get(1));
        model.put("player3", pl.get(2));
        model.put("player4", pl.get(3));
        model.put("player5", pl.get(4));

        TableInfoCalc tableInfoCalc= new TableInfoCalc();
        model.put("tableinfo", tableInfoCalc.extraStatsCalc(pl));

        //Информация о цвете таблиц
        model.put("seats", preflopStatsCalc.hudSeatsColor(pl));

       /* String picture;
        if(pl.get(0).getVpip()<=20) picture="/img/vpip/20.png";
        else if (pl.get(0).getVpip()<=30) picture="/img/vpip/30.png";
        else  picture="/img/vpip/35.png";
        File f = new File("E:/idea_projects/smartpoker/src/main/resources/static"+picture);
        if(f.exists() && !f.isDirectory()) {
            System.out.println("FILE EXSIST");
        }
        else picture=null;
        model.put("picture", picture);

        */
    }


}
