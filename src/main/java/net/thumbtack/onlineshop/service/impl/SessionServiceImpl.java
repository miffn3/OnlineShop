package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.repository.iface.SessionRepository;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.SessionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final AdministratorService administratorService;

    public SessionServiceImpl(SessionRepository sessionRepository, AdministratorService administratorService) {
        this.sessionRepository = sessionRepository;
        this.administratorService = administratorService;
    }

    @Override
    public Session getSession(String cookie) {
        Set<Session> sessions = getAllSessions();
        for (Session session:sessions) {
            if (session.getCookie().equals(cookie)){
                return session;
            }
        }
        return null;
    }

    @Override
    public Set<Session> getAllSessions() {
        Set<Session> sessions = new HashSet<>((Collection)sessionRepository.findAll());
        return sessions;
    }

    @Override
    public Session addSession(Long userId) {
        Session session = new Session();
        session.setCookie(generateCookieValue());
        session.setUserId(userId);
        return sessionRepository.save(session);
    }

    @Override
    public Session logIn(String login, String password) {
        return addSession(administratorService.getAdminByLogin(login).getId());
    }

    @Override
    public void logOut(String cookie) {

    }

    private static String generateCookieValue() {
        return UUID.randomUUID().toString();
    }
}
