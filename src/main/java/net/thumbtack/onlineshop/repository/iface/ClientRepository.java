package net.thumbtack.onlineshop.repository.iface;

import net.thumbtack.onlineshop.entity.Client;

import java.util.List;

public interface ClientRepository {
    int addClient(Client client);
    Client getClientById(int id);
    void deleteClient(int id);
    void updateClient(Client client);
    List<Client> getAllClients();
}
