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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AdministratorServiceTest {
//    private AdministratorService underTest;
//
//    @Mock
//    private AdministratorRepository administratorRepository;
//
//    @Captor
//    public ArgumentCaptor<Administrator> captor;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        underTest = new AdministratorServiceImpl(administratorRepository);
//    }
//
//    @Test
//    public void registration() {
//        Administrator administrator = new Administrator(1,"Petr","Petrov",
//                "Petrovich","petr1","petrpass","lead");
//
//
//        underTest.registration(administrator);
//        verify(administratorRepository).addAdministrator(captor.capture());
//        Administrator value = captor.getValue();
//        assertEquals(administrator,value);
//    }
//
//    @Test
//    public void getAllAdministrators() {
//        List<Administrator> administrators = Arrays.asList(
//                new Administrator(1,"Petr","Petrov",
//                        "Petrovich","petr1","petrpass","lead"),
//                new Administrator(2,"Petr","Petrov",
//                        "Petrovich","petr1","petrpass","lead1"));
//
//        when(administratorRepository.getAllAdmins()).thenReturn(administrators);
//
//        List<Administrator> allAdministrators = underTest.getAllAdministrators();
//        assertThat(allAdministrators, hasSize(2));
//        assertThat(allAdministrators, allOf(hasItem(administrators.get(0)), hasItem(administrators.get(1))));
//    }
//
//    @Test
//    public void editAdmin() {
//        Administrator administrator = new Administrator(1,"Petr","Petrov",
//                "Petrovich","petr1","petrpass","lead");
//
//
//        underTest.editAdmin(administrator);
//        verify(administratorRepository).updateAdministrator(captor.capture());
//        Administrator value = captor.getValue();
//        assertEquals(administrator,value);
//    }

}