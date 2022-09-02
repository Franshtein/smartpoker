package com.stsetsevich.smartpoker.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="user_smarthand_acc_cookies")
public class UserSmarthandAccountAndCookies {
    @Id
    private Long id;
    private String smarthandLogin;
    private String smarthandPassword;
    private String sessionId;
    private Date addCookiesDate;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
