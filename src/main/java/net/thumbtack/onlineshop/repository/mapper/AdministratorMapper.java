package net.thumbtack.onlineshop.repository.mapper;

import net.thumbtack.onlineshop.entity.Administrator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorMapper implements RowMapper<Administrator> {
    @Override
    public Administrator mapRow(ResultSet resultSet, int i) throws SQLException {
        Administrator administrator = new Administrator();
        administrator.setId(resultSet.getInt("id"));
        administrator.setFirstName(resultSet.getString("firstName"));
        administrator.setLastName(resultSet.getString("lastName"));
        administrator.setPatronymic(resultSet.getString("patronymic"));
        administrator.setLogin(resultSet.getString("login"));
        administrator.setPassword(resultSet.getString("password"));
        administrator.setPosition(resultSet.getString("position"));
        return administrator;
    }
}
