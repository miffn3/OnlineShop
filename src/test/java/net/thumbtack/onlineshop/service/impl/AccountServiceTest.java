package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.OnlineShopException;
import net.thumbtack.onlineshop.service.iface.AccountService;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class AccountServiceTest {
    private AccountService underTest;

    @Mock
    private AdministratorService administratorService;

    @Mock
    private SessionService sessionService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new AccountServiceImpl(administratorService, sessionService);
    }

    @Test
    public void getAdmin() {
        Session session = new Session(1,"cookie");

        Administrator administrator = new Administrator(1,"Petr","Petrov",
                "Petrovich","petr1","petrpass11","admin", "lead");
        try {
            when(sessionService.getSession("cookie")).thenReturn(session);
            when(administratorService.getAdminById(1)).thenReturn(administrator);
        } catch (OnlineShopException ex) {
            System.out.println(ex.getOnlineShopErrorCode());
        }

        Administrator administrator1 = underTest.getAdminById(session.getUserId());
        assertEquals(administrator1,administrator);
    }
}