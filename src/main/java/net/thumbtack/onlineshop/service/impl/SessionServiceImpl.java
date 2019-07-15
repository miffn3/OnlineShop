package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.repository.iface.SessionRepository;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.SessionService;

import java.util.List;
import java.util.UUID;

public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final AdministratorService administratorService;

    public SessionServiceImpl(SessionRepository sessionRepository, AdministratorService administratorService) {
        this.sessionRepository = sessionRepository;
        this.administratorService = administratorService;
    }

    @Override
    public Session getSession(String login) {
        List<Session> sessions = sessionRepository.findAll();
        return sessions.stream().filter(o -> o.getLogin().equals(login)).findFirst().get();
    }

    @Override
    public void logOut(String cookie) {
        sessionRepository.deleteSession(cookie);
    }

    @Override
    public Session validateCookie(String cookie) {
        List<Session> sessions = sessionRepository.findAll();
        try {
            return sessions.stream().filter(o -> o.getCookie().equals(cookie)).findFirst().get();
        } catch (NullPointerException ex) {
            return null;
        }

    }

    @Override
    public Session logIn(String login, String password) {
        Session session = new Session(login, generateCookieValue(), "");
        if (administratorService.isAdminExist(login)) {
            session.setRole("admin");
        }
        sessionRepository.addSession(session);
        return session;
    }

    private static String generateCookieValue() {
        return UUID.randomUUID().toString();
    }
}
