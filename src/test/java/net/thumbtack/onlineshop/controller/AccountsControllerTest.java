package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.response.AdministratorResponseDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.service.iface.AccountService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
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
        Administrator administrator = new Administrator(1,"Petr","Petrov",
                "Petrovich","petr1","petrpass","lead");

        Session session = new Session("petr1", "cookie", "admin");

        when(accountService.getAdmin("cookie")).thenReturn(administrator);
        when(sessionService.validateCookie("cookie")).thenReturn(session);

        AdministratorResponseDto administratorResponseDto = underTest.getCurrentUser(session.getCookie()).getBody();
        assertEquals(administratorResponseDto.getId(), administrator.getId());
    }
}