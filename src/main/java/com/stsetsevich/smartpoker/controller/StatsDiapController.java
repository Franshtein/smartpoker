package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.CalcDiapVariant;
import com.stsetsevich.smartpoker.domain.Stat;
import com.stsetsevich.smartpoker.repos.StatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Controller
public class StatsDiapController {
    @Autowired
    StatRepo statRepo;

    @GetMapping("/statsdiap")
    public String main(Model model1) {

        //  Iterable<Stat> stats = statRepo.findAll();
        ArrayList<Stat> stats = statRepo.findAllByStatnameIsNotNullOrderById();
        Comparator<Stat> comparator = Comparator.comparing(obj -> obj.getStatname());
        Collections.sort(stats, comparator);
        model1.addAttribute("stats", stats);
        return "statsdiap";
    }

    @PostMapping("/statsdiap")
    public String add(String statname, double point1, double point2, double point3, double point4, boolean needLink, boolean needImage, String dependOnStat, CalcDiapVariant calcDiapVariant, Model model1) {

        statRepo.findStatByStatname(statname);

        Stat stat = statRepo.findStatByStatname(statname);
        stat.setPoint1(point1);
        stat.setPoint2(point2);
        stat.setPoint3(point3);
        stat.setPoint4(point4);
        stat.setNeedLink(needLink);
        stat.setNeedImage(needImage);
        stat.setDependOnStat(dependOnStat);

        stat.setCalcDiapVariant(calcDiapVariant);
        statRepo.save(stat);

        ArrayList<Stat> stats = statRepo.findAllByStatnameIsNotNullOrderById();
        Comparator<Stat> comparator = Comparator.comparing(obj -> obj.getStatname());
        Collections.sort(stats, comparator);
        model1.addAttribute("stats", stats);

        return "statsdiap";
    }
}
