package com.stsetsevich.smartpoker.service.hud;

import com.stsetsevich.smartpoker.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Класс, который заполняет для HUD таблицу с дополнительной статистикой.
 * {@link com.stsetsevich.smartpoker.controller.HudController}
 */
@Service
public class ExtraStatsCalc{
    @Autowired
    private StatInfo statInfoMother;

    //по игроку и id стата вычисляет, какими статами нужно заполнить таблицу и вызывает нужный метод для заполнения.
    //возвращает заполненную таблицу с доп. статами.
    public ArrayList<StatInfo> extraStatsCalc(Player player, String statName) {
        ArrayList<StatInfo> stats = new ArrayList<>();
        if (statName != null) {
            switch (statName) {
                case ("total_pfr"):
                    getPfrStats(player, stats);
                    return stats;
                case ("total3bet"):
                    System.out.println("3bet work");
                    getThreeBetStats(player, stats);
                    return stats;
                case ("total4bet"):
                    System.out.println("4bet work");
                    getFourBetStats(player, stats);
                    return stats;
                case ("3"):
                    System.out.println("method not exsist");
                    break;

            }
        }
        return null;
    }

    private ArrayList<StatInfo> getThreeBetStats(Player pl, ArrayList<StatInfo> stats) {
        StatInfo statInfo;
        String statname = "total3bet";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "total3bet_ep";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "total3bet_mp";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "total3bet_co";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "total3bet_bu";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "total3bet_sb";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "total3bet_bb";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        ;
        stats.add(statInfo);

        return stats;
    }

    private ArrayList<StatInfo> getFourBetStats(Player pl, ArrayList<StatInfo> stats) {
        StatInfo statInfo;
        String statname = "total4bet";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "total4betEp";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "total4betMp";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "total4betCo";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "total4betBu";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "total4betSb";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "total4betBb";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        return stats;
    }

    private ArrayList<StatInfo> getPfrStats(Player pl, ArrayList<StatInfo> stats) {
        StatInfo statInfo;
        String statname = "total_pfr";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "pfr_ep";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "pfr_mp";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "pfr_co";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "pfr_bu";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        statname = "pfr_sb";
        statInfo = statInfoMother.getStatInfo();
        statInfo.setInfo(statname, pl);
        stats.add(statInfo);

        return stats;
    }

}
