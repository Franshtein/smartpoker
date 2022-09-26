package com.stsetsevich.smartpoker.controller;


import com.stsetsevich.smartpoker.engine.*;
import com.stsetsevich.smartpoker.engine.parse.ParsePlayer;
import com.stsetsevich.smartpoker.engine.parse.StatsParse;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import com.stsetsevich.smartpoker.repos.UserRepo;
import com.stsetsevich.smartpoker.repos.UserSmarthandAccountAndCookiesRepo;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

@Controller
public class ParsePlayerController {


    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserSmarthandAccountAndCookiesRepo userSmarthandAccountAndCookiesRepo;
    @Autowired
    SetPlayersAtTable setPlayersAtTable;
    @Autowired
    ParsePlayer parsePlayer;
    @Autowired AddOrUpdatePlayer addOrUpdatePlayer;

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String searchPlayer(Map<String, Object> model) {

        return "search";
    }

    @PostMapping("/addFile")
    public String addPlayer(@RequestParam String addFile, Map<String, Object> model) {
        try {

            File file = new File("E:/nicks.txt");
            Scanner scanner;
            String string;
            int i = 0;

            scanner = new Scanner(file);

            while (scanner.hasNext()) {

                string = scanner.nextLine();
                addOrUpdatePlayer.tryAddNewPlayer(string);

            }


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return "search";
    }

    @PostMapping("/addFile2")
    public String addPlayer2(@RequestParam String addFile2, Map<String, Object> model) {
    /*    if (addFile != "") {
            StatsParse statsParse = new StatsParse(addFile);
            if (statsParse.getStats() != null) {
                if (playerRepo.findByNickname(statsParse.searchNickname()) == null) {
                    playerRepo.save(statsParse.getStats());
                } else System.out.println("That player excists in the DataBase");
            }
        }
    */
        try {

            Document document = parsePlayer.parsePlayer(addFile2);
            if (document != null) {
                StatsParse statsParse = new StatsParse(document);
                if (statsParse.getStats() != null) {
                    if (playerRepo.findByNickname(statsParse.searchNickname()) == null) {
                        playerRepo.save(statsParse.getStats());
                    } else System.out.println("That player excists in the DataBase");
                }
            } else System.out.println("There is no data on player " + addFile2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "search";
    }
}