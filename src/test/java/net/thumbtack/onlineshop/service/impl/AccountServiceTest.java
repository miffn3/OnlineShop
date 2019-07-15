package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.service.iface.AccountService;
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
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AccountServiceTest {
    private AccountService underTest;

    @Mock
    private AdministratorService administratorService;

    @Mock
    private SessionService sessionService;

    @Captor
    public ArgumentCaptor<String> captor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new AccountServiceImpl(administratorService, sessionService);
    }

    @Test
    public void getAdmin() {
        Session session = new Session("petr1", "cookie", "admin");

        Administrator administrator = new Administrator(1,"Petr","Petrov",
                "Petrovich","petr1","petrpass","lead");

        when(sessionService.validateCookie("cookie")).thenReturn(session);
        when(administratorService.getAdminByLogin("petr1")).thenReturn(administrator);

        Administrator administrator1 = underTest.getAdmin(session.getCookie());
        assertEquals(administrator1,administrator);
    }
}