package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;

import java.util.ArrayList;
import java.util.stream.DoubleStream;

public class TableInfoCalc {

    public static ArrayList<StatValue> extraStatsCalc(ArrayList <Player> players) {
        ArrayList<StatValue> stats = new ArrayList<>();
        StatValue statValue= new StatValue(Double.toString(calcAvgEvBb100(players)), 0);
        stats.add(statValue);

        return stats;
    }

    private static double calcAvgEvBb100 (ArrayList<Player> players){

     /*  double avg=0;
        for (Player player : players) {
            avg += player.getAvgBb100();
        }
        return avg/players.size(); */
        return players.stream().flatMapToDouble(player -> DoubleStream.of(player.getAvgBb100())).average().orElse(0);
    }

}
