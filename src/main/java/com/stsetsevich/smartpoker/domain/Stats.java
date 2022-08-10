package com.stsetsevich.smartpoker.domain;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Stats {

    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public void getText() {
        try {
            Document doc = Jsoup.connect("https://yandex.by/")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            //    Elements listNews = doc.select("div#tabnews_newsc.content-tabs__items.content-tabs__items_active_true");
            Elements listNews = doc.select("div#wd-_topnews-1.b-widget-data.b-wrapper.b-wrapper-");
            for (Element element : listNews.select("a"))
                System.out.println(element.text());

        } catch (Exception exception) {
            System.out.println("Не удается получить доступ к информации");
        }
        System.out.println("Working?");
    }

    public void getWiki() {
        try {
            Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
            log(doc.title());
            Elements newsHeadlines = doc.select("#mp-itn b a");
            for (Element headline : newsHeadlines) {
                log("%s\n\t%s",
                        headline.attr("title"), headline.absUrl("href"));
            }

        } catch (Exception exception) {
            System.out.println("Не удается получить доступ к информации");
        }
        System.out.println("Working WIKI?");
    }


    public void getStats() {

        try {
            File file = new File("E:/test2.html");
            Document doc = Jsoup.parse(file, StandardCharsets.UTF_8.name());
            //    Elements listNews = doc.select("div#tabnews_newsc.content-tabs__items.content-tabs__items_active_true");
            System.out.println("Chet idet");

            //   System.out.println(doc.title());

            Elements listNews = doc.select("#page-stats");
            System.out.println(doc.text());
            for (Element element : listNews.select("span b")) {

                System.out.println(element.text());
                //  System.out.println("A chto vivodit?");
            }
            System.out.println(listNews.select("span b").get(1).text());
            System.out.println(listNews.select("span span").get(1).text());


        } catch (Exception exception) {
            System.out.println("WTF");
        }
        System.out.println("Working?");
    }

    public Player getStats2() {

        try {
            File file = new File("E:/poker_nicks/Franshtein.html");
            // Document doc = Jsoup.connect("E:/test2.html").get();
            Document doc = Jsoup.parse(file, StandardCharsets.UTF_8.name());


            //Достаем и присваиваем ник игрока
            System.out.println("1");
            Player player = new Player(searchNickname(doc));
            //Достаем и присваиваем базовые статы
            System.out.println("2");
            searchBaseStats(doc, player);
            //Достаем и присваиваем статы по лимитам
            System.out.println("3");
            searchStatsPerLimit(doc, player);
            //   player.setNickname(username);
            System.out.println("4");
           searchMainStats(doc, player);
            System.out.println("5");
            search6MaxStats(doc, player);
            System.out.println("6");
            searchPostflopStats(doc, player);
            System.out.println("7");
            search35betFoldStats(doc, player);
            System.out.println("8");
            search3betPvP(doc, player);
            System.out.println("9");

            return player;

        } catch (Exception exception) {
            System.out.println("WTF");
            exception.printStackTrace();
        }
        System.out.println("Working?");
        return null;
    }

    private String searchNickname(Document doc) {
        Elements listNews = doc.select("a#userName.nav-create-btn");
        String username = listNews.select("a").text();
        username = username.split("Онлайн")[0];

        return username;
    }

    private void searchBaseStats(Document doc, Player player) {
        Elements list = doc.select("#page-stats");
        //  System.out.println(doc.text());
        //   for (Element element : list.select("span b")) {

        //       System.out.println(element.text());
        //    }


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

    private void searchStatsPerLimit(Document doc, Player player) {
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
        //    for (int i=0; i<list.select("td").size(); i++)
        //   {
        //       String text=list.select("td").get(i).text();
        //      text=replaceChar(text);
        //       System.out.println(text);
        //  if (i==0) player.setTotalHands(Double.parseDouble(text));
        // if (i==1) player.setWinnings(Double.parseDouble(text));
        //  if (i==2) player.setVpip(Double.parseDouble(text));
        // if (i==3) player.setHome(text);
        // if (i==4) player.setAvgBb100(Double.parseDouble(text));
        //  if (i==5) player.setTotalPfr(Double.parseDouble(text));
        //  if (i==6) player.setWwsf(Double.parseDouble(text));
        //  if (i==7) player.setEv(Double.parseDouble(text));
        //  if (i==8) player.setTotal3bet(Double.parseDouble(text));
        //  }


        public void searchMainStats(Document doc, Player player) {
            Elements list = doc.select("#page-stats");
            String text;
            int i=0;
            for (Element element : list.select("div.hud-table.ui-resizable.ui-resizable-disabled")) {

                if (i == 0) {
                    //УДАЛЯЕМ ВСЕ ЭЛЕМЕНТЫ <small>, так как они прикреплялись к тексту и портили значения
                    element.select("small").remove();

                    player.setStealTotal(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "18").text()));
                    player.setStealCo(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "19").text()));
                    player.setStealBu(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "20").text()));
                    player.setStealSb(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "21").text()));
                    //----------------------------------------------
                    player.setTotal3betIp(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "35").text()));
                    player.setTotal3betOop(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "36").text()));
                    player.setSqueezeTotal(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "37").text()));
                    player.setTotal4bet(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "38").text()));
                    player.setTotal4betIp(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "39").text()));
                    player.setTotal4betOop(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "40").text()));
                    player.setWtsd(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "42").text()));
                    player.setW$sd(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "43").text()));
                    //--------------------------------------------------------
                    player.setFoldTo3betTotal(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "45").text()));
                    player.setFoldTo3betTotalIp(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "46").text()));
                    player.setFoldTo3betTotalOop(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "47").text()));
                    player.setFoldToSqueezeTotal(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "48").text()));
                    player.setFoldTo4betTotal(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "49").text()));

                    //------------------------------------------------------------------------

                    player.setcBetFlopTotal(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "67").text()));
                    player.setcBetFlopIp(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "68").text()));
                    player.setcBetFlopOop(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "69").text()));
                    player.setFoldVsCbetFlopTotal(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "70").text()));
                    player.setFoldVsCbetFlopIp(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "71").text()));
                    player.setFoldVsCbetFlopOop(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "72").text()));
                    player.setDonkFlop(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "73").text()));
                    player.setCheckCallFlop(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "74").text()));
                    player.setCheckRaiseFlop(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "75").text()));
                    player.setAfqFlop(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "76").text()));
                    //------------------------------------------------------------------
                    player.setcBetTurnTotal(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "78").text()));
                    player.setcBetTurnIp(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "79").text()));
                    player.setcBetTurnOop(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "80").text()));
                    player.setFoldVsCbetTurnTotal(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "81").text()));
                    player.setFoldVsCbetTurnIp(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "82").text()));
                    player.setFoldVsCbetTurnOop(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "83").text()));
                    player.setDonkTurn(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "84").text()));
                    player.setCheckCallTurn(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "85").text()));
                    player.setCheckRaiseTurn(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "86").text()));
                    player.setAfqTurn(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "87").text()));
                    //------------------------------------------------------------------
                    player.setcBetRiverTotal(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "89").text()));
                    player.setcBetRiverIp(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "90").text()));
                    player.setcBetRiverOop(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "91").text()));
                    player.setFoldVsCbetRiverTotal(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "92").text()));
                    player.setFoldVsCbetRiverIp(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "93").text()));
                    player.setFoldVsCbetRiverOop(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "94").text()));
                    player.setDonkRiver(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "95").text()));
                    player.setCheckCallRiver(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "96").text()));
                    player.setCheckRaiseRiver(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "97").text()));
                    player.setAfqRiver(Double.parseDouble(
                            element.getElementsByAttributeValue("tabindex", "98").text()));
                    //------------------------------------------------------------------
                }
                i++;
            }



        }

    public void search6MaxStats(Document doc, Player player) {
        Elements list = doc.select("#page-stats");
        String text;
        int i=0;
        for (Element element : list.select("div.hud-table.ui-resizable.ui-resizable-disabled")) {

            if(i==1) {
                //УДАЛЯЕМ ВСЕ ЭЛЕМЕНТЫ <small>, так как они прикреплялись к тексту и портили значения
                element.select("small").remove();

                player.setPfrEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "10").text()));
                player.setPfrMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "11").text()));
                player.setPfrCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "12").text()));
                player.setPfrBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "13").text()));
                player.setPfrSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "14").text()));
                //-----------------------------------------------------------------
                player.setTotalIsoraise(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "17").text()));
                player.setIsoraiseEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "18").text()));
                player.setIsoraiseMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "19").text()));
                player.setIsoraiseCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "20").text()));
                player.setIsoraisBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "21").text()));
                player.setIsoraiseSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "22").text()));
                player.setIsoraiseBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "23").text()));
                //-----------------------------------------------------------------
                player.setTotalCallOpenraise(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "25").text()));
                player.setCallOpenraiseMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "27").text()));
                player.setCallOpenraiseCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "28").text()));
                player.setCallOpenraiseBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "29").text()));
                player.setCallOpenraiseSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "30").text()));
                player.setCallOpenraiseBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "31").text()));
                //-----------------------------------------------------------------
                player.setFoldTo3betTotalOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "41").text()));
                player.setFoldTo3betOopEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "42").text()));
                player.setFoldTo3betOopMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "43").text()));
                player.setFoldTo3betOopCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "44").text()));
                player.setFoldTo3betOopSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "46").text()));
                player.setFoldTo3betOopBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "47").text()));
                //-----------------------------------------------------------------
                player.setFoldTo3betTotalIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "49").text()));
                player.setFoldTo3betIpEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "50").text()));
                player.setFoldTo3betIpMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "51").text()));
                player.setFoldTo3betIpCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "52").text()));
                player.setFoldTo3betIpBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "53").text()));
                player.setFoldTo3betIpBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "55").text()));
                //-----------------------------------------------------------------
                player.setFoldTo3betTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "57").text()));
                player.setFoldTo3betTotalEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "58").text()));
                player.setFoldTo3betTotalMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "59").text()));
                player.setFoldTo3betTotalCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "60").text()));
                player.setFoldTo3betTotalBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "61").text()));
                player.setFoldTo3betTotalSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "62").text()));
                player.setFoldTo3betTotalBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "63").text()));
                //-----------------------------------------------------------------
                player.setTotal3betEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "66").text()));
                player.setTotal3betMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "67").text()));
                player.setTotal3betCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "68").text()));
                player.setTotal3betBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "69").text()));
                player.setTotal3betSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "70").text()));
                player.setTotal3betBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "71").text()));
                //-----------------------------------------------------------------
                player.setOpen4betTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "73").text()));
                player.setOpen4betCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "76").text()));
                player.setOpen4betBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "77").text()));
                player.setOpen4betSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "78").text()));
                player.setOpen4betBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "79").text()));
                //-----------------------------------------------------------------
                player.setSqueezeTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "81").text()));
                player.setSqueezeCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "84").text()));
                player.setSqueezeBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "85").text()));
                player.setSqueezeSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "86").text()));
                player.setSqueezeBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "87").text()));
                //-----------------------------------------------------------------
                player.setFoldToSqueezeTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "89").text()));
                player.setFoldToSqueezeEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "90").text()));
                player.setFoldToSqueezeMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "91").text()));
                player.setFoldToSqueezeCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "92").text()));
                player.setFoldToSqueezeBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "93").text()));
                //-----------------------------------------------------------------
                player.setLimpTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "97").text()));
                player.setLimpEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "98").text()));
                player.setLimpMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "99").text()));
                player.setLimpCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "100").text()));
                player.setLimpBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "101").text()));
                player.setLimpSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "102").text()));
            }
            i++;
        }
    }

    public void searchPostflopStats(Document doc, Player player)
    {
        Elements list = doc.select("#page-stats");
        String text;
        int i=0;
        for (Element element : list.select("div.hud-table.ui-resizable.ui-resizable-disabled")) {

            if (i == 3) {
                //УДАЛЯЕМ ВСЕ ЭЛЕМЕНТЫ <small>, так как они прикреплялись к тексту и портили значения
                element.select("small").remove();

                player.setBetToMissCbetFlopTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "5").text()));
                player.setBetToMissCbetTurnTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "6").text()));
                player.setBetToMissCbetRiverTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "7").text()));
                //----------------------------------------------
                player.setBetToMissCbetFlopIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "9").text()));
                player.setBetToMissCbetTurnIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "10").text()));
                player.setBetToMissCbetRiverIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "11").text()));
                //----------------------------------------------
                player.setBetToMissCbetTurnOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "14").text()));
                player.setBetToMissCbetRiverOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "15").text()));
                //----------------------------------------------
                player.setRaiseToCbetFlopTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "17").text()));
                player.setRaiseToCbetTurnTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "18").text()));
                player.setRaiseToCbetRiverTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "19").text()));
                //----------------------------------------------
                player.setRaiseToCbetFlopIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "21").text()));
                player.setRaiseToCbetTurnIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "22").text()));
                player.setRaiseToCbetRiverIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "23").text()));
                //----------------------------------------------
                player.setRaiseToCbetFlopOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "25").text()));
                player.setRaiseToCbetTurnOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "26").text()));
                player.setRaiseToCbetRiverOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "27").text()));
                //----------------------------------------------
                player.setSkippedCbetFoldFlopTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "29").text()));
                player.setSkippedCbetFoldTurnTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "30").text()));
                player.setSkippedCbetFoldRiverTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "31").text()));
                //----------------------------------------------
                player.setSkippedCbetFoldTurnIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "34").text()));
                player.setSkippedCbetFoldRiverIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "35").text()));
                //----------------------------------------------
                player.setSkippedCbetFoldFlopOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "37").text()));
                player.setSkippedCbetFoldTurnOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "38").text()));
                player.setSkippedCbetFoldRiverOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "39").text()));
                //----------------------------------------------
                player.setAggFactorFlop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "41").text()));
                player.setAggFactorTurn(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "42").text()));
                player.setAggFactorRiver(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "43").text()));
                //----------------------------------------------
                player.setWonAfterRaiseFlop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "45").text()));
                player.setWonAfterRaiseTurn(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "46").text()));
                player.setWonAfterRaiseRiver(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "47").text()));
                //----------------------------------------------
            }
            i++;
        }
    }

    public void search35betFoldStats(Document doc, Player player)
    {
        Elements list = doc.select("#page-stats");
        String text;
        int i=0;
        for (Element element : list.select("div.hud-table.ui-resizable.ui-resizable-disabled")) {

            if (i == 4) {
                //УДАЛЯЕМ ВСЕ ЭЛЕМЕНТЫ <small>, так как они прикреплялись к тексту и портили значения
                element.select("small").remove();

                player.setTotal3betIpMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "19").text()));
                player.setTotal3betIpCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "20").text()));
                player.setTotal3betIpBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "21").text()));
                player.setTotal3betIpBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "23").text()));
                //----------------------------------------------
                player.setTotal3betOopSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "30").text()));
                player.setTotal3betOopBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "31").text()));
                //----------------------------------------------
                player.setTotal4bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "49").text()));
                player.setTotal4betEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "50").text()));
                player.setTotal4betMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "51").text()));
                player.setTotal4betCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "52").text()));
                player.setTotal4betBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "53").text()));
                player.setTotal4betSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "54").text()));
                player.setTotal4betBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "55").text()));
                //----------------------------------------------
                player.setTotal4betIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "57").text()));
                player.setTotal4betEpIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "58").text()));
                player.setTotal4betMpIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "59").text()));
                player.setTotal4betCoIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "60").text()));
                player.setTotal4betBuIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "61").text()));
                player.setTotal4betSbIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "62").text()));
                player.setTotal4betBbIp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "63").text()));
                //----------------------------------------------
                player.setTotal4betOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "65").text()));
                player.setTotal4betEpOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "66").text()));
                player.setTotal4betMpOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "67").text()));
                player.setTotal4betCoOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "68").text()));
                player.setTotal4betSbOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "70").text()));
                player.setTotal4betBbOop(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "71").text()));
                //----------------------------------------------
                player.setFoldTo4betTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "73").text()));
                player.setFoldTo4betMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "75").text()));
                player.setFoldTo4betCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "76").text()));
                player.setFoldTo4betBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "77").text()));
                player.setFoldTo4betSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "78").text()));
                player.setFoldTo4betBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "79").text()));
                //----------------------------------------------
                player.setTotal5bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "89").text()));
                player.setTotal5betMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "91").text()));
                player.setTotal5betCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "92").text()));
                player.setTotal5betBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "93").text()));
                player.setTotal5betSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "94").text()));
                player.setTotal5betBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "95").text()));
                //----------------------------------------------
                player.setFoldTo5betTotal(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "97").text()));
                player.setFoldTo5betEp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "98").text()));
                player.setFoldTo5betMp(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "99").text()));
                player.setFoldTo5betCo(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "100").text()));
                player.setFoldTo5betBu(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "101").text()));
                player.setFoldTo5betSb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "102").text()));
                player.setFoldTo5betBb(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "103").text()));
                //----------------------------------------------
            }
            i++;
        }
    }

    public void search3betPvP(Document doc, Player player)
    {
        Elements list = doc.select("#page-stats");
        String text;
        int i=0;
        for (Element element : list.select("div.hud-table.ui-resizable.ui-resizable-disabled")) {

            if (i == 5) {
                //УДАЛЯЕМ ВСЕ ЭЛЕМЕНТЫ <small>, так как они прикреплялись к тексту и портили значения
                element.select("small").remove();

                player.setMpVsEp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "7").text()));
                //------------------------------------
                player.setCoVsEp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "13").text()));
                player.setCoVsMp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "14").text()));
                //------------------------------------
                player.setBuVsEp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "19").text()));
                player.setBuVsMp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "20").text()));
                player.setBuVsCo3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "21").text()));
                //------------------------------------
                player.setSbVsEp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "25").text()));
                player.setSbVsMp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "26").text()));
                player.setSbVsCo3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "27").text()));
                player.setSbVsBu3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "28").text()));
                //------------------------------------
                player.setBbVsEp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "31").text()));
                player.setBbVsMp3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "32").text()));
                player.setBbVsCo3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "33").text()));
                player.setBbVsBu3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "34").text()));
                player.setBbVsSb3bet(Double.parseDouble(
                        element.getElementsByAttributeValue("tabindex", "35").text()));
            }
            i++;
        }
    }
        private String replaceChar (String text)
        {
            double value;
            if (text.contains("k")) {
                text = text.split("k")[0];
                value = Double.parseDouble(text);
                value = value * 1000;
                text = Double.toString(value);
            }
            if (text.contains("$")) {
                text = text.replace("$", "");
            }
            return text;
        }

        private static void log (String msg, String...vals){
            System.out.println(String.format(msg, vals));

        }

    }