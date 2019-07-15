package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.service.iface.AccountService;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.SessionService;

public class AccountServiceImpl implements AccountService {
    private final AdministratorService administratorService;
    private final SessionService sessionService;

    public AccountServiceImpl(AdministratorService administratorService, SessionService sessionService) {
        this.administratorService = administratorService;
        this.sessionService = sessionService;
    }

    @Override
    public Administrator getAdmin(String cookie) {
        Session session = sessionService.validateCookie(cookie);
        return administratorService.getAdminByLogin(session.getLogin());
    }

    @Override
    public Client getClient(String cookie) {
        return null;
    }
}
