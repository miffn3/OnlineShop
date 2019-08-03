package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.repository.iface.SessionRepository;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.ClientService;
import net.thumbtack.onlineshop.service.iface.SessionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final AdministratorService administratorService;
    private final ClientService clientService;

    public SessionServiceImpl(SessionRepository sessionRepository, AdministratorService administratorService,
                              ClientService clientService) {
        this.sessionRepository = sessionRepository;
        this.administratorService = administratorService;
        this.clientService = clientService;
    }

    private static String generateCookieValue() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Session getSession(String cookie) {
        Set<Session> sessions = getAllSessions();
        for (Session session : sessions) {
            if (session.getCookie().equals(cookie)) {
                return session;
            }
        }
        return null;
    }

    @Override
    public Set<Session> getAllSessions() {
        Set<Session> sessions = new HashSet<>((Collection) sessionRepository.findAll());
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
        Administrator administrator = administratorService.getAdminByLogin(login);
        Client client = clientService.getClientByLogin(login);
        if (administrator != null) {
            if (administratorService.isUserExist(login, password)) {
                return addSession(administrator.getId());
            }
        }
        if (client != null) {
            if (clientService.isUserExist(login, password)) {
                return addSession(client.getId());
            }
        }
        return null;
    }

    @Override
    public void logOut(String cookie) {
        Session session = getSession(cookie);
        sessionRepository.delete(session);
    }
}
