package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.repository.iface.ClientRepository;
import net.thumbtack.onlineshop.service.iface.ClientService;
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
    public void getAllClients() {
        List<Client> clients = Arrays.asList(
                new Client(1, "Ivan", "Ivanov", "Ivanovich","Ivan98" ,"pass999","ivan98@mail.ru", "Ivanovo", "999", 555),
                new Client(2, "Petr", "Petrov", "Petrovich","Petr88" ,"pass999","petr88@mail.ru", "Petrovo", "999", 555));
        when(clientRepository.getAllClients()).thenReturn(clients);

        List<Client> allClients = underTest.getAllClients();
        assertThat(allClients, hasSize(2));
        assertThat(allClients, allOf(hasItem(clients.get(0)), hasItem(clients.get(1))));
    }
}