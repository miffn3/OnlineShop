package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.dto.request.ClientUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.exception.OnlineShopException;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    int registration(Client client) throws OnlineShopException;
    void editClient(ClientUpdateRequestDto updateRequestDto, int id) throws OnlineShopException;
    boolean isLoginExist(String login);
    boolean isPasswordExist(String login, String password);
    Client getClientById(int id);
    Client getClientByLogin(String login) throws OnlineShopException;
}
