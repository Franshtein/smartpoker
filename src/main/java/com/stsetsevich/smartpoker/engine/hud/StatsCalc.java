package com.stsetsevich.smartpoker.engine.hud;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.StatValue;
import com.stsetsevich.smartpoker.repos.StatRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;

public class StatsCalc{
    @Autowired
    StatRepo statRepo;
    protected enum Variant {
        ONE, TWO, THREE;
    }
    //Проверка, в какой диапазон попадает значение стата игрока, чтобы определить каким цветом его закрашивать
    //Три разных варианта, в зависимости от типа стата, т.к. некоторые нужно инвертировать и т.п.
    protected static int checkDiap(double stat, double points[], Variant variant) {
        if (variant == Variant.ONE) {
            if (stat <= points[0]) return 0;
            if (stat <= points[1]) return 1;
            if (stat <= points[2]) return 2;
            if (stat <= points[3]) return 3;
            return 4;
        }
        if (variant == Variant.TWO) {
            if (stat <= points[0]) return 0;
            if (stat <= points[1]) return 1;
            if (stat <= points[2]) return 3;
            if (stat <= points[3]) return 2;
            return 2;
        }
        if (variant == Variant.THREE) {
            if (stat >= points[3]) return 0;
            if (stat >= points[2]) return 1;
            if (stat >= points[1]) return 2;
            if (stat >= points[0]) return 3;
            return 4;
        }
        return 0;
    }
    protected int checkDiap(double stat, double points[], Variant variant, double primaryStat, String statName, StatRepo statRepo) {
        int primaryValue = checkDiap(primaryStat, getPoints(statName), Variant.ONE);
        double weight=1;
        if(primaryValue==4) weight=1.4;
        if(primaryValue==3) weight=1.2;
        if(primaryValue==1) weight=0.8;
        if(primaryValue==0) weight=0.6;
        if (variant == Variant.ONE) {
            if (stat <= points[0] * weight) return 0;
            if (stat <= points[1] * weight) return 1;
            if (stat <= points[2] * weight) return 2;
            if (stat <= points[3] * weight) return 3;
            return 4;
        }
        if (variant == Variant.TWO) {
            if (stat <= points[0] * weight) return 0;
            if (stat <= points[1] * weight) return 1;
            if (stat <= points[2] * weight) return 3;
            if (stat <= points[3] * weight) return 2;
            return 2;
        }
        if (variant == Variant.THREE) {
            if (stat >= points[3] * weight) return 0;
            if (stat >= points[2] * weight) return 1;
            if (stat >= points[1] * weight) return 2;
            if (stat >= points[0] * weight) return 3;
            return 4;
        }
        return 0;
    }

    //Получаем массив точек для будущей проверки, в какой диапазон попадает значение стата игрока.
    protected  double[] getPoints(String statname) {
        double[] points = new double[4];
        points[0] = statRepo.findStatByStatname(statname).getPoint1();
        points[1] = statRepo.findStatByStatname(statname).getPoint2();
        points[2] = statRepo.findStatByStatname(statname).getPoint3();
        points[3] = statRepo.findStatByStatname(statname).getPoint4();
        return points;
    }

    //Выясняем, каким цветом будем закрашивать таблицы с данными об игроках.
    public ArrayList<String> hudSeatsColor(ArrayList<Player> players) {
        ArrayList<String> seats = new ArrayList<>();
        String seat;
        for (Player pl : players) {
            if (!pl.getNickname().equals("Empty Seat")) {
                int i = checkDiap(pl.getAvgBb100(), getPoints("avgBb100"), Variant.ONE);
                seat = "table-striped-red";
                if (i == 0) seat = "table-striped-pink";
                if (i == 1) seat = "table-striped-blue";
                if (i == 2) seat = "table-striped-green";
                if (i == 3) seat = "table-striped-yellow";
            }
            else seat = "table-striped-empty";
            seats.add(seat);
        }
        return seats;
    }

    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine4(ArrayList<Player> players) {
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
    public HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine5(ArrayList<Player> players) {
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

}
