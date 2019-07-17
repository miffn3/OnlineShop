package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.dto.request.AdministratorUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.exception.OnlineShopException;

import java.util.List;

public interface AdministratorService {
    boolean registration(Administrator administrator) throws OnlineShopException;
    List<Administrator> getAllAdministrators();
    void editAdmin(AdministratorUpdateRequestDto updateRequestDto, int id) throws OnlineShopException;
    boolean isLoginExist(String login);
    boolean isPasswordExist(String login, String password);
    Administrator getAdminById(int id);
    Administrator getAdminByLogin(String login) throws OnlineShopException;
}
