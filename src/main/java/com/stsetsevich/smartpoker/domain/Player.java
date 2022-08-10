package com.stsetsevich.smartpoker.domain;

import javax.persistence.*;

@Entity
@Table(name="player")
public class Player {
    //-------------------Short Info-------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nickname;
    private double totalHands;
    private double nl10Hands;
    private double nl16Hands;
    private double nl25Hands;
    private double winnings;
    private double ev;
    private String home;
    private double vpip;
    private double avgBb100;
    private double nl10Bb100;
    private double nl10EvBb100;
    private double nl16Bb100;
    private double nl16EvBb100;
    private double nl25Bb100;
    private double nl25EvBb100;


    //-------------------Tab Main-------------------------------------
    private double stealTotal;
    private double stealCo;
    private double stealBu;
    private double stealSb;
    private double wwsf;
    private double wtsd;
    private double w$sd;
    private double cBetFlopTotal;
    private double cBetFlopIp;
    private double cBetFlopOop;
    private double cBetTurnTotal;
    private double cBetTurnIp;
    private double cBetTurnOop;
    private double cBetRiverTotal;
    private double cBetRiverIp;
    private double cBetRiverOop;
    private double foldVsCbetFlopTotal;
    private double foldVsCbetFlopIp;
    private double foldVsCbetFlopOop;
    private double foldVsCbetTurnTotal;
    private double foldVsCbetTurnIp;
    private double foldVsCbetTurnOop;
    private double foldVsCbetRiverTotal;
    private double foldVsCbetRiverIp;
    private double foldVsCbetRiverOop;
    private double donkFlop;
    private double donkTurn;
    private double donkRiver;
    private double checkCallFlop;
    private double checkCallTurn;
    private double checkCallRiver;
    private double checkRaiseFlop;
    private double checkRaiseTurn;
    private double checkRaiseRiver;
    private double afqFlop;
    private double afqTurn;
    private double afqRiver;



    //-------------------Tab 6-Max[SH]-------------------------------------
    private double totalPfr;
    private double pfrEp;
    private double pfrMp;
    private double pfrCo;
    private double pfrBu;
    private double pfrSb;
    private double totalIsoraise;
    private double isoraiseEp;
    private double isoraiseMp;
    private double isoraiseCo;
    private double isoraisBu;
    private double isoraiseSb;
    private double isoraiseBb;
    private double totalCallOpenraise;
    private double callOpenraiseMp;
    private double callOpenraiseCo;
    private double callOpenraiseBu;
    private double callOpenraiseSb;
    private double callOpenraiseBb;
    private double foldTo3betTotal;
    private double foldTo3betTotalIp;
    private double foldTo3betTotalOop;
    private double foldTo3betTotalEp;
    private double foldTo3betTotalMp;
    private double foldTo3betTotalCo;
    private double foldTo3betTotalBu;
    private double foldTo3betTotalSb;
    private double foldTo3betTotalBb;
    private double foldTo3betIpEp;
    private double foldTo3betIpMp;
    private double foldTo3betIpCo;
    private double foldTo3betIpBu;
    private double foldTo3betIpSb;
    private double foldTo3betIpBb;
    private double foldTo3betOopEp;
    private double foldTo3betOopMp;
    private double foldTo3betOopCo;
    private double foldTo3betOopSb;
    private double foldTo3betOopBb;
    private double total3bet;
    private double total3betEp;
    private double total3betMp;
    private double total3betCo;
    private double total3betBu;
    private double total3betSb;
    private double total3betBb;
    private double open4betTotal;
    private double open4betCo;
    private double open4betBu;
    private double open4betSb;
    private double open4betBb;
    private double squeezeTotal;
    private double squeezeCo;
    private double squeezeBu;
    private double squeezeSb;
    private double squeezeBb;
    private double foldToSqueezeTotal;
    private double foldToSqueezeEp;
    private double foldToSqueezeMp;
    private double foldToSqueezeCo;
    private double foldToSqueezeBu;
    private double limpTotal;
    private double limpEp;
    private double limpMp;
    private double limpCo;
    private double limpBu;
    private double limpSb;


    //-------------------Tab PostFlop-------------------------------------
    private double betToMissCbetFlopTotal;
    private double betToMissCbetTurnTotal;
    private double betToMissCbetRiverTotal;
    private double betToMissCbetFlopIp;
    private double betToMissCbetTurnIp;
    private double betToMissCbetRiverIp;
    private double betToMissCbetTurnOop;
    private double betToMissCbetRiverOop;
    private double raiseToCbetFlopTotal;
    private double raiseToCbetTurnTotal;
    private double raiseToCbetRiverTotal;
    private double raiseToCbetFlopIp;
    private double raiseToCbetTurnIp;
    private double raiseToCbetRiverIp;
    private double raiseToCbetFlopOop;
    private double raiseToCbetTurnOop;
    private double raiseToCbetRiverOop;
    private double skippedCbetFoldFlopTotal;
    private double skippedCbetFoldTurnTotal;
    private double skippedCbetFoldRiverTotal;
    private double skippedCbetFoldTurnIp;
    private double skippedCbetFoldRiverIp;
    private double skippedCbetFoldFlopOop;
    private double skippedCbetFoldTurnOop;
    private double skippedCbetFoldRiverOop;
    private double aggFactorFlop;
    private double aggFactorTurn;
    private double aggFactorRiver;
    private double wonAfterRaiseFlop;
    private double wonAfterRaiseTurn;
    private double wonAfterRaiseRiver;


    //-------------------Tab 3bet-5bet/Folds-------------------------------------
    private double total3betIp;
    private double total3betOop;
    private double foldTo4betTotal;
    private double foldTo4betMp;
    private double foldTo4betCo;
    private double foldTo4betBu;
    private double foldTo4betSb;
    private double foldTo4betBb;
    private double total4bet;
    private double total4betIp;
    private double total4betOop;
    private double total4betEp;
    private double total4betMp;
    private double total4betCo;
    private double total4betBu;
    private double total4betSb;
    private double total4betBb;
    private double total4betEpIp;
    private double total4betMpIp;
    private double total4betCoIp;
    private double total4betBuIp;
    private double total4betSbIp;
    private double total4betBbIp;
    private double total4betEpOop;
    private double total4betMpOop;
    private double total4betCoOop;
    private double total4betSbOop;
    private double total4betBbOop;
    private double total5bet;
    private double total5betMp;
    private double total5betCo;
    private double total5betBu;
    private double total5betSb;
    private double total5betBb;
    private double foldTo5betEp;
    private double foldTo5betMp;
    private double foldTo5betCo;
    private double foldTo5betBu;
    private double foldTo5betSb;
    private double foldTo5betBb;


