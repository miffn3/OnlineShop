package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.OnlineShopException;

public interface SessionService {
    Session logIn(String login, String password) throws OnlineShopException;
    void logOut(String cookie);
    Session getSession(String cookie) throws OnlineShopException;
}
