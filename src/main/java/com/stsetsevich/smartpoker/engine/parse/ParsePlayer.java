package com.stsetsevich.smartpoker.engine.parse;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.stsetsevich.smartpoker.domain.User;
import com.stsetsevich.smartpoker.domain.SmarthandCookies;
import com.stsetsevich.smartpoker.repos.UserRepo;
import com.stsetsevich.smartpoker.repos.SmarthandCookiesRepo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class ParsePlayer {
    @Autowired
    UserRepo userRepo;
    @Autowired
    SmarthandCookiesRepo smarthandCookiesRepo;
    private static Connection.Response response;
    private static Map<String, String> cookie;
    private long id=0L;




    private void setCookies() throws IOException {
        response = Jsoup
                .connect("https://smarthand.pro/handler")
                .method(Connection.Method.POST)
                .data("login", "LixPSx")
                .data("pass", "2809390")
                .data("doLogin", "Войти")
                .execute();

        cookie = response.cookies();
        response = Jsoup
                .connect("https://smarthand.pro/settings")
                .cookies(response.cookies())
                .execute();


        SmarthandCookies smarthandAccount = smarthandCookiesRepo.findById(0);


        smarthandAccount.setSessionId(cookie.get("PHPSESSID"));
       try {
           System.out.println("1");
           smarthandCookiesRepo.save(smarthandAccount);
           System.out.println("2");
       }
        catch (Exception exception)
        {
            System.out.println(exception.toString());

        }
      //  System.out.println(response.body());
    }
    private void getCookies(WebClient webClient) throws IOException {

        System.out.println("001");
        SmarthandCookies smarthandAccountAndCookies = smarthandCookiesRepo.findById(id);
        System.out.println("002");
     //   System.out.println(smarthandAccountAndCookies.getSessionId());
        if(smarthandAccountAndCookies==null || smarthandAccountAndCookies.getSessionId()==null || smarthandAccountAndCookies.getSessionId()=="")
        {
            System.out.println("003");
            setCookies();
            System.out.println("004");
            smarthandAccountAndCookies = smarthandCookiesRepo.findById(id);
            System.out.println("005");
            Cookie cookie1 = new Cookie("smarthand.pro", "lang", "en");
            Cookie cookie2 = new Cookie("smarthand.pro", "template", "default");
            Cookie cookie3 = new Cookie("smarthand.pro", "PHPSESSID", smarthandAccountAndCookies.getSessionId());
            webClient.getCookieManager().addCookie(cookie1);
            webClient.getCookieManager().addCookie(cookie2);
            webClient.getCookieManager().addCookie(cookie3);
            System.out.println("006");
        }
        else {
            System.out.println("007");
            Cookie cookie1 = new Cookie("smarthand.pro", "lang", "en");
            Cookie cookie2 = new Cookie("smarthand.pro", "template", "default");
            Cookie cookie3 = new Cookie("smarthand.pro", "PHPSESSID", smarthandAccountAndCookies.getSessionId());
            webClient.getCookieManager().addCookie(cookie1);
            webClient.getCookieManager().addCookie(cookie2);
            webClient.getCookieManager().addCookie(cookie3);
            System.out.println("008");
        }
    }


    public Document parsePlayer(String nickname) throws IOException {

        nickname = nickname.split(" \\(PS\\)")[0];
        String url = "https://smarthand.pro/ps/#"+nickname;

        final WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);

        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false);

        CookieManager cookieManager = webClient.getCookieManager();

        System.out.println(cookieManager.getCookies().toString());
        cookieManager.clearCookies();
        getCookies(webClient);




        System.out.println(cookieManager.getCookies().toString());
        HtmlPage htmlPage;
        try {
             htmlPage = webClient.getPage(url);
        }
        catch (Exception e){
            System.out.println("Невозможно вставить такой ник в строку поиска");
            return null;
        }


        webClient.waitForBackgroundJavaScript(15000);
        DomElement element;
        element  = htmlPage.getElementById("error1");
        if (element!=null) return null;
        //Если со старыми куками все-таки что-то не то (перезаписались и т.п.)
        try {
            try {
                htmlPage = webClient.getPage(url);
            }
            catch (Exception e){
                System.out.println("Невозможно вставить такой ник в строку поиска");
                return null;
            }
           element  = htmlPage.getElementById("table_1");
           htmlPage = element.click();
        }
        //Создаем новые куки
        catch (Exception e)
        {
            setCookies();
            getCookies(webClient);
            element  = htmlPage.getElementById("table_1");
            htmlPage = element.click();
        }




        webClient.waitForBackgroundJavaScript(15000);
        element = htmlPage.getElementById("table_3");

        htmlPage = element.click();

        webClient.waitForBackgroundJavaScript(15000);
        element = htmlPage.getElementById("table_4");

        htmlPage = element.click();

        webClient.waitForBackgroundJavaScript(15000);
        element = htmlPage.getElementById("table_5");

        htmlPage = element.click();

        webClient.waitForBackgroundJavaScript(15000);
      //  System.out.println(htmlPage.asXml());
        Document document = Jsoup.parse(htmlPage.asXml());
        webClient.close();
        return document;
    }
}
