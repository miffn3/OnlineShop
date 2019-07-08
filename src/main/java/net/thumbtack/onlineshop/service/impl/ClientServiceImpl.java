package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.repository.iface.ClientRepository;
import net.thumbtack.onlineshop.service.iface.ClientService;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
