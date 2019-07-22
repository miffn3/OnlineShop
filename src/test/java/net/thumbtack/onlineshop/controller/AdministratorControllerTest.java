package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.AdministratorDto;
import net.thumbtack.onlineshop.dto.request.AdministratorUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AdministratorControllerTest {
    private AdministratorController underTest;

    @Mock
    private AdministratorService administratorService;

    @Mock
    private SessionService sessionService;

    @Before
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
        underTest = new AdministratorController(this.administratorService, this.sessionService);
    }

    @Test
    public void registrationAdmin() {
        Administrator administrator = new Administrator();

        administrator.setId(1L);
        administrator.setFirstName("Petr");
        administrator.setLastName("Petrov");
        administrator.setPatronymic("Petrovich");
        administrator.setLogin("petr1");
        administrator.setPosition("manager");
        administrator.setPassword("petrpass11");

        AdministratorDto administratorDto = new AdministratorDto();
        administratorDto.setFirstName("Petr");
        administratorDto.setLastName("Petrov");
        administratorDto.setPatronymic("Petrovich");
        administratorDto.setLogin("petr1");
        administratorDto.setPosition("manager");
        administratorDto.setPassword("petrpass11");

        Session session = new Session();
        session.setUserId(1L);
        session.setCookie("cookie");

        when(sessionService.logIn(administrator.getLogin(), administrator.getPassword())).thenReturn(session);
        when(administratorService.addAdministrator(administratorDto)).thenReturn(administrator);

        Administrator response = underTest.registrationAdmin(administratorDto).getBody();
        assertEquals(administrator, response);
    }

    @Test
    public void editAdmin() {
        Administrator administrator = new Administrator();

        administrator.setId(1L);
        administrator.setFirstName("Petr");
        administrator.setLastName("Petrov");
        administrator.setPatronymic("Petrovich");
        administrator.setLogin("petr1");
        administrator.setPosition("manager");
        administrator.setPassword("petrpass11");

        AdministratorUpdateRequestDto updateRequestDto = new AdministratorUpdateRequestDto();
        updateRequestDto.setFirstName(administrator.getFirstName());
        updateRequestDto.setLastName(administrator.getLastName());
        updateRequestDto.setPatronymic(administrator.getPatronymic());
        updateRequestDto.setOldPassword(administrator.getPassword());
        updateRequestDto.setPosition(administrator.getPosition());
        updateRequestDto.setNewPassword("petrpass111234");

        Administrator updatedAdmin = new Administrator();
        updatedAdmin.setId(administrator.getId());
        updatedAdmin.setFirstName(updateRequestDto.getFirstName());
        updatedAdmin.setLastName(updateRequestDto.getLastName());
        updatedAdmin.setPatronymic(updateRequestDto.getPatronymic());
        updatedAdmin.setPosition(updateRequestDto.getPosition());

        Session session = new Session();
        session.setUserId(1L);
        session.setCookie("cookie");

        when(sessionService.getSession(session.getCookie())).thenReturn(session);
        when(administratorService.editAdmin(updateRequestDto, administrator.getId())).thenReturn(updatedAdmin);

        Administrator response = underTest.editAdmin(session.getCookie(), updateRequestDto).getBody();
        assertEquals(updatedAdmin, response);
    }
}