package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.CalcDiapVariant;
import com.stsetsevich.smartpoker.domain.Hud;
import com.stsetsevich.smartpoker.domain.Stat;
import com.stsetsevich.smartpoker.domain.User;
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
        model1.addAttribute("needstat", "-");
        model1.addAttribute("stats", stats);
        model1.addAttribute("numrows", 5);
        model1.addAttribute("numcols", 5);
        model1.addAttribute("roundOfBidding", "PREFLOP");
        return "hud-edit";
    }

    @PostMapping("/hud-edit")
    public String add(Model model1, int numcols, int numrows, String allstatsname) {

        System.out.println(allstatsname);
        List<Stat> stats = statRepo.findAllByStatnameIsNotNullOrderById();
        Comparator<Stat> comparator = Comparator.comparing(obj -> obj.getStatname());
        Collections.sort(stats, comparator);
        model1.addAttribute("needstat", "-");
        model1.addAttribute("stats", stats);
        model1.addAttribute("numrows", numrows);
        model1.addAttribute("numcols", numcols);
        return "hud-edit";
    }
    @PostMapping("/sethud")
    public String add2(Model model1,int numcols, int numrows, String allstatsname, String roundOfBidding) {
        System.out.println("working");
        //System.out.println(allstatsname);
        //allstatsname= allstatsname.replace("/", "\n");
        //System.out.println(allstatsname);
        allstatsname = hudEdit.parseStatFromStringToNumberView(allstatsname, numrows, numcols, roundOfBidding);


        List<Stat> stats = statRepo.findAllByStatnameIsNotNullOrderById();
        Comparator<Stat> comparator = Comparator.comparing(obj -> obj.getStatname());
        Collections.sort(stats, comparator);
        model1.addAttribute("needstat", "-");
        model1.addAttribute("stats", stats);
        model1.addAttribute("numrows", numrows);
        model1.addAttribute("numcols", numcols);
        return "hud-edit";
    }
    @PostMapping("/createhud")
    public String add3(Model model1, int numcols, int numrows, String allstatsname, String roundOfBidding) {

        System.out.println(numcols +" "+ numrows);
        //System.out.println(allstatsname);



        List<Stat> stats = statRepo.findAllByStatnameIsNotNullOrderById();
        Comparator<Stat> comparator = Comparator.comparing(obj -> obj.getStatname());
        Collections.sort(stats, comparator);

        int[] masrows = new int[numrows];

        for(int i=0;  i<numrows; i++)
        {
            masrows[i]=i;
        }
        int[] mascols = new int[numcols];
        for(int i=0;  i<numcols; i++)
        {
            mascols[i]=i;
        }



        model1.addAttribute("numrowscount", masrows);
        model1.addAttribute("numcolscount", mascols);
        model1.addAttribute("needstat", "-");
        model1.addAttribute("stats", stats);
        model1.addAttribute("numrows", numrows);
        model1.addAttribute("numcols", numcols);
        model1.addAttribute("roundOfBidding", roundOfBidding);
        return "hud-edit";
    }
}
