package net.thumbtack.onlineshop.repository.impl;

import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.repository.iface.ClientRepository;
import net.thumbtack.onlineshop.repository.mapper.ClientMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientRepositoryImpl implements ClientRepository {
    private JdbcTemplate jdbcTemplate;
    private ClientMapper clientMapper;

    @Override
    public void addClient(Client client){
        if (client == null) {
            throw new IllegalArgumentException();
        }
        jdbcTemplate.update("INSERT INTO administrator " +
                        "(firstName, lastName, patronymic, login, password, email, address, phone, deposit) " +
                        "VALUES (?,?,?,?,?,?,?,?,?)",
                client.getFirstName(),
                client.getLastName(),
                client.getPatronymic(),
                client.getLogin(),
                client.getLastName(),
                client.getEmail(),
                client.getAddress(),
                client.getPhone(),
                client.getDeposit());
    }

    @Override
    public Client getClientById(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM client WHERE id = ?",
                new Object[]{id}, clientMapper);
    }

    @Override
    public void deleteClient(int id){

    }

    @Override
    public void updateClient(Client client){

    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM client");
        rows.forEach(row -> {
            Client client = new Client();
            client.setId((long)row.get("id"));
            client.setFirstName((String)row.get("firstName"));
            client.setLastName((String)row.get("lastName"));
            client.setPatronymic((String)row.get("patronymic"));
            client.setLogin((String)row.get("login"));
            client.setPassword((String)row.get("password"));
            client.setEmail((String)row.get("email"));
            client.setAddress((String)row.get("address"));
            client.setPhone((String)row.get("phone"));
            client.setDeposit((long)row.get("deposit"));
            clients.add(client);
        });

        return clients;
    }
}
