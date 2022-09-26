package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.domain.UserSmarthandAccountAndCookies;
import com.stsetsevich.smartpoker.engine.parse.ParsePlayer;
import com.stsetsevich.smartpoker.exceptions.PlayerNotFoundException;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import com.stsetsevich.smartpoker.repos.UserRepo;
import com.stsetsevich.smartpoker.repos.UserSmarthandAccountAndCookiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

    private PlayerNotFoundException playerNotFoundException;

    private List<Player> players;

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
        }
        if(!players.get(1).getNickname().equals(p2))
        {
            players.set(1,setPlayer(p2, 1));
        }
        if(!players.get(2).getNickname().equals(p3))
        {
            players.set(2,setPlayer(p3, 2));
        }
        if(!players.get(3).getNickname().equals(p4))
        {
            players.set(3,setPlayer(p4, 3));
        }
        if(!players.get(4).getNickname().equals(p5))
        {
            players.set(4,setPlayer(p5, 4));
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

    public PlayerNotFoundException getPlayerNotFoundException() {
        return playerNotFoundException;
    }

    public void setPlayerNotFoundException(PlayerNotFoundException playerNotFoundException) {
        this.playerNotFoundException = playerNotFoundException;
    }
}
