package net.thumbtack.onlineshop.repository.impl;

import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.repository.iface.ClientRepository;
import net.thumbtack.onlineshop.repository.mapper.ClientMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ClientRepositoryImpl implements ClientRepository {
    private JdbcTemplate jdbcTemplate;
    private ClientMapper clientMapper;

    @Override
    public void addClient(Client client){

    }

    @Override
    public Client getClientById(int id){
        return null;
    }

    @Override
    public void deleteClient(int id){

    }

    @Override
    public void updateClient(Client client){

    }

    @Override
    public List<Client> getAllClients() {
        return null;
    }
}
