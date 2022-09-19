package com.stsetsevich.smartpoker.engine;

import com.stsetsevich.smartpoker.domain.CalcDiapVariant;
import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.domain.Stat;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import com.stsetsevich.smartpoker.repos.StatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class StatInfo {
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
    @Autowired
    StatRepo statRepo;
    @Autowired
    PlayerRepo playerRepo;
    @Autowired
    EntityManager entityManager;


    private Player player;
    private Stat statTry;
    private String stat;
    private double statValue;
    private double[] points;
    private int statColorDiap;
    private String statColor;
    private String picture;
    private double dependOnStat=0;
    private String address;
    private String statName;


    @Lookup
    public StatInfo getStatInfo() {
        return null;
    }
    public StatInfo()
    {

    }

    public void setInfo(String stat)
    {
        this.statName=stat;
        this.stat=stat;
        this.address="#";
    }
    public void setInfo(String stat, Player player)
    {
        this.player=player;
        this.statName=stat;
        this.stat=stat;
        this.statTry =statRepo.findStatByStatname(stat);
        setStatValue();
        setPoints();
        this.statColorDiap=checkDiap();
        setStatColor();
        this.address="#";
        setPicture();

    }
    public void setInfo(String stat, Player player, boolean needHref)
    {
        this.player=player;
        this.statName=stat;
        this.stat=stat;
        this.statTry =statRepo.findStatByStatname(stat);
        setStatValue();
        setPoints();
        this.statColorDiap=checkDiap();
        setStatColor();
        if(needHref == true)
        {
            this.address="extrastats?player="+player.getNickname()+"&stat="+statName;
        }
        else this.address="#";
        setPicture();
    }
    private void setStatValue()
    {
        Query query = entityManager.createNativeQuery("SELECT "+ statTry.getStatname()+" FROM player where nickname='"+player.getNickname()+"'");
        List<String> list=query.getResultList();
        List<Object> list2 = new ArrayList<>(list);
        stat=list2.get(0).toString();
        this.statValue=Double.parseDouble(stat);
    }
    private void setPoints()
    {
        this.points= statTry.getPoints();
    }
    private int checkDiap() {
        CalcDiapVariant variant = statTry.getCalcDiapVariant();
        if (variant == CalcDiapVariant.ONE) {
            if (statValue <= points[0]) return 0;
            if (statValue <= points[1]) return 1;
            if (statValue <= points[2]) return 2;
            if (statValue <= points[3]) return 3;
            return 4;
        }
        if (variant == CalcDiapVariant.TWO) {
            if (statValue <= points[0]) return 0;
            if (statValue <= points[1]) return 1;
            if (statValue <= points[2]) return 3;
            if (statValue <= points[3]) return 2;
            return 2;
        }
        if (variant == CalcDiapVariant.THREE) {
            if (statValue >= points[3]) return 0;
            if (statValue >= points[2]) return 1;
            if (statValue >= points[1]) return 2;
            if (statValue >= points[0]) return 3;
            return 4;
        }
        return 0;
    }
    public void setStatColor() {
        if(statColorDiap==0) this.statColor= StatColor.INFINITY_TO_POINT1.getColor();
        else if(statColorDiap==1) this.statColor= StatColor.POINT1_TO_POINT2.getColor();
        else if(statColorDiap==2) this.statColor= StatColor.POINT2_TO_POINT3.getColor();
        else if(statColorDiap==3) this.statColor= StatColor.POINT3_TO_POINT4.getColor();
        else this.statColor= StatColor.POINT4_TO_INFINITY.getColor();

    }

    public void setPicture() {
        String picture;
        if(!statTry.equals("-")) {
            if (dependOnStat == 0) {
                double dstat = statValue;
                // int istat = (int) dstat;
                int istat = Math.round((int) dstat);
                // System.out.println(istat);
                //picture = "/img/" + statName + "/" + istat + ".png";
                picture = "/img/diap/" + istat + ".png";
                // System.out.println(picture);
            } else {
                double dstat = statValue;
                int istat = Math.round((int) dstat);
                int depIStat = Math.round((int) dependOnStat);
                // System.out.println(istat);
                picture = "/img/" + statTry.getStatname() + "/" + depIStat + "-" + istat + ".png";
                // System.out.println(picture);
            }
            File f = new File("E:/idea_projects/smartpoker/src/main/resources/static" + picture);
            if (f.exists() && !f.isDirectory()) {
                //  System.out.println("FILE EXSIST");
            } else picture = null;
        }
        else picture = null;
        this.picture = picture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Stat getStatTry() {
        return statTry;
    }

    public void setStatTry(Stat statTry) {
        this.statTry = statTry;
    }

    public double getStatValue() {
        return statValue;
    }

    public void setStatValue(double statValue) {
        this.statValue = statValue;
    }

    public double[] getPoints() {
        return points;
    }

    public void setPoints(double[] points) {
        this.points = points;
    }

    public int getStatColorDiap() {
        return statColorDiap;
    }

    public void setStatColorDiap(int statColorDiap) {
        this.statColorDiap = statColorDiap;
    }

    public String getStatColor() {
        return statColor;
    }

    public void setStatColor(String statColor) {
        this.statColor = statColor;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getDependOnStat() {
        return dependOnStat;
    }

    public void setDependOnStat(double dependOnStat) {
        this.dependOnStat = dependOnStat;
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
