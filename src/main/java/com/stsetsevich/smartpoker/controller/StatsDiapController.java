package com.stsetsevich.smartpoker.controller;

import com.stsetsevich.smartpoker.domain.Message;
import com.stsetsevich.smartpoker.domain.Stat;
import com.stsetsevich.smartpoker.repos.StatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

@Controller
public class StatsDiapController {
    @Autowired
    StatRepo statRepo;

    @GetMapping("/statsdiap")
    public String main(Model model1) {

     //  Iterable<Stat> stats = statRepo.findAll();
        ArrayList<Stat> stats = statRepo.findAllByStatnameIsNotNullOrderById();
        model1.addAttribute("stats", stats);
        return "statsdiap";
    }

    @PostMapping("/statsdiap")
    public String add(String statname, double point1, double point2,double point3,double point4, Model model1) {

        System.out.println(statname+point1+point2+point3+point4);

        statRepo.findStatByStatname(statname);
        System.out.println(statRepo.findStatByStatname(statname).getId());
        Stat stat = statRepo.findStatByStatname(statname);
        stat.setPoint1(point1);
        stat.setPoint2(point2);
        stat.setPoint3(point3);
        stat.setPoint4(point4);
        statRepo.save(stat);
       // Iterable<Stat> stats = statRepo.findAll();
        ArrayList<Stat> stats = statRepo.findAllByStatnameIsNotNullOrderById();
        model1.addAttribute("stats", stats);

        return "statsdiap";
    }
}
