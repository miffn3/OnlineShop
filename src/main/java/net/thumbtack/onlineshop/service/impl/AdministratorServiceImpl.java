package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.dto.request.AdministratorUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.exception.*;
import net.thumbtack.onlineshop.repository.iface.AdministratorRepository;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class AdministratorServiceImpl implements AdministratorService {
    @Value("${min_password_length}")
    private int min_password_length;

    @Value("${max_name_length}")
    private int max_name_length;

    private final AdministratorRepository administratorRepository;

    public AdministratorServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public boolean registration(Administrator administrator) throws OnlineShopException {
        validate(administrator);

        if(isLoginExist(administrator.getLogin())) {
            return false;
        }

        administratorRepository.addAdministrator(administrator);
        return true;
    }

    @Override
    public List<Administrator> getAllAdministrators() {
        return administratorRepository.getAllAdmins();
    }

    @Override
    public void editAdmin(AdministratorUpdateRequestDto updateRequestDto, int id) throws OnlineShopException {
        Administrator administrator = getAdminById(id);

        if(!isPasswordExist(administrator.getLogin(), updateRequestDto.getOldPassword())) {
            throw new OnlineShopException(OnlineShopErrorCode.USER_WRONG_PASSWORD);
        }

        administrator.setFirstName(updateRequestDto.getFirstName());
        administrator.setLastName(updateRequestDto.getLastName());
        administrator.setPatronymic(updateRequestDto.getPatronymic());
        administrator.setPosition(updateRequestDto.getPosition());
        administrator.setPassword(updateRequestDto.getNewPassword());
        validate(administrator);

        this.administratorRepository.updateAdministrator(administrator);
    }

    @Override
    public boolean isLoginExist(String login) {
        List<Administrator> administrators = getAllAdministrators();

        return administrators.stream().filter(o -> o.getLogin().equals(login)).findFirst().isPresent();
    }

    @Override
    public boolean isPasswordExist(String login, String password) {
        List<Administrator> administrators = getAllAdministrators();

        return administrators.stream().filter(o -> o.getLogin().equals(login)
                && o.getPassword().equals(password)).findFirst().isPresent();
    }

    @Override
    public Administrator getAdminById(int id) {
        return administratorRepository.getAdminById(id);
    }

    @Override
    public Administrator getAdminByLogin(String login) throws OnlineShopException {
        List<Administrator> administrators = getAllAdministrators();
        try {
            return administrators.stream().filter(o -> o.getLogin().equals(login)).findFirst().get();
        } catch (NullPointerException ex) {
            throw new LoginNotFoundException(login);
        }
    }

    private void validate(Administrator administrator) throws OnlineShopException {
        int passwordLength = administrator.getPassword().length();
        int loginLength = administrator.getLogin().length();
        int firstNameLength = administrator.getFirstName().length();
        int lastNameLength = administrator.getLastName().length();
        int patronymicLength = administrator.getPatronymic().length();

        if(passwordLength < min_password_length) {
            throw new PasswordShortLengthException(min_password_length);
        }

        if(passwordLength > max_name_length) {
            throw new PasswordLongLengthException(max_name_length);
        }

        if(loginLength > max_name_length) {
            throw new OnlineShopException(OnlineShopErrorCode.USER_WRONG_LOGIN, administrator.getLogin());
        }

        if(firstNameLength > max_name_length) {
            throw new OnlineShopException(OnlineShopErrorCode.USER_WRONG_FIRSTNAME, administrator.getFirstName());
        }

        if(lastNameLength > max_name_length) {
            throw new OnlineShopException(OnlineShopErrorCode.USER_WRONG_LASTNAME, administrator.getLastName());
        }

        if(patronymicLength > max_name_length) {
            throw new OnlineShopException(OnlineShopErrorCode.USER_WRONG_PATRONYMIC, administrator.getPatronymic());
        }
    }
 }