    //-------------------Tab 3bet PvP [SH]-------------------------------------
    private double mpVsEp3bet;
    private double coVsEp3bet;
    private double coVsMp3bet;
    private double buVsEp3bet;
    private double buVsMp3bet;
    private double buVsCo3bet;
    private double sbVsEp3bet;
    private double sbVsMp3bet;
    private double sbVsCo3bet;
    private double sbVsBu3bet;
    private double bbVsEp3bet;
    private double bbVsMp3bet;
    private double bbVsCo3bet;
    private double bbVsBu3bet;
    private double bbVsSb3bet;

    public Player()
    {
    }

    public Player(String nickname)
    {
        this.nickname=nickname;
    }
    public String getNickname() {
        return nickname;
    }

    public double getTotal3betIp() {
        return total3betIp;
    }

    public void setTotal3betIp(double total3betIp) {
        this.total3betIp = total3betIp;
    }

    public double getTotal3betOop() {
        return total3betOop;
    }

    public void setTotal3betOop(double total3betOop) {
        this.total3betOop = total3betOop;
    }

    public double getTotal4betIp() {
        return total4betIp;
    }

    public void setTotal4betIp(double total4betIp) {
        this.total4betIp = total4betIp;
    }

    public double getTotal4betOop() {
        return total4betOop;
    }

