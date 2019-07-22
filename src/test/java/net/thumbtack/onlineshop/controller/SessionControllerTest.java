package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.LogInRequestDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.entity.User;
import net.thumbtack.onlineshop.service.iface.AccountService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class SessionControllerTest {
    private SessionController underTest;

    @Mock
    private SessionService sessionService;

    @Mock
    private AccountService accountService;

    @Before
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
        underTest = new SessionController(this.sessionService,this.accountService);
    }

    @Test
    public void logIn() {
        Administrator administrator = new Administrator();

        administrator.setId(1L);
        administrator.setFirstName("Petr");
        administrator.setLastName("Petrov");
        administrator.setPatronymic("Petrovich");
        administrator.setLogin("petr12");
        administrator.setPassword("petrpass1234");
        administrator.setPosition("manager");

        LogInRequestDto loginData = new LogInRequestDto();
        loginData.setLogin(administrator.getLogin());
        loginData.setPassword(administrator.getPassword());

        Session session = new Session();
        session.setCookie("cookie");
        session.setUserId(administrator.getId());

        when(sessionService.logIn(administrator.getLogin(), administrator.getPassword())).thenReturn(session);
        when(accountService.getCurrentUserById(administrator.getId())).thenReturn(administrator);

        User response = underTest.logIn(loginData).getBody();
        assertEquals(administrator, response);
    }

}