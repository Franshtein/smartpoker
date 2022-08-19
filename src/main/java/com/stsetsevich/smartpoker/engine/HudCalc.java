package com.stsetsevich.smartpoker.engine;


import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import com.stsetsevich.smartpoker.repos.StatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HudCalc {

    public static ArrayList<String> hudSeatsColor(ArrayList<Player> players, StatRepo statRepo)
    {
        ArrayList<String> seats = new ArrayList<>();
        String seat;
        for(Player pl : players)
        {
            int i=checkDiap(pl.getAvgBb100(), setPoints("avgBb100", statRepo), true);
            seat="table-striped-green";
            if(i==0) seat = "table-striped-blue";
            if(i==1) seat = "table-striped-yellow";
            if(i==2) seat = "table-striped-red";
            if(i==3) seat = "table-striped-pink";
            seats.add(seat);
        }
        return seats;
    }

    public static HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine1(ArrayList<Player> players, StatRepo statRepo)
    {
        HashMap<Integer, ArrayList<StatValue>> playerStat= new HashMap<>();
        int i=0;
        for (Player pl : players) {
            ArrayList<StatValue> stats = new ArrayList<>();
            StatValue statValue;
            String statname = "vpip";
            statValue = new StatValue(Double.toString(pl.getVpip()), checkDiap(pl.getVpip(),
                    setPoints(statname, statRepo), false));
            stats.add(statValue);

            statname = "totalPfr";
            statValue = new StatValue(Double.toString(pl.getTotalPfr()), checkDiap(pl.getTotalPfr(),
                    setPoints(statname, statRepo), false));
            stats.add(statValue);

            statname = "total3bet";
            statValue = new StatValue(Double.toString(pl.getTotal3bet()), checkDiap(pl.getTotal3bet(),
                    setPoints(statname, statRepo), false));
            stats.add(statValue);

            statname = "foldTo3betTotal";
            statValue = new StatValue(Double.toString(pl.getFoldTo3betTotal()), checkDiap(pl.getFoldTo3betTotal(),
                    setPoints(statname, statRepo), false));
            stats.add(statValue);
            playerStat.put(i, stats);
            i++;
        }
        return playerStat;
    }
    public static HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine2(ArrayList<Player> players, StatRepo statRepo)
    {
        HashMap<Integer, ArrayList<StatValue>> playerStat= new HashMap<>();
        int i=0;
        for (Player pl : players) {
            ArrayList<StatValue> stats = new ArrayList<>();
            StatValue statValue;
            String statname = "cBetFlopTotal";
            statValue = new StatValue(Double.toString(pl.getcBetFlopTotal()), checkDiap(pl.getcBetFlopTotal(),
                    setPoints(statname, statRepo), false));
            stats.add(statValue);

            statname = "cBetTurnTotal";
            statValue = new StatValue(Double.toString(pl.getcBetTurnTotal()), checkDiap(pl.getcBetTurnTotal(),
                    setPoints(statname, statRepo), false));
            stats.add(statValue);

            statname = "skippedCbetFoldFlopTotal";
            statValue = new StatValue(Double.toString(pl.getSkippedCbetFoldFlopTotal()), checkDiap(pl.getSkippedCbetFoldFlopTotal(),
                    setPoints(statname, statRepo), false));
            stats.add(statValue);

            statname = "skippedCbetFoldTurnTotal";
            statValue = new StatValue(Double.toString(pl.getSkippedCbetFoldTurnTotal()), checkDiap(pl.getSkippedCbetFoldTurnTotal(),
                    setPoints(statname, statRepo), false));
            stats.add(statValue);
            playerStat.put(i, stats);
            i++;
        }
        return playerStat;
    }
    public static  HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine3(ArrayList<Player> players, StatRepo statRepo)
    {
        HashMap<Integer, ArrayList<StatValue>> playerStat= new HashMap<>();
        int i=0;
        for (Player pl : players) {
        ArrayList <StatValue> stats = new ArrayList<>();
        StatValue statValue;
        String statname="squeezeTotal";
        statValue=new StatValue(Double.toString(pl.getSqueezeTotal()), checkDiap(pl.getSqueezeTotal(),
                setPoints(statname, statRepo), false));
        stats.add(statValue);

        statname="foldToSqueezeTotal";
        statValue=new StatValue(Double.toString(pl.getFoldToSqueezeTotal()), checkDiap(pl.getFoldToSqueezeTotal(),
                setPoints(statname, statRepo), false));
        stats.add(statValue);

        statname="wtsd";
        statValue=new StatValue(Double.toString(pl.getWtsd()), checkDiap(pl.getWtsd(),
                setPoints(statname, statRepo), false));
        stats.add(statValue);

        statname="wwsf";
        statValue=new StatValue(Double.toString(pl.getWwsf()), checkDiap(pl.getWwsf(),
                setPoints(statname, statRepo), false));
        stats.add(statValue);
            playerStat.put(i, stats);
            i++;
        }
        return playerStat;
    }

    public static  HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine4(ArrayList<Player> players, StatRepo statRepo)
    {
        HashMap<Integer, ArrayList<StatValue>> playerStat= new HashMap<>();
        int i=0;
        for (Player pl : players) {
        ArrayList <StatValue> stats = new ArrayList<>();
        StatValue statValue;
        String statname="aggFactorFlop";
        statValue=new StatValue(Double.toString(pl.getAggFactorFlop()), checkDiap(pl.getAggFactorFlop(),
                setPoints(statname, statRepo), false));
        stats.add(statValue);

        statname="aggFactorTurn";
        statValue=new StatValue(Double.toString(pl.getAggFactorTurn()), checkDiap(pl.getAggFactorTurn(),
                setPoints(statname, statRepo), false));
        stats.add(statValue);

        statname="aggFactorRiver";
        statValue=new StatValue(Double.toString(pl.getAggFactorRiver()), checkDiap(pl.getAggFactorRiver(),
                setPoints(statname, statRepo), false));
        stats.add(statValue);

        statname="totalHands";
        statValue=new StatValue(Double.toString(pl.getTotalHands()), checkDiap(pl.getTotalHands(),
                setPoints(statname, statRepo), true));
        stats.add(statValue);
            playerStat.put(i, stats);
            i++;
        }
        return playerStat;
    }
    public static ArrayList<StatValue> extraStatsCalc(Player player, int statId, StatRepo statRepo)
    {
        ArrayList <StatValue> stats = new ArrayList<>();
        switch (statId)
        {
            case (0):
                getThreeBetStats(player, stats, statRepo);
                return stats;
            case (1):
                System.out.println("not method exsist");
                break;
            case (2):
                System.out.println("not method exsist");
                break;
            case (3):
                System.out.println("not method exsist");
                break;

        }

        return null;
    }
    private static ArrayList<StatValue> getThreeBetStats(Player pl, ArrayList<StatValue> stats, StatRepo statRepo)
    {
        StatValue statValue;
        String statname="total3bet";
        statValue=new StatValue(Double.toString(pl.getTotal3bet()), checkDiap(pl.getTotal3bet(),
                setPoints(statname, statRepo), false));
        stats.add(statValue);

        statname="total3bet";

        statValue=new StatValue(Double.toString(pl.getTotal3betEp()), checkDiap(pl.getTotal3betEp(),  setPoints(statname, statRepo), false));
        stats.add(statValue);
        statValue=new StatValue(Double.toString(pl.getTotal3betMp()), checkDiap(pl.getTotal3betMp(),  setPoints(statname, statRepo), false));
        stats.add(statValue);
        statValue=new StatValue(Double.toString(pl.getTotal3betCo()), checkDiap(pl.getTotal3betCo(),  setPoints(statname, statRepo), false));
        stats.add(statValue);
        statValue=new StatValue(Double.toString(pl.getTotal3betBu()), checkDiap(pl.getTotal3betBu(),  setPoints(statname, statRepo), false));
        stats.add(statValue);
        statValue=new StatValue(Double.toString(pl.getTotal3betSb()), checkDiap(pl.getTotal3betSb(),  setPoints(statname, statRepo), false));
        stats.add(statValue);
        statValue=new StatValue(Double.toString(pl.getTotal3betBb()), checkDiap(pl.getTotal3betBb(),  setPoints(statname, statRepo), false));
        stats.add(statValue);

        /*
        ArrayList<String> statsName = new ArrayList<>();
        statsName.add("total3bet");
        statsName.add("total3betEp");
        statsName.add("total3betMp");
        statsName.add("total3betCo");
        statsName.add("total3betBu");
        statsName.add("total3betSb");
        statsName.add("total3betBb");
        statsCalc(stats, statRepo, statsName, pl);
        */

        return stats;
    }

    private static ArrayList<StatValue> statsCalc(ArrayList<StatValue> stats, StatRepo statRepo, ArrayList<String> statsName, Player pl)
    {
        StatValue statValue;
        for(String statName: statsName)
        {
            statValue=new StatValue(Double.toString(pl.getTotal3bet()), checkDiap(pl.getTotal3bet(),
                    setPoints(statName, statRepo), false));
            stats.add(statValue);
        }
        return stats;
    }
    private static int checkDiap(double stat, double points[], boolean revert)
    {
        if(revert==false)
        {
            if (stat<=points[0]) return 0;
            if (stat<=points[1]) return 1;
            if (stat<=points[2]) return 2;
            if (stat<=points[3]) return 3;
            return 4;
        }
        if(revert==true)
        {
            if (stat<=points[0]) return 0;
            if (stat<=points[1]) return 1;
            if (stat<=points[2]) return 3;
            if (stat<=points[3]) return 2;
            return 2;
        }
        return 0;
    }
    private static double[] setPoints(String statname, StatRepo statRepo)
    {
        double[] points = new double[4];
        points[0]= statRepo.findStatByStatname(statname).getPoint1();
        points[1]= statRepo.findStatByStatname(statname).getPoint2();
        points[2]= statRepo.findStatByStatname(statname).getPoint3();
        points[3]= statRepo.findStatByStatname(statname).getPoint4();
        return points;
    }
}
