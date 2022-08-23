package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;

public class StatValue {
    private String stat;
    private int value;
    private String address;
    private String statName;

    public StatValue(String stat, int value, String statName) {
        this.stat = stat;
        this.value = value;
        this.statName=statName;
        this.address="#";
    }
    public StatValue(String stat, int value, boolean needHref, String statName, Player player) {
        this.stat = stat;
        this.value = value;
        this.statName=statName;
        if(needHref == true)
        {
            this.address="extrastats?player="+player.getNickname()+"&stat="+statName;
        }
        else this.address="#";
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }
}
