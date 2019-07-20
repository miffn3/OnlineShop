package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.dto.request.ClientDto;
import net.thumbtack.onlineshop.entity.Client;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ClientService {
    Client getClientById(Long id);
    Set getAllClients();
    Client addClient(ClientDto clientDto);
    boolean isLoginExist(String login);
    boolean isPasswordExist(String password);
}
