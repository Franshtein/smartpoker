package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.domain.UserSmarthandAccountAndCookies;
import com.stsetsevich.smartpoker.engine.parse.ParsePlayer;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import com.stsetsevich.smartpoker.repos.UserRepo;
import com.stsetsevich.smartpoker.repos.UserSmarthandAccountAndCookiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class SetPlayersAtTable {

    @Autowired

    PlayerRepo playerRepo;
    @Autowired
    UserRepo userRepo;
    UserSmarthandAccountAndCookies userSmarthandAccountAndCookies;
    @Autowired
    UserSmarthandAccountAndCookiesRepo userSmarthandAccountAndCookiesRepo;
    @Autowired
    ParsePlayer parsePlayer;
    @Autowired
    AddOrUpdatePlayer addOrUpdatePlayer;

    public static Player checkPlayer(PlayerRepo playerRepo, String player) {
        Player playerNick = playerRepo.findByNickname(player);
        if (playerNick == null) {
            System.out.println("Игрок не найден, установлено значение по умолчанию");
            playerNick = playerRepo.findByNickname("Franshtik (PS)");
        }
        return playerNick;
    }

    public static ArrayList<String> getPlayerStats(PlayerRepo playerRepo, String nickname) {
        Player player = playerRepo.findByNickname(nickname);
        ArrayList<String> stats = new ArrayList<>();
        stats.add(player.getNickname());
        stats.add(Double.toString(player.getVpip()));
        stats.add(Double.toString(player.getTotalPfr()));
        stats.add(Double.toString(player.getTotal3bet()));
        stats.add(Double.toString(player.getFoldTo3betTotal()));
        return stats;
    }

    public ArrayList<Player> getAllPlayers(String p1, String p2, String p3, String p4, String p5) {
        ArrayList<String> playerName = new ArrayList<>();
        playerName.add(p1);
        playerName.add(p2);
        playerName.add(p3);
        playerName.add(p4);
        playerName.add(p5);
        ArrayList<Player> players = new ArrayList<>();
        for (String pn : playerName) {
            Player player = playerRepo.findByNickname(pn);
            if (!pn.equals("Empty Seat")) {
                if (player == null) {
                    player = playerRepo.findByNickname(pn + " (PS)");
                }
            }
            players.add(player);
        }

        int i = 0;
        for (Player pl : players) {
            if (pl == null) {

                //tryAddNewPlayer(playerName.get(i));
                addOrUpdatePlayer.tryAddNewPlayer(playerName.get(i));

            } else addOrUpdatePlayer.updatePlayerIfNeed(pl);

            pl = playerRepo.findByNickname(playerName.get(i));
            if (pl == null) pl = playerRepo.findByNickname(playerName.get(i) + " (PS)");
            if (pl == null) {
                System.out.println("Игрок не найден, установлено значение по умолчанию");
                pl = playerRepo.findByNickname("Empty Seat");
            }


            players.set(i, pl);
            i++;

        }
        return players;
    }

}
