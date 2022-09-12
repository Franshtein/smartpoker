package com.stsetsevich.smartpoker.engine.hud;


import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.StatValue;
import com.stsetsevich.smartpoker.repos.StatRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class TurnStatsCalc extends StatsCalc {


    //Записываем в коллекцию данные каждого игрока для первой строки таблицы
    //Вычисляем значения и в какой диапазон они попадают
    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine1(ArrayList<Player> players, StatRepo statRepo) {
        HashMap<Integer, ArrayList<StatValue>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatValue> stats = new ArrayList<>();
            StatValue statValue;
            try {


                String statname = "cBetTurnTotal";
                statValue = new StatValue(Double.toString(pl.getcBetTurnTotal()), checkDiap(pl.getcBetTurnTotal(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "foldVsCbetTurnTotal";
                statValue = new StatValue(Double.toString(pl.getFoldVsCbetTurnTotal()), checkDiap(pl.getFoldVsCbetTurnTotal(),
                        getPoints(statname, statRepo), Variant.THREE), true, statname, pl);
                stats.add(statValue);

                statname = "skippedCbetFoldTurnTotal";
                statValue = new StatValue(Double.toString(pl.getSkippedCbetFoldTurnTotal()), checkDiap(pl.getSkippedCbetFoldTurnTotal(),
                        getPoints(statname, statRepo), Variant.THREE), true, statname, pl);
                stats.add(statValue);

                statname = "raiseToCbetTurnTotal";
                statValue = new StatValue(Double.toString(pl.getRaiseToCbetTurnTotal()), checkDiap(pl.getRaiseToCbetTurnTotal(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
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
    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine2(ArrayList<Player> players, StatRepo statRepo) {
        HashMap<Integer, ArrayList<StatValue>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {

            ArrayList<StatValue> stats = new ArrayList<>();
            StatValue statValue;
            try {

                String statname = "donkTurn";
                statValue = new StatValue(Double.toString(pl.getDonkTurn()), checkDiap(pl.getDonkTurn(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "checkCallTurn";
                statValue = new StatValue(Double.toString(pl.getCheckCallTurn()), checkDiap(pl.getCheckCallTurn(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "checkRaiseTurn";
                statValue = new StatValue(Double.toString(pl.getCheckRaiseTurn()), checkDiap(pl.getCheckRaiseTurn(),
                        getPoints(statname, statRepo), Variant.ONE, pl.getVpip(), "vpip", statRepo), statname);
                stats.add(statValue);

                statname = "afqTurn";
                statValue = new StatValue(Double.toString(pl.getAfqTurn()), checkDiap(pl.getAfqTurn(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);
            } finally {
                playerStat.put(i, stats);
                i++;
            }
        }
        return playerStat;
    }
    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine3(ArrayList<Player> players, StatRepo statRepo) {
        HashMap<Integer, ArrayList<StatValue>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatValue> stats = new ArrayList<>();
            StatValue statValue;
            try {

                String statname = "betToMissCbetTurnTotal";
                statValue = new StatValue(Double.toString(pl.getBetToMissCbetTurnTotal()), checkDiap(pl.getBetToMissCbetTurnTotal(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "wonAfterRaiseTurn";
                statValue = new StatValue(Double.toString(pl.getWonAfterRaiseTurn()), checkDiap(pl.getWonAfterRaiseTurn(),
                        getPoints(statname, statRepo), Variant.TWO), statname);
                stats.add(statValue);

                statname = "aggFactorTurn";
                statValue = new StatValue(Double.toString(pl.getAggFactorTurn()), checkDiap(pl.getAggFactorTurn(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "totalHands";
                statValue = new StatValue(Double.toString(pl.getTotalHands()), checkDiap(pl.getTotalHands(),
                        getPoints(statname, statRepo), Variant.TWO), statname);
                stats.add(statValue);

            } finally {
                playerStat.put(i, stats);
                i++;
            }
        }
        return playerStat;
    }



}
