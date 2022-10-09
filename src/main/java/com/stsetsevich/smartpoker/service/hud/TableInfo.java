package com.stsetsevich.smartpoker.service.hud;

/**
 * Хранит назвние стата и его значение. Используется классом @{@link TableInfoCalc}
 */
public class TableInfo {

    private String stat;
    private double value;
    TableInfo(String stat, double value)
    {
        this.stat=stat;
        this.value=value;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
