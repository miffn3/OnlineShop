package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.dto.request.AdministratorUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.exception.ServerException;

import java.util.List;

public interface AdministratorService {
    void  registration(Administrator administrator) throws ServerException;
    List<Administrator> getAllAdministrators();
    void editAdmin(AdministratorUpdateRequestDto updateRequestDto, int id) throws ServerException;
    boolean isLoginExist(String login);
    boolean isPasswordExist(String login, String password);
    Administrator getAdminById(int id);
    Administrator getAdminByLogin(String login) throws ServerException;
}
