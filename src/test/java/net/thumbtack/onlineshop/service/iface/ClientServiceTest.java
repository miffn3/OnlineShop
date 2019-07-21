package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.dto.request.ClientDto;
import net.thumbtack.onlineshop.dto.request.ClientUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.repository.iface.ClientRepository;
import net.thumbtack.onlineshop.service.iface.ClientService;
import net.thumbtack.onlineshop.service.impl.ClientServiceImpl;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClientServiceTest {
    private ClientService underTest;

    @Mock
    private ClientRepository clientRepository;

    @Captor
    public ArgumentCaptor<Client> captor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new ClientServiceImpl(clientRepository);
    }

    @Test
    public void getClientById() {
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
        Optional<Client> opt = Optional.of(client);

        when(clientRepository.findById(1L)).thenReturn(opt);

        Client client1 = underTest.getClientById(client.getId());
        assertEquals(client1, client);

    }

    @Test
    public void addClient() {
        Client client = new Client();

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

        underTest.addClient(clientDto);

        verify(clientRepository).save(captor.capture());
        Client value = captor.getValue();
        assertEquals(client,value);
    }

    @Test
    public void getClientByLogin() {
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
        
        Set<Client> clients = new HashSet<>();
        clients.add(client);

        when(underTest.getAllClients()).thenReturn(clients);

        Client client1 = underTest.getClientByLogin(client.getLogin());
        assertEquals(client1,client);
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

        Optional<Client> opt = Optional.of(client);
        when(clientRepository.findById(1L)).thenReturn(opt);
        client.setPassword(updateRequestDto.getNewPassword());

        underTest.editClient(updateRequestDto, client.getId());

        verify(clientRepository).save(captor.capture());
        Client value = captor.getValue();
        assertEquals(client,value);
    }

    @Test
    public void isLoginExist() {
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

        Set<Client> clients = new HashSet<>();
        clients.add(client);

        when(underTest.getAllClients()).thenReturn(clients);

        boolean isExist = underTest.isLoginExist(client.getLogin());
        assertTrue(isExist);
    }

    @Test
    public void isUserExist() {
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

        Set<Client> clients = new HashSet<>();
        clients.add(client);

        when(underTest.getAllClients()).thenReturn(clients);

        boolean isExist = underTest.isUserExist(client.getLogin(), client.getPassword());
        assertTrue(isExist);
    }

    @Test
    public void isPasswordCorrect() {
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

        Set<Client> clients = new HashSet<>();
        clients.add(client);

        when(underTest.getAllClients()).thenReturn(clients);

        boolean isExist = underTest.isPasswordCorrect(client.getPassword());
        assertTrue(isExist);
    }

    @Test
    public void getAllClients() {
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

        Set<Client> clients = new HashSet<>();
        clients.add(client);

        when(underTest.getAllClients()).thenReturn(clients);

        Set<Client> clientsGet = underTest.getAllClients();
        assertEquals(clientsGet,clients);
    }
}