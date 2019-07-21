package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.repository.iface.SessionRepository;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import net.thumbtack.onlineshop.service.impl.SessionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SessionServiceTest {
    private SessionService underTest;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private AdministratorService administratorService;

    @Mock
    private ClientService clientService;

    @Captor
    public ArgumentCaptor<Session> captor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new SessionServiceImpl(sessionRepository, administratorService, clientService);
    }

    @Test
    public void logOut() {
        Session session = new Session();
        session.setUserId(1L);
        session.setCookie("cookie");

        when(underTest.getSession(session.getCookie())).thenReturn(session);
        verify(sessionRepository).delete(captor.capture());
        Session value = captor.getValue();

        assertEquals(session,value);

    }

    @Test
    public void logIn() {
        Session session = new Session();
        session.setUserId(1L);
        session.setCookie("cookie");

        Administrator administrator = new Administrator();
        administrator.setId(1L);
        administrator.setLogin("login");
        administrator.setPassword("password");

        when(administratorService.isUserExist(administrator.getLogin(), administrator.getPassword())).thenReturn(true);
        when(administratorService.getAdminByLogin(administrator.getLogin())).thenReturn(administrator);
        when(underTest.addSession(administrator.getId())).thenReturn(session);

        Session sessionTmp = underTest.logIn(administrator.getLogin(), administrator.getPassword());

        assertEquals(session, sessionTmp);
    }

    @Test
    public void getSession() {
        Session session = new Session();
        session.setUserId(1L);
        session.setCookie("cookie");

        Set<Session> sessions = new HashSet<>();
        sessions.add(session);

        when(underTest.getAllSessions()).thenReturn(sessions);

        Session sessionTmp = underTest.getSession(session.getCookie());

        assertEquals(session,sessionTmp);
    }

    @Test
    public void getAllSessions() {
        Session session = new Session();
        session.setUserId(1L);
        session.setCookie("cookie");

        Set<Session> sessions = new HashSet<>();
        sessions.add(session);

        when(sessionRepository.findAll()).thenReturn(sessions);

        Set<Session> sessionsTmp = underTest.getAllSessions();
        assertEquals(sessions,sessionsTmp);
    }

    @Test
    public void addSession() {
        Session session = new Session();
        session.setUserId(1L);

        underTest.addSession(session.getUserId());
        verify(sessionRepository).save(captor.capture());

        Session value = captor.getValue();

        assertEquals(session.getUserId(), value.getUserId());
    }
}