package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.LogInRequestDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.entity.User;
import net.thumbtack.onlineshop.exception.OnlineShopException;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.SessionService;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sessions")
public class SessionController {
    private final SessionService sessionService;
    private final AdministratorService administratorService;

    public SessionController(SessionService sessionService, AdministratorService administratorService) {
        this.sessionService = sessionService;
        this.administratorService = administratorService;
    }

    @PostMapping("/")
    public ResponseEntity<User> logIn(@RequestBody LogInRequestDto loginData){

        Session session = sessionService.logIn(loginData.getLogin(), loginData.getPassword());
        Administrator administrator = administratorService.getAdminById(session.getUserId());

        administrator.setLogin(null);
        administrator.setPassword(null);
        administrator.setRole(null);

        HttpCookie cookie =  ResponseCookie.from("JAVASESSIONID", session.getCookie())
                .path("/")
                .maxAge(30*60)
                .build();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(administrator);
    }

    @DeleteMapping("/")
    public ResponseEntity logOut(@CookieValue(value = "JAVASESSIONID", defaultValue= "None") String cookie) {

        Session session;
        try {
            session = sessionService.getSession(cookie);
        } catch (OnlineShopException ex) {
            throw ex;
        }

        sessionService.logOut(cookie);
        return ResponseEntity.ok().body(null);
    }
}
