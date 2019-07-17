package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.service.iface.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Client>> getAllClients(@CookieValue("JAVASESSIONID") String cookie) {
        List<Client> clients = this.clientService.getAllClients();
        List<Client> allClients = new ArrayList<>();
        for (Client client:clients) {
            client.setDeposit(null);
            client.setLogin(null);
            client.setPassword(null);
            client.setRole(null);
            allClients.add(client);
        }
        return new ResponseEntity<>(allClients, HttpStatus.OK);
    }
}
