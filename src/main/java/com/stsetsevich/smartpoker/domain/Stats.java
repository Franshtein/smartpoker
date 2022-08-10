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
                        //  playerRepo.save(player);
            return player;

        } catch (Exception exception) {
            System.out.println("WTF");
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
        List<String> list1 = new ArrayList<>();
        int i=0;
        for (Element element : list.select("div.hud-table.ui-resizable.ui-resizable-disabled")) {


            //УДАЛЯЕМ ВСЕ ЭЛЕМЕНТЫ <small>, так как они прикреплялись к тексту и портили значения
            element.select("small").remove();

                  list1.add(element.getElementsByAttributeValue("tabindex", "18").text());
            System.out.println(list1 + " " +i);
            i++;

         //   player.setStealTotal(Double.parseDouble(
         //           element.getElementsByAttributeValue("tabindex", "18").text()));
        }
        System.out.println(list1);
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