package com.stsetsevich.smartpoker.service.edithud;

import com.stsetsevich.smartpoker.domain.*;
import com.stsetsevich.smartpoker.repos.HudRepo;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import com.stsetsevich.smartpoker.repos.StatRepo;
import com.stsetsevich.smartpoker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * Обслуживает {@link com.stsetsevich.smartpoker.controller.HudEditController}
 * 1.Конвертирует названия стата в число (на основе его ID).
 * Записывает последовательность конвертированных чисел в строку.
 * Сохраняет строку в БД {@link Hud}
 * 2.Проводит обратные манипуляции и отдает таблицу с названиями статов для {@link com.stsetsevich.smartpoker.controller.HudEditController}
 * 3.Устанавливает пользователю HUD по умолчанию.
 */
@Service
public class HudEdit {
    @Autowired
    private StatRepo statRepo;
    @Autowired
    private  PlayerRepo playerRepo;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private HudRepo hudRepo;

    public HudEdit() {
    }

    /**
     * Конвертирует названия стата в число (на основе его ID).
     * * Записывает последовательность конвертированных чисел в строку.
     * * Сохраняет строку в БД {@link Hud}
     */
    public void convertStatFromStringToNumberView(String stats, int numrows, int numcols, String roundOfBidding) {

        String statNumbers = "";
        long number;
        int firstFound = 0;
        int secondFound;
        for (int i = 1; i < stats.length(); i++) {
            if (stats.charAt(i) == '/') {
                secondFound = i;
                number = statRepo.findStatByStatname(stats.substring(firstFound, secondFound)).getId();
                statNumbers += number;
                statNumbers += "/";
                firstFound = secondFound + 1;

            }
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username);

        Hud hud = hudRepo.findByUserIdAndRoundOfBidding(user.getId(), RoundOfBidding.valueOf(roundOfBidding));
        if (hud == null) hud = new Hud();
        hud.setUserId(user.getId());
        hud.setNumberOfRows(numrows);
        hud.setNumberOfColums(numcols);
        hud.setStatsId(statNumbers);
        hud.setRoundOfBidding(RoundOfBidding.valueOf(roundOfBidding));
        hudRepo.save(hud);

    }

    /**
     * Конвертирует числа из БД в название стата (на основе его ID).
     * Записывает последовательность конвертированных строк в таблицу (двумерный массив).
     * отдает таблицу с названиями статов для {@link com.stsetsevich.smartpoker.controller.HudEditController}
     */
    public String[][] convertStatFromNumberToStringView(RoundOfBidding roundOfBidding) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username);
        Hud hud = hudRepo.findByUserIdAndRoundOfBidding(user.getId(), roundOfBidding);
        if (hud == null) hud = hudRepo.findByUserIdAndRoundOfBidding(0L, roundOfBidding);

        String numbers = hud.getStatsId();
        long number;
        String[][] stats = new String[hud.getNumberOfRows()][hud.getNumberOfColums()];
        int firstFound = 0;
        int secondFound;
        int i = 0;
        int j = 0;
        for (int ch = 0; ch < numbers.length(); ch++) {
            if (numbers.charAt(ch) == '/') {
                secondFound = ch;
                number = Long.parseLong(numbers.substring(firstFound, secondFound));
                Optional<Stat> stat = statRepo.findById(number);

                stats[i][j] = stat.get().getStatname();
                firstFound = secondFound + 1;
                j++;
                if (j == hud.getNumberOfColums()) {
                    j = 0;
                   i++;
                }
            }
        }

        return stats;
    }

    //устанавливает для пользователь HUD по умолчанию.
    public void setDefaultHud(RoundOfBidding roundOfBidding) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username);

        Hud defaultHud = hudRepo.findByUserIdAndRoundOfBidding(0L, roundOfBidding);
        Hud userHud = hudRepo.findByUserIdAndRoundOfBidding(user.getId(), roundOfBidding);

        if (userHud == null) userHud = new Hud();
        userHud.setUserId(user.getId());
        userHud.setStatsId(defaultHud.getStatsId());
        userHud.setRoundOfBidding(defaultHud.getRoundOfBidding());
        userHud.setNumberOfRows(defaultHud.getNumberOfRows());
        userHud.setNumberOfColums(defaultHud.getNumberOfColums());
        hudRepo.save(userHud);

    }


}
