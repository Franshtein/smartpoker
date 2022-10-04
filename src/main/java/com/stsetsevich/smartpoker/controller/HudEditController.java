package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.*;
import com.stsetsevich.smartpoker.engine.edithud.HudEdit;
import com.stsetsevich.smartpoker.repos.HudRepo;
import com.stsetsevich.smartpoker.repos.StatRepo;
import com.stsetsevich.smartpoker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HudEditController {
    @Autowired
    StatRepo statRepo;
    @Autowired
    HudEdit hudEdit;
    @Autowired
    HudRepo hudRepo;
    @Autowired
    UserRepo userRepo;

    @GetMapping("/hud-edit")
    public String main(Model model1) {
        List<Stat> stats = statRepo.findAllByStatnameIsNotNullOrderById();
        Comparator<Stat> comparator = Comparator.comparing(obj -> obj.getStatname());
        Collections.sort(stats, comparator);
        String[][] statnames = hudEdit.parseStatFromNumberToStringView(RoundOfBidding.valueOf("PREFLOP"));
        model1.addAttribute("requiredstat", "-");
        model1.addAttribute("stats", stats);
        model1.addAttribute("numrows", statnames.length);
        model1.addAttribute("numcols", statnames[0].length);
        model1.addAttribute("roundOfBidding", "PREFLOP");
        model1.addAttribute("statsTable", statnames);
        return "hud-edit";
    }


    @PostMapping("/sethud")
    public String add2(Model model1, int numcols, int numrows, String allstatsname, String roundOfBidding) {

        allstatsname = hudEdit.parseStatFromStringToNumberView(allstatsname, numrows, numcols, roundOfBidding);

        List<Stat> stats = statRepo.findAllByStatnameIsNotNullOrderById();

        Comparator<Stat> comparator = Comparator.comparing(obj -> obj.getStatname());
        Collections.sort(stats, comparator);

        String[][] statnames = hudEdit.parseStatFromNumberToStringView(RoundOfBidding.valueOf(roundOfBidding));
        model1.addAttribute("statsTable", statnames);
        model1.addAttribute("roundOfBidding", roundOfBidding);
        model1.addAttribute("requiredstat", "-");
        model1.addAttribute("stats", stats);
        model1.addAttribute("numrows", numrows);
        model1.addAttribute("numcols", numcols);
        return "hud-edit";
    }

    @PostMapping("/createhud")
    public String add3(Model model1, int numcols, int numrows, String allstatsname, String roundOfBidding) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username);
        Hud hud = hudRepo.findByUserIdAndRoundOfBidding(user.getId(), RoundOfBidding.valueOf(roundOfBidding));
        if (hud != null) {
            if (hud.getNumberOfRows() == numrows && hud.getNumberOfColums() == numcols) {
                String[][] statnames = hudEdit.parseStatFromNumberToStringView(RoundOfBidding.valueOf(roundOfBidding));
                model1.addAttribute("statsTable", statnames);
            } else setTable(numrows, numcols, model1);

        } else setTable(numrows, numcols, model1);

        List<Stat> stats = statRepo.findAllByStatnameIsNotNullOrderById();
        Comparator<Stat> comparator = Comparator.comparing(obj -> obj.getStatname());
        Collections.sort(stats, comparator);

        model1.addAttribute("requiredstat", "-");
        model1.addAttribute("stats", stats);
        model1.addAttribute("numrows", numrows);
        model1.addAttribute("numcols", numcols);
        model1.addAttribute("roundOfBidding", roundOfBidding);
        return "hud-edit";
    }

    private static void setTable(int numrows, int numcols, Model model1) {
        int[] masrows = new int[numrows];

        for (int i = 0; i < numrows; i++) {
            masrows[i] = i;
        }
        int[] mascols = new int[numcols];
        for (int i = 0; i < numcols; i++) {
            mascols[i] = i;
        }

        model1.addAttribute("numrowscount", masrows);
        model1.addAttribute("numcolscount", mascols);
    }
}
