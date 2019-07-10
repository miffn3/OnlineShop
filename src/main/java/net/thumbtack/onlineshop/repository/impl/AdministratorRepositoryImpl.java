package net.thumbtack.onlineshop.repository.impl;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.repository.iface.AdministratorRepository;
import net.thumbtack.onlineshop.repository.mapper.AdministratorMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdministratorRepositoryImpl implements AdministratorRepository {
    private JdbcTemplate jdbcTemplate;
    private AdministratorMapper administratorMapper;

    public AdministratorRepositoryImpl(JdbcTemplate jdbcTemplate, AdministratorMapper administratorMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.administratorMapper = administratorMapper;
    }

    @Override
    public void addAdministrator(Administrator administrator) {
        if (administrator == null) {
            throw new IllegalArgumentException();
        }
        jdbcTemplate.update("INSERT INTO administrator " +
                "(firstName, lastName, patronymic, login, password, position) VALUES (?,?,?,?,?,?)",
                administrator.getFirstName(),
                administrator.getLastName(),
                administrator.getPatronymic(),
                administrator.getLogin(),
                administrator.getLastName(),
                administrator.getPosition());
    }

    @Override
    public Administrator getAdminById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM administrator WHERE id = ?",
                new Object[]{id}, administratorMapper);
    }

    @Override
    public void deleteAdministrator(int id) {

    }

    @Override
    public void updateAdministrator(Administrator administrator) {

    }

    @Override
    public List<Administrator> getAllAdmins() {
        List<Administrator> administrators = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM administrator");
        rows.forEach(row -> {
            Administrator administrator = new Administrator();
            administrator.setId((long)row.get("id"));
            administrator.setFirstName((String)row.get("firstName"));
            administrator.setLastName((String)row.get("lastName"));
            administrator.setPatronymic((String)row.get("patronymic"));
            administrator.setLogin((String)row.get("login"));
            administrator.setPassword((String)row.get("password"));
            administrator.setPosition((String)row.get("position"));
            administrators.add(administrator);
        });

        return administrators;
    }
}
