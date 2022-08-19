package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.repos.PlayerRepo;

import java.util.ArrayList;

public class SetPlayersAtTable {
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
}
