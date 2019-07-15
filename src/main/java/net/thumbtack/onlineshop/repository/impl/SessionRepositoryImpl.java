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
        jdbcTemplate.update("INSERT INTO session SET userId=?,cookie=?",
                session.getUserId(),
                session.getCookie());
    }

    @Override
    public List<Session> findAll() {
        List<Session> sessions = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT userId, cookie FROM session");
        rows.forEach(row -> {
            Session session = new Session();
            session.setUserId((int)row.get("userId"));
            session.setCookie((String)row.get("cookie"));
            sessions.add(session);
        });

        return sessions;
    }

    @Override
    public void deleteSession(String cookie) {
        jdbcTemplate.update("DELETE userId, cookie FROM session WHERE cookie=?",
                cookie);
    }
}


