package net.thumbtack.onlineshop.repository.mapper;

import net.thumbtack.onlineshop.entity.Session;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionMapper implements RowMapper<Session> {
    @Override
    public Session mapRow(ResultSet resultSet, int i) throws SQLException {
        Session session = new Session();
        session.setUserId(resultSet.getInt("userId"));
        session.setCookie(resultSet.getString("cookie"));
        return session;
    }
}
