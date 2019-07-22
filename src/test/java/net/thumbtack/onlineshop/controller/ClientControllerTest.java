package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.ClientDto;
import net.thumbtack.onlineshop.dto.request.ClientUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.ClientService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ClientControllerTest {
    private ClientController underTest;

    @Mock
    private ClientService clientService;

    @Mock
    private SessionService sessionService;

    @Mock
    private AdministratorService administratorService;

    @Before
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
        underTest = new ClientController(this.clientService, this.sessionService, this.administratorService);
    }

    @Test
    public void getAllClients() {
        Client client = new Client();

        client.setId(2L);
        client.setFirstName("Petr");
        client.setLastName("Petrov");
        client.setPatronymic("Petrovich");
        client.setLogin("perlog");
        client.setPassword("petrpass123");
        client.setEmail("petr@mail.ru");
        client.setAddress("Ul. Petrova");
        client.setPhone("8080");

        Set<Client> clients = new HashSet<>();

        Session session = new Session();
        session.setUserId(1L);
        session.setCookie("cookie");

        Administrator administrator = new Administrator();

        administrator.setId(1L);
        administrator.setFirstName("Petr");
        administrator.setLastName("Petrov");
        administrator.setPatronymic("Petrovich");
        administrator.setLogin("petr1");
        administrator.setPosition("manager");
        administrator.setPassword("petrpass11");

        when(administratorService.getAdministratorById(session.getUserId())).thenReturn(administrator);
        when(sessionService.getSession(session.getCookie())).thenReturn(session);
        when(clientService.getAllClients()).thenReturn(clients);

        Set<Client> response = underTest.getAllClients(session.getCookie()).getBody();
        assertEquals(clients, response);
    }

    @Test
    public void registrationClient() {
        Client client = new Client();

        client.setId(1L);
        client.setFirstName("Petr");
        client.setLastName("Petrov");
        client.setPatronymic("Petrovich");
        client.setLogin("perlog");
        client.setPassword("petrpass123");
        client.setEmail("petr@mail.ru");
        client.setAddress("Ul. Petrova");
        client.setPhone("8080");

        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setPatronymic(client.getPatronymic());
        clientDto.setLogin(client.getLogin());
        clientDto.setPassword(client.getPassword());
        clientDto.setEmail(client.getEmail());
        clientDto.setPhone(client.getPhone());
        clientDto.setAddress(client.getAddress());

        Session session = new Session();
        session.setUserId(1L);
        session.setCookie("cookie");

        when(sessionService.logIn(client.getLogin(), client.getPassword())).thenReturn(session);
        when(clientService.addClient(clientDto)).thenReturn(client);

        Client response = underTest.registrationClient(clientDto).getBody();
        assertEquals(client,response);
    }

    @Test
    public void editClient() {
        Client client = new Client();

        client.setId(1L);
        client.setFirstName("Petr");
        client.setLastName("Petrov");
        client.setPatronymic("Petrovich");
        client.setLogin("perlog");
        client.setPassword("petrpass123");
        client.setEmail("petr@mail.ru");
        client.setAddress("Ul. Petrova");
        client.setPhone("8080");

        ClientUpdateRequestDto updateRequestDto = new ClientUpdateRequestDto();
        updateRequestDto.setFirstName(client.getFirstName());
        updateRequestDto.setLastName(client.getLastName());
        updateRequestDto.setPatronymic(client.getPatronymic());
        updateRequestDto.setOldPassword(client.getPassword());
        updateRequestDto.setNewPassword("petrpass111234");
        updateRequestDto.setEmail(client.getEmail());
        updateRequestDto.setAddress(client.getAddress());
        updateRequestDto.setPhone(client.getPhone());

        Client updatedClient = new Client();
        updatedClient.setId(client.getId());
        updatedClient.setFirstName(updateRequestDto.getFirstName());
        updatedClient.setLastName(updateRequestDto.getLastName());
        updatedClient.setPatronymic(updateRequestDto.getPatronymic());
        updatedClient.setEmail(updateRequestDto.getEmail());
        updatedClient.setAddress(updateRequestDto.getAddress());
        updatedClient.setPhone(updateRequestDto.getPhone());

        Session session = new Session();
        session.setUserId(1L);
        session.setCookie("cookie");

        when(sessionService.getSession(session.getCookie())).thenReturn(session);
        when(clientService.editClient(updateRequestDto, client.getId())).thenReturn(updatedClient);

        Client response = underTest.editClient(session.getCookie(), updateRequestDto).getBody();
        assertEquals(updatedClient, response);


    }
}