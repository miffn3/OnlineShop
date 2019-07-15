package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.entity.Administrator;

import java.util.List;

public interface AdministratorService {
    int registration(Administrator administrator);
    List<Administrator> getAllAdministrators();
    void editAdmin(Administrator administrator);
    boolean isAdminExist(String login);
    Administrator getAdminByLogin(String login);
}
