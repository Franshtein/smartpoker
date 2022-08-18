package com.stsetsevich.smartpoker.engine;

public class StatValue {
    private String stat;
    private int value;

    public StatValue(String stat, int value) {
        this.stat = stat;
        this.value = value;
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
}
