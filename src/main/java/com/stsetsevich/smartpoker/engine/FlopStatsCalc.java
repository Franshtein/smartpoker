package com.stsetsevich.smartpoker.engine;


import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.repos.StatRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class FlopStatsCalc extends StatsCalc {


    //Записываем в коллекцию данные каждого игрока для первой строки таблицы
    //Вычисляем значения и в какой диапазон они попадают
    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine1(ArrayList<Player> players, StatRepo statRepo) {
        HashMap<Integer, ArrayList<StatValue>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatValue> stats = new ArrayList<>();
            StatValue statValue;
            try {


                String statname = "cBetFlopTotal";
                statValue = new StatValue(Double.toString(pl.getcBetFlopTotal()), checkDiap(pl.getcBetFlopTotal(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "foldVsCbetFlopTotal";
                statValue = new StatValue(Double.toString(pl.getFoldVsCbetFlopTotal()), checkDiap(pl.getTotalPfr(),
                        getPoints(statname, statRepo), Variant.ONE), true, statname, pl);
                stats.add(statValue);

                statname = "skippedCbetFoldFlopTotal";
                statValue = new StatValue(Double.toString(pl.getSkippedCbetFoldFlopTotal()), checkDiap(pl.getSkippedCbetFoldFlopTotal(),
                        getPoints(statname, statRepo), Variant.ONE), true, statname, pl);
                stats.add(statValue);

                statname = "raiseToCbetFlopTotal";
                statValue = new StatValue(Double.toString(pl.getRaiseToCbetFlopTotal()), checkDiap(pl.getRaiseToCbetFlopTotal(),
                        getPoints(statname, statRepo), Variant.THREE), statname);
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

                String statname = "donkFlop";
                statValue = new StatValue(Double.toString(pl.getDonkFlop()), checkDiap(pl.getDonkFlop(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "checkCallFlop";
                statValue = new StatValue(Double.toString(pl.getCheckCallFlop()), checkDiap(pl.getCheckCallFlop(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "checkRaiseFlop";
                statValue = new StatValue(Double.toString(pl.getCheckRaiseFlop()), checkDiap(pl.getCheckRaiseFlop(),
                        getPoints(statname, statRepo), Variant.ONE, pl.getVpip(), "vpip", statRepo), statname);
                stats.add(statValue);

                statname = "afqFlop";
                statValue = new StatValue(Double.toString(pl.getaAfqFlop()), checkDiap(pl.getAfqFlop(),
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

                String statname = "betToMissCbetFlopTotal";
                statValue = new StatValue(Double.toString(pl.getBetToMissCbetFlopTotal()), checkDiap(pl.getBetToMissCbetFlopTotal(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "wonAfterRaiseFlop";
                statValue = new StatValue(Double.toString(pl.getWonAfterRaiseFlop()), checkDiap(pl.getWonAfterRaiseFlop(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
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
