package com.stsetsevich.smartpoker.engine.hud;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.StatValue;
import com.stsetsevich.smartpoker.repos.StatRepo;

import java.util.ArrayList;
import java.util.HashMap;

public interface StatsCalcInterface {

    HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine1(ArrayList<Player> players, StatRepo statRepo);
    HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine2(ArrayList<Player> players, StatRepo statRepo);
    HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine3(ArrayList<Player> players, StatRepo statRepo);
    HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine4(ArrayList<Player> players, StatRepo statRepo);
    HashMap<Integer, ArrayList<StatValue>> hudStatsCalcLine5(ArrayList<Player> players, StatRepo statRepo);
}
