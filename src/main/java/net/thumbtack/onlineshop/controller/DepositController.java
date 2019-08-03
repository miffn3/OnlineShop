package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.DepositRequestDto;
import net.thumbtack.onlineshop.entity.Client;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.SessionAccessDeniedException;
import net.thumbtack.onlineshop.exception.SessionDoesntExistException;
import net.thumbtack.onlineshop.service.iface.ClientService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/deposits")
public class DepositController {
    private final SessionService sessionService;
    private final ClientService clientService;

    public DepositController(SessionService sessionService, ClientService clientService) {
        this.sessionService = sessionService;
        this.clientService = clientService;
    }

    @PutMapping("/")
    public ResponseEntity<Client> addMoney(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie,
            @RequestBody DepositRequestDto deposit) {
        Client client = getClient(cookie);
        client.setDeposit(clientService.addMoney(deposit.getDeposit(), client.getId()));
        return new ResponseEntity<>(client, HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<Client> getDeposit(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {
        Client client = getClient(cookie);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    private Client getClient(@CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {
        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }

        Client client = clientService.getClientById(session.getUserId());
        if (client == null) {
            throw new SessionAccessDeniedException();
        }
        return client;
    }
}
