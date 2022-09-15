package com.stsetsevich.smartpoker.engine.hud;


import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.StatValue;
import com.stsetsevich.smartpoker.repos.StatRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class FlopStatsCalc extends StatsCalc {


    //Записываем в коллекцию данные каждого игрока для первой строки таблицы
    //Вычисляем значения и в какой диапазон они попадают
    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine1(ArrayList<Player> players) {
        HashMap<Integer, ArrayList<StatValue>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatValue> stats = new ArrayList<>();
            StatValue statValue;
            try {


                String statname = "cBetFlopTotal";
                statValue = new StatValue(Double.toString(pl.getcBetFlopTotal()), checkDiap(pl.getcBetFlopTotal(),
                        getPoints(statname), Variant.ONE), statname);
                stats.add(statValue);

                statname = "foldVsCbetFlopTotal";
                statValue = new StatValue(Double.toString(pl.getFoldVsCbetFlopTotal()), checkDiap(pl.getFoldVsCbetFlopTotal(),
                        getPoints(statname), Variant.THREE), true, statname, pl);
                stats.add(statValue);

                statname = "skippedCbetFoldFlopTotal";
                statValue = new StatValue(Double.toString(pl.getSkippedCbetFoldFlopTotal()), checkDiap(pl.getSkippedCbetFoldFlopTotal(),
                        getPoints(statname), Variant.THREE), true, statname, pl);
                stats.add(statValue);

                statname = "raiseToCbetFlopTotal";
                statValue = new StatValue(Double.toString(pl.getRaiseToCbetFlopTotal()), checkDiap(pl.getRaiseToCbetFlopTotal(),
                        getPoints(statname), Variant.ONE), statname);
                stats.add(statValue);

            } finally {
                playerStat.put(i, stats);
                i++;
            }


        }
        return playerStat;
    }

    //Записываем в коллекцию данные каждого игрока для второй строки таблицы
    //Вычисляем значения и в какой диапазон они попадают
    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine2(ArrayList<Player> players) {
        HashMap<Integer, ArrayList<StatValue>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {

            ArrayList<StatValue> stats = new ArrayList<>();
            StatValue statValue;
            try {

                String statname = "donkFlop";
                statValue = new StatValue(Double.toString(pl.getDonkFlop()), checkDiap(pl.getDonkFlop(),
                        getPoints(statname), Variant.ONE), statname);
                stats.add(statValue);

                statname = "checkCallFlop";
                statValue = new StatValue(Double.toString(pl.getCheckCallFlop()), checkDiap(pl.getCheckCallFlop(),
                        getPoints(statname), Variant.ONE), statname);
                stats.add(statValue);

                statname = "checkRaiseFlop";
                statValue = new StatValue(Double.toString(pl.getCheckRaiseFlop()), checkDiap(pl.getCheckRaiseFlop(),
                        getPoints(statname), Variant.ONE, pl.getVpip(), "vpip", statRepo), statname);
                stats.add(statValue);

                statname = "afqFlop";
                statValue = new StatValue(Double.toString(pl.getAfqFlop()), checkDiap(pl.getAfqFlop(),
                        getPoints(statname), Variant.ONE), statname);
                stats.add(statValue);
            } finally {
                playerStat.put(i, stats);
                i++;
            }
        }
        return playerStat;
    }
    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine3(ArrayList<Player> players) {
        HashMap<Integer, ArrayList<StatValue>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatValue> stats = new ArrayList<>();
            StatValue statValue;
            try {

                String statname = "betToMissCbetFlopTotal";
                statValue = new StatValue(Double.toString(pl.getBetToMissCbetFlopTotal()), checkDiap(pl.getBetToMissCbetFlopTotal(),
                        getPoints(statname), Variant.ONE), statname);
                stats.add(statValue);

                statname = "wonAfterRaiseFlop";
                statValue = new StatValue(Double.toString(pl.getWonAfterRaiseFlop()), checkDiap(pl.getWonAfterRaiseFlop(),
                        getPoints(statname), Variant.TWO), statname);
                stats.add(statValue);

                statname = "aggFactorFlop";
                statValue = new StatValue(Double.toString(pl.getAggFactorFlop()), checkDiap(pl.getAggFactorFlop(),
                        getPoints(statname), Variant.ONE), statname);
                stats.add(statValue);

                statname = "totalHands";
                statValue = new StatValue(Double.toString(pl.getTotalHands()), checkDiap(pl.getTotalHands(),
                        getPoints(statname), Variant.TWO), statname);
                stats.add(statValue);

            } finally {
                playerStat.put(i, stats);
                i++;
            }
        }
        return playerStat;
    }




}
