package com.stsetsevich.smartpoker.engine.edithud;

import com.stsetsevich.smartpoker.domain.*;
import com.stsetsevich.smartpoker.repos.HudRepo;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import com.stsetsevich.smartpoker.repos.StatRepo;
import com.stsetsevich.smartpoker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class HudEdit {
    @Autowired
    StatRepo statRepo;
    @Autowired
    PlayerRepo playerRepo;
    @Autowired
    EntityManager entityManager;
    @Autowired
    UserRepo userRepo;
    @Autowired
    HudRepo hudRepo;

    public HudEdit() {
    }

    public String parseStatFromStringToNumberView(String stats, int numrows, int numcols, String roundOfBidding) {
        //stats= stats.replace("/", "\n");
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
        /////////////////////////
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username);
       // Hud hud = hudRepo.findByUserId(user.getId());
        Hud hud = hudRepo.findByUserIdAndRoundOfBidding(user.getId(), RoundOfBidding.valueOf(roundOfBidding));
        if (hud == null) hud = new Hud();
        hud.setUserId(user.getId());
        hud.setNumberOfRows(numrows);
        hud.setNumberOfColums(numcols);
        hud.setStatsId(statNumbers);
        hud.setRoundOfBidding(RoundOfBidding.valueOf(roundOfBidding));
        hudRepo.save(hud);
       // parseStatFromNumberToStringView();
        return statNumbers;
    }

    public String[][] parseStatFromNumberToStringView(RoundOfBidding roundOfBidding) {

        ////////////////////////////
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username);
        Hud hud = hudRepo.findByUserIdAndRoundOfBidding(user.getId(), roundOfBidding);
        if (hud == null) hud = hudRepo.findByUserIdAndRoundOfBidding(0L, roundOfBidding);

        String numbers = hud.getStatsId();
        String statNames = "";
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
                statNames += stat.get().getStatname();
                statNames += "/";
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

}
