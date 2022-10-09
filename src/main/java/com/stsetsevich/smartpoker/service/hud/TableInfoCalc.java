package com.stsetsevich.smartpoker.service.hud;

import com.stsetsevich.smartpoker.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

/**
 * Рассчитывает цвет HUD игрока.
 * Рассчитывает общую информацию о столе для {@link com.stsetsevich.smartpoker.controller.HudController}
 * Она выводится в правом верхнем углу
 */
@Service
public class TableInfoCalc {
    @Autowired
    StatInfo statInfoMother;
    @Autowired
    StatInfoService statInfoService;

    //Рассчитывает общую информацию о столе. Она выводится в правом верхнем углу hud.html
    public List<TableInfo> generalStatsCalc(List<Player> players) {
        List<Player> subPlayers = new ArrayList<>(players);
        subPlayers.removeIf(n -> (n.getNickname().equals("Empty Seat") || n.getTotalHands()<3000));
        List<TableInfo> stats = new ArrayList<>();
        TableInfo tableInfo = new TableInfo("avgBb100", calcAvgEvBb100(subPlayers));

        stats.add(tableInfo);

        return stats;
    }

    private static double calcAvgEvBb100(List<Player> players) {

        try {
         return  players.stream().flatMapToDouble(player -> DoubleStream.of(player.getAvgBb100())).average().orElse(0);
        }
        catch (Exception exception)
        {
            return 0;
        }
    }
    //Рассчитывает цвет HUD игрока.
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
