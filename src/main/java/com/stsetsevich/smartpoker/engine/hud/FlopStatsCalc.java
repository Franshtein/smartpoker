package com.stsetsevich.smartpoker.engine.hud;


import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.StatInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class FlopStatsCalc extends StatsCalc {
    @Autowired
    StatInfo statInfoMother;

    //Записываем в коллекцию данные каждого игрока для первой строки таблицы
    //Вычисляем значения и в какой диапазон они попадают
    public HashMap<Integer, ArrayList<StatInfo>> hudStatsCalcLine1(ArrayList<Player> players) {
        HashMap<Integer, ArrayList<StatInfo>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatInfo> stats = new ArrayList<>();
            StatInfo statInfo;
            try {


                String statname = "cBetFlopTotal";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

                statname = "foldVsCbetFlopTotal";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

                statname = "skippedCbetFoldFlopTotal";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

                statname = "raiseToCbetFlopTotal";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

            } finally {
                playerStat.put(i, stats);
                i++;
            }


        }
        return playerStat;
    }

    //Записываем в коллекцию данные каждого игрока для второй строки таблицы
    //Вычисляем значения и в какой диапазон они попадают
    public HashMap<Integer, ArrayList<StatInfo>> hudStatsCalcLine2(ArrayList<Player> players) {
        HashMap<Integer, ArrayList<StatInfo>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {

            ArrayList<StatInfo> stats = new ArrayList<>();
            StatInfo statInfo;
            try {

                String statname = "donkFlop";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

                statname = "checkCallFlop";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

                statname = "checkRaiseFlop";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

                statname = "afqFlop";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);
            } finally {
                playerStat.put(i, stats);
                i++;
            }
        }
        return playerStat;
    }
    public HashMap<Integer, ArrayList<StatInfo>> hudStatsCalcLine3(ArrayList<Player> players) {
        HashMap<Integer, ArrayList<StatInfo>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatInfo> stats = new ArrayList<>();
            StatInfo statInfo;
            try {

                String statname = "betToMissCbetFlopTotal";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

                statname = "wonAfterRaiseFlop";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

                statname = "aggFactorFlop";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

                statname = "totalHands";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

            } finally {
                playerStat.put(i, stats);
                i++;
            }
        }
        return playerStat;
    }




}
