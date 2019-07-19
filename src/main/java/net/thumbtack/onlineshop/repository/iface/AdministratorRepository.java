package net.thumbtack.onlineshop.repository.iface;

import net.thumbtack.onlineshop.entity.Administrator;

import java.util.List;

public interface AdministratorRepository {

    int addAdministrator(Administrator administrator);
    Administrator getAdminById(int id);
    void deleteAdministrator(int id);
    void updateAdministrator(Administrator administrator);
    List<Administrator> getAllAdmins();
}
