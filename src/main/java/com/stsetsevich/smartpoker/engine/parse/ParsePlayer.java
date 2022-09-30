package com.stsetsevich.smartpoker.engine.parse;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.SilentJavaScriptErrorListener;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.stsetsevich.smartpoker.domain.SmarthandCookies;
import com.stsetsevich.smartpoker.repos.SmarthandCookiesRepo;
import com.stsetsevich.smartpoker.repos.UserRepo;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        System.out.println(response.body());

        SmarthandCookies smarthandAccount = smarthandCookiesRepo.findById(id);


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
        System.out.println("AFTER UPDATE COOKIES SESSION, ID IS "+smarthandAccount.getSessionId());
        System.out.println("ALL COOKIES IS "+cookie);
      //  System.out.println(response.body());
    }
    private void getCookies(WebClient webClient) throws IOException {

        System.out.println("001");
        SmarthandCookies smarthandAccountAndCookies = smarthandCookiesRepo.findById(id);
        System.out.println("002");
        System.out.println("NOW SESSION ID IS "+smarthandAccountAndCookies.getSessionId());
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

        webClient.setCssErrorHandler(new SilentCssErrorHandler());
        webClient.setJavaScriptErrorListener(new SilentJavaScriptErrorListener());

        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.waitForBackgroundJavaScript(15000);

        CookieManager cookieManager = webClient.getCookieManager();

        System.out.println(cookieManager.getCookies().toString());
        cookieManager.clearCookies();
        getCookies(webClient);




        System.out.println(cookieManager.getCookies().toString());
        HtmlPage htmlPage;
        try {
             htmlPage = webClient.getPage(url);
            System.out.println("TYT PROSHLI ");
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

           element  = htmlPage.getElementById("table_1");
        if(element==null) {
            cookieManager.clearCookies();
            webClient.getCookieManager().clearCookies();
            System.out.println("TYT COOKIE 1111"+webClient.getCookieManager().getCookies());
            setCookies();
            getCookies(webClient);
            webClient.waitForBackgroundJavaScript(15000);
            htmlPage = webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(15000);
            element  = htmlPage.getElementById("table_1");
        }
        if(element==null)
        {
            System.out.println("SOMESING GOES WRONG... :(");
            return null;
        }

           htmlPage = element.click();




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
