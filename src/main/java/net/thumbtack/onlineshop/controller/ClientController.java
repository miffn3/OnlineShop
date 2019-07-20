package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.ClientDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.OnlineShopException;
import net.thumbtack.onlineshop.service.iface.ClientService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public ResponseEntity<Set<Client>> getAllClients(@CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {

        Session session = sessionService.getSession(cookie);

        Set<Client> clients = this.clientService.getAllClients();
        Set<Client> allClients = new HashSet<>();
        for (Client client:clients) {
            client.setDeposit(null);
            client.setLogin(null);
            client.setPassword(null);
            allClients.add(client);
        }
        return new ResponseEntity<>(allClients, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Client> registrationClient(@RequestBody ClientDto clientDto) {

        Client  client = clientService.addClient(clientDto);
        Session session = sessionService.logIn(client.getLogin(), client.getPassword());
        client.setLogin(null);
        client.setPassword(null);
        HttpCookie cookie = ResponseCookie.from("JAVASESSIONID", session.getCookie())
                .path("/api/")
                .maxAge(30*60)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(client);
    }
}
