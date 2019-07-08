package net.thumbtack.onlineshop.repository.mapper;

import net.thumbtack.onlineshop.entity.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet resultSet, int i) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt("id"));
        client.setFirstName(resultSet.getString("firstName"));
        client.setLastName(resultSet.getString("lastName"));
        client.setPatronymic(resultSet.getString("patronymic"));
        client.setLogin(resultSet.getString("login"));
        client.setPassword(resultSet.getString("password"));
        client.setEmail(resultSet.getString("email"));
        client.setAddress(resultSet.getString("address"));
        client.setPhone(resultSet.getString("phone"));
        client.setDeposit(resultSet.getInt("deposit"));
        return client;
    }
}
