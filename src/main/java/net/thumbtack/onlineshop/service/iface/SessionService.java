package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.entity.Session;

public interface SessionService {
    Session getSession(String login);
    Session logIn(String login, String password);
    void logOut(String cookie);
    Session validateCookie(String cookie);
}
