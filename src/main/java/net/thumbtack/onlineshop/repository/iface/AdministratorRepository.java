package net.thumbtack.onlineshop.repository.iface;

import net.thumbtack.onlineshop.entity.Administrator;

public interface AdministratorRepository {

    void addAdministrator(Administrator administrator);
    Administrator getAdminById(int id);
    void deleteAdministrator(int id);
    void updateAdministrator(Administrator administrator);
}
