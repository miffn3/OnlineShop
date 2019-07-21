package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.entity.User;
import net.thumbtack.onlineshop.service.impl.AccountServiceImpl;
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
    public void getCurrentUser() { ;
        Administrator administrator = new Administrator();

        administrator.setId(1L);
        administrator.setFirstName("Petr");
        administrator.setLastName("Petrov");
        administrator.setPatronymic("Petrovich");
        administrator.setPosition("manager");
        when(administratorService.getAdministratorById(1L)).thenReturn(administrator);

        User administrator1 = underTest.getCurrentUserById(administrator.getId());
        assertEquals(administrator1,administrator);
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
        when(clientService.getClientById(1L)).thenReturn(client);

        User client1 = underTest.getCurrentUserById(client.getId());
        assertEquals(client1,client);
    }
}