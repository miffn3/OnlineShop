package net.thumbtack.onlineshop.entity;

public class Session {
    private int userId;
    private String cookie;

    public Session() {
    }

    public Session(int userId, String cookie) {
        this.userId = userId;
        this.cookie = cookie;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
