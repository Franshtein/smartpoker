package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.stream.DoubleStream;

public class TableInfoCalc {
    @Autowired
    StatInfo statInfoMother;
    public ArrayList<StatInfo> extraStatsCalc(ArrayList<Player> players) {
        ArrayList<Player> subPlayers = new ArrayList<>(players);
        subPlayers.removeIf(n -> (n.getNickname().equals("Empty Seat")));
        ArrayList<StatInfo> stats = new ArrayList<>();
       /// StatInfo statInfo = new StatInfo(Double.toString(calcAvgEvBb100(subPlayers)), 0, "avgEvBb100");
       // statInfo = statInfoMother.getStatInfo();
       // statInfo.setInfo(statname, pl);
       // stats.add(statInfo);

        return stats;
    }

    private static double calcAvgEvBb100(ArrayList<Player> players) {

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
