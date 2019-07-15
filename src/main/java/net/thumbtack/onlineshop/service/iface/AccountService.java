package net.thumbtack.onlineshop.service.iface;


import net.thumbtack.onlineshop.entity.Administrator;

public interface AccountService {
    Administrator getAdminById(int id);
}
