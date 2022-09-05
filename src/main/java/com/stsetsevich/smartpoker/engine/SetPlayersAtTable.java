package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.domain.User;
import com.stsetsevich.smartpoker.domain.UserSmarthandAccountAndCookies;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import com.stsetsevich.smartpoker.repos.UserRepo;
import com.stsetsevich.smartpoker.repos.UserSmarthandAccountAndCookiesRepo;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.GregorianCalendar;


@Service
public class SetPlayersAtTable {

    @Autowired

    PlayerRepo playerRepo;
    @Autowired
     UserRepo userRepo;
    UserSmarthandAccountAndCookies userSmarthandAccountAndCookies;
    @Autowired
    UserSmarthandAccountAndCookiesRepo userSmarthandAccountAndCookiesRepo;

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

    public ArrayList<Player> getAllPlayerStats(String p1, String p2, String p3, String p4, String p5) {
        ArrayList<String> playerName = new ArrayList<>();
        playerName.add(p1);
        playerName.add(p2);
        playerName.add(p3);
        playerName.add(p4);
        playerName.add(p5);
        ArrayList<Player> players = new ArrayList<>();
        for (String pn : playerName)
        {
            Player player = playerRepo.findByNickname(pn);
            players.add(player);
        }

        int i = 0;
        for (Player pl : players) {
            Player pl2 = playerRepo.findByNickname(playerName.get(i) + " (PS)");
            if (pl2 == null) {
                if (pl == null) {
                  tryAddNewPlayer(playerName.get(i));
                    pl = playerRepo.findByNickname(playerName.get(i)+ " (PS)");
                    if (pl == null) {
                        System.out.println("Игрок не найден, установлено значение по умолчанию");
                        pl = playerRepo.findByNickname("Franshtik (PS)");

                    }

                }
            } else {

                java.util.Date lastUpdate = pl2.getDateUpdate();
                java.util.Date today = new Date(System.currentTimeMillis());
                double totalHands = pl2.getTotalHands();

                long updateOld = -1;
                try {
                    updateOld = (today.getTime() - lastUpdate.getTime()) / (24 * 60 * 60 * 1000);
                } catch (Exception exception) {

                }

                if (updateOld < 0 ||
                        (updateOld >= 1 && totalHands <=5000) ||
                        (updateOld >= 5 && totalHands <=15000) ||
                        (updateOld >= 14 && totalHands <=50000) ||
                        (updateOld >= 30)){
                    playerRepo.delete(pl2);
                    tryAddNewPlayer(playerName.get(i));
                    pl = playerRepo.findByNickname(playerName.get(i)+ " (PS)");
                    if (pl == null) {
                        System.out.println("Игрок не найден, установлено значение по умолчанию");
                        pl = playerRepo.findByNickname("Franshtik (PS)");

                    }
                } else pl = pl2;

            }
            players.set(i, pl);
            i++;

        }
        return players;
    }
    public void tryAddNewPlayer(String playerName)
    {
        try {
            Document document = ParsePlayer.parsePlayer(playerName, userRepo, userSmarthandAccountAndCookiesRepo);
            if (document != null) {
                StatsParse statsParse = new StatsParse(document);
                if (statsParse.getStats() != null) {
                    if (playerRepo.findByNickname(statsParse.searchNickname()) == null) {
                        playerRepo.save(statsParse.getStats());

                    } else System.out.println("That player excists in the DataBase");
                }
            } else System.out.println("There is no data on player " + playerName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    private void updatePlayerIfNeed(Player player) {
        java.util.Date lastUpdate = player.getDateUpdate();
        java.util.Date today = new Date(System.currentTimeMillis());
        double totalHands = player.getTotalHands();

        long updateOld = -1;
        try {
            updateOld = (today.getTime() - lastUpdate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception exception) {

        }

        if (updateOld < 0 ||
                (updateOld >= 1 && totalHands <= 5000) ||
                (updateOld >= 5 && totalHands <= 15000) ||
                (updateOld >= 14 && totalHands <= 50000) ||
                (updateOld >= 30)) {
            playerRepo.delete(player);
        }
    }
}
