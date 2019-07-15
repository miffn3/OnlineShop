package net.thumbtack.onlineshop.repository.iface;

import net.thumbtack.onlineshop.entity.Session;

import java.util.List;

public interface SessionRepository {
    void addSession(Session session);
    List<Session> findAll();
    void deleteSession(String cookie);
}

