package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.DoubleStream;


@Service
public class CalcPlayerStats {
    @Autowired
    PlayerRepo playerRepo;

    private ArrayList<Player> allPlayers;


    public ArrayList<StatValue> extraStatsCalc() {
        allPlayers=new ArrayList<>((Collection) playerRepo.findAll());
        ArrayList<Player> subPlayers = new ArrayList<>(allPlayers);
        //subPlayers.removeIf(n -> (n.getNickname().equals("Empty Seat")));
        ArrayList<StatValue> stats = new ArrayList<>();
        StatValue statValue = new StatValue(Double.toString(calcAvgEvBb100(subPlayers)), 0, "avgEvBb100");
        stats.add(statValue);
        statValue = new StatValue(Double.toString(over10kHandsCalcAvgEvBb100(subPlayers)), 0, "avgEvBb100, 10k+ Hands");
        stats.add(statValue);
        statValue = new StatValue(Double.toString(over10kHandsAndPlusEvCalcAvgEvBb100(subPlayers)), 0, "avgEvBb100, 10k+ Hands, ev>0");
        stats.add(statValue);
        statValue = new StatValue(Double.toString(calcAvgVpip(subPlayers)), 0, "avgVpip");
        stats.add(statValue);
        statValue = new StatValue(Double.toString(over10kHandsCalcAvgVpip(subPlayers)), 0, "avgVpip, 10k+ Hands");
        stats.add(statValue);
        statValue = new StatValue(Double.toString(over10kHandsAndPlusEvCalcAvgVpip(subPlayers)), 0, "avgVpip, 10k+ Hands, ev>0");
        stats.add(statValue);
        statValue = new StatValue(Double.toString(over10kHandsAndPlusEvCalcAvgCheckRaiseFlop(subPlayers)), 0, "avgCheckRaiseFlop, 10k+ Hands, ev>0");
        stats.add(statValue);
        statValue = new StatValue(Double.toString(over10kHandsAndPlusEvCalcAvgCheckRaiseTurn(subPlayers)), 0, "avgCheckRaiseTurn, 10k+ Hands, ev>0");
        stats.add(statValue);
        statValue = new StatValue(Double.toString(over10kHandsAndPlusEvCalcAvgCheckRaiseRiver(subPlayers)), 0, "avgCheckRaiseRiver, 10k+ Hands, ev>0");
        stats.add(statValue);
        statValue = new StatValue(Double.toString(nice(subPlayers)), 0, "need, 10k+ Hands, ev>0");
        stats.add(statValue);
        statValue = new StatValue(Double.toString(nice2(subPlayers)), 0, "avgCheckRaiseRiver, 10k+ Hands, ev>0");
        stats.add(statValue);
        statValue = new StatValue(Double.toString(nice3(subPlayers)), 0, "avgCheckRaiseRiver, 10k+ Hands, ev>0");
        stats.add(statValue);
        return stats;

    }
    private static double nice(ArrayList<Player> players) {
        ArrayList<Player> subPlayers = new ArrayList<>(players);
        subPlayers.removeIf(n -> (n.getTotalHands()<=10000|| n.getAvgBb100()<4));
        System.out.println(subPlayers.size());
        return subPlayers.stream().flatMapToDouble(player -> DoubleStream.of(player.getWonAfterRaiseFlop())).average().orElse(0);
    }
    private static double nice2(ArrayList<Player> players) {
        ArrayList<Player> subPlayers = new ArrayList<>(players);
        subPlayers.removeIf(n -> (n.getTotalHands()<=10000|| n.getAvgBb100()<4));
        System.out.println(subPlayers.size());
        return subPlayers.stream().flatMapToDouble(player -> DoubleStream.of(player.getWonAfterRaiseTurn())).average().orElse(0);
    }
    private static double nice3(ArrayList<Player> players) {
        ArrayList<Player> subPlayers = new ArrayList<>(players);
        subPlayers.removeIf(n -> (n.getTotalHands()<=10000|| n.getAvgBb100()<4));
        System.out.println(subPlayers.size());
        return subPlayers.stream().flatMapToDouble(player -> DoubleStream.of(player.getWonAfterRaiseRiver())).average().orElse(0);
    }

    private static double calcAvgEvBb100(ArrayList<Player> players) {
        return players.stream().flatMapToDouble(player -> DoubleStream.of(player.getAvgBb100())).average().orElse(0);
    }
    private static double over10kHandsCalcAvgEvBb100(ArrayList<Player> players) {
        ArrayList<Player> subPlayers = new ArrayList<>(players);
        subPlayers.removeIf(n -> (n.getTotalHands()<=10000));
        return subPlayers.stream().flatMapToDouble(player -> DoubleStream.of(player.getAvgBb100())).average().orElse(0);
    }
    private static double over10kHandsAndPlusEvCalcAvgEvBb100(ArrayList<Player> players) {
        ArrayList<Player> subPlayers = new ArrayList<>(players);
        subPlayers.removeIf(n -> (n.getTotalHands()<=10000 || n.getAvgBb100()<0));
        return subPlayers.stream().flatMapToDouble(player -> DoubleStream.of(player.getAvgBb100())).average().orElse(0);
    }
    private static double calcAvgVpip(ArrayList<Player> players) {
        return players.stream().flatMapToDouble(player -> DoubleStream.of(player.getVpip())).average().orElse(0);
    }
    private static double over10kHandsCalcAvgVpip(ArrayList<Player> players) {
        ArrayList<Player> subPlayers = new ArrayList<>(players);
        subPlayers.removeIf(n -> (n.getTotalHands()<=10000));
        return subPlayers.stream().flatMapToDouble(player -> DoubleStream.of(player.getVpip())).average().orElse(0);
    }
    private static double over10kHandsAndPlusEvCalcAvgVpip(ArrayList<Player> players) {
        ArrayList<Player> subPlayers = new ArrayList<>(players);
        subPlayers.removeIf(n -> (n.getTotalHands()<=10000 || n.getAvgBb100()<0));
        return subPlayers.stream().flatMapToDouble(player -> DoubleStream.of(player.getVpip())).average().orElse(0);
    }
    private static double over10kHandsAndPlusEvCalcAvgCheckRaiseFlop(ArrayList<Player> players) {
        ArrayList<Player> subPlayers = new ArrayList<>(players);
        subPlayers.removeIf(n -> (n.getTotalHands()<=10000 || n.getAvgBb100()<0));
        return subPlayers.stream().flatMapToDouble(player -> DoubleStream.of(player.getCheckRaiseFlop())).average().orElse(0);
    }
    private static double over10kHandsAndPlusEvCalcAvgCheckRaiseTurn(ArrayList<Player> players) {
        ArrayList<Player> subPlayers = new ArrayList<>(players);
        subPlayers.removeIf(n -> (n.getTotalHands()<=10000 || n.getAvgBb100()<0));
        return subPlayers.stream().flatMapToDouble(player -> DoubleStream.of(player.getCheckRaiseTurn())).average().orElse(0);
    }
    private static double over10kHandsAndPlusEvCalcAvgCheckRaiseRiver(ArrayList<Player> players) {
        ArrayList<Player> subPlayers = new ArrayList<>(players);
        subPlayers.removeIf(n -> (n.getTotalHands()<=10000 || n.getAvgBb100()<0));
        return subPlayers.stream().flatMapToDouble(player -> DoubleStream.of(player.getCheckRaiseRiver())).average().orElse(0);
    }

}
