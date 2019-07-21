package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.dto.request.ClientDto;
import net.thumbtack.onlineshop.dto.request.ClientUpdateRequestDto;
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
    public Set<Client> getAllClients() {
        return new HashSet<>((Collection)clientRepository.findAll());
    }

    @Override
    public Client addClient(ClientDto clientDto) {
        Client client = new Client();
        client.setPassword(clientDto.getPassword());
        client.setLogin(clientDto.getLogin().toLowerCase());
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

        return clients.stream().filter(o -> o.getLogin().equals(login.toLowerCase())).findFirst().isPresent();
    }

    @Override
    public boolean isUserExist(String login, String password) {
        Set<Client> clients = getAllClients();

        return clients.stream().filter(o -> o.getLogin().toLowerCase().equals(login.toLowerCase())
                && o.getPassword().equals(password)).findFirst().isPresent();
    }

    @Override
    public boolean isPasswordCorrect(String password) {
        Set<Client> clients = getAllClients();

        return clients.stream().filter(o -> o.getPassword().toLowerCase().equals(password)).findFirst().isPresent();
    }

    @Override
    public Client getClientByLogin(String login) {
        Set<Client> clients = new HashSet<>((Collection)clientRepository.findAll());
        for (Client client:clients) {
            if(client.getLogin().equals(login.toLowerCase())) {
                return client;
            }
        }
        return null;
    }

    @Override
    public Client editClient(ClientUpdateRequestDto updateRequestDto, Long id) {
        Client client = clientRepository.findById(id).get();
        client.setFirstName(updateRequestDto.getFirstName());
        client.setLastName(updateRequestDto.getLastName());
        client.setPatronymic(updateRequestDto.getPatronymic());
        client.setPassword(updateRequestDto.getNewPassword());
        client.setPhone(updateRequestDto.getPhone());
        client.setAddress(updateRequestDto.getAddress());
        client.setEmail(updateRequestDto.getEmail());
        return clientRepository.save(client);
    }
}
