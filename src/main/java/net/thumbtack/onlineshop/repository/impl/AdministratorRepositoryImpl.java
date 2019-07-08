package net.thumbtack.onlineshop.repository.impl;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.repository.iface.AdministratorRepository;
import net.thumbtack.onlineshop.repository.mapper.AdministratorMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
        jdbcTemplate.update("INSERT INTO administrators " +
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
        return null;
    }

    @Override
    public void deleteAdministrator(int id) {

    }

    @Override
    public void updateAdministrator(Administrator administrator) {

    }
}
