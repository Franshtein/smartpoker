package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.hud.StatsCalc;
import com.stsetsevich.smartpoker.repos.StatRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ExtraStatsCalc extends StatsCalc {

    //По инстансу игрока и id стата вычисляем, какими статами нужно заполнить таблицу и заполняем её.
    public ArrayList<StatValue> extraStatsCalc(Player player, String statName) {
        ArrayList<StatValue> stats = new ArrayList<>();
        if (statName != null) {
            switch (statName) {
                case ("totalPfr"):
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
                    System.out.println("not method exsist");
                    break;

            }
        }
        return null;
    }

    private ArrayList<StatValue> getThreeBetStats(Player pl, ArrayList<StatValue> stats) {
        StatValue statValue;
        String statname = "total3bet";
        statValue = new StatValue(Double.toString(pl.getTotal3bet()), checkDiap(pl.getTotal3bet(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total3betEp";
        statValue = new StatValue(Double.toString(pl.getTotal3betEp()), checkDiap(pl.getTotal3betEp(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total3betMp";
        statValue = new StatValue(Double.toString(pl.getTotal3betMp()), checkDiap(pl.getTotal3betMp(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total3betCo";
        statValue = new StatValue(Double.toString(pl.getTotal3betCo()), checkDiap(pl.getTotal3betCo(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total3betBu";
        statValue = new StatValue(Double.toString(pl.getTotal3betBu()), checkDiap(pl.getTotal3betBu(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total3betSb";
        statValue = new StatValue(Double.toString(pl.getTotal3betSb()), checkDiap(pl.getTotal3betSb(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total3betBb";
        statValue = new StatValue(Double.toString(pl.getTotal3betBb()), checkDiap(pl.getTotal3betBb(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        return stats;
    }

    private ArrayList<StatValue> getFourBetStats(Player pl, ArrayList<StatValue> stats) {
        StatValue statValue;
        String statname = "total4bet";
        statValue = new StatValue(Double.toString(pl.getTotal4bet()), checkDiap(pl.getTotal4bet(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total4betEp";
        statValue = new StatValue(Double.toString(pl.getTotal4betEp()), checkDiap(pl.getTotal4betEp(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total4betMp";
        statValue = new StatValue(Double.toString(pl.getTotal4betMp()), checkDiap(pl.getTotal4betMp(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total4betCo";
        statValue = new StatValue(Double.toString(pl.getTotal4betCo()), checkDiap(pl.getTotal4betCo(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total4betBu";
        statValue = new StatValue(Double.toString(pl.getTotal4betBu()), checkDiap(pl.getTotal4betBu(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total4betSb";
        statValue = new StatValue(Double.toString(pl.getTotal4betSb()), checkDiap(pl.getTotal4betSb(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total4betBb";
        statValue = new StatValue(Double.toString(pl.getTotal4betBb()), checkDiap(pl.getTotal4betBb(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        return stats;
    }

    private ArrayList<StatValue> getPfrStats(Player pl, ArrayList<StatValue> stats) {
        StatValue statValue;
        String statname = "totalPfr";
        statValue = new StatValue(Double.toString(pl.getTotalPfr()), checkDiap(pl.getTotalPfr(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "pfrEp";
        statValue = new StatValue(Double.toString(pl.getPfrEp()), checkDiap(pl.getPfrEp(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "pfrMp";
        statValue = new StatValue(Double.toString(pl.getPfrMp()), checkDiap(pl.getPfrMp(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "pfrCo";
        statValue = new StatValue(Double.toString(pl.getPfrCo()), checkDiap(pl.getPfrCo(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "pfrBu";
        statValue = new StatValue(Double.toString(pl.getPfrBu()), checkDiap(pl.getPfrBu(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        statname = "pfrSb";
        statValue = new StatValue(Double.toString(pl.getPfrSb()), checkDiap(pl.getPfrSb(),
                getPoints(statname), Variant.ONE), statname);
        stats.add(statValue);

        return stats;
    }

}
