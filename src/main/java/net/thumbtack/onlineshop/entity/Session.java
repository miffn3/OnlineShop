package net.thumbtack.onlineshop.entity;

public class Session {
    private String login;
    private String cookie;
    private String role;

    public Session() {
    }

    public Session(String login, String cookie, String role) {
        this.login = login;
        this.cookie = cookie;
        this.role = role;
    }

    public Session(String login, String cookie) {
        this.login = login;
        this.cookie = cookie;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
