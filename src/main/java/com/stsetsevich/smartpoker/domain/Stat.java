package com.stsetsevich.smartpoker.domain;


import javax.persistence.*;

@Entity
public class Stat {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "statname", unique = true)
    private String statname;
    private double point1;
    private double point2;
    private double point3;
    private double point4;

    @Column(name = "calc_variant")
    @Enumerated(EnumType.STRING)
    private CalcDiapVariant calcDiapVariant;

    public Stat(){
    }

    public Stat(String statname, double point1, double point2, double point3, double point4) {
        this.statname = statname;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getStatname() {
        return statname;
    }

    public void setStatname(String statname) {
        this.statname = statname;
    }

    public double getPoint1() {
        return point1;
    }

    public void setPoint1(double point1) {
        this.point1 = point1;
    }

    public double getPoint2() {
        return point2;
    }

    public void setPoint2(double point2) {
        this.point2 = point2;
    }

    public double getPoint3() {
        return point3;
    }

    public void setPoint3(double point3) {
        this.point3 = point3;
    }

    public double getPoint4() {
        return point4;
    }

    public void setPoint4(double point4) {
        this.point4 = point4;
    }

    public CalcDiapVariant getCalcDiapVariant() {
        return calcDiapVariant;
    }

    public void setCalcDiapVariant(CalcDiapVariant calcDiapVariant) {
        this.calcDiapVariant = calcDiapVariant;
    }

    public double[] getPoints()
    {
        double[] points= {getPoint1(), getPoint2(), getPoint3(), getPoint4()};
        return points;
    }
}
