package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.entity.Session;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface SessionService {
    Session getSession(String cookie);

    Set<Session> getAllSessions();

    Session addSession(Long userId);

    Session logIn(String login, String password);

    void logOut(String cookie);

}
