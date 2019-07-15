package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.ServerException;

public interface SessionService {
    Session logIn(String login, String password) throws ServerException;
    void logOut(String cookie);
    Session getSession(String cookie) throws ServerException;
}
