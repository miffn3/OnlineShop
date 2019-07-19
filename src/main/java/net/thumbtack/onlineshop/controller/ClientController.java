package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.OnlineShopException;
import net.thumbtack.onlineshop.service.iface.ClientService;
import net.thumbtack.onlineshop.service.iface.SessionService;
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
    private final SessionService sessionService;

    public ClientController(ClientService clientService, SessionService sessionService) {
        this.clientService = clientService;
        this.sessionService = sessionService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Client>> getAllClients(@CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {

        Session session;
        try {
            session = sessionService.getSession(cookie);
        } catch (OnlineShopException ex) {
            throw ex;
        }
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
