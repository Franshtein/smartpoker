package com.stsetsevich.smartpoker.service.hud;

import com.stsetsevich.smartpoker.domain.CalcDiapVariant;
import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.domain.Stat;
import com.stsetsevich.smartpoker.repos.PlayerRepo;
import com.stsetsevich.smartpoker.repos.StatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Заполняет и хранит всю необходимую инфу о стате игрока. Используется для создания таблиц статов в
 * {@link PlayersAtTable}
 */
@Service
@Scope("prototype")
public class StatInfo {
    enum StatColor {
        INFINITY_TO_POINT1("colorblue"), POINT1_TO_POINT2("coloryellow"), POINT2_TO_POINT3("colorred"), POINT3_TO_POINT4("colorgreen"), POINT4_TO_INFINITY("colorpink"), DEFAULT("colorblack");
        private String color;

        StatColor(String color) {
            this.color = color;
        }

        private String getColor() {
            return color;
        }
    }

    @Autowired
    private StatRepo statRepo;
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private StatInfoService statInfoService;

    private Player player;
    private Stat statObject;
    private String statName; //хранит название стата
    private double statValue; //хранит значение стата
    private String statValueStr = "-"; //Если стат не "-", примет значение StatValue. Нужен для вывода в худ.
    private double[] points; //точки для вычисления диапазона, в который попадает значение стата
    private int statColorDiap; //хранит диапазон, в который попадает значение стата. Нужен для опр. цвета
    private String statColor; //вычисленное название css-селектора с цветом стата.
    private String picture; //адрес картинки с диапазоном
    private double dependOnStat = 0; //от какого стата зависит это стат. По умолчанию 0 - это "-". То есть зависимостей нет

    @Lookup
    public StatInfo getStatInfo() {
        return null;
    }

    public StatInfo() {

    }

    //"Фасад", рассчитывает всю необходимую инфу для класса.
    public void setInfo(String stat, Player player) {
        this.player = player;
        this.statName = stat;
        this.statObject = statRepo.findStatByStatname(stat);
        if (!statName.equals("-")) {
            setStatValue();
            statValueStr = String.valueOf(statValue);
            if(statName.equals("total_hands"))
            {
                int totalHands=(int) statValue;
                statValueStr = String.valueOf(totalHands);
            }
            setPoints();
            //Если стат не зависит от другого - рассчитываем обычный диапазон для цвета
            if (statObject.getDependOnStat().equals("-")) this.statColorDiap = checkDiap();
                //рассчитываем зависимый диапазон
            else this.statColorDiap = checkDependentDiap();
            setStatColor();
            setPicture();
        } else {
            this.statColorDiap = 5; //черный цвет для пустого стата '-'
            setStatColor();
        }

    }

    private void setStatValue() {
        Query query = entityManager.createNativeQuery("SELECT " + statObject.getStatname() + " FROM player where nickname='" + player.getNickname() + "'");
        List<String> list = query.getResultList();
        List<Object> list2 = new ArrayList<>(list);
        String stat = list2.get(0).toString();
        this.statValue = Double.parseDouble(stat);
    }

    private void setPoints() {
        this.points = statObject.getPoints();
    }

    private int checkDiap() {
        //у каждого стата свой вариант расчета диапазона для цвета
        CalcDiapVariant variant = statObject.getCalcDiapVariant();
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

    //сначала рассчитывается диапазон основного стата. Потом он влияет на это, зависимый, стат.
    protected int checkDependentDiap() {
        CalcDiapVariant variant = statObject.getCalcDiapVariant();

        int primaryValue = statInfoService.checkPrimaryDiap(statObject.getDependOnStat(), player);

        //Если значение (цвет) основного стата красный (оптимально), то он не влияет на зависимый стат
        double weight = 1;
        //Если значение не оптимальное - назначается вес стата (в обе стороны).
        if (primaryValue == 4) weight = 1.4;
        if (primaryValue == 3) weight = 1.2;
        if (primaryValue == 1) weight = 0.8;
        if (primaryValue == 0) weight = 0.6;
        if (variant == CalcDiapVariant.ONE) {
            if (statValue <= points[0] * weight) return 0;
            if (statValue <= points[1] * weight) return 1;
            if (statValue <= points[2] * weight) return 2;
            if (statValue <= points[3] * weight) return 3;
            return 4;
        }
        if (variant == CalcDiapVariant.TWO) {
            if (statValue <= points[0] * weight) return 0;
            if (statValue <= points[1] * weight) return 1;
            if (statValue <= points[2] * weight) return 3;
            if (statValue <= points[3] * weight) return 2;
            return 2;
        }
        if (variant == CalcDiapVariant.THREE) {
            if (statValue >= points[3] * weight) return 0;
            if (statValue >= points[2] * weight) return 1;
            if (statValue >= points[1] * weight) return 2;
            if (statValue >= points[0] * weight) return 3;
            return 4;
        }
        return 0;
    }

    //Определяет название css-селектора с цветом стата.
    public void setStatColor() {
        if (statColorDiap == 0) this.statColor = StatColor.INFINITY_TO_POINT1.getColor();
        else if (statColorDiap == 1) this.statColor = StatColor.POINT1_TO_POINT2.getColor();
        else if (statColorDiap == 2) this.statColor = StatColor.POINT2_TO_POINT3.getColor();
        else if (statColorDiap == 3) this.statColor = StatColor.POINT3_TO_POINT4.getColor();
        else if (statColorDiap == 4) this.statColor = StatColor.POINT4_TO_INFINITY.getColor();
        else this.statColor = StatColor.DEFAULT.getColor();

    }

    //Задает адрес картинки с диапазоном. Если для стата картинки нет, то будет null.
    public void setPicture() {
        String picture;
        if (statObject.isNeedImage()) {
            //Если стат не зависит от другого
            if (dependOnStat == 0) {
                double dstat = statValue;
                int istat = Math.round((int) dstat);
                picture = "/images/diap/" + istat + ".png";
            }
            //Если стат зависит от другого. Пока не реализовано (нет картинок и структуры папок)
            else {
                double dstat = statValue;
                int istat = Math.round((int) dstat);
                int depIStat = Math.round((int) dependOnStat);
                picture = "/images/" + statObject.getStatname() + "/" + depIStat + "-" + istat + ".png";
            }
        } else picture = null;
        this.picture = picture;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Stat getStatObject() {
        return statObject;
    }

    public void setStatObject(Stat statObject) {
        this.statObject = statObject;
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

    public String getStatValueStr() {
        return statValueStr;
    }

    public void setStatValueStr(String statValueStr) {
        this.statValueStr = statValueStr;
    }
}
