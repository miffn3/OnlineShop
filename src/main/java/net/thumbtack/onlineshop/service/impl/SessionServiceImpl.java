package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.*;
import net.thumbtack.onlineshop.repository.iface.SessionRepository;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.SessionService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final AdministratorService administratorService;

    public SessionServiceImpl(SessionRepository sessionRepository, AdministratorService administratorService) {
        this.sessionRepository = sessionRepository;
        this.administratorService = administratorService;
    }

    @Override
    public void logOut(String cookie) {
        sessionRepository.deleteSession(cookie);
    }

    @Override
    public Session getSession(String cookie) throws OnlineShopException {
        List<Session> sessions = sessionRepository.findAll();

        try {
            return sessions.stream().filter(o -> o.getCookie().equals(cookie)).findFirst().get();
        } catch (NoSuchElementException ex) {
            throw new SessionException();
        }
    }

    @Override
    public Session logIn(String login, String password) throws OnlineShopException {
        if(administratorService.isPasswordExist(login, password)) {
            Session session = new Session(administratorService.getAdminByLogin(login).getId(),generateCookieValue());
            sessionRepository.addSession(session);
            return session;
        } else if (!administratorService.isLoginExist(login)){
            throw new LoginNotFoundException(login);
        } else if(!administratorService.isPasswordExist(login, password)) {
            throw new PasswordAuthorizationException();
        }
        return null;
    }

    private static String generateCookieValue() {
        return UUID.randomUUID().toString();
    }
}
