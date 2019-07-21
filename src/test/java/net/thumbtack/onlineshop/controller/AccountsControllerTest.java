package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.entity.User;
import net.thumbtack.onlineshop.service.iface.AccountService;
import net.thumbtack.onlineshop.service.iface.ClientService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AccountsControllerTest {
    private AccountsController underTest;

    @Mock
    private AccountService accountService;

    @Mock
    private SessionService sessionService;

    @Before
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
        underTest = new AccountsController(this.accountService, this.sessionService);
    }


    @Test
    public void getCurrentUser() {
        Administrator administrator = new Administrator();

        administrator.setId(1L);
        administrator.setFirstName("Petr");
        administrator.setLastName("Petrov");
        administrator.setPatronymic("Petrovich");
        administrator.setPosition("manager");

        Session session = new Session();
        session.setCookie("cookie");
        session.setUserId(administrator.getId());

        when(sessionService.getSession(session.getCookie())).thenReturn(session);
        when(accountService.getCurrentUserById(administrator.getId())).thenReturn(administrator);

        User response = underTest.getCurrentUser(session.getCookie()).getBody();

        assertEquals(response, administrator);
    }

    @Test
    public void getCurrentUserClient() { ;
        Client client = new Client();

        client.setId(1L);
        client.setFirstName("Petr");
        client.setLastName("Petrov");
        client.setPatronymic("Petrovich");
        client.setEmail("petr@mail.ru");
        client.setAddress("Ul. Petrova");
        client.setPhone("8080");

        Session session = new Session();
        session.setCookie("cookie");
        session.setUserId(client.getId());

        when(sessionService.getSession(session.getCookie())).thenReturn(session);
        when(accountService.getCurrentUserById(client.getId())).thenReturn(client);

        User response = underTest.getCurrentUser(session.getCookie()).getBody();

        assertEquals(response, client);
    }
}