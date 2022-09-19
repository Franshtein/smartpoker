package com.stsetsevich.smartpoker.engine.hud;


import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.StatInfo;
import com.stsetsevich.smartpoker.repos.StatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class PreflopStatsCalc extends StatsCalc {


    //Записываем в коллекцию данные каждого игрока для первой строки таблицы
    //Вычисляем значения и в какой диапазон они попадают
    @Autowired
    StatRepo statRepo;
    @Autowired
    StatInfo statInfoMother;

    public HashMap<Integer, ArrayList<StatInfo>> hudStatsCalcLine1(ArrayList<Player> players) {
        HashMap<Integer, ArrayList<StatInfo>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatInfo> stats = new ArrayList<>();
            StatInfo statInfo;

            try {


                String statname = "vpip";

                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

                statname = "total_pfr";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl, needHref);
                stats.add(statInfo);

                statname = "total3bet";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl, needHref);
                stats.add(statInfo);

                statname = "fold_to3bet_total";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl, needHref);
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

                String statname = "total4bet";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl, needHref);
                stats.add(statInfo);
                statname = "foldTo4betTotal";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);
                statname = "total5bet";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl, needHref);
                stats.add(statInfo);
                statname = "foldTo5betTotal";
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

    //Записываем в коллекцию данные каждого игрока для третьей строки таблицы
    //Вычисляем значения и в какой диапазон они попадают
    public HashMap<Integer, ArrayList<StatInfo>> hudStatsCalcLine3(ArrayList<Player> players) {
        HashMap<Integer, ArrayList<StatInfo>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatInfo> stats = new ArrayList<>();
            StatInfo statInfo;
            try {

                String statname = "squeezeTotal";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);
/*
            statname = "foldToSqueezeTotal";
            statValue = new StatValue(Double.toString(pl.getFoldToSqueezeTotal()), checkDiap(pl.getFoldToSqueezeTotal(),
                    getPoints(statname, statRepo), Variant.ONE), statname);
            stats.add(statValue);
*/
                statname = "wtsd";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

                statname = "w$sd";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

                statname = "wwsf";
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

    //Записываем в коллекцию данные каждого игрока для четвертой строки таблицы
    //Вычисляем значения и в какой диапазон они попадают
    @Override
    public HashMap<Integer, ArrayList<StatInfo>> hudStatsCalcLine4(ArrayList<Player> players) {
        HashMap<Integer, ArrayList<StatInfo>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatInfo> stats = new ArrayList<>();
            StatInfo statInfo;
            try {

                String statname = "pfrBu";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);
                statname = "foldTo3betIpBu";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);
                statname = "callOpenraiseBb";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);
                statname = "bbVsBu3bet";
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

    @Override
    public HashMap<Integer, ArrayList<StatInfo>> hudStatsCalcLine5(ArrayList<Player> players) {
        HashMap<Integer, ArrayList<StatInfo>> playerStat = new HashMap<>();
        int i = 0;
        for (Player pl : players) {
            ArrayList<StatInfo> stats = new ArrayList<>();
            StatInfo statInfo;
            try {

                String statname = "aggFactorFlop";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

                statname = "aggFactorTurn";
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statname, pl);
                stats.add(statInfo);

                statname = "aggFactorRiver";
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
