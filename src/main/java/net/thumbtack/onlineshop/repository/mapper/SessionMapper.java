package net.thumbtack.onlineshop.repository.mapper;

import net.thumbtack.onlineshop.entity.Session;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionMapper implements RowMapper<Session> {
    @Override
    public Session mapRow(ResultSet resultSet, int i) throws SQLException {
        Session session = new Session();
        session.setLogin(resultSet.getString("login"));
        session.setCookie(resultSet.getString("cookie"));
        session.setRole(resultSet.getString("role"));
        return session;
    }
}
