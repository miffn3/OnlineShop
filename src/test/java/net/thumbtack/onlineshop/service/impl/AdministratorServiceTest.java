package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.repository.iface.AdministratorRepository;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class AdministratorServiceTest {
    private AdministratorService underTest;

    @Mock
    private AdministratorRepository administratorRepository;

    @Captor
    public ArgumentCaptor<Administrator> captor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new AdministratorServiceImpl(administratorRepository);
    }

    @Test
    public void registry() {
        Administrator administrator = new Administrator(1,"Petr","Petrov",
                "Petrovich","petr1","petrpass","lead");


        underTest.registration(administrator);
        verify(administratorRepository).addAdministrator(captor.capture());
        Administrator value = captor.getValue();
        assertEquals(administrator,value);
    }
}