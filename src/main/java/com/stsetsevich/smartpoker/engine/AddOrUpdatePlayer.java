package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.parse.ParsePlayer;
import com.stsetsevich.smartpoker.engine.parse.StatsParse;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
@Service
public class AddOrUpdatePlayer {
    @Autowired
    ParsePlayer parsePlayer;
    @Autowired
    PlayerRepo playerRepo;


    public void tryAddNewPlayer(String playerName)
    {
        try {
            Document document = parsePlayer.parsePlayer(playerName);
            if (document != null) {
                StatsParse statsParse = new StatsParse(document);
                Player player = playerRepo.findByNickname(statsParse.searchNickname());
                if (statsParse.getStats() != null) {
                    if (player == null) {
                        playerRepo.save(statsParse.getStats());

                    } else
                    {
                        System.out.println("That player excists in the DataBase");
                        updatePlayerIfNeed(player);
                    }
                }
            } else System.out.println("There is no data on player " + playerName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void updatePlayerIfNeed(Player player) {
        if (!player.getNickname().equals("Empty Seat")) {
            java.util.Date lastUpdate = player.getDateUpdate();
            java.util.Date today = new Date(System.currentTimeMillis());
            double totalHands = player.getTotalHands();

            long updateOld = -100;
            try {
                updateOld = (today.getTime() - lastUpdate.getTime()) / (24 * 60 * 60 * 1000);
            } catch (Exception exception) {

            }

            if (updateOld < -99 ||
                    (updateOld >= 1 && totalHands <= 5000) ||
                    (updateOld >= 5 && totalHands <= 15000) ||
                    (updateOld >= 14 && totalHands <= 50000) ||
                    (updateOld >= 30)) {
                playerRepo.delete(player);
                tryAddNewPlayer(player.getNickname());
            }
        }
    }
}
