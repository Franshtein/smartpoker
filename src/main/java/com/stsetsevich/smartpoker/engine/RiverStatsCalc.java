package com.stsetsevich.smartpoker.engine;


import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.repos.StatRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class RiverStatsCalc extends StatsCalc {


    //Записываем в коллекцию данные каждого игрока для первой строки таблицы
    //Вычисляем значения и в какой диапазон они попадают
    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine1(ArrayList<Player> players, StatRepo statRepo) {
        HashMap<Integer, ArrayList<StatValue>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatValue> stats = new ArrayList<>();
            StatValue statValue;
            try {


                String statname = "cBetRiverTotal";
                statValue = new StatValue(Double.toString(pl.getcBetRiverTotal()), checkDiap(pl.getcBetRiverTotal(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "foldVsCbetRiverTotal";
                statValue = new StatValue(Double.toString(pl.getFoldVsCbetRiverTotal()), checkDiap(pl.getFoldVsCbetRiverTotal(),
                        getPoints(statname, statRepo), Variant.THREE), true, statname, pl);
                stats.add(statValue);

                statname = "skippedCbetFoldRiverTotal";
                statValue = new StatValue(Double.toString(pl.getSkippedCbetFoldRiverTotal()), checkDiap(pl.getSkippedCbetFoldRiverTotal(),
                        getPoints(statname, statRepo), Variant.THREE), true, statname, pl);
                stats.add(statValue);

                statname = "raiseToCbetRiverTotal";
                statValue = new StatValue(Double.toString(pl.getRaiseToCbetRiverTotal()), checkDiap(pl.getRaiseToCbetRiverTotal(),
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

                String statname = "donkRiver";
                statValue = new StatValue(Double.toString(pl.getDonkRiver()), checkDiap(pl.getDonkRiver(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "checkCallTurn";
                statValue = new StatValue(Double.toString(pl.getCheckCallRiver()), checkDiap(pl.getCheckCallRiver(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "checkRaiseRiver";
                statValue = new StatValue(Double.toString(pl.getCheckRaiseRiver()), checkDiap(pl.getCheckRaiseRiver(),
                        getPoints(statname, statRepo), Variant.ONE, pl.getVpip(), "vpip", statRepo), statname);
                stats.add(statValue);

                statname = "afqRiver";
                statValue = new StatValue(Double.toString(pl.getAfqRiver()), checkDiap(pl.getAfqRiver(),
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

                String statname = "betToMissCbetRiverTotal";
                statValue = new StatValue(Double.toString(pl.getBetToMissCbetRiverTotal()), checkDiap(pl.getBetToMissCbetRiverTotal(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "wonAfterRaiseRiver";
                statValue = new StatValue(Double.toString(pl.getWonAfterRaiseRiver()), checkDiap(pl.getWonAfterRaiseRiver(),
                        getPoints(statname, statRepo), Variant.TWO), statname);
                stats.add(statValue);

                statname = "aggFactorRiver";
                statValue = new StatValue(Double.toString(pl.getAggFactorRiver()), checkDiap(pl.getAggFactorRiver(),
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
    //Записываем в коллекцию данные каждого игрока для третьей строки таблицы
    //Вычисляем значения и в какой диапазон они попадают

    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine4(ArrayList<Player> players, StatRepo statRepo) {
        HashMap<Integer, ArrayList<StatValue>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatValue> stats = new ArrayList<>();
            StatValue statValue;
            try {

                String statname = "";
                statValue = new StatValue("-", 1, statname);
                stats.add(statValue);

            } finally {
                playerStat.put(i, stats);
                i++;
            }
        }
        return playerStat;
    }
    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine5(ArrayList<Player> players, StatRepo statRepo) {
        HashMap<Integer, ArrayList<StatValue>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatValue> stats = new ArrayList<>();
            StatValue statValue;
            try {

                String statname = "";
                statValue = new StatValue("-", 1, statname);
                stats.add(statValue);

            } finally {
                playerStat.put(i, stats);
                i++;
            }
        }
        return playerStat;
    }
    //Записываем в коллекцию данные каждого игрока для четвертой строки таблицы
    //Вычисляем значения и в какой диапазон они попадают



}
