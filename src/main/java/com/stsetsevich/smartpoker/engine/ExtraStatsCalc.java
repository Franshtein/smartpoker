package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.repos.StatRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ExtraStatsCalc extends StatsCalc {
    //По инстансу игрока и id стата вычисляем, какими статами нужно заполнить таблицу и заполняем её.
    public static ArrayList<StatValue> extraStatsCalc(Player player, String statName, StatRepo statRepo) {
        ArrayList<StatValue> stats = new ArrayList<>();
        switch (statName) {
            case ("totalPfr"):
                getPfrStats(player, stats, statRepo);
                return stats;
            case ("total3bet"):
                getThreeBetStats(player, stats, statRepo);
                return stats;
            case ("2"):
                System.out.println("not method exsist");
                break;
            case ("3"):
                System.out.println("not method exsist");
                break;

        }

        return null;
    }

    private static ArrayList<StatValue> getThreeBetStats(Player pl, ArrayList<StatValue> stats, StatRepo statRepo) {
        StatValue statValue;
        String statname = "total3bet";
        statValue = new StatValue(Double.toString(pl.getTotal3bet()), checkDiap(pl.getTotal3bet(),
                getPoints(statname, statRepo), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total3betEp";
        statValue = new StatValue(Double.toString(pl.getTotal3betEp()), checkDiap(pl.getTotal3betEp(),
                getPoints(statname, statRepo), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total3betMp";
        statValue = new StatValue(Double.toString(pl.getTotal3betMp()), checkDiap(pl.getTotal3betMp(),
                getPoints(statname, statRepo), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total3betCo";
        statValue = new StatValue(Double.toString(pl.getTotal3betCo()), checkDiap(pl.getTotal3betCo(),
                getPoints(statname, statRepo), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total3betBu";
        statValue = new StatValue(Double.toString(pl.getTotal3betBu()), checkDiap(pl.getTotal3betBu(),
                getPoints(statname, statRepo), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total3betSb";
        statValue = new StatValue(Double.toString(pl.getTotal3betSb()), checkDiap(pl.getTotal3betSb(),
                getPoints(statname, statRepo), Variant.ONE), statname);
        stats.add(statValue);

        statname = "total3betBb";
        statValue = new StatValue(Double.toString(pl.getTotal3betBb()), checkDiap(pl.getTotal3betBb(),
                getPoints(statname, statRepo), Variant.ONE), statname);
        stats.add(statValue);

        return stats;
    }
    private static ArrayList<StatValue> getPfrStats(Player pl, ArrayList<StatValue> stats, StatRepo statRepo) {
        StatValue statValue;
        String statname = "totalPfr";
        statValue = new StatValue(Double.toString(pl.getTotalPfr()), checkDiap(pl.getTotalPfr(),
                getPoints(statname, statRepo), Variant.ONE), statname);
        stats.add(statValue);

        statname = "pfrEp";
        statValue = new StatValue(Double.toString(pl.getPfrEp()), checkDiap(pl.getPfrEp(),
                getPoints(statname, statRepo), Variant.ONE), statname);
        stats.add(statValue);

        statname = "pfrMp";
        statValue = new StatValue(Double.toString(pl.getPfrMp()), checkDiap(pl.getPfrMp(),
                getPoints(statname, statRepo), Variant.ONE), statname);
        stats.add(statValue);

        statname = "pfrCo";
        statValue = new StatValue(Double.toString(pl.getPfrCo()), checkDiap(pl.getPfrCo(),
                getPoints(statname, statRepo), Variant.ONE), statname);
        stats.add(statValue);

        statname = "pfrBu";
        statValue = new StatValue(Double.toString(pl.getPfrBu()), checkDiap(pl.getPfrBu(),
                getPoints(statname, statRepo), Variant.ONE), statname);
        stats.add(statValue);

        statname = "pfrSb";
        statValue = new StatValue(Double.toString(pl.getPfrSb()), checkDiap(pl.getPfrSb(),
                getPoints(statname, statRepo), Variant.ONE), statname);
        stats.add(statValue);

        return stats;
    }

}