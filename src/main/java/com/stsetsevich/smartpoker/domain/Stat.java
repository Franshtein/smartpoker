package com.stsetsevich.smartpoker.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stat {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String statname;
    private double point1;
    private double point2;
    private double point3;
    private double point4;

    public Stat(){
        //У Entity обязательно должен быть конструктор без
        //параметров!!!
    }





}
