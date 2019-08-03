package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.entity.User;
import net.thumbtack.onlineshop.service.iface.AccountService;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.ClientService;

public class AccountServiceImpl implements AccountService {
    private final AdministratorService administratorService;
    private final ClientService clientService;

    public AccountServiceImpl(AdministratorService administratorService, ClientService clientService) {
        this.administratorService = administratorService;
        this.clientService = clientService;
    }

    @Override
    public User getCurrentUserById(Long id) {
        Administrator administrator = administratorService.getAdministratorById(id);
        Client client = clientService.getClientById(id);
        if (administrator != null) {
            return administrator;
        } else {
            client.setProducts(null);
            return client;
        }
    }
}
