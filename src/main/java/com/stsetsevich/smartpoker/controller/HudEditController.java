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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/hud-edit")
public class HudEditController {
    @Autowired
    StatRepo statRepo;
    @Autowired
    HudEdit hudEdit;
    @Autowired
    HudRepo hudRepo;
    @Autowired
    UserRepo userRepo;

    @GetMapping
    public String editHud(Model model1) {
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
    @GetMapping("{roundOfBidding}")
    public String rollback(Model model1,@PathVariable("roundOfBidding") String roundOfBidding) {
        List<Stat> stats = statRepo.findAllByStatnameIsNotNullOrderById();
        Comparator<Stat> comparator = Comparator.comparing(obj -> obj.getStatname());
        Collections.sort(stats, comparator);
        RoundOfBidding roundOfBidding1=null;
        try {
            roundOfBidding1= RoundOfBidding.valueOf(roundOfBidding);
        }
        catch (Exception exception)
        {
            System.out.println("variable not valid");
        }
        if (roundOfBidding1==null) roundOfBidding1= RoundOfBidding.valueOf("PREFLOP");
        String[][] statnames = hudEdit.parseStatFromNumberToStringView(roundOfBidding1);
        model1.addAttribute("requiredstat", "-");
        model1.addAttribute("stats", stats);
        model1.addAttribute("numrows", statnames.length);
        model1.addAttribute("numcols", statnames[0].length);
        model1.addAttribute("roundOfBidding", roundOfBidding1);
        model1.addAttribute("statsTable", statnames);
        return "hud-edit";
    }


    @PostMapping("/sethud")
    public String setHud(Model model1, int numcols, int numrows, String allstatsname, String roundOfBidding) {

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
    public String createHud(Model model1, int numcols, int numrows, String allstatsname, String roundOfBidding) {


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

    @PostMapping("/setdefaulthud")
    public String setDefaultHud(Model model1, String roundOfBidding) {

        Hud hud = hudRepo.findByUserIdAndRoundOfBidding(0l, RoundOfBidding.valueOf(roundOfBidding));
            String[][] statnames = hudEdit.setDefaultHud(RoundOfBidding.valueOf(roundOfBidding));
            model1.addAttribute("statsTable", statnames);


            List<Stat> stats = statRepo.findAllByStatnameIsNotNullOrderById();
            Comparator<Stat> comparator = Comparator.comparing(obj -> obj.getStatname());
            Collections.sort(stats, comparator);

            model1.addAttribute("requiredstat", "-");
            model1.addAttribute("stats", stats);
            model1.addAttribute("numrows", hud.getNumberOfRows());
            model1.addAttribute("numcols", hud.getNumberOfColums());
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
