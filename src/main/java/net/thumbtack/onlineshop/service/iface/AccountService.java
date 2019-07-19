package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.entity.User;

public interface AccountService {
    User getCurrentUserById(int id);
}
