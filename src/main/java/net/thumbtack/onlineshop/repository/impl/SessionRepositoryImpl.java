package net.thumbtack.onlineshop.repository.impl;

import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.repository.iface.SessionRepository;
import net.thumbtack.onlineshop.repository.mapper.SessionMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SessionRepositoryImpl implements SessionRepository {
    private JdbcTemplate jdbcTemplate;
    private SessionMapper sessionMapper;

    public SessionRepositoryImpl(JdbcTemplate jdbcTemplate, SessionMapper sessionMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionMapper = sessionMapper;
    }

    @Override
    public void addSession(Session session) {
        jdbcTemplate.update("UPDATE user SET " +
                "cookie=? WHERE login=?",
                session.getCookie(),
                session.getLogin());
    }

    @Override
    public List<Session> findAll() {
        List<Session> sessions = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT login, cookie, role FROM user");
        rows.forEach(row -> {
            Session session = new Session();
            session.setLogin((String)row.get("login"));
            session.setCookie((String)row.get("cookie"));
            session.setRole((String)row.get("role"));
            sessions.add(session);
        });

        return sessions;
    }

    @Override
    public void deleteSession(String cookie) {
        jdbcTemplate.update("DELETE cookie FROM user WHERE cookie=?",
                cookie);
    }
}


