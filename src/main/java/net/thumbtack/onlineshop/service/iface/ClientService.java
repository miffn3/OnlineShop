package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.dto.request.ClientDto;
import net.thumbtack.onlineshop.dto.request.ClientUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Client;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ClientService {
    Client getClientById(Long id);

    Set<Client> getAllClients();

    Client addClient(ClientDto clientDto);

    Client getClientByLogin(String login);

    Client editClient(ClientUpdateRequestDto updateRequestDto, Long id);

    boolean isLoginExist(String login);

    boolean isUserExist(String login, String password);

    boolean isPasswordCorrect(String password);

    Long addMoney(Long deposit, Long id);

    Long getMoney(Long deposit, Long id);
}
