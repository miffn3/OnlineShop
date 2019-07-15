package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.ServerErrorCode;
import net.thumbtack.onlineshop.exception.ServerException;
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
    public Session getSession(String cookie) throws ServerException {
        List<Session> sessions = sessionRepository.findAll();

        try {
            return sessions.stream().filter(o -> o.getCookie().equals(cookie)).findFirst().get();
        } catch (NoSuchElementException ex) {
            throw new ServerException(ServerErrorCode.SESSION_WRONG_COOKIE);
        }
    }

    @Override
    public Session logIn(String login, String password) throws ServerException {
        if(administratorService.isPasswordExist(login, password)) {
            Session session = new Session(administratorService.getAdminByLogin(login).getId(),generateCookieValue());
            sessionRepository.addSession(session);
            return session;
        } else if (!administratorService.isLoginExist(login)){
            throw new ServerException(ServerErrorCode.USER_WRONG_LOGIN, login);
        } else if(!administratorService.isPasswordExist(login, password)) {
            throw new ServerException(ServerErrorCode.USER_WRONG_PASSWORD);
        }
        return null;
    }

    private static String generateCookieValue() {
        return UUID.randomUUID().toString();
    }
}
