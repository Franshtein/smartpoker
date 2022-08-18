package com.stsetsevich.smartpoker.engine;


import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HudCalc {

    public static Player checkPlayer (PlayerRepo playerRepo, String player)
    {
        Player playerNick = playerRepo.findByNickname(player);
        if (playerNick == null) {
            System.out.println("Игрок не найден, установлено значение по умолчанию");
            playerNick = playerRepo.findByNickname("Franshtik (PS)");
        }
        return playerNick;
    }
    public static ArrayList<String> getPlayerStats(PlayerRepo playerRepo, String nickname)
    {
       Player player =  playerRepo.findByNickname(nickname);
       ArrayList<String> stats = new ArrayList<>();
       stats.add(player.getNickname());
       stats.add(Double.toString(player.getVpip()));
       stats.add(Double.toString(player.getTotalPfr()));
       stats.add(Double.toString(player.getTotal3bet()));
       stats.add(Double.toString(player.getFoldTo3betTotal()));
       return stats;
    }

    public static ArrayList<Player> getAllPlayerStats(PlayerRepo playerRepo, String p1, String p2,String p3,String p4,String p5)
    {
        Player player=playerRepo.findByNickname(p1);
            if (player == null) {
                System.out.println("Игрок не найден, установлено значение по умолчанию");
                player = playerRepo.findByNickname("Franshtik (PS)");
            }
            Player player2 = playerRepo.findByNickname(p2);

            if (player2 == null) {
                System.out.println("Игрок не найден, установлено значение по умолчанию");
                player2 = playerRepo.findByNickname("Franshtik (PS)");
            }
            Player player3 = playerRepo.findByNickname(p3);

            if (player3 == null) {
                System.out.println("Игрок не найден, установлено значение по умолчанию");
                player3 = playerRepo.findByNickname("Franshtik (PS)");
            }
            Player player4 = playerRepo.findByNickname(p4);

            if (player4 == null) {
                System.out.println("Игрок не найден, установлено значение по умолчанию");
                player4 = playerRepo.findByNickname("Franshtik (PS)");
            }
            Player player5 = playerRepo.findByNickname(p5);

            if (player5 == null) {
                System.out.println("Игрок не найден, установлено значение по умолчанию");
                player5 = playerRepo.findByNickname("Franshtik (PS)");
            }

        ArrayList<Player> players = new ArrayList<>();
            players.add(player);
            players.add(player2);
            players.add(player3);
            players.add(player4);
            players.add(player5);
;
        return players;
    }
    public static ArrayList<StatValue> extraStatsCalc(Player player, int statId)
    {
        ArrayList <StatValue> stats = new ArrayList<>();
        switch (statId)
        {
            case (0):
                getThreeBetStats(player, stats);
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
    private static ArrayList<StatValue> getThreeBetStats(Player pl, ArrayList<StatValue> stats)
    {
        StatValue statValue;

        statValue=new StatValue(Double.toString(pl.getTotal3bet()), checkDiap(pl.getTotal3bet(), 5, 7, 9, 11));
        stats.add(statValue);
        statValue=new StatValue(Double.toString(pl.getTotal3betEp()), checkDiap(pl.getTotal3betEp(), 5, 7, 9, 11));
        stats.add(statValue);
        statValue=new StatValue(Double.toString(pl.getTotal3betMp()), checkDiap(pl.getTotal3betMp(), 5, 7, 9, 11));
        stats.add(statValue);
        statValue=new StatValue(Double.toString(pl.getTotal3betCo()), checkDiap(pl.getTotal3betCo(), 5, 7, 9, 11));
        stats.add(statValue);
        statValue=new StatValue(Double.toString(pl.getTotal3betBu()), checkDiap(pl.getTotal3betBu(), 5, 7, 9, 11));
        stats.add(statValue);
        statValue=new StatValue(Double.toString(pl.getTotal3betSb()), checkDiap(pl.getTotal3betSb(), 5, 7, 9, 11));
        stats.add(statValue);
        statValue=new StatValue(Double.toString(pl.getTotal3betBb()), checkDiap(pl.getTotal3betBb(), 5, 7, 9, 11));
        stats.add(statValue);
        return stats;
    }
    private static int checkDiap(double stat, double point1, double point2,double point3,double point4)
    {
        if (stat<=point1) return 0;
        if (stat<=point2) return 1;
        if (stat<=point3) return 2;
        if (stat<=point4) return 3;
        return 4;
    }
}
