package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

public class TableInfoCalc {
    @Autowired
    StatInfo statInfoMother;
    public List<TableInfo> extraStatsCalc(List<Player> players) {
        List<Player> subPlayers = new ArrayList<>(players);
        subPlayers.removeIf(n -> (n.getNickname().equals("Empty Seat") || n.getTotalHands()<3000));
        List<TableInfo> stats = new ArrayList<>();
        TableInfo tableInfo = new TableInfo("avgBb100", calcAvgEvBb100(subPlayers));

        stats.add(tableInfo);

        return stats;
    }

    private static double calcAvgEvBb100(List<Player> players) {

     /*  double avg=0;
        for (Player player : players) {
            avg += player.getAvgBb100();
        }
        return avg/players.size(); */
        try {
         return  players.stream().flatMapToDouble(player -> DoubleStream.of(player.getAvgBb100())).average().orElse(0);
        }
        catch (Exception exception)
        {
            return 0;
        }
    }

}
