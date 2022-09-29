package com.stsetsevich.smartpoker.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="smarthand_cookies")
public class SmarthandCookies {
    @Id
    private Long id;
    private String smarthandLogin;
    private String smarthandPassword;
    private String sessionId;
    private Date addCookiesDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getAddCookiesDate() {
        return addCookiesDate;
    }

    public void setAddCookiesDate(Date addCookiesDate) {
        this.addCookiesDate = addCookiesDate;
    }


    public String getSmarthandLogin() {
        return smarthandLogin;
    }

    public void setSmarthandLogin(String smarthandLogin) {
        this.smarthandLogin = smarthandLogin;
    }

    public String getSmarthandPassword() {
        return smarthandPassword;
    }

    public void setSmarthandPassword(String smarthandPassword) {
        this.smarthandPassword = smarthandPassword;
    }
}
