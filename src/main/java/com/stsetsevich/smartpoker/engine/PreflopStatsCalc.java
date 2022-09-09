package com.stsetsevich.smartpoker.engine;


import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.domain.Stat;
import com.stsetsevich.smartpoker.repos.StatRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class PreflopStatsCalc extends StatsCalc {


    //Записываем в коллекцию данные каждого игрока для первой строки таблицы
    //Вычисляем значения и в какой диапазон они попадают
    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine1(ArrayList<Player> players, StatRepo statRepo) {
        HashMap<Integer, ArrayList<StatValue>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatValue> stats = new ArrayList<>();
            StatValue statValue;
            try {


                String statname = "vpip";
                statValue = new StatValue(Double.toString(pl.getVpip()), checkDiap(pl.getVpip(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "totalPfr";
                statValue = new StatValue(Double.toString(pl.getTotalPfr()), checkDiap(pl.getTotalPfr(),
                        getPoints(statname, statRepo), Variant.ONE), true, statname, pl.getVpip(), pl);
                stats.add(statValue);

                statname = "total3bet";
                statValue = new StatValue(Double.toString(pl.getTotal3bet()), checkDiap(pl.getTotal3bet(),
                        getPoints(statname, statRepo), Variant.ONE), true, statname, pl);
                stats.add(statValue);

                statname = "foldTo3betTotal";
                statValue = new StatValue(Double.toString(pl.getFoldTo3betTotal()), checkDiap(pl.getFoldTo3betTotal(),
                        getPoints(statname, statRepo), Variant.THREE), statname);
                stats.add(statValue);

            }
            finally {
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

                String statname = "total4bet";
                statValue = new StatValue(Double.toString(pl.getTotal4bet()), checkDiap(pl.getTotal4bet(),
                        getPoints(statname, statRepo), Variant.ONE), true, statname, pl);
                stats.add(statValue);
                statname = "foldTo4betTotal";
                statValue = new StatValue(Double.toString(pl.getFoldTo4betTotal()), checkDiap(pl.getFoldTo4betTotal(),
                        getPoints(statname, statRepo), Variant.THREE), statname);
                stats.add(statValue);
                statname = "total5bet";
                statValue = new StatValue(Double.toString(pl.getTotal5bet()), checkDiap(pl.getTotal5bet(),
                        getPoints(statname, statRepo), Variant.ONE), true, statname, pl);
                stats.add(statValue);
                statname = "foldTo5betTotal";
                statValue = new StatValue(Double.toString(pl.getFoldTo5betTotal()), checkDiap(pl.getFoldTo5betTotal(),
                        getPoints(statname, statRepo), Variant.THREE), statname);
                stats.add(statValue);

            }
            finally {
                playerStat.put(i, stats);
                i++;
            }



        }
        return playerStat;
    }

    //Записываем в коллекцию данные каждого игрока для третьей строки таблицы
    //Вычисляем значения и в какой диапазон они попадают
    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine3(ArrayList<Player> players, StatRepo statRepo) {
        HashMap<Integer, ArrayList<StatValue>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatValue> stats = new ArrayList<>();
            StatValue statValue;
            try {

            String statname = "squeezeTotal";
            statValue = new StatValue(Double.toString(pl.getSqueezeTotal()), checkDiap(pl.getSqueezeTotal(),
                    getPoints(statname, statRepo), Variant.ONE), statname);
            stats.add(statValue);

            statname = "foldToSqueezeTotal";
            statValue = new StatValue(Double.toString(pl.getFoldToSqueezeTotal()), checkDiap(pl.getFoldToSqueezeTotal(),
                    getPoints(statname, statRepo), Variant.ONE), statname);
            stats.add(statValue);

            statname = "wtsd";
            statValue = new StatValue(Double.toString(pl.getWtsd()), checkDiap(pl.getWtsd(),
                    getPoints(statname, statRepo), Variant.ONE), statname);
            stats.add(statValue);

            statname = "wwsf";
            statValue = new StatValue(Double.toString(pl.getWwsf()), checkDiap(pl.getWwsf(),
                    getPoints(statname, statRepo), Variant.ONE), statname);
            stats.add(statValue);
            }
            finally {
                playerStat.put(i, stats);
                i++;
            }
        }
        return playerStat;
    }

    //Записываем в коллекцию данные каждого игрока для четвертой строки таблицы
    //Вычисляем значения и в какой диапазон они попадают
    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine4(ArrayList<Player> players, StatRepo statRepo) {
        HashMap<Integer, ArrayList<StatValue>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatValue> stats = new ArrayList<>();
            StatValue statValue;
            try {

                String statname = "pfrBu";
                statValue = new StatValue(Double.toString(pl.getPfrBu()), checkDiap(pl.getPfrBu(),
                        getPoints(statname, statRepo), Variant.ONE), true, statname, pl);
                stats.add(statValue);
                statname = "foldTo3betIpBu";
                statValue = new StatValue(Double.toString(pl.getFoldTo3betIpBu()), checkDiap(pl.getFoldTo3betIpBu(),
                        getPoints(statname, statRepo), Variant.THREE), statname);
                stats.add(statValue);
                statname = "callOpenraiseBb";
                statValue = new StatValue(Double.toString(pl.getCallOpenraiseBb()), checkDiap(pl.getCallOpenraiseBb(),
                        getPoints(statname, statRepo), Variant.ONE), true, statname, pl);
                stats.add(statValue);
                statname = "bbVsBu3bet";
                statValue = new StatValue(Double.toString(pl.getBbVsBu3bet()), checkDiap(pl.getBbVsBu3bet(),
                        getPoints(statname, statRepo), Variant.ONE),true, statname, pl);
                stats.add(statValue);

            }
            finally {
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

                String statname = "aggFactorFlop";
                statValue = new StatValue(Double.toString(pl.getAggFactorFlop()), checkDiap(pl.getAggFactorFlop(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "aggFactorTurn";
                statValue = new StatValue(Double.toString(pl.getAggFactorTurn()), checkDiap(pl.getAggFactorTurn(),
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

            }
            finally {
                playerStat.put(i, stats);
                i++;
            }
        }
        return playerStat;
    }

    /*
    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine25(ArrayList<Player> players, StatRepo statRepo) {
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

                statname = "cBetTurnTotal";
                statValue = new StatValue(Double.toString(pl.getcBetTurnTotal()), checkDiap(pl.getcBetTurnTotal(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "skippedCbetFoldFlopTotal";
                statValue = new StatValue(Double.toString(pl.getSkippedCbetFoldFlopTotal()), checkDiap(pl.getSkippedCbetFoldFlopTotal(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);

                statname = "skippedCbetFoldTurnTotal";
                statValue = new StatValue(Double.toString(pl.getSkippedCbetFoldTurnTotal()), checkDiap(pl.getSkippedCbetFoldTurnTotal(),
                        getPoints(statname, statRepo), Variant.ONE), statname);
                stats.add(statValue);
            }
            finally {
                playerStat.put(i, stats);
                i++;
            }
        }
        return playerStat;
    }
*/
}
