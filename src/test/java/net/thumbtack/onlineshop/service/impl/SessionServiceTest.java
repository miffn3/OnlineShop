package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.repository.iface.SessionRepository;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SessionServiceTest {
//    private SessionService underTest;
//
//    @Mock
//    private SessionRepository sessionRepository;
//
//    @Mock
//    private AdministratorService administratorService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        underTest = new SessionServiceImpl(sessionRepository, administratorService);
//    }
//
//    @Test
//    public void getSession() {
//        Administrator administrator = new Administrator(1,"Petr","Petrov",
//                "Petrovich","petr1","petrpass","lead");
//
//        List<Session> sessions = Arrays.asList(
//                new Session("petr1", "cookie","admin"));
//
//        when(sessionRepository.findAll()).thenReturn(sessions);
//
//        Session sessionTest = underTest.getSession(administrator.getLogin());
//        assertEquals(sessions.get(0), sessionTest);
//    }

    @Test
    public void logOut() {
    }

    @Test
    public void validateCookie() {
    }

    @Test
    public void logIn() {
    }
}