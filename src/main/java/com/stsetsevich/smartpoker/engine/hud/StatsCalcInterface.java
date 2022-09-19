package com.stsetsevich.smartpoker.engine.hud;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.engine.StatInfo;
import com.stsetsevich.smartpoker.repos.StatRepo;

import java.util.ArrayList;
import java.util.HashMap;

public interface StatsCalcInterface {

    HashMap<Integer, ArrayList<StatInfo>> hudStatsCalcLine1(ArrayList<Player> players, StatRepo statRepo);
    HashMap<Integer, ArrayList<StatInfo>> hudStatsCalcLine2(ArrayList<Player> players, StatRepo statRepo);
    HashMap<Integer, ArrayList<StatInfo>> hudStatsCalcLine3(ArrayList<Player> players, StatRepo statRepo);
    HashMap<Integer, ArrayList<StatInfo>> hudStatsCalcLine4(ArrayList<Player> players, StatRepo statRepo);
    HashMap<Integer, ArrayList<StatInfo>> hudStatsCalcLine5(ArrayList<Player> players, StatRepo statRepo);
}
