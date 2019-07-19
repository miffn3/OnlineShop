package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.OnlineShopException;
import net.thumbtack.onlineshop.service.iface.AccountService;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.ClientService;
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
    private ClientService clientService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new AccountServiceImpl(administratorService, clientService);
    }

    @Test
    public void getAdmin() { ;

        Administrator administrator = new Administrator(1,"Petr","Petrov",
                "Petrovich","petr1","petrpass11","admin", "lead");
        try {
            when(administratorService.getAdminById(1)).thenReturn(administrator);
        } catch (OnlineShopException ex) {
            System.out.println(ex.getOnlineShopErrorCode());
        }

//        User administrator1 = underTest.getAdminById(session.getUserId());
//        assertEquals(administrator1,administrator);
    }
}