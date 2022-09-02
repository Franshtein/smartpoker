package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import com.stsetsevich.smartpoker.repos.UserRepo;
import com.stsetsevich.smartpoker.repos.UserSmarthandAccountAndCookiesRepo;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SetPlayersAtTable {
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

    public static ArrayList<Player> getAllPlayerStats(PlayerRepo playerRepo, UserRepo userRepo, UserSmarthandAccountAndCookiesRepo userSmarthandAccountAndCookiesRepo, String p1, String p2, String p3, String p4, String p5) {
        Player player = playerRepo.findByNickname(p1);
        Player player2 = playerRepo.findByNickname(p2);
        Player player3 = playerRepo.findByNickname(p3);
        Player player4 = playerRepo.findByNickname(p4);
        Player player5 = playerRepo.findByNickname(p5);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        ArrayList<String> playerName = new ArrayList<>();
        playerName.add(p1);
        playerName.add(p2);
        playerName.add(p3);
        playerName.add(p4);
        playerName.add(p5);
        int i = 0;
        for (Player pl : players) {
            Player pl2 = playerRepo.findByNickname(playerName.get(i) + " (PS)");
            if (pl2 == null) {
                if (pl == null) {
                    try {
                        Document document = ParsePlayer.parsePlayer(playerName.get(i), userRepo, userSmarthandAccountAndCookiesRepo);
                        if (document != null) {
                            StatsParse statsParse = new StatsParse(document);
                            if (statsParse.getStats() != null) {
                                if (playerRepo.findByNickname(statsParse.searchNickname()) == null) {
                                    playerRepo.save(statsParse.getStats());

                                } else System.out.println("That player excists in the DataBase");
                            }
                        } else System.out.println("There is no data on player " + playerName.get(i));

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    pl = playerRepo.findByNickname(playerName.get(i) + " (PS)");
                    if (pl == null) {
                        System.out.println("Игрок не найден, установлено значение по умолчанию");
                        pl = playerRepo.findByNickname("Franshtik (PS)");

                    }


                }
            } else {
                Date lastUpdate = pl2.getDateUpdate();
                System.out.println(lastUpdate + " ogogo");
                Date today = new Date(System.currentTimeMillis());
                java.util.Date needLastUpdate = lastUpdate;
                java.util.Date needToday = today;

                long updateOld = -1;
                try {
                    updateOld = (needToday.getTime() - needLastUpdate.getTime()) / (24 * 60 * 60 * 1000);
                } catch (Exception exception) {

                }

                if (updateOld < 0 || updateOld >= 2) {
                    playerRepo.delete(pl2);
                    try {
                        Document document = ParsePlayer.parsePlayer(playerName.get(i), userRepo, userSmarthandAccountAndCookiesRepo);
                        if (document != null) {
                            StatsParse statsParse = new StatsParse(document);
                            if (statsParse.getStats() != null) {
                                if (playerRepo.findByNickname(statsParse.searchNickname()) == null) {
                                    playerRepo.save(statsParse.getStats());

                                } else System.out.println("That player excists in the DataBase");
                            }
                        } else System.out.println("There is no data on player " + playerName.get(i));

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    pl = playerRepo.findByNickname(playerName.get(i) + " (PS)");
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
}
