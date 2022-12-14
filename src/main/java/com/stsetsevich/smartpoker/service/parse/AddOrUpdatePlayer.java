package com.stsetsevich.smartpoker.service.parse;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.service.parse.ParsePlayer;
import com.stsetsevich.smartpoker.service.parse.StatsParse;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;

/**
 * Управляет добавлением игрока в БД.
 * Если не находит игрока в БД, использует  {@link ParsePlayer}
 * Если игрок есть в БД - проверяет, нужно ли актуализировать информацию.
 */
@Service
public class AddOrUpdatePlayer {
    @Autowired
    ParsePlayer parsePlayer;
    @Autowired
    PlayerRepo playerRepo;

    //Управляет добавлением нового игрока
    public void tryAddNewPlayer(String playerName) {
        try {
            Document document = parsePlayer.parsePlayer(playerName);
            if (document != null) {
                StatsParse statsParse = new StatsParse(document);
                Player player = playerRepo.findByNickname(statsParse.searchNickname());
                if (statsParse.getStats() != null) {
                    if (player == null) {
                        playerRepo.save(statsParse.getStats());
                    //Заглушка на случай, если метод будет вызван при существующем игроке в БД.
                    } else {
                        System.out.println("That player excists in the DataBase");
                        updatePlayerIfNeed(player);
                    }
                }
            } else System.out.println("There is no data on player " + playerName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Проверяет, нужно ли обновление данных для игрока в БД.
    //Если нужно - удаляет старую запись и вызывает метод добавления нового игрока
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
            //Если даты добавления нет, либо она устарела - удаляем игрока и добавляем его заново.
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
