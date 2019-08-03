package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.LogInRequestDto;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.entity.User;
import net.thumbtack.onlineshop.exception.SessionDoesntExistException;
import net.thumbtack.onlineshop.service.iface.AccountService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/session")
public class SessionController {
    private final SessionService sessionService;
    private final AccountService accountService;

    public SessionController(SessionService sessionService, AccountService accountService) {
        this.sessionService = sessionService;
        this.accountService = accountService;
    }

    @PostMapping("/")
    public ResponseEntity<User> logIn(@RequestBody @Valid LogInRequestDto loginData) {
        Session session = sessionService.logIn(loginData.getLogin(), loginData.getPassword());
        User user = accountService.getCurrentUserById(session.getUserId());
        HttpCookie cookie = ResponseCookie.from("JAVASESSIONID", session.getCookie())
                .path("/")
                .maxAge(30 * 60)
                .build();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(user);
    }

    @DeleteMapping("/")
    public ResponseEntity logOut(@CookieValue(value = "JAVASESSIONID", defaultValue = "None") String cookie) {
        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }
        sessionService.logOut(cookie);
        return ResponseEntity.ok().body(null);
    }
}
