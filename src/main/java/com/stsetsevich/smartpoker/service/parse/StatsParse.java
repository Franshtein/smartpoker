package com.stsetsevich.smartpoker.service.parse;

import com.stsetsevich.smartpoker.domain.Player;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.nio.charset.StandardCharsets;


public class StatsParse {


    Document doc;
    Player player;


    public StatsParse(Document doc) {

        // Document doc = Jsoup.connect("E:/test2.html").get();
        try {
            this.doc = doc;
            this.player = new Player(searchNickname());
        } catch (Exception exception) {
            System.out.println("Ошибка при создании экземпляра класса");
        }


        //Достаем и присваиваем ник игрока
        System.out.println("1");
        Player player = new Player(searchNickname());
    }

    public Player getStats() {

        try {

            //Достаем и присваиваем ник игрока
            System.out.println("1");
            //Достаем и присваиваем базовые статы
            System.out.println("2");
            searchBaseStats();
            //Достаем и присваиваем статы по лимитам
            System.out.println("3");
            searchStatsPerLimit();
            System.out.println("4");
            searchMainStats();
            System.out.println("5");
            search6MaxStats();
            System.out.println("6");
            searchPostflopStats();
            System.out.println("7");
            search35betFoldStats();
            System.out.println("8");
            search3betPvP();
            System.out.println("9");
            return player;

        } catch (Exception exception) {
            System.out.println("WTF");
            exception.printStackTrace();
        }
        System.out.println("Working?");
        return null;
    }

    public String searchNickname() {
        Elements listNews = doc.select("a#userName.nav-create-btn");
        String username = listNews.select("a").text();
        username = username.split(" Онлайн")[0];
        username = username.split(" Last online:")[0];

        return username;
    }

    private void searchBaseStats() {
        Elements list = doc.select("#page-stats");


        for (int i = 0; i < list.select("span span").size(); i++) {
            String text = list.select("span span").get(i).text();
            text = replaceChar(text);
            if (i == 0) player.setTotalHands(Double.parseDouble(text));
            if (i == 1) player.setWinnings(Double.parseDouble(text));
            if (i == 2) player.setVpip(Double.parseDouble(text));
            if (i == 3) player.setHome(text);
            if (i == 4) player.setAvgBb100(Double.parseDouble(text));
            if (i == 5) player.setTotalPfr(Double.parseDouble(text));
            if (i == 6) player.setWwsf(Double.parseDouble(text));
            if (i == 7) player.setEv(Double.parseDouble(text));
            if (i == 8) player.setTotal3bet(Double.parseDouble(text));
        }
    }

    private void searchStatsPerLimit() {
        Elements list = doc.select("#page-stats");
        String text;
        for (Element element : list.select("div.stat-left-side tbody tr")) {
            if (element.select("th").text().equals("NL10")) {
                for (int i = 0; i < element.select("td").size(); i++) {
                    text = element.select("td").get(i).text();
                    text = replaceChar(text);
                    if (i == 0) player.setNl10Hands(Double.parseDouble(text));
                    if (i == 2) player.setNl10Bb100(Double.parseDouble(text));
                    if (i == 4) player.setNl10EvBb100(Double.parseDouble(text));
                }

            }
            if (element.select("th").text().equals("NL16")) {
                for (int i = 0; i < element.select("td").size(); i++) {
                    text = element.select("td").get(i).text();
                    text = replaceChar(text);
                    if (i == 0) player.setNl16Hands(Double.parseDouble(text));
                    if (i == 2) player.setNl16Bb100(Double.parseDouble(text));
                    if (i == 4) player.setNl16EvBb100(Double.parseDouble(text));
                }
            }
            if (element.select("th").text().equals("NL25")) {
                for (int i = 0; i < element.select("td").size(); i++) {
                    text = element.select("td").get(i).text();
                    text = replaceChar(text);
                    if (i == 0) player.setNl25Hands(Double.parseDouble(text));
                    if (i == 2) player.setNl25Bb100(Double.parseDouble(text));
                    if (i == 4) player.setNl25EvBb100(Double.parseDouble(text));
                }


            }
        }
    }

