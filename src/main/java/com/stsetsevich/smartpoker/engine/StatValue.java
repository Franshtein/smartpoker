package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;

public class StatValue {
    enum StatColor
    {
        INFINITY_TO_POINT1("colorblue"), POINT1_TO_POINT2("coloryellow"), POINT2_TO_POINT3("colorred"), POINT3_TO_POINT4("colorpink"), POINT4_TO_INFINITY("colorgreen");
        private String color;

        StatColor(String color) {
            this.color=color;
        }
        private String getColor()
        {
            return color;
        }
    }
    private String stat;
    private int value;
    private String address;
    private String statName;
    private String statColor;
    private String dependOnStatName;

    public StatValue(String stat, int value, String statName) {
        this.stat = stat;
        this.value = value;
        this.statName=statName;
        this.address="#";
        setStatColor(value);
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
        setStatColor(value);
    }
    public StatValue(String stat, int value, String statName, String dependOnStatName) {
        this.stat = stat;
        this.value = value;
        this.statName=statName;
        this.dependOnStatName=dependOnStatName;
        this.address="#";
        setStatColor(value);
    }
    public StatValue(String stat, int value, boolean needHref, String statName, String dependOnStatName, Player player) {
        this.stat = stat;
        this.value = value;
        this.statName=statName;
        this.dependOnStatName=dependOnStatName;
        if(needHref == true)
        {
            this.address="extrastats?player="+player.getNickname()+"&stat="+statName;
        }
        else this.address="#";
        setStatColor(value);
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

    public String getStatColor() {
        return statColor;
    }

    public void setStatColor(int value) {
        if(value==0) this.statColor=StatColor.INFINITY_TO_POINT1.getColor();
        else if(value==1) this.statColor=StatColor.POINT1_TO_POINT2.getColor();
        else if(value==2) this.statColor=StatColor.POINT2_TO_POINT3.getColor();
        else if(value==3) this.statColor=StatColor.POINT3_TO_POINT4.getColor();
        else this.statColor=StatColor.POINT4_TO_INFINITY.getColor();

    }

}

