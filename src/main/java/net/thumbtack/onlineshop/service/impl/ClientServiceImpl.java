package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.dto.request.ClientUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.exception.OnlineShopException;
import net.thumbtack.onlineshop.repository.iface.ClientRepository;
import net.thumbtack.onlineshop.service.iface.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    @Override
    public int registration(Client client) throws OnlineShopException {
        return 0;
    }

    @Override
    public void editClient(ClientUpdateRequestDto updateRequestDto, int id) throws OnlineShopException {

    }

    @Override
    public boolean isLoginExist(String login) {
        return false;
    }

    @Override
    public boolean isPasswordExist(String login, String password) {
        return false;
    }

    @Override
    public Client getClientById(int id) {
        return null;
    }

    @Override
    public Client getClientByLogin(String login) throws OnlineShopException {
        return null;
    }
}
