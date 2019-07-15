package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.response.AdministratorResponseDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.ServerException;
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
    public ResponseEntity getCurrentUser(
            @CookieValue(value = "JAVASESSIONID", defaultValue = "none") String cookie) {

        Session session;
        try {
            session = sessionService.getSession(cookie);
        } catch (ServerException ex) {
            System.out.println(ex.getServerErrorCode());
            return null;
        }

        Administrator administrator = accountService.getAdminById(session.getUserId());

        AdministratorResponseDto administratorResponseDto = new AdministratorResponseDto(
                administrator.getId(), administrator.getFirstName(), administrator.getLastName(),
                        administrator.getPatronymic(), administrator.getPosition());
        return ResponseEntity
                .ok()
                .body(administratorResponseDto);
    }
}
