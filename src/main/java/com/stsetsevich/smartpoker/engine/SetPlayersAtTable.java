package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.domain.RoundOfBidding;
import com.stsetsevich.smartpoker.domain.UserSmarthandAccountAndCookies;
import com.stsetsevich.smartpoker.engine.edithud.HudEdit;
import com.stsetsevich.smartpoker.engine.parse.ParsePlayer;
import com.stsetsevich.smartpoker.exceptions.PlayerNotFoundException;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import com.stsetsevich.smartpoker.repos.UserRepo;
import com.stsetsevich.smartpoker.repos.UserSmarthandAccountAndCookiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


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
    @Autowired
    StatInfo statInfoMother;
    @Autowired
    HudEdit hudEdit;

    private PlayerNotFoundException playerNotFoundException;

    private List<Player> players;
    Map<Integer, StatInfo[][]> preflopStats;
    Map<Integer, StatInfo[][]> flopStats;
    Map<Integer, StatInfo[][]> turnStats;
    Map<Integer, StatInfo[][]> riverStats;

    public  Player checkPlayer(String player) {
        Player playerNick = playerRepo.findByNickname(player);
        if (playerNick == null) {
            System.out.println("Игрок не найден, установлено значение по умолчанию");
            playerNick = playerRepo.findByNickname("Empty Seat");
        }
        return playerNick;
    }
    public void playersInit(List<Player> players)
    {
        this.players=players;
        preflopStats=statsInit(players, "PREFLOP");
        flopStats=statsInit(players, "FLOP");
        turnStats=statsInit(players, "TURN");
        riverStats=statsInit(players, "RIVER");
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

    public List<Player> getAllPlayers(String p1, String p2, String p3, String p4, String p5){
        playerNotFoundException=null;
        if(!players.get(0).getNickname().equals(p1))
        {
            players.set(0,setPlayer(p1, 0));
            preflopStats.put(0, playerStatsCalc(players.get(0), "PREFLOP"));
            flopStats.put(0, playerStatsCalc(players.get(0), "FLOP"));
            turnStats.put(0, playerStatsCalc(players.get(0), "TURN"));
            riverStats.put(0, playerStatsCalc(players.get(0), "RIVER"));
        }
        if(!players.get(1).getNickname().equals(p2))
        {
            players.set(1,setPlayer(p2, 1));
            preflopStats.put(1, playerStatsCalc(players.get(1), "PREFLOP"));
            flopStats.put(1, playerStatsCalc(players.get(1), "FLOP"));
            turnStats.put(1, playerStatsCalc(players.get(1), "TURN"));
            riverStats.put(1, playerStatsCalc(players.get(1), "RIVER"));
        }
        if(!players.get(2).getNickname().equals(p3))
        {
            players.set(2,setPlayer(p3, 2));
            preflopStats.put(20, playerStatsCalc(players.get(2), "PREFLOP"));
            flopStats.put(2, playerStatsCalc(players.get(2), "FLOP"));
            turnStats.put(2, playerStatsCalc(players.get(2), "TURN"));
            riverStats.put(2, playerStatsCalc(players.get(2), "RIVER"));
        }
        if(!players.get(3).getNickname().equals(p4))
        {
            players.set(3,setPlayer(p4, 3));
            preflopStats.put(3, playerStatsCalc(players.get(3), "PREFLOP"));
            flopStats.put(3, playerStatsCalc(players.get(3), "FLOP"));
            turnStats.put(3, playerStatsCalc(players.get(3), "TURN"));
            riverStats.put(3, playerStatsCalc(players.get(3), "RIVER"));
        }
        if(!players.get(4).getNickname().equals(p5))
        {
            players.set(4,setPlayer(p5, 4));
            preflopStats.put(4, playerStatsCalc(players.get(4), "PREFLOP"));
            flopStats.put(4, playerStatsCalc(players.get(4), "FLOP"));
            turnStats.put(4, playerStatsCalc(players.get(4), "TURN"));
            riverStats.put(4, playerStatsCalc(players.get(4), "RIVER"));
        }

        return players;
    }
    private Player setPlayer(String playerName, int i)
    {
        Player player = playerRepo.findByNickname(playerName);
            if (player == null) {
                player = playerRepo.findByNickname(playerName + " (PS)");
            }
        if (player == null) {

            addOrUpdatePlayer.tryAddNewPlayer(playerName);

        } else addOrUpdatePlayer.updatePlayerIfNeed(player);

        player = playerRepo.findByNickname(playerName);
        if (player == null) player = playerRepo.findByNickname(playerName + " (PS)");
        if (player == null) {
            System.out.println("Игрок не найден, установлено значение по умолчанию");
            player = playerRepo.findByNickname("Empty Seat");
            setPlayerNotFoundException(new PlayerNotFoundException(i, playerName));
        }

        return player;
    }
    public Map<Integer, StatInfo[][]> statsInit(List<Player> players, String table) {
        Map<Integer, StatInfo[][]> playerStat = new HashMap<>();
        int key = 0;
        for (Player pl : players) {

            StatInfo statInfo;
            String[][] statnames = hudEdit.parseStatFromNumberToStringView(RoundOfBidding.valueOf(table));
            StatInfo[][] stats = new StatInfo[statnames.length][statnames[0].length];
            try {
                // String[] statnames = {"vpip", "total_pfr", "total3bet", "fold_to3bet_total"};

                for (int i=0; i<statnames.length; i++)
                {
                    for (int j=0; j<statnames[0].length; j++)
                    {
                        statInfo = statInfoMother.getStatInfo();
                        statInfo.setInfo(statnames[i][j], pl);
                        stats[i][j]=statInfo;
                    }
                }
            } finally {
                playerStat.put(key, stats);
                key++;
            }


        }
        return playerStat;
    }

    public StatInfo[][] playerStatsCalc(Player player, String table) {
        Map<Integer, StatInfo[][]> playerStat = new HashMap<>();

            StatInfo statInfo;
            String[][] statnames = hudEdit.parseStatFromNumberToStringView(RoundOfBidding.valueOf(table));
            StatInfo[][] stats = new StatInfo[statnames.length][statnames[0].length];
                // String[] statnames = {"vpip", "total_pfr", "total3bet", "fold_to3bet_total"};

                for (int i=0; i<statnames.length; i++)
                {
                    for (int j=0; j<statnames[0].length; j++)
                    {
                        statInfo = statInfoMother.getStatInfo();
                        statInfo.setInfo(statnames[i][j], player);
                        stats[i][j]=statInfo;
                    }
                }

        return stats;
    }



    public PlayerNotFoundException getPlayerNotFoundException() {
        return playerNotFoundException;
    }

    public void setPlayerNotFoundException(PlayerNotFoundException playerNotFoundException) {
        this.playerNotFoundException = playerNotFoundException;
    }

    public Map<Integer, StatInfo[][]> getPreflopStats() {
        return preflopStats;
    }

    public Map<Integer, StatInfo[][]> getFlopStats() {
        return flopStats;
    }

    public Map<Integer, StatInfo[][]> getTurnStats() {
        return turnStats;
    }

    public Map<Integer, StatInfo[][]> getRiverStats() {
        return riverStats;
    }
}