    public void setTotal4betOop(double total4betOop) {
        this.total4betOop = total4betOop;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getTotalHands() {
        return totalHands;
    }

    public void setTotalHands(double totalHands) {
        this.totalHands = totalHands;
    }

    public double getNl10Hands() {
        return nl10Hands;
    }

    public void setNl10Hands(double nl10Hands) {
        this.nl10Hands = nl10Hands;
    }

    public double getIsoraiseEp() {
        return isoraiseEp;
    }

    public void setIsoraiseEp(double isoraiseEp) {
        this.isoraiseEp = isoraiseEp;
    }

    public double getNl25Hands() {
        return nl25Hands;
    }

    public void setNl25Hands(double nl25Hands) {
        this.nl25Hands = nl25Hands;
    }

    public double getWinnings() {
        return winnings;
    }

    public void setWinnings(double winnings) {
        this.winnings = winnings;
    }

    public double getEv() {
        return ev;
    }

    public void setEv(double ev) {
        this.ev = ev;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public double getVpip() {
        return vpip;
    }

    public void setVpip(double vpip) {
        this.vpip = vpip;
    }

    public double getAvgBb100() {
        return avgBb100;
    }

    public void setAvgBb100(double avgBb100) {
        this.avgBb100 = avgBb100;
    }

    public double getNl10Bb100() {
        return nl10Bb100;
    }

    public void setNl10Bb100(double nl10Bb100) {
        this.nl10Bb100 = nl10Bb100;
    }

    public double getNl10EvBb100() {
        return nl10EvBb100;
    }

    public void setNl10EvBb100(double nl10EvBb100) {
        this.nl10EvBb100 = nl10EvBb100;
    }

    public double getNl25Bb100() {
        return nl25Bb100;
    }

    public void setNl25Bb100(double nl25Bb100) {
        this.nl25Bb100 = nl25Bb100;
    }

    public double getNl25EvBb100() {
        return nl25EvBb100;
    }

    public void setNl25EvBb100(double nl25EvBb100) {
        this.nl25EvBb100 = nl25EvBb100;
    }

    public double getStealTotal() {
        return stealTotal;
    }

    public void setStealTotal(double stealTotal) {
        this.stealTotal = stealTotal;
    }

    public double getStealCo() {
        return stealCo;
    }

    public void setStealCo(double stealCo) {
        this.stealCo = stealCo;
    }

    public double getStealBu() {
        return stealBu;
    }

    public void setStealBu(double stealBu) {
        this.stealBu = stealBu;
    }

    public double getStealSb() {
        return stealSb;
    }

    public void setStealSb(double stealSb) {
        this.stealSb = stealSb;
    }

    public double getWwsf() {
        return wwsf;
    }

    public void setWwsf(double wwsf) {
        this.wwsf = wwsf;
    }

    public double getWtsd() {
        return wtsd;
    }

    public void setWtsd(double wtsd) {
        this.wtsd = wtsd;
    }

    public double getW$sd() {
        return w$sd;
    }

    public void setW$sd(double w$sd) {
        this.w$sd = w$sd;
    }

    public double getcBetFlopTotal() {
        return cBetFlopTotal;
    }

    public void setcBetFlopTotal(double cBetFlopTotal) {
        this.cBetFlopTotal = cBetFlopTotal;
    }

    public double getcBetFlopIp() {
        return cBetFlopIp;
    }

    public void setcBetFlopIp(double cBetFlopIp) {
        this.cBetFlopIp = cBetFlopIp;
    }

    public double getcBetFlopOop() {
        return cBetFlopOop;
    }

    public void setcBetFlopOop(double cBetFlopOop) {
        this.cBetFlopOop = cBetFlopOop;
    }

    public double getcBetTurnTotal() {
        return cBetTurnTotal;
    }

    public void setcBetTurnTotal(double cBetTurnTotal) {
        this.cBetTurnTotal = cBetTurnTotal;
    }

    public double getcBetTurnIp() {
        return cBetTurnIp;
    }

    public void setcBetTurnIp(double cBetTurnIp) {
        this.cBetTurnIp = cBetTurnIp;
    }

    public double getcBetTurnOop() {
        return cBetTurnOop;
    }

    public void setcBetTurnOop(double cBetTurnOop) {
        this.cBetTurnOop = cBetTurnOop;
    }

    public double getcBetRiverTotal() {
        return cBetRiverTotal;
    }

    public void setcBetRiverTotal(double cBetRiverTotal) {
        this.cBetRiverTotal = cBetRiverTotal;
    }

    public double getcBetRiverIp() {
        return cBetRiverIp;
    }

    public void setcBetRiverIp(double cBetRiverIp) {
        this.cBetRiverIp = cBetRiverIp;
    }

    public double getcBetRiverOop() {
        return cBetRiverOop;
    }

    public void setcBetRiverOop(double cBetRiverOop) {
        this.cBetRiverOop = cBetRiverOop;
    }

    public double getFoldVsCbetFlopTotal() {
        return foldVsCbetFlopTotal;
    }

    public void setFoldVsCbetFlopTotal(double foldVsCbetFlopTotal) {
        this.foldVsCbetFlopTotal = foldVsCbetFlopTotal;
    }

    public double getFoldVsCbetFlopIp() {
        return foldVsCbetFlopIp;
    }

    public void setFoldVsCbetFlopIp(double foldVsCbetFlopIp) {
        this.foldVsCbetFlopIp = foldVsCbetFlopIp;
    }

    public double getFoldVsCbetFlopOop() {
        return foldVsCbetFlopOop;
    }

    public void setFoldVsCbetFlopOop(double foldVsCbetFlopOop) {
        this.foldVsCbetFlopOop = foldVsCbetFlopOop;
    }

    public double getFoldVsCbetTurnTotal() {
        return foldVsCbetTurnTotal;
    }

    public void setFoldVsCbetTurnTotal(double foldVsCbetTurnTotal) {
        this.foldVsCbetTurnTotal = foldVsCbetTurnTotal;
    }

    public double getFoldVsCbetTurnIp() {
        return foldVsCbetTurnIp;
    }

    public void setFoldVsCbetTurnIp(double foldVsCbetTurnIp) {
        this.foldVsCbetTurnIp = foldVsCbetTurnIp;
    }

    public double getFoldVsCbetTurnOop() {
        return foldVsCbetTurnOop;
    }

    public void setFoldVsCbetTurnOop(double foldVsCbetTurnOop) {
        this.foldVsCbetTurnOop = foldVsCbetTurnOop;
    }

    public double getFoldVsCbetRiverTotal() {
        return foldVsCbetRiverTotal;
    }

    public void setFoldVsCbetRiverTotal(double foldVsCbetRiverTotal) {
        this.foldVsCbetRiverTotal = foldVsCbetRiverTotal;
    }

    public double getFoldVsCbetRiverIp() {
        return foldVsCbetRiverIp;
    }

    public void setFoldVsCbetRiverIp(double foldVsCbetRiverIp) {
        this.foldVsCbetRiverIp = foldVsCbetRiverIp;
    }

    public double getFoldVsCbetRiverOop() {
        return foldVsCbetRiverOop;
    }

    public void setFoldVsCbetRiverOop(double foldVsCbetRiverOop) {
        this.foldVsCbetRiverOop = foldVsCbetRiverOop;
    }

    public double getDonkFlop() {
        return donkFlop;
    }

    public void setDonkFlop(double donkFlop) {
        this.donkFlop = donkFlop;
    }

    public double getDonkTurn() {
        return donkTurn;
    }

    public void setDonkTurn(double donkTurn) {
        this.donkTurn = donkTurn;
    }

    public double getDonkRiver() {
        return donkRiver;
    }

    public void setDonkRiver(double donkRiver) {
        this.donkRiver = donkRiver;
    }

    public double getCheckCallFlop() {
        return checkCallFlop;
    }

    public void setCheckCallFlop(double checkCallFlop) {
        this.checkCallFlop = checkCallFlop;
    }

    public double getCheckCallTurn() {
        return checkCallTurn;
    }

    public void setCheckCallTurn(double checkCallTurn) {
        this.checkCallTurn = checkCallTurn;
    }

    public double getCheckCallRiver() {
        return checkCallRiver;
    }

    public void setCheckCallRiver(double checkCallRiver) {
        this.checkCallRiver = checkCallRiver;
    }

    public double getCheckRaiseFlop() {
        return checkRaiseFlop;
    }

    public void setCheckRaiseFlop(double checkRaiseFlop) {
        this.checkRaiseFlop = checkRaiseFlop;
    }

    public double getCheckRaiseTurn() {
        return checkRaiseTurn;
    }

    public void setCheckRaiseTurn(double checkRaiseTurn) {
        this.checkRaiseTurn = checkRaiseTurn;
    }

    public double getCheckRaiseRiver() {
        return checkRaiseRiver;
    }

    public void setCheckRaiseRiver(double checkRaiseRiver) {
        this.checkRaiseRiver = checkRaiseRiver;
    }

    public double getAfqFlop() {
        return afqFlop;
    }

    public void setAfqFlop(double afqFlop) {
        this.afqFlop = afqFlop;
    }

    public double getAfqTurn() {
        return afqTurn;
    }

    public void setAfqTurn(double afqTurn) {
        this.afqTurn = afqTurn;
    }

    public double getAfqRiver() {
        return afqRiver;
    }

    public void setAfqRiver(double afqRiver) {
        this.afqRiver = afqRiver;
    }

    public double getTotalPfr() {
        return totalPfr;
    }

    public void setTotalPfr(double totalPfr) {
        this.totalPfr = totalPfr;
    }

    public double getPfrEp() {
        return pfrEp;
    }

    public void setPfrEp(double pfrEp) {
        this.pfrEp = pfrEp;
    }

    public double getPfrMp() {
        return pfrMp;
    }

    public void setPfrMp(double pfrMp) {
        this.pfrMp = pfrMp;
    }

    public double getPfrCo() {
        return pfrCo;
    }

    public void setPfrCo(double pfrCo) {
        this.pfrCo = pfrCo;
    }

    public double getPfrBu() {
        return pfrBu;
    }

    public void setPfrBu(double pfrBu) {
        this.pfrBu = pfrBu;
    }

    public double getPfrSb() {
        return pfrSb;
    }

    public void setPfrSb(double pfrSb) {
        this.pfrSb = pfrSb;
    }

    public double getTotalIsoraise() {
        return totalIsoraise;
    }

    public void setTotalIsoraise(double totalIsoraise) {
        this.totalIsoraise = totalIsoraise;
    }

    public double getIsoraiseMp() {
        return isoraiseMp;
    }

    public void setIsoraiseMp(double isoraiseMp) {
        this.isoraiseMp = isoraiseMp;
    }

    public double getIsoraiseCo() {
        return isoraiseCo;
    }

    public void setIsoraiseCo(double isoraiseCo) {
        this.isoraiseCo = isoraiseCo;
    }

    public double getIsoraisBu() {
        return isoraisBu;
    }

    public void setIsoraisBu(double isoraisBu) {
        this.isoraisBu = isoraisBu;
    }

    public double getIsoraiseSb() {
        return isoraiseSb;
    }

    public void setIsoraiseSb(double isoraiseSb) {
        this.isoraiseSb = isoraiseSb;
    }

    public double getIsoraiseBb() {
        return isoraiseBb;
    }

    public void setIsoraiseBb(double isoraiseBb) {
        this.isoraiseBb = isoraiseBb;
    }

    public double getTotalCallOpenraise() {
        return totalCallOpenraise;
    }

    public void setTotalCallOpenraise(double totalCallOpenraise) {
        this.totalCallOpenraise = totalCallOpenraise;
    }

    public double getCallOpenraiseMp() {
        return callOpenraiseMp;
    }

    public void setCallOpenraiseMp(double callOpenraiseMp) {
        this.callOpenraiseMp = callOpenraiseMp;
    }

    public double getCallOpenraiseCo() {
        return callOpenraiseCo;
    }

    public void setCallOpenraiseCo(double callOpenraiseCo) {
        this.callOpenraiseCo = callOpenraiseCo;
    }

    public double getCallOpenraiseBu() {
        return callOpenraiseBu;
    }

    public void setCallOpenraiseBu(double callOpenraiseBu) {
        this.callOpenraiseBu = callOpenraiseBu;
    }

    public double getCallOpenraiseSb() {
        return callOpenraiseSb;
    }

    public void setCallOpenraiseSb(double callOpenraiseSb) {
        this.callOpenraiseSb = callOpenraiseSb;
    }

    public double getCallOpenraiseBb() {
        return callOpenraiseBb;
    }

    public void setCallOpenraiseBb(double callOpenraiseBb) {
        this.callOpenraiseBb = callOpenraiseBb;
    }

    public double getFoldTo3betTotal() {
        return foldTo3betTotal;
    }

    public void setFoldTo3betTotal(double foldTo3betTotal) {
        this.foldTo3betTotal = foldTo3betTotal;
    }

    public double getFoldTo3betTotalIp() {
        return foldTo3betTotalIp;
    }

    public void setFoldTo3betTotalIp(double foldTo3betTotalIp) {
        this.foldTo3betTotalIp = foldTo3betTotalIp;
    }

    public double getFoldTo3betTotalOop() {
        return foldTo3betTotalOop;
    }

    public void setFoldTo3betTotalOop(double foldTo3betTotalOop) {
        this.foldTo3betTotalOop = foldTo3betTotalOop;
    }

    public double getFoldTo3betTotalEp() {
        return foldTo3betTotalEp;
    }

    public void setFoldTo3betTotalEp(double foldTo3betTotalEp) {
        this.foldTo3betTotalEp = foldTo3betTotalEp;
    }

    public double getFoldTo3betTotalMp() {
        return foldTo3betTotalMp;
    }

    public void setFoldTo3betTotalMp(double foldTo3betTotalMp) {
        this.foldTo3betTotalMp = foldTo3betTotalMp;
    }

    public double getFoldTo3betTotalCo() {
        return foldTo3betTotalCo;
    }

    public void setFoldTo3betTotalCo(double foldTo3betTotalCo) {
        this.foldTo3betTotalCo = foldTo3betTotalCo;
    }

    public double getFoldTo3betTotalBu() {
        return foldTo3betTotalBu;
    }

    public void setFoldTo3betTotalBu(double foldTo3betTotalBu) {
        this.foldTo3betTotalBu = foldTo3betTotalBu;
    }

    public double getFoldTo3betTotalSb() {
        return foldTo3betTotalSb;
    }

    public void setFoldTo3betTotalSb(double foldTo3betTotalSb) {
        this.foldTo3betTotalSb = foldTo3betTotalSb;
    }

    public double getFoldTo3betTotalBb() {
        return foldTo3betTotalBb;
    }

    public void setFoldTo3betTotalBb(double foldTo3betTotalBb) {
        this.foldTo3betTotalBb = foldTo3betTotalBb;
    }

    public double getFoldTo3betIpEp() {
        return foldTo3betIpEp;
    }

    public void setFoldTo3betIpEp(double foldTo3betIpEp) {
        this.foldTo3betIpEp = foldTo3betIpEp;
    }

    public double getFoldTo3betIpMp() {
        return foldTo3betIpMp;
    }

    public void setFoldTo3betIpMp(double foldTo3betIpMp) {
        this.foldTo3betIpMp = foldTo3betIpMp;
    }

    public double getFoldTo3betIpCo() {
        return foldTo3betIpCo;
    }

    public void setFoldTo3betIpCo(double foldTo3betIpCo) {
        this.foldTo3betIpCo = foldTo3betIpCo;
    }

    public double getFoldTo3betIpBu() {
        return foldTo3betIpBu;
    }

    public void setFoldTo3betIpBu(double foldTo3betIpBu) {
        this.foldTo3betIpBu = foldTo3betIpBu;
    }

    public double getFoldTo3betIpSb() {
        return foldTo3betIpSb;
    }

    public void setFoldTo3betIpSb(double foldTo3betIpSb) {
        this.foldTo3betIpSb = foldTo3betIpSb;
    }

    public double getFoldTo3betIpBb() {
        return foldTo3betIpBb;
    }

    public void setFoldTo3betIpBb(double foldTo3betIpBb) {
        this.foldTo3betIpBb = foldTo3betIpBb;
    }

    public double getFoldTo3betOopEp() {
        return foldTo3betOopEp;
    }

    public void setFoldTo3betOopEp(double foldTo3betOopEp) {
        this.foldTo3betOopEp = foldTo3betOopEp;
    }

    public double getFoldTo3betOopMp() {
        return foldTo3betOopMp;
    }

    public void setFoldTo3betOopMp(double foldTo3betOopMp) {
        this.foldTo3betOopMp = foldTo3betOopMp;
    }

    public double getFoldTo3betOopCo() {
        return foldTo3betOopCo;
    }

    public void setFoldTo3betOopCo(double foldTo3betOopCo) {
        this.foldTo3betOopCo = foldTo3betOopCo;
    }

    public double getFoldTo3betOopSb() {
        return foldTo3betOopSb;
    }

    public void setFoldTo3betOopSb(double foldTo3betOopSb) {
        this.foldTo3betOopSb = foldTo3betOopSb;
    }

    public double getFoldTo3betOopBb() {
        return foldTo3betOopBb;
    }

    public void setFoldTo3betOopBb(double foldTo3betOopBb) {
        this.foldTo3betOopBb = foldTo3betOopBb;
    }

    public double getTotal3bet() {
        return total3bet;
    }

    public void setTotal3bet(double total3bet) {
        this.total3bet = total3bet;
    }

    public double getTotal3betEp() {
        return total3betEp;
    }

    public void setTotal3betEp(double total3betEp) {
        this.total3betEp = total3betEp;
    }

    public double getTotal3betMp() {
        return total3betMp;
    }

    public void setTotal3betMp(double total3betMp) {
        this.total3betMp = total3betMp;
    }

    public double getTotal3betCo() {
        return total3betCo;
    }

    public void setTotal3betCo(double total3betCo) {
        this.total3betCo = total3betCo;
    }

    public double getTotal3betBu() {
        return total3betBu;
    }

    public void setTotal3betBu(double total3betBu) {
        this.total3betBu = total3betBu;
    }

    public double getTotal3betSb() {
        return total3betSb;
    }

    public void setTotal3betSb(double total3betSb) {
        this.total3betSb = total3betSb;
    }

    public double getTotal3betBb() {
        return total3betBb;
    }

    public void setTotal3betBb(double total3betBb) {
        this.total3betBb = total3betBb;
    }

    public double getOpen4betTotal() {
        return open4betTotal;
    }

    public void setOpen4betTotal(double open4betTotal) {
        this.open4betTotal = open4betTotal;
    }

    public double getOpen4betCo() {
        return open4betCo;
    }

    public void setOpen4betCo(double open4betCo) {
        this.open4betCo = open4betCo;
    }

    public double getOpen4betBu() {
        return open4betBu;
    }

    public void setOpen4betBu(double open4betBu) {
        this.open4betBu = open4betBu;
    }

    public double getOpen4betSb() {
        return open4betSb;
    }

    public void setOpen4betSb(double open4betSb) {
        this.open4betSb = open4betSb;
    }

    public double getOpen4betBb() {
        return open4betBb;
    }

    public void setOpen4betBb(double open4betBb) {
        this.open4betBb = open4betBb;
    }

    public double getSqueezeTotal() {
        return squeezeTotal;
    }

    public void setSqueezeTotal(double squeezeTotal) {
        this.squeezeTotal = squeezeTotal;
    }

    public double getSqueezeCo() {
        return squeezeCo;
    }

    public void setSqueezeCo(double squeezeCo) {
        this.squeezeCo = squeezeCo;
    }

    public double getSqueezeBu() {
        return squeezeBu;
    }

    public void setSqueezeBu(double squeezeBu) {
        this.squeezeBu = squeezeBu;
    }

    public double getSqueezeSb() {
        return squeezeSb;
    }

    public void setSqueezeSb(double squeezeSb) {
        this.squeezeSb = squeezeSb;
    }

    public double getSqueezeBb() {
        return squeezeBb;
    }

    public void setSqueezeBb(double squeezeBb) {
        this.squeezeBb = squeezeBb;
    }

    public double getFoldToSqueezeTotal() {
        return foldToSqueezeTotal;
    }

    public void setFoldToSqueezeTotal(double foldToSqueezeTotal) {
        this.foldToSqueezeTotal = foldToSqueezeTotal;
    }

    public double getFoldToSqueezeEp() {
        return foldToSqueezeEp;
    }

    public void setFoldToSqueezeEp(double foldToSqueezeEp) {
        this.foldToSqueezeEp = foldToSqueezeEp;
    }

    public double getFoldToSqueezeMp() {
        return foldToSqueezeMp;
    }

    public void setFoldToSqueezeMp(double foldToSqueezeMp) {
        this.foldToSqueezeMp = foldToSqueezeMp;
    }

    public double getFoldToSqueezeCo() {
        return foldToSqueezeCo;
    }

    public void setFoldToSqueezeCo(double foldToSqueezeCo) {
        this.foldToSqueezeCo = foldToSqueezeCo;
    }

    public double getFoldToSqueezeBu() {
        return foldToSqueezeBu;
    }

    public void setFoldToSqueezeBu(double foldToSqueezeBu) {
        this.foldToSqueezeBu = foldToSqueezeBu;
    }

    public double getLimpTotal() {
        return limpTotal;
    }

    public void setLimpTotal(double limpTotal) {
        this.limpTotal = limpTotal;
    }

    public double getLimpEp() {
        return limpEp;
    }

    public void setLimpEp(double limpEp) {
        this.limpEp = limpEp;
    }

    public double getLimpMp() {
        return limpMp;
    }

    public void setLimpMp(double limpMp) {
        this.limpMp = limpMp;
    }

    public double getLimpCo() {
        return limpCo;
    }

    public void setLimpCo(double limpCo) {
        this.limpCo = limpCo;
    }

    public double getLimpBu() {
        return limpBu;
    }

    public void setLimpBu(double limpBu) {
        this.limpBu = limpBu;
    }

    public double getLimpSb() {
        return limpSb;
    }

    public void setLimpSb(double limpSb) {
        this.limpSb = limpSb;
    }

    public double getBetToMissCbetFlopTotal() {
        return betToMissCbetFlopTotal;
    }

    public void setBetToMissCbetFlopTotal(double betToMissCbetFlopTotal) {
        this.betToMissCbetFlopTotal = betToMissCbetFlopTotal;
    }

    public double getBetToMissCbetTurnTotal() {
        return betToMissCbetTurnTotal;
    }

    public void setBetToMissCbetTurnTotal(double betToMissCbetTurnTotal) {
        this.betToMissCbetTurnTotal = betToMissCbetTurnTotal;
    }

    public double getBetToMissCbetRiverTotal() {
        return betToMissCbetRiverTotal;
    }

    public void setBetToMissCbetRiverTotal(double betToMissCbetRiverTotal) {
        this.betToMissCbetRiverTotal = betToMissCbetRiverTotal;
    }

    public double getBetToMissCbetFlopIp() {
        return betToMissCbetFlopIp;
    }

    public void setBetToMissCbetFlopIp(double betToMissCbetFlopIp) {
        this.betToMissCbetFlopIp = betToMissCbetFlopIp;
    }

    public double getBetToMissCbetTurnIp() {
        return betToMissCbetTurnIp;
    }

    public void setBetToMissCbetTurnIp(double betToMissCbetTurnIp) {
        this.betToMissCbetTurnIp = betToMissCbetTurnIp;
    }

    public double getBetToMissCbetRiverIp() {
        return betToMissCbetRiverIp;
    }

    public void setBetToMissCbetRiverIp(double betToMissCbetRiverIp) {
        this.betToMissCbetRiverIp = betToMissCbetRiverIp;
    }

    public double getBetToMissCbetTurnOop() {
        return betToMissCbetTurnOop;
    }

    public void setBetToMissCbetTurnOop(double betToMissCbetTurnOop) {
        this.betToMissCbetTurnOop = betToMissCbetTurnOop;
    }

    public double getBetToMissCbetRiverOop() {
        return betToMissCbetRiverOop;
    }

    public void setBetToMissCbetRiverOop(double betToMissCbetRiverOop) {
        this.betToMissCbetRiverOop = betToMissCbetRiverOop;
    }

    public double getRaiseToCbetFlopTotal() {
        return raiseToCbetFlopTotal;
    }

    public void setRaiseToCbetFlopTotal(double raiseToCbetFlopTotal) {
        this.raiseToCbetFlopTotal = raiseToCbetFlopTotal;
    }

    public double getRaiseToCbetTurnTotal() {
        return raiseToCbetTurnTotal;
    }

    public void setRaiseToCbetTurnTotal(double raiseToCbetTurnTotal) {
        this.raiseToCbetTurnTotal = raiseToCbetTurnTotal;
    }

    public double getRaiseToCbetRiverTotal() {
        return raiseToCbetRiverTotal;
    }

    public void setRaiseToCbetRiverTotal(double raiseToCbetRiverTotal) {
        this.raiseToCbetRiverTotal = raiseToCbetRiverTotal;
    }

    public double getRaiseToCbetFlopIp() {
        return raiseToCbetFlopIp;
    }

    public void setRaiseToCbetFlopIp(double raiseToCbetFlopIp) {
        this.raiseToCbetFlopIp = raiseToCbetFlopIp;
    }

    public double getRaiseToCbetTurnIp() {
        return raiseToCbetTurnIp;
    }

    public void setRaiseToCbetTurnIp(double raiseToCbetTurnIp) {
        this.raiseToCbetTurnIp = raiseToCbetTurnIp;
    }

    public double getRaiseToCbetRiverIp() {
        return raiseToCbetRiverIp;
    }

    public void setRaiseToCbetRiverIp(double raiseToCbetRiverIp) {
        this.raiseToCbetRiverIp = raiseToCbetRiverIp;
    }

    public double getRaiseToCbetFlopOop() {
        return raiseToCbetFlopOop;
    }

    public void setRaiseToCbetFlopOop(double raiseToCbetFlopOop) {
        this.raiseToCbetFlopOop = raiseToCbetFlopOop;
    }

    public double getRaiseToCbetTurnOop() {
        return raiseToCbetTurnOop;
    }

    public void setRaiseToCbetTurnOop(double raiseToCbetTurnOop) {
        this.raiseToCbetTurnOop = raiseToCbetTurnOop;
    }

    public double getRaiseToCbetRiverOop() {
        return raiseToCbetRiverOop;
    }

    public void setRaiseToCbetRiverOop(double raiseToCbetRiverOop) {
        this.raiseToCbetRiverOop = raiseToCbetRiverOop;
    }

    public double getSkippedCbetFoldFlopTotal() {
        return skippedCbetFoldFlopTotal;
    }

    public void setSkippedCbetFoldFlopTotal(double skippedCbetFoldFlopTotal) {
        this.skippedCbetFoldFlopTotal = skippedCbetFoldFlopTotal;
    }

    public double getSkippedCbetFoldTurnTotal() {
        return skippedCbetFoldTurnTotal;
    }

    public void setSkippedCbetFoldTurnTotal(double skippedCbetFoldTurnTotal) {
        this.skippedCbetFoldTurnTotal = skippedCbetFoldTurnTotal;
    }

    public double getSkippedCbetFoldRiverTotal() {
        return skippedCbetFoldRiverTotal;
    }

    public void setSkippedCbetFoldRiverTotal(double skippedCbetFoldRiverTotal) {
        this.skippedCbetFoldRiverTotal = skippedCbetFoldRiverTotal;
    }

    public double getSkippedCbetFoldTurnIp() {
        return skippedCbetFoldTurnIp;
    }

    public void setSkippedCbetFoldTurnIp(double skippedCbetFoldTurnIp) {
        this.skippedCbetFoldTurnIp = skippedCbetFoldTurnIp;
    }

    public double getSkippedCbetFoldRiverIp() {
        return skippedCbetFoldRiverIp;
    }

    public void setSkippedCbetFoldRiverIp(double skippedCbetFoldRiverIp) {
        this.skippedCbetFoldRiverIp = skippedCbetFoldRiverIp;
    }

    public double getSkippedCbetFoldFlopOop() {
        return skippedCbetFoldFlopOop;
    }

    public void setSkippedCbetFoldFlopOop(double skippedCbetFoldFlopOop) {
        this.skippedCbetFoldFlopOop = skippedCbetFoldFlopOop;
    }

    public double getSkippedCbetFoldTurnOop() {
        return skippedCbetFoldTurnOop;
    }

    public void setSkippedCbetFoldTurnOop(double skippedCbetFoldTurnOop) {
        this.skippedCbetFoldTurnOop = skippedCbetFoldTurnOop;
    }

    public double getSkippedCbetFoldRiverOop() {
        return skippedCbetFoldRiverOop;
    }

    public void setSkippedCbetFoldRiverOop(double skippedCbetFoldRiverOop) {
        this.skippedCbetFoldRiverOop = skippedCbetFoldRiverOop;
    }

    public double getAggFactorFlop() {
        return aggFactorFlop;
    }

    public void setAggFactorFlop(double aggFactorFlop) {
        this.aggFactorFlop = aggFactorFlop;
    }

    public double getAggFactorTurn() {
        return aggFactorTurn;
    }

    public void setAggFactorTurn(double aggFactorTurn) {
        this.aggFactorTurn = aggFactorTurn;
    }

    public double getAggFactorRiver() {
        return aggFactorRiver;
    }

    public void setAggFactorRiver(double aggFactorRiver) {
        this.aggFactorRiver = aggFactorRiver;
    }

    public double getWonAfterRaiseFlop() {
        return wonAfterRaiseFlop;
    }

    public void setWonAfterRaiseFlop(double wonAfterRaiseFlop) {
        this.wonAfterRaiseFlop = wonAfterRaiseFlop;
    }

    public double getWonAfterRaiseTurn() {
        return wonAfterRaiseTurn;
    }

    public void setWonAfterRaiseTurn(double wonAfterRaiseTurn) {
        this.wonAfterRaiseTurn = wonAfterRaiseTurn;
    }

    public double getWonAfterRaiseRiver() {
        return wonAfterRaiseRiver;
    }

    public void setWonAfterRaiseRiver(double wonAfterRaiseRiver) {
        this.wonAfterRaiseRiver = wonAfterRaiseRiver;
    }

    public double getFoldTo4betTotal() {
        return foldTo4betTotal;
    }

    public void setFoldTo4betTotal(double foldTo4betTotal) {
        this.foldTo4betTotal = foldTo4betTotal;
    }

    public double getFoldTo4betMp() {
        return foldTo4betMp;
    }

    public void setFoldTo4betMp(double foldTo4betMp) {
        this.foldTo4betMp = foldTo4betMp;
    }

    public double getFoldTo4betCo() {
        return foldTo4betCo;
    }

    public void setFoldTo4betCo(double foldTo4betCo) {
        this.foldTo4betCo = foldTo4betCo;
    }

    public double getFoldTo4betBu() {
        return foldTo4betBu;
    }

    public void setFoldTo4betBu(double foldTo4betBu) {
        this.foldTo4betBu = foldTo4betBu;
    }

    public double getFoldTo4betSb() {
        return foldTo4betSb;
    }

    public void setFoldTo4betSb(double foldTo4betSb) {
        this.foldTo4betSb = foldTo4betSb;
    }

    public double getFoldTo4betBb() {
        return foldTo4betBb;
    }

    public void setFoldTo4betBb(double foldTo4betBb) {
        this.foldTo4betBb = foldTo4betBb;
    }

    public double getTotal4bet() {
        return total4bet;
    }

    public void setTotal4bet(double total4bet) {
        this.total4bet = total4bet;
    }

    public double getTotal4betEp() {
        return total4betEp;
    }

    public void setTotal4betEp(double total4betEp) {
        this.total4betEp = total4betEp;
    }

    public double getTotal4betMp() {
        return total4betMp;
    }

    public void setTotal4betMp(double total4betMp) {
        this.total4betMp = total4betMp;
    }

    public double getTotal4betCo() {
        return total4betCo;
    }

    public void setTotal4betCo(double total4betCo) {
        this.total4betCo = total4betCo;
    }

    public double getTotal4betBu() {
        return total4betBu;
    }

    public void setTotal4betBu(double total4betBu) {
        this.total4betBu = total4betBu;
    }

    public double getTotal4betSb() {
        return total4betSb;
    }

    public void setTotal4betSb(double total4betSb) {
        this.total4betSb = total4betSb;
    }

    public double getTotal4betBb() {
        return total4betBb;
    }

    public void setTotal4betBb(double total4betBb) {
        this.total4betBb = total4betBb;
    }

    public double getTotal4betEpIp() {
        return total4betEpIp;
    }

    public void setTotal4betEpIp(double total4betEpIp) {
        this.total4betEpIp = total4betEpIp;
    }

    public double getTotal4betMpIp() {
        return total4betMpIp;
    }

    public void setTotal4betMpIp(double total4betMpIp) {
        this.total4betMpIp = total4betMpIp;
    }

    public double getTotal4betCoIp() {
        return total4betCoIp;
    }

    public void setTotal4betCoIp(double total4betCoIp) {
        this.total4betCoIp = total4betCoIp;
    }

    public double getTotal4betBuIp() {
        return total4betBuIp;
    }

    public void setTotal4betBuIp(double total4betBuIp) {
        this.total4betBuIp = total4betBuIp;
    }

    public double getTotal4betSbIp() {
        return total4betSbIp;
    }

    public void setTotal4betSbIp(double total4betSbIp) {
        this.total4betSbIp = total4betSbIp;
    }

    public double getTotal4betBbIp() {
        return total4betBbIp;
    }

    public void setTotal4betBbIp(double total4betBbIp) {
        this.total4betBbIp = total4betBbIp;
    }

    public double getTotal4betEpOop() {
        return total4betEpOop;
    }

    public void setTotal4betEpOop(double total4betEpOop) {
        this.total4betEpOop = total4betEpOop;
    }

    public double getTotal4betMpOop() {
        return total4betMpOop;
    }

    public void setTotal4betMpOop(double total4betMpOop) {
        this.total4betMpOop = total4betMpOop;
    }

    public double getTotal4betCoOop() {
        return total4betCoOop;
    }

    public void setTotal4betCoOop(double total4betCoOop) {
        this.total4betCoOop = total4betCoOop;
    }

    public double getTotal4betSbOop() {
        return total4betSbOop;
    }

    public void setTotal4betSbOop(double total4betSbOop) {
        this.total4betSbOop = total4betSbOop;
    }

    public double getTotal4betBbOop() {
        return total4betBbOop;
    }

    public void setTotal4betBbOop(double total4betBbOop) {
        this.total4betBbOop = total4betBbOop;
    }

    public double getTotal5bet() {
        return total5bet;
    }

    public void setTotal5bet(double total5bet) {
        this.total5bet = total5bet;
    }

    public double getTotal5betMp() {
        return total5betMp;
    }

    public void setTotal5betMp(double total5betMp) {
        this.total5betMp = total5betMp;
    }

    public double getTotal5betCo() {
        return total5betCo;
    }

    public void setTotal5betCo(double total5betCo) {
        this.total5betCo = total5betCo;
    }

    public double getTotal5betBu() {
        return total5betBu;
    }

    public void setTotal5betBu(double total5betBu) {
        this.total5betBu = total5betBu;
    }

    public double getTotal5betSb() {
        return total5betSb;
    }

    public void setTotal5betSb(double total5betSb) {
        this.total5betSb = total5betSb;
    }

    public double getTotal5betBb() {
        return total5betBb;
    }

    public void setTotal5betBb(double total5betBb) {
        this.total5betBb = total5betBb;
    }

    public double getFoldTo5betEp() {
        return foldTo5betEp;
    }

    public void setFoldTo5betEp(double foldTo5betEp) {
        this.foldTo5betEp = foldTo5betEp;
    }

    public double getFoldTo5betMp() {
        return foldTo5betMp;
    }

    public void setFoldTo5betMp(double foldTo5betMp) {
        this.foldTo5betMp = foldTo5betMp;
    }

    public double getFoldTo5betCo() {
        return foldTo5betCo;
    }

    public void setFoldTo5betCo(double foldTo5betCo) {
        this.foldTo5betCo = foldTo5betCo;
    }

    public double getFoldTo5betBu() {
        return foldTo5betBu;
    }

    public void setFoldTo5betBu(double foldTo5betBu) {
        this.foldTo5betBu = foldTo5betBu;
    }

    public double getFoldTo5betSb() {
        return foldTo5betSb;
    }

    public void setFoldTo5betSb(double foldTo5betSb) {
        this.foldTo5betSb = foldTo5betSb;
    }

    public double getFoldTo5betBb() {
        return foldTo5betBb;
    }

    public void setFoldTo5betBb(double foldTo5betBb) {
        this.foldTo5betBb = foldTo5betBb;
    }

    public double getMpVsEp3bet() {
        return mpVsEp3bet;
    }

    public void setMpVsEp3bet(double mpVsEp3bet) {
        this.mpVsEp3bet = mpVsEp3bet;
    }

    public double getCoVsEp3bet() {
        return coVsEp3bet;
    }

    public void setCoVsEp3bet(double coVsEp3bet) {
        this.coVsEp3bet = coVsEp3bet;
    }

    public double getCoVsMp3bet() {
        return coVsMp3bet;
    }

    public void setCoVsMp3bet(double coVsMp3bet) {
        this.coVsMp3bet = coVsMp3bet;
    }

    public double getBuVsEp3bet() {
        return buVsEp3bet;
    }

    public void setBuVsEp3bet(double buVsEp3bet) {
        this.buVsEp3bet = buVsEp3bet;
    }

    public double getBuVsMp3bet() {
        return buVsMp3bet;
    }

    public void setBuVsMp3bet(double buVsMp3bet) {
        this.buVsMp3bet = buVsMp3bet;
    }

    public double getBuVsCo3bet() {
        return buVsCo3bet;
    }

    public void setBuVsCo3bet(double buVsCo3bet) {
        this.buVsCo3bet = buVsCo3bet;
    }

    public double getSbVsEp3bet() {
        return sbVsEp3bet;
    }

    public void setSbVsEp3bet(double sbVsEp3bet) {
        this.sbVsEp3bet = sbVsEp3bet;
    }

    public double getSbVsMp3bet() {
        return sbVsMp3bet;
    }

    public void setSbVsMp3bet(double sbVsMp3bet) {
        this.sbVsMp3bet = sbVsMp3bet;
    }

    public double getSbVsCo3bet() {
        return sbVsCo3bet;
    }

    public void setSbVsCo3bet(double sbVsCo3bet) {
        this.sbVsCo3bet = sbVsCo3bet;
    }

    public double getSbVsBu3bet() {
        return sbVsBu3bet;
    }

    public void setSbVsBu3bet(double sbVsBu3bet) {
        this.sbVsBu3bet = sbVsBu3bet;
    }

    public double getBbVsEp3bet() {
        return bbVsEp3bet;
    }

    public void setBbVsEp3bet(double bbVsEp3bet) {
        this.bbVsEp3bet = bbVsEp3bet;
    }

    public double getBbVsMp3bet() {
        return bbVsMp3bet;
    }

    public void setBbVsMp3bet(double bbVsMp3bet) {
        this.bbVsMp3bet = bbVsMp3bet;
    }

    public double getBbVsCo3bet() {
        return bbVsCo3bet;
    }

    public void setBbVsCo3bet(double bbVsCo3bet) {
        this.bbVsCo3bet = bbVsCo3bet;
    }

    public double getBbVsBu3bet() {
        return bbVsBu3bet;
    }

    public void setBbVsBu3bet(double bbVsBu3bet) {
        this.bbVsBu3bet = bbVsBu3bet;
    }

    public double getBbVsSb3bet() {
        return bbVsSb3bet;
    }

    public void setBbVsSb3bet(double bbVsSb3bet) {
        this.bbVsSb3bet = bbVsSb3bet;
    }
    public double getNl16Hands() {
        return nl16Hands;
    }

    public void setNl16Hands(double nl16Hands) {
        this.nl16Hands = nl16Hands;
    }

    public double getNl16Bb100() {
        return nl16Bb100;
    }

    public void setNl16Bb100(double nl16Bb100) {
        this.nl16Bb100 = nl16Bb100;
    }

    public double getNl16EvBb100() {
        return nl16EvBb100;
    }

    public void setNl16EvBb100(double nl16EvBb100) {
        this.nl16EvBb100 = nl16EvBb100;
    }

}
