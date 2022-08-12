package com.stsetsevich.smartpoker.engine;


import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HudCalc {
    @Autowired
    PlayerRepo playerRepo;

    public ArrayList<String> getPlayerStats(PlayerRepo playerRepo, String nickname)
    {
       Player player =  playerRepo.findByNickname(nickname);
       ArrayList<String> stats = new ArrayList<>();
       stats.add(player.getNickname());
       stats.add(Double.toString(player.getVpip()));
       stats.add(Double.toString(player.getTotalPfr()));
       stats.add(Double.toString(player.getTotal3bet()));
       stats.add(Double.toString(player.getFoldTo3betTotal()));
       return stats;
    }

    public ArrayList<Player> getAllPlayerStats(String p1, String p2,String p3,String p4,String p5,String p6)
    {
        return null;
    }
}
