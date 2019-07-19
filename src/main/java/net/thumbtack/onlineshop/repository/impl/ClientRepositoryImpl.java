package net.thumbtack.onlineshop.repository.impl;

import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.repository.iface.ClientRepository;
import net.thumbtack.onlineshop.repository.mapper.ClientMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientRepositoryImpl implements ClientRepository {
    private JdbcTemplate jdbcTemplate;
    private ClientMapper clientMapper;

    public ClientRepositoryImpl(JdbcTemplate jdbcTemplate, ClientMapper clientMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.clientMapper = clientMapper;
    }

    @Override
    public int addClient(Client client){
        if (client == null) {
            throw new IllegalArgumentException();
        }
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("firstName", client.getFirstName());
        parameters.put("lastName", client.getLastName());
        parameters.put("patronymic", client.getPatronymic());
        parameters.put("login", client.getLogin());
        parameters.put("password", client.getPassword());
        parameters.put("email", client.getEmail());
        parameters.put("address", client.getAddress());
        parameters.put("phone", client.getPhone());
        parameters.put("deposit", client.getDeposit());
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        return (int) newId;
    }

    @Override
    public Client getClientById(int id){
        return jdbcTemplate.queryForObject("SELECT firstName, lastName, patronymic, login, password, email, address, phone, deposit FROM user WHERE id = ?",
                new Object[]{id}, clientMapper);
    }

    @Override
    public void deleteClient(int id){
        jdbcTemplate.update("DELETE FROM user WHERE id=?", id);
    }

    @Override
    public void updateClient(Client client){

    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT firstName, lastName, patronymic, login, password, email, address, phone, deposit FROM user");
        rows.forEach(row -> {
            Client client = new Client();
            client.setId((int)row.get("id"));
            client.setFirstName((String)row.get("firstName"));
            client.setLastName((String)row.get("lastName"));
            client.setPatronymic((String)row.get("patronymic"));
            client.setLogin((String)row.get("login"));
            client.setPassword((String)row.get("password"));
            client.setEmail((String)row.get("email"));
            client.setAddress((String)row.get("address"));
            client.setPhone((String)row.get("phone"));
            client.setDeposit((Integer) row.get("deposit"));
            clients.add(client);
        });

        return clients;
    }
}
