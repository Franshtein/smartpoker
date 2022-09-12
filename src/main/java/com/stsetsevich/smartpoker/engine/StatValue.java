package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.Player;

import java.io.File;

public class StatValue {
    enum StatColor
    {
        INFINITY_TO_POINT1("colorblue"), POINT1_TO_POINT2("coloryellow"), POINT2_TO_POINT3("colorred"), POINT3_TO_POINT4("colorgreen"), POINT4_TO_INFINITY("colorpink");
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
    private double dependOnStat=0;
    private String picture;


    public StatValue(String stat, int value, String statName) {
        this.stat = stat;
        this.value = value;
        this.statName=statName;
        this.address="#";
        setStatColor(value);
        setPicture();
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
        setPicture();
    }
    public StatValue(String stat, int value, String statName, Double dependOnStat) {
        this.stat = stat;
        this.value = value;
        this.statName=statName;
        this.dependOnStat=dependOnStat;
        this.address="#";
        setStatColor(value);
        setPicture();
    }
    public StatValue(String stat, int value, boolean needHref, String statName, Double dependOnStat, Player player) {
        this.stat = stat;
        this.value = value;
        this.statName=statName;
        this.dependOnStat=dependOnStat;
        if(needHref == true)
        {
            this.address="extrastats?player="+player.getNickname()+"&stat="+statName;
        }
        else this.address="#";
        setStatColor(value);
        setPicture();
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

    public double getDependOnStat() {
        return dependOnStat;
    }

    public void setDependOnStat(double dependOnStat) {
        this.dependOnStat = dependOnStat;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture() {
        String picture;
        if(!stat.equals("-")) {
            if (getDependOnStat() == 0) {
                double dstat = Double.parseDouble(this.stat);
                int istat = (int) dstat;
                // System.out.println(istat);
                picture = "/img/" + statName + "/" + istat + ".png";
                // System.out.println(picture);
            } else {
                double dstat = Double.parseDouble(this.stat);
                int istat = (int) dstat;
                int depIStat = (int) getDependOnStat();
                // System.out.println(istat);
                picture = "/img/" + statName + "/" + depIStat + "-" + istat + ".png";
                // System.out.println(picture);
            }
            File f = new File("E:/idea_projects/smartpoker/src/main/resources/static" + picture);
            if (f.exists() && !f.isDirectory()) {
                System.out.println("FILE EXSIST");
            } else picture = null;
        }
        else picture = null;
        this.picture = picture;
    }

    public void setStatColor(int value) {
        if(value==0) this.statColor=StatColor.INFINITY_TO_POINT1.getColor();
        else if(value==1) this.statColor=StatColor.POINT1_TO_POINT2.getColor();
        else if(value==2) this.statColor=StatColor.POINT2_TO_POINT3.getColor();
        else if(value==3) this.statColor=StatColor.POINT3_TO_POINT4.getColor();
        else this.statColor=StatColor.POINT4_TO_INFINITY.getColor();

    }



}