    private void searchMainStats() {
        Elements list = doc.select("#page-stats");
        String text;
        int i = 0;
        for (Element element : list.select("div.hud-table.ui-resizable.ui-resizable-disabled")) {

            if (i == 0) {
                //УДАЛЯЕМ ВСЕ ЭЛЕМЕНТЫ <small>, так как они прикреплялись к тексту и портили значения
                element.select("small").remove();

                player.setStealTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "18").text().replace("-","0")));
                player.setStealCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "19").text().replace("-","0")));
                player.setStealBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "20").text().replace("-","0")));
                player.setStealSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "21").text().replace("-","0")));
                //----------------------------------------------
                player.setTotal3betIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "35").text().replace("-","0")));
                player.setTotal3betOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "36").text().replace("-","0")));
                player.setSqueezeTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "37").text().replace("-","0")));
                player.setTotal4bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "38").text().replace("-","0")));
                player.setTotal4betIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "39").text().replace("-","0")));
                player.setTotal4betOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "40").text().replace("-","0")));
                player.setWtsd(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "42").text().replace("-","0")));
                player.setW$sd(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "43").text().replace("-","0")));
                //--------------------------------------------------------
                player.setFoldTo3betTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "45").text().replace("-","0")));
                player.setFoldTo3betTotalIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "46").text().replace("-","0")));
                player.setFoldTo3betTotalOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "47").text().replace("-","0")));
                player.setFoldToSqueezeTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "48").text().replace("-","0")));
                player.setFoldTo4betTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "49").text().replace("-","0")));

                //------------------------------------------------------------------------

                player.setcBetFlopTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "67").text().replace("-","0")));
                player.setcBetFlopIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "68").text().replace("-","0")));
                player.setcBetFlopOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "69").text().replace("-","0")));
                player.setFoldVsCbetFlopTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "70").text().replace("-","0")));
                player.setFoldVsCbetFlopIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "71").text().replace("-","0")));
                player.setFoldVsCbetFlopOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "72").text().replace("-","0")));
                player.setDonkFlop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "73").text().replace("-","0")));
                player.setCheckCallFlop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "74").text().replace("-","0")));
                player.setCheckRaiseFlop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "75").text().replace("-","0")));
                player.setAfqFlop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "76").text().replace("-","0")));
                //------------------------------------------------------------------
                player.setcBetTurnTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "78").text().replace("-","0")));
                player.setcBetTurnIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "79").text().replace("-","0")));
                player.setcBetTurnOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "80").text().replace("-","0")));
                player.setFoldVsCbetTurnTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "81").text().replace("-","0")));
                player.setFoldVsCbetTurnIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "82").text().replace("-","0")));
                player.setFoldVsCbetTurnOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "83").text().replace("-","0")));
                player.setDonkTurn(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "84").text().replace("-","0")));
                player.setCheckCallTurn(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "85").text().replace("-","0")));
                player.setCheckRaiseTurn(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "86").text().replace("-","0")));
                player.setAfqTurn(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "87").text().replace("-","0")));
                //------------------------------------------------------------------
                player.setcBetRiverTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "89").text().replace("-","0")));
                player.setcBetRiverIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "90").text().replace("-","0")));
                player.setcBetRiverOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "91").text().replace("-","0")));
                player.setFoldVsCbetRiverTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "92").text().replace("-","0")));
                player.setFoldVsCbetRiverIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "93").text().replace("-","0")));
                player.setFoldVsCbetRiverOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "94").text().replace("-","0")));
                player.setDonkRiver(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "95").text().replace("-","0")));
                player.setCheckCallRiver(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "96").text().replace("-","0")));
                player.setCheckRaiseRiver(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "97").text().replace("-","0")));
                player.setAfqRiver(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "98").text().replace("-","0")));
                //------------------------------------------------------------------
            }
            i++;
        }


    }

    private void search6MaxStats() {
        Elements list = doc.select("#page-stats");
        String text;
        int i = 0;
        for (Element element : list.select("div.hud-table.ui-resizable.ui-resizable-disabled")) {

            if (i == 1) {
                //УДАЛЯЕМ ВСЕ ЭЛЕМЕНТЫ <small>, так как они прикреплялись к тексту и портили значения
                element.select("small").remove();

                player.setPfrEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "10").text().replace("-","0")));
                player.setPfrMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "11").text().replace("-","0")));
                player.setPfrCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "12").text().replace("-","0")));
                player.setPfrBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "13").text().replace("-","0")));
                player.setPfrSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "14").text().replace("-","0")));
                //-----------------------------------------------------------------
                player.setTotalIsoraise(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "17").text().replace("-","0")));
                player.setIsoraiseEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "18").text().replace("-","0")));
                player.setIsoraiseMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "19").text().replace("-","0")));
                player.setIsoraiseCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "20").text().replace("-","0")));
                player.setIsoraisBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "21").text().replace("-","0")));
                player.setIsoraiseSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "22").text().replace("-","0")));
                player.setIsoraiseBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "23").text().replace("-","0")));
                //-----------------------------------------------------------------
                player.setTotalCallOpenraise(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "25").text().replace("-","0")));
                player.setCallOpenraiseMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "27").text().replace("-","0")));
                player.setCallOpenraiseCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "28").text().replace("-","0")));
                player.setCallOpenraiseBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "29").text().replace("-","0")));
                player.setCallOpenraiseSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "30").text().replace("-","0")));
                player.setCallOpenraiseBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "31").text().replace("-","0")));
                //-----------------------------------------------------------------
                player.setFoldTo3betTotalOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "41").text().replace("-","0")));
                player.setFoldTo3betOopEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "42").text().replace("-","0")));
                player.setFoldTo3betOopMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "43").text().replace("-","0")));
                player.setFoldTo3betOopCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "44").text().replace("-","0")));
                player.setFoldTo3betOopSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "46").text().replace("-","0")));
                player.setFoldTo3betOopBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "47").text().replace("-","0")));
                //-----------------------------------------------------------------
                player.setFoldTo3betTotalIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "49").text().replace("-","0")));
                player.setFoldTo3betIpEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "50").text().replace("-","0")));
                player.setFoldTo3betIpMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "51").text().replace("-","0")));
                player.setFoldTo3betIpCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "52").text().replace("-","0")));
                player.setFoldTo3betIpBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "53").text().replace("-","0")));
                player.setFoldTo3betIpBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "55").text().replace("-","0")));
                //-----------------------------------------------------------------
                player.setFoldTo3betTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "57").text().replace("-","0")));
                player.setFoldTo3betTotalEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "58").text().replace("-","0")));
                player.setFoldTo3betTotalMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "59").text().replace("-","0")));
                player.setFoldTo3betTotalCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "60").text().replace("-","0")));
                player.setFoldTo3betTotalBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "61").text().replace("-","0")));
                player.setFoldTo3betTotalSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "62").text().replace("-","0")));
                player.setFoldTo3betTotalBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "63").text().replace("-","0")));
                //-----------------------------------------------------------------
                player.setTotal3betEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "66").text().replace("-","0")));
                player.setTotal3betMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "67").text().replace("-","0")));
                player.setTotal3betCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "68").text().replace("-","0")));
                player.setTotal3betBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "69").text().replace("-","0")));
                player.setTotal3betSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "70").text().replace("-","0")));
                player.setTotal3betBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "71").text().replace("-","0")));
                //-----------------------------------------------------------------
                player.setOpen4betTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "73").text().replace("-","0")));
                player.setOpen4betCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "76").text().replace("-","0")));
                player.setOpen4betBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "77").text().replace("-","0")));
                player.setOpen4betSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "78").text().replace("-","0")));
                player.setOpen4betBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "79").text().replace("-","0")));
                //-----------------------------------------------------------------
                player.setSqueezeTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "81").text().replace("-","0")));
                player.setSqueezeCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "84").text().replace("-","0")));
                player.setSqueezeBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "85").text().replace("-","0")));
                player.setSqueezeSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "86").text().replace("-","0")));
                player.setSqueezeBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "87").text().replace("-","0")));
                //-----------------------------------------------------------------
                player.setFoldToSqueezeTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "89").text().replace("-","0")));
                player.setFoldToSqueezeEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "90").text().replace("-","0")));
                player.setFoldToSqueezeMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "91").text().replace("-","0")));
                player.setFoldToSqueezeCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "92").text().replace("-","0")));
                player.setFoldToSqueezeBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "93").text().replace("-","0")));
                //-----------------------------------------------------------------
                player.setLimpTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "97").text().replace("-","0")));
                player.setLimpEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "98").text().replace("-","0")));
                player.setLimpMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "99").text().replace("-","0")));
                player.setLimpCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "100").text().replace("-","0")));
                player.setLimpBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "101").text().replace("-","0")));
                player.setLimpSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "102").text().replace("-","0")));
            }
            i++;
        }
    }

    private void searchPostflopStats() {
        Elements list = doc.select("#page-stats");
        String text;
        int i = 0;
        for (Element element : list.select("div.hud-table.ui-resizable.ui-resizable-disabled")) {

            if (i == 3) {
                //УДАЛЯЕМ ВСЕ ЭЛЕМЕНТЫ <small>, так как они прикреплялись к тексту и портили значения
                element.select("small").remove();

                player.setBetToMissCbetFlopTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "5").text().replace("-","0")));
                player.setBetToMissCbetTurnTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "6").text().replace("-","0")));
                player.setBetToMissCbetRiverTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "7").text().replace("-","0")));
                //----------------------------------------------
                player.setBetToMissCbetFlopIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "9").text().replace("-","0")));
                player.setBetToMissCbetTurnIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "10").text().replace("-","0")));
                player.setBetToMissCbetRiverIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "11").text().replace("-","0")));
                //----------------------------------------------
                player.setBetToMissCbetTurnOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "14").text().replace("-","0")));
                player.setBetToMissCbetRiverOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "15").text().replace("-","0")));
                //----------------------------------------------
                player.setRaiseToCbetFlopTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "17").text().replace("-","0")));
                player.setRaiseToCbetTurnTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "18").text().replace("-","0")));
                player.setRaiseToCbetRiverTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "19").text().replace("-","0")));
                //----------------------------------------------
                player.setRaiseToCbetFlopIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "21").text().replace("-","0")));
                player.setRaiseToCbetTurnIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "22").text().replace("-","0")));
                player.setRaiseToCbetRiverIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "23").text().replace("-","0")));
                //----------------------------------------------
                player.setRaiseToCbetFlopOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "25").text().replace("-","0")));
                player.setRaiseToCbetTurnOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "26").text().replace("-","0")));
                player.setRaiseToCbetRiverOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "27").text().replace("-","0")));
                //----------------------------------------------
                player.setSkippedCbetFoldFlopTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "29").text().replace("-","0")));
                player.setSkippedCbetFoldTurnTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "30").text().replace("-","0")));
                player.setSkippedCbetFoldRiverTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "31").text().replace("-","0")));
                //----------------------------------------------
                player.setSkippedCbetFoldTurnIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "34").text().replace("-","0")));
                player.setSkippedCbetFoldRiverIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "35").text().replace("-","0")));
                //----------------------------------------------
                player.setSkippedCbetFoldFlopOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "37").text().replace("-","0")));
                player.setSkippedCbetFoldTurnOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "38").text().replace("-","0")));
                player.setSkippedCbetFoldRiverOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "39").text().replace("-","0")));
                //----------------------------------------------
                player.setAggFactorFlop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "41").text().replace("-","0")));
                player.setAggFactorTurn(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "42").text().replace("-","0")));
                player.setAggFactorRiver(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "43").text().replace("-","0")));
                //----------------------------------------------
                player.setWonAfterRaiseFlop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "45").text().replace("-","0")));
                player.setWonAfterRaiseTurn(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "46").text().replace("-","0")));
                player.setWonAfterRaiseRiver(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "47").text().replace("-","0")));
                //----------------------------------------------
            }
            i++;
        }
    }

    private void search35betFoldStats() {
        Elements list = doc.select("#page-stats");
        String text;
        int i = 0;
        for (Element element : list.select("div.hud-table.ui-resizable.ui-resizable-disabled")) {

            if (i == 4) {
                //УДАЛЯЕМ ВСЕ ЭЛЕМЕНТЫ <small>, так как они прикреплялись к тексту и портили значения
                element.select("small").remove();

                player.setTotal3betIpMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "19").text().replace("-","0")));
                player.setTotal3betIpCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "20").text().replace("-","0")));
                player.setTotal3betIpBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "21").text().replace("-","0")));
                player.setTotal3betIpBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "23").text().replace("-","0")));
                //----------------------------------------------
                player.setTotal3betOopSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "30").text().replace("-","0")));
                player.setTotal3betOopBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "31").text().replace("-","0")));
                //----------------------------------------------
                player.setTotal4bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "49").text().replace("-","0")));
                player.setTotal4betEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "50").text().replace("-","0")));
                player.setTotal4betMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "51").text().replace("-","0")));
                player.setTotal4betCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "52").text().replace("-","0")));
                player.setTotal4betBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "53").text().replace("-","0")));
                player.setTotal4betSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "54").text().replace("-","0")));
                player.setTotal4betBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "55").text().replace("-","0")));
                //----------------------------------------------
                player.setTotal4betIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "57").text().replace("-","0")));
                player.setTotal4betEpIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "58").text().replace("-","0")));
                player.setTotal4betMpIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "59").text().replace("-","0")));
                player.setTotal4betCoIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "60").text().replace("-","0")));
                player.setTotal4betBuIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "61").text().replace("-","0")));
                player.setTotal4betSbIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "62").text().replace("-","0")));
                player.setTotal4betBbIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "63").text().replace("-","0")));
                //----------------------------------------------
                player.setTotal4betOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "65").text().replace("-","0")));
                player.setTotal4betEpOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "66").text().replace("-","0")));
                player.setTotal4betMpOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "67").text().replace("-","0")));
                player.setTotal4betCoOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "68").text().replace("-","0")));
                player.setTotal4betSbOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "70").text().replace("-","0")));
                player.setTotal4betBbOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "71").text().replace("-","0")));
                //----------------------------------------------
                player.setFoldTo4betTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "73").text().replace("-","0")));
                player.setFoldTo4betMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "75").text().replace("-","0")));
                player.setFoldTo4betCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "76").text().replace("-","0")));
                player.setFoldTo4betBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "77").text().replace("-","0")));
                player.setFoldTo4betSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "78").text().replace("-","0")));
                player.setFoldTo4betBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "79").text().replace("-","0")));
                //----------------------------------------------
                player.setTotal5bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "89").text().replace("-","0")));
                player.setTotal5betMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "91").text().replace("-","0")));
                player.setTotal5betCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "92").text().replace("-","0")));
                player.setTotal5betBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "93").text().replace("-","0")));
                player.setTotal5betSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "94").text().replace("-","0")));
                player.setTotal5betBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "95").text().replace("-","0")));
                //----------------------------------------------
                player.setFoldTo5betTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "97").text().replace("-","0")));
                player.setFoldTo5betEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "98").text().replace("-","0")));
                player.setFoldTo5betMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "99").text().replace("-","0")));
                player.setFoldTo5betCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "100").text().replace("-","0")));
                player.setFoldTo5betBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "101").text().replace("-","0")));
                player.setFoldTo5betSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "102").text().replace("-","0")));
                player.setFoldTo5betBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "103").text().replace("-","0")));
                //----------------------------------------------
            }
            i++;
        }
    }

    private void search3betPvP() {
        Elements list = doc.select("#page-stats");
        String text;
        int i = 0;
        for (Element element : list.select("div.hud-table.ui-resizable.ui-resizable-disabled")) {

            if (i == 5) {
                //УДАЛЯЕМ ВСЕ ЭЛЕМЕНТЫ <small>, так как они прикреплялись к тексту и портили значения
                element.select("small").remove();

                player.setMpVsEp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "7").text().replace("-","0")));
                //------------------------------------
                player.setCoVsEp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "13").text().replace("-","0")));
                player.setCoVsMp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "14").text().replace("-","0")));
                //------------------------------------
                player.setBuVsEp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "19").text().replace("-","0")));
                player.setBuVsMp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "20").text().replace("-","0")));
                player.setBuVsCo3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "21").text().replace("-","0")));
                //------------------------------------
                player.setSbVsEp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "25").text().replace("-","0")));
                player.setSbVsMp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "26").text().replace("-","0")));
                player.setSbVsCo3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "27").text().replace("-","0")));
                player.setSbVsBu3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "28").text().replace("-","0")));
                //------------------------------------
                player.setBbVsEp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "31").text().replace("-","0")));
                player.setBbVsMp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "32").text().replace("-","0")));
                player.setBbVsCo3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "33").text().replace("-","0")));
                player.setBbVsBu3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "34").text().replace("-","0")));
                player.setBbVsSb3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "35").text().replace("-","0")));
            }
            i++;
        }
    }

    private String replaceChar(String text) {
        double value;
        if (text.contains("$")) {
            text = text.replace("$", "");
        }
        if (text.contains("k")) {
            text = text.split("k")[0];
            value = Double.parseDouble(text);
            value = value * 1000;
            text = Double.toString(value);
        }
        if (text.contains("m")) {
            text = text.split("m")[0];
            value = Double.parseDouble(text);
            value = value * 1000000;
            text = Double.toString(value);
        }
        return text;
    }

    private static void log(String msg, String... vals) {
        System.out.println(String.format(msg, vals));

    }

}