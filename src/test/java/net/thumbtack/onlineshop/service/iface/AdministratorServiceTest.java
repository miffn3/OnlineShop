package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.dto.request.AdministratorDto;
import net.thumbtack.onlineshop.dto.request.AdministratorUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.repository.iface.AdministratorRepository;
import net.thumbtack.onlineshop.service.impl.AdministratorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
        Administrator administrator = new Administrator();

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

        underTest.addAdministrator(administratorDto);

        verify(administratorRepository).save(captor.capture());
        Administrator value = captor.getValue();
        assertEquals(administrator,value);
    }

    @Test
    public void getAdministratorById() {
        Administrator administrator = new Administrator();

        administrator.setId(1L);
        administrator.setFirstName("Petr");
        administrator.setLastName("Petrov");
        administrator.setPatronymic("Petrovich");
        administrator.setPosition("manager");
        administrator.setLogin("petr1");
        administrator.setPassword("petrpass11");

        Optional<Administrator> opt = Optional.of(administrator);

        when(administratorRepository.findById(1L)).thenReturn(opt);

        Administrator administrator1 = underTest.getAdministratorById(administrator.getId());
        assertEquals(administrator1,administrator);
    }

    @Test
    public void editAdmin() {
        Administrator administrator = new Administrator();

        administrator.setId(1L);
        administrator.setFirstName("Petr");
        administrator.setLastName("Petrov");
        administrator.setPatronymic("Petrovich");
        administrator.setPosition("manager");
        administrator.setLogin("petr1");
        administrator.setPassword("petrpass11");

        AdministratorUpdateRequestDto updateRequestDto = new AdministratorUpdateRequestDto();
        updateRequestDto.setFirstName(administrator.getFirstName());
        updateRequestDto.setLastName(administrator.getLastName());
        updateRequestDto.setPatronymic(administrator.getPatronymic());
        updateRequestDto.setOldPassword(administrator.getPassword());
        updateRequestDto.setPosition(administrator.getPosition());
        updateRequestDto.setNewPassword("petrpass111234");

        Optional<Administrator> opt = Optional.of(administrator);
        when(administratorRepository.findById(1L)).thenReturn(opt);
        administrator.setPassword(updateRequestDto.getNewPassword());

        underTest.editAdmin(updateRequestDto, administrator.getId());

        verify(administratorRepository).save(captor.capture());
        Administrator value = captor.getValue();
        assertEquals(administrator,value);
    }

    @Test
    public void getAdminByLogin() {
        Administrator administrator = new Administrator();

        administrator.setId(1L);
        administrator.setFirstName("Petr");
        administrator.setLastName("Petrov");
        administrator.setPatronymic("Petrovich");
        administrator.setPosition("manager");
        administrator.setLogin("petr1");
        administrator.setPassword("petrpass11");

        Set<Administrator> administrators = new HashSet<>();
        administrators.add(administrator);

        when(underTest.getAllAdmins()).thenReturn(administrators);

        Administrator administrator1 = underTest.getAdminByLogin(administrator.getLogin());
        assertEquals(administrator1,administrator);
    }

    @Test
    public void getAllAdmins() {
        Administrator administrator = new Administrator();

        administrator.setId(1L);
        administrator.setFirstName("Petr");
        administrator.setLastName("Petrov");
        administrator.setPatronymic("Petrovich");
        administrator.setPosition("manager");
        administrator.setLogin("petr1");
        administrator.setPassword("petrpass11");

        Set<Administrator> administrators = new HashSet<>();
        administrators.add(administrator);

        when(administratorRepository.findAll()).thenReturn(administrators);

        Set<Administrator> admins = underTest.getAllAdmins();
        assertEquals(admins,administrators);
    }

    @Test
    public void isLoginExist() {
        Administrator administrator = new Administrator();

        administrator.setId(1L);
        administrator.setFirstName("Petr");
        administrator.setLastName("Petrov");
        administrator.setPatronymic("Petrovich");
        administrator.setPosition("manager");
        administrator.setLogin("petr1");
        administrator.setPassword("petrpass11");

        Set<Administrator> administrators = new HashSet<>();
        administrators.add(administrator);

        when(administratorRepository.findAll()).thenReturn(administrators);

        boolean isExist = underTest.isLoginExist(administrator.getLogin());
        assertTrue(isExist);
    }

    @Test
    public void isUserExist() {
        Administrator administrator = new Administrator();

        administrator.setId(1L);
        administrator.setFirstName("Petr");
        administrator.setLastName("Petrov");
        administrator.setPatronymic("Petrovich");
        administrator.setPosition("manager");
        administrator.setLogin("petr1");
        administrator.setPassword("petrpass11");

        Set<Administrator> administrators = new HashSet<>();
        administrators.add(administrator);

        when(administratorRepository.findAll()).thenReturn(administrators);

        boolean isExist = underTest.isUserExist(administrator.getLogin(), administrator.getPassword());
        assertTrue(isExist);
    }

    @Test
    public void isPasswordCorrect() {
        Administrator administrator = new Administrator();

        administrator.setId(1L);
        administrator.setFirstName("Petr");
        administrator.setLastName("Petrov");
        administrator.setPatronymic("Petrovich");
        administrator.setPosition("manager");
        administrator.setLogin("petr1");
        administrator.setPassword("petrpass11");

        Set<Administrator> administrators = new HashSet<>();
        administrators.add(administrator);

        when(administratorRepository.findAll()).thenReturn(administrators);

        boolean isExist = underTest.isPasswordCorrect(administrator.getPassword());
        assertTrue(isExist);
    }

}