package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.domain.RoundOfBidding;
import com.stsetsevich.smartpoker.engine.edithud.HudEdit;
import com.stsetsevich.smartpoker.exceptions.PlayerNotFoundException;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Заполняет для HUD таблицы с игроками.
 * {@link com.stsetsevich.smartpoker.controller.HudController}
 */
@Service
public class PlayersAtTable {

    @Autowired
    PlayerRepo playerRepo;
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

    //Первичная инициализация пустых таблиц (Empty Seat) для HUD
    public void initPlayers() {
        this.players = new ArrayList<>(Arrays.asList(playerRepo.findByNickname("Empty Seat"), playerRepo.findByNickname("Empty Seat"), playerRepo.findByNickname("Empty Seat"), playerRepo.findByNickname("Empty Seat"), playerRepo.findByNickname("Empty Seat")));
        preflopStats = statsInit(players, "PREFLOP");
        flopStats = statsInit(players, "FLOP");
        turnStats = statsInit(players, "TURN");
        riverStats = statsInit(players, "RIVER");
    }

    //возвращает список всех игроков за столом
    public List<Player> getAllPlayers() {
        return players;
    }

    //меняет игрока, если было изменение и возвращает список всех игроков за столом
    public List<Player> getAllPlayers(String p1, String p2, String p3, String p4, String p5) {
        //Если ник игрока не равен актуальному,
        //то за стол сел дргой игрок. Меняем в списке игрока и инициализируем ему статы
        if (!players.get(0).getNickname().equals(p1)) {
            players.set(0, setPlayer(p1, 0));
            preflopStats.put(0, playerStatsCalc(players.get(0), "PREFLOP"));
            flopStats.put(0, playerStatsCalc(players.get(0), "FLOP"));
            turnStats.put(0, playerStatsCalc(players.get(0), "TURN"));
            riverStats.put(0, playerStatsCalc(players.get(0), "RIVER"));
        }
        if (!players.get(1).getNickname().equals(p2)) {
            players.set(1, setPlayer(p2, 1));
            preflopStats.put(1, playerStatsCalc(players.get(1), "PREFLOP"));
            flopStats.put(1, playerStatsCalc(players.get(1), "FLOP"));
            turnStats.put(1, playerStatsCalc(players.get(1), "TURN"));
            riverStats.put(1, playerStatsCalc(players.get(1), "RIVER"));
        }
        if (!players.get(2).getNickname().equals(p3)) {
            players.set(2, setPlayer(p3, 2));
            preflopStats.put(20, playerStatsCalc(players.get(2), "PREFLOP"));
            flopStats.put(2, playerStatsCalc(players.get(2), "FLOP"));
            turnStats.put(2, playerStatsCalc(players.get(2), "TURN"));
            riverStats.put(2, playerStatsCalc(players.get(2), "RIVER"));
        }
        if (!players.get(3).getNickname().equals(p4)) {
            players.set(3, setPlayer(p4, 3));
            preflopStats.put(3, playerStatsCalc(players.get(3), "PREFLOP"));
            flopStats.put(3, playerStatsCalc(players.get(3), "FLOP"));
            turnStats.put(3, playerStatsCalc(players.get(3), "TURN"));
            riverStats.put(3, playerStatsCalc(players.get(3), "RIVER"));
        }
        if (!players.get(4).getNickname().equals(p5)) {
            players.set(4, setPlayer(p5, 4));
            preflopStats.put(4, playerStatsCalc(players.get(4), "PREFLOP"));
            flopStats.put(4, playerStatsCalc(players.get(4), "FLOP"));
            turnStats.put(4, playerStatsCalc(players.get(4), "TURN"));
            riverStats.put(4, playerStatsCalc(players.get(4), "RIVER"));
        }
        return players;
    }

    /**
     * Ищет игрока в БД. Если не находит, использует {@link AddOrUpdatePlayer}
     * Если информации на игрока все равно нет,
     * отправляет "пустого игрока" (Empty Seat)
     * и создает не пробрасываемое @exception {@link PlayerNotFoundException}
     */
    private Player setPlayer(String playerName, int i) {
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
            System.out.println("Player not found, set default value");
            player = playerRepo.findByNickname("Empty Seat");
            setPlayerNotFoundException(new PlayerNotFoundException(i, playerName));
        }

        return player;
    }

    //Инициализирует статы всех игроков для таблицы с необходимой улицей торгов на основании HUD пользователя и сохраняет их в мапу.
    //Ключ - номер игрока, Значение - двумерный массив статов
    public Map<Integer, StatInfo[][]> statsInit(List<Player> players, String table) {
        Map<Integer, StatInfo[][]> playerStat = new HashMap<>();
        int key = 0; //Ключ для мапы - номер игрока в списке.
        for (Player pl : players) {
            //Сохраняем высчитанные статы как значение в map.
            playerStat.put(key, playerStatsCalc(pl, table));
            key++;
        }

        return playerStat;
    }

    /**
     * Высчитывает статы игрока для таблицы с необходимой улицей торгов {@link RoundOfBidding}
     * на основании HUD пользователя. {@link com.stsetsevich.smartpoker.domain.Hud}
     */
    public StatInfo[][] playerStatsCalc(Player player, String table) {
        Map<Integer, StatInfo[][]> playerStat = new HashMap<>();

        StatInfo statInfo;
        //Получает двумерный массив с названиями статов, которые предустановлены в HUD пользователя.
        String[][] statnames = hudEdit.convertStatFromNumberToStringView(RoundOfBidding.valueOf(table));
        //Будет хранить объекты с подробной информацией о стате
        StatInfo[][] stats = new StatInfo[statnames.length][statnames[0].length];

        //проходит по массиву и получает подробную информацию о стате на основании его названия
        //сохраняет объект в stats[][]
        for (int i = 0; i < statnames.length; i++) {
            for (int j = 0; j < statnames[0].length; j++) {
                statInfo = statInfoMother.getStatInfo();
                statInfo.setInfo(statnames[i][j], player);
                stats[i][j] = statInfo;
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
