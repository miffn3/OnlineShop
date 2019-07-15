package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Client;

public interface AccountService {
    Administrator getAdmin(String cookie);
    Client getClient(String cookie);
}
