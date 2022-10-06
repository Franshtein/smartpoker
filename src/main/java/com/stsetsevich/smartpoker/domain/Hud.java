package com.stsetsevich.smartpoker.domain;


import javax.persistence.*;

@Entity
public class Hud {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private long userId;
    private int numberOfRows;
    private int numberOfColums;
    private String statsId;
    @Column(name = "round_of_bidding")
    @Enumerated(EnumType.STRING)
    private RoundOfBidding roundOfBidding;

    public Hud(){
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColums() {
        return numberOfColums;
    }

    public void setNumberOfColums(int numberOfColums) {
        this.numberOfColums = numberOfColums;
    }

    public String getStatsId() {
        return statsId;
    }

    public void setStatsId(String statsId) {
        this.statsId = statsId;
    }

    public RoundOfBidding getRoundOfBidding() {
        return roundOfBidding;
    }

    public void setRoundOfBidding(RoundOfBidding roundOfBidding) {
        this.roundOfBidding = roundOfBidding;
    }
}
