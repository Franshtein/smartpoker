package com.stsetsevich.smartpoker.service.hud;

import com.stsetsevich.smartpoker.domain.CalcDiapVariant;
import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.domain.Stat;
import com.stsetsevich.smartpoker.repos.StatRepo;
import com.stsetsevich.smartpoker.service.hud.StatInfo;
import com.stsetsevich.smartpoker.service.hud.TableInfoCalc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


/**
 * 1.Рассчитывает для {@link StatInfo} диапазон главного стата.
 * 2.Рассчитывает цвета таблиц игроков для {@link TableInfoCalc}
 */
@Service
public class StatInfoService {

    @Autowired
    StatRepo statRepo;
    @Autowired
    EntityManager entityManager;
    private Stat stat;
    private double statValue;
    private double points[];

    StatInfoService() {

    }

    public int checkPrimaryDiap(String stat, Player player) {
        this.stat = statRepo.findStatByStatname(stat);
        Query query = entityManager.createNativeQuery("SELECT " + stat + " FROM player where nickname='" + player.getNickname() + "'");
        List<String> list = query.getResultList();
        List<Object> list2 = new ArrayList<>(list);
        stat = list2.get(0).toString();
        this.statValue = Double.parseDouble(stat);
        setPoints();
        return checkDiap();

    }

    private int checkDiap() {
        CalcDiapVariant variant = stat.getCalcDiapVariant();
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

    private void setPoints() {
        this.points = stat.getPoints();
    }


}
