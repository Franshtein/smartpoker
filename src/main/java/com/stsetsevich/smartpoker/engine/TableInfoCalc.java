package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

@Component
public class TableInfoCalc {
    @Autowired
    StatInfo statInfoMother;
    @Autowired
    StatInfoService statInfoService;
    public List<TableInfo> generalStatsCalc(List<Player> players) {
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

    public List<String> hudSeatsColor(List<Player> players) {
        List<String> seats = new ArrayList<>();
        String seat;
        for (Player pl : players) {
            if (!pl.getNickname().equals("Empty Seat")) {
                int i = statInfoService.checkPrimaryDiap("avg_bb100", pl);
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

}
