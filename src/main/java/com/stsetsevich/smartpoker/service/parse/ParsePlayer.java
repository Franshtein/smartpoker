package com.stsetsevich.smartpoker.service.parse;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.SilentJavaScriptErrorListener;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.stsetsevich.smartpoker.domain.SmarthandCookies;
import com.stsetsevich.smartpoker.repos.SmarthandCookiesRepo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Подключается к сайту smarthand.pro
 * Парсит страницу с заданным игроком
 * return Document if player exist
 * return null if user player exist
 * <p>
 * !!!Может возникнуть проблема с авторизацией на smarthand.pro в течение 15 минут,
 * !!!если кто-то зашел c другого места на сайт под этим логином.
 * !!!Блокировка снимается сайтом через 15 минут.
 * <p>
 * !!!Может быть решено регистрацией нескольких аккаунтов и авторизацией через них в случае провала первого.
 */
@Service
public class ParsePlayer {

    @Autowired
    SmarthandCookiesRepo smarthandCookiesRepo;
    private final long id = 0L; //id кукис в БД


    /**
     * Получает куки smarthand.pro и сохраняет их в БД {@link SmarthandCookies}
     */
    private void setCookies() throws IOException {

        SmarthandCookies smarthandAccount = smarthandCookiesRepo.findById(id);
        //получаем куки через библиотеку Jsoup
        Connection.Response response = Jsoup
                .connect("https://smarthand.pro/handler")
                .method(Connection.Method.POST)
                .data("login", smarthandAccount.getSmarthandLogin())
                .data("pass", smarthandAccount.getSmarthandPassword())
                .data("doLogin", "Войти")
                .execute();

        Map<String, String> cookie = response.cookies();
        /*response = Jsoup
                .connect("https://smarthand.pro/settings")
                .cookies(response.cookies())
                .execute();
         */



        //сохраняем в бд идентификатор сессии
        smarthandAccount.setSessionId(cookie.get("PHPSESSID"));
        try {
            System.out.println("1");
            smarthandCookiesRepo.save(smarthandAccount);
            System.out.println("2");
        } catch (Exception exception) {
            System.out.println(exception.toString());

        }

    }

    //получает куки для smarthand.pro из БД
    private void getCookies(WebClient webClient) throws IOException {

        SmarthandCookies smarthandAccountAndCookies = smarthandCookiesRepo.findById(id);

        //Если кукис нет, создаем новые
        if (smarthandAccountAndCookies == null || smarthandAccountAndCookies.getSessionId() == null || smarthandAccountAndCookies.getSessionId() == "") {
            setCookies();
            smarthandAccountAndCookies = smarthandCookiesRepo.findById(id);

        }
        Cookie cookie1 = new Cookie("smarthand.pro", "lang", "en");
        Cookie cookie2 = new Cookie("smarthand.pro", "template", "default");
        Cookie cookie3 = new Cookie("smarthand.pro", "PHPSESSID", smarthandAccountAndCookies.getSessionId());
        webClient.getCookieManager().addCookie(cookie1);
        webClient.getCookieManager().addCookie(cookie2);
        webClient.getCookieManager().addCookie(cookie3);

    }


    /**
     * Parse player information from smarthand.pro
     *
     * @param nickname
     * @return Document with xml if player exist and null otherwise
     * @throws IOException
     */
    public Document parsePlayer(String nickname) throws IOException {

        nickname = nickname.split(" \\(PS\\)")[0];
        String url = "https://smarthand.pro/ps/#" + nickname;

        //настройка вебклиента HTMLUnit
        final WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);

        webClient.setCssErrorHandler(new SilentCssErrorHandler());
        webClient.setJavaScriptErrorListener(new SilentJavaScriptErrorListener());

        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.waitForBackgroundJavaScript(15000);

        getCookies(webClient);

        HtmlPage htmlPage;
        try {
            htmlPage = webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(15000);
        }
        //Ни разу не выбрасывалось, заглушка на всякий случай
        catch (Exception e) {
            System.out.println("Невозможно вставить такой ник в строку поиска");
            return null;
        }

        DomElement element;

        //Существует на странице только если есть какие-то проблемы на стороне smarthand.pro
        //С куками все в порядке, авторизация на сайте есть.
        element = htmlPage.getElementById("error1");
        if (element != null) return null;

        element = htmlPage.getElementById("table_1");
        webClient.waitForBackgroundJavaScript(15000);

        //Если со старыми куками все-таки что-то не то (удалились, перезаписались и т.п.)
        //создадим новые куки и попробуем еще раз
        if (element == null) {
            webClient.getCookieManager().clearCookies();
            setCookies();
            getCookies(webClient);

            htmlPage = webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(15000);

            element = htmlPage.getElementById("table_1");
            webClient.waitForBackgroundJavaScript(15000);
        }
        if (element == null) {
            System.out.println("SOMESING GOES WRONG... :(");
            return null;
        }

        //Если дошли сюда, значит все ок и нужно подгрузить заскриптованные данные
        //Для этого поочередно кликаем на нужные нам таблицы
        htmlPage = element.click();
        webClient.waitForBackgroundJavaScript(15000);

        element = htmlPage.getElementById("table_3");
        webClient.waitForBackgroundJavaScript(15000);
        htmlPage = element.click();

        element = htmlPage.getElementById("table_4");
        webClient.waitForBackgroundJavaScript(15000);
        htmlPage = element.click();

        element = htmlPage.getElementById("table_5");
        webClient.waitForBackgroundJavaScript(15000);
        htmlPage = element.click();
        webClient.waitForBackgroundJavaScript(15000);

        //Парсим итоговую страницу с подгруженными данными
        Document document = Jsoup.parse(htmlPage.asXml());
        webClient.close();
        return document;
    }
}
