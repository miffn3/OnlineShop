package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    User getCurrentUserById(Long id);
}
