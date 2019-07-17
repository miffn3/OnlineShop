package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.entity.User;
import net.thumbtack.onlineshop.exception.OnlineShopException;
import net.thumbtack.onlineshop.service.iface.AccountService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/accounts")
public class AccountsController {
    private final AccountService accountService;
    private final SessionService sessionService;

    public AccountsController(AccountService accountService, SessionService sessionService) {
        this.accountService = accountService;
        this.sessionService = sessionService;
    }

    @GetMapping("/")
    public ResponseEntity<User> getCurrentUser(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {

        Session session;
        try {
            session = sessionService.getSession(cookie);
        } catch (OnlineShopException ex) {
            throw ex;
        }

        Administrator administrator = accountService.getAdminById(session.getUserId());
        administrator.setRole(null);
        administrator.setPassword(null);
        administrator.setLogin(null);

        return ResponseEntity
                .ok()
                .body(administrator);
    }
}
