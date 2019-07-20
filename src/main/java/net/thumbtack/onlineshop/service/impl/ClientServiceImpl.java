package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.dto.request.ClientDto;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.repository.iface.ClientRepository;
import net.thumbtack.onlineshop.service.iface.ClientService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        Client clientTmp = client.orElse(null);
        if(clientTmp != null) {
            clientTmp.setLogin(null);
            clientTmp.setPassword(null);
            clientTmp.setDeposit(null);
        }
        return clientTmp;
    }

    @Override
    public Set getAllClients() {
        return new HashSet<>((Collection)clientRepository.findAll());
    }

    @Override
    public Client addClient(ClientDto clientDto) {
        Client client = new Client();
        client.setPassword(clientDto.getPassword());
        client.setLogin(clientDto.getLogin());
        client.setPatronymic(clientDto.getPatronymic());
        client.setLastName(clientDto.getLastName());
        client.setFirstName(clientDto.getFirstName());
        client.setEmail(clientDto.getEmail());
        client.setAddress(clientDto.getAddress());
        client.setPhone(clientDto.getPhone());
        return clientRepository.save(client);
    }

    @Override
    public boolean isLoginExist(String login) {
        Set<Client> clients = getAllClients();

        return clients.stream().filter(o -> o.getLogin().equals(login)).findFirst().isPresent();
    }

    @Override
    public boolean isPasswordExist(String password) {
        return false;
    }


}
