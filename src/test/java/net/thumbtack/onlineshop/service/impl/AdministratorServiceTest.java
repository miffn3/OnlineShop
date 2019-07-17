package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.dto.request.AdministratorUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.exception.OnlineShopException;
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
    public void registration() {
        Administrator administrator = new Administrator(1,"Petr","Petrov",
                "Petrovich","petr1","petrpass","admin","lead");


        try {
            underTest.registration(administrator);
        } catch (OnlineShopException ex) {
            System.out.println(ex.getOnlineShopErrorCode());
        }

        verify(administratorRepository).addAdministrator(captor.capture());
        Administrator value = captor.getValue();
        assertEquals(administrator,value);
    }

    @Test
    public void getAllAdministrators() {
        List<Administrator> administrators = Arrays.asList(
                new Administrator(1,"Petr","Petrov",
                        "Petrovich","petr1","petrpass11","admin","lead"),
                new Administrator(2,"Petr","Petrov",
                        "Petrovich","petr1","petrpass11","admin","lead1"));

        when(administratorRepository.getAllAdmins()).thenReturn(administrators);

        List<Administrator> allAdministrators = underTest.getAllAdministrators();
        assertThat(allAdministrators, hasSize(2));
        assertThat(allAdministrators, allOf(hasItem(administrators.get(0)), hasItem(administrators.get(1))));
    }

    @Test
    public void editAdmin() {
        Administrator administrator = new Administrator(1,"Petr","Petrov",
                "Petrovich","petr1","lalala1212","admin","lead");
        AdministratorUpdateRequestDto updateRequestDto =
                new AdministratorUpdateRequestDto(administrator.getFirstName(), administrator.getLastName(),
                        administrator.getPatronymic(), administrator.getPosition(),
                        "petrpass11", " lalala1212");

        try {
            when(underTest.isPasswordExist(administrator.getLogin(), updateRequestDto.getOldPassword())).thenReturn(true);
            underTest.editAdmin(updateRequestDto, administrator.getId());
        } catch (OnlineShopException ex) {
            System.out.println(ex.getOnlineShopErrorCode());
        }

        verify(administratorRepository).updateAdministrator(captor.capture());
        Administrator value = captor.getValue();
        assertEquals(administrator,value);
    }

}