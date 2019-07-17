package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.AdministratorUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.entity.User;
import net.thumbtack.onlineshop.exception.LoginDuplicateException;
import net.thumbtack.onlineshop.exception.OnlineShopException;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admins")
public class AdministratorController {
    private final AdministratorService administratorService;
    private final SessionService sessionService;

    public AdministratorController(AdministratorService administratorService, SessionService sessionService) {
        this.administratorService = administratorService;
        this.sessionService = sessionService;
    }

    @PostMapping("/")
    public ResponseEntity<User> registrationAdmin(
            @RequestBody Administrator administrator) {

        try {
            if(!administratorService.registration(administrator)) {
                throw new LoginDuplicateException(administrator.getLogin());
            }
        } catch (OnlineShopException ex) {
            throw ex;
        }

        Session session;
        try {
            session = sessionService.logIn(administrator.getLogin(), administrator.getPassword());
        } catch (OnlineShopException ex) {
            throw ex;
        }

        Administrator administratorResponse = new Administrator(session.getUserId(), administrator.getFirstName(),
                administrator.getLastName(), administrator.getPatronymic(),
                null , null, null,  administrator.getPosition());

        HttpCookie cookie = ResponseCookie.from("JAVASESSIONID", session.getCookie())
                .path("**/api/**")
                .maxAge(30*60)
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(administratorResponse);
    }

    @PutMapping("/")
    public ResponseEntity<User> editAdmin(
            @CookieValue(value = "JAVASESSIONID", defaultValue="none") String cookie,
            @RequestBody AdministratorUpdateRequestDto updateRequestDto) {

        Session session;
        try {
             session = sessionService.getSession(cookie);
        } catch (OnlineShopException ex) {
            System.out.println(ex.getOnlineShopErrorCode());
            return null;
        }

        try {
            this.administratorService.editAdmin(updateRequestDto, session.getUserId());
        } catch (OnlineShopException ex) {
            throw ex;
        }

        Administrator administratorResponse = new Administrator(session.getUserId(), updateRequestDto.getFirstName(),
                updateRequestDto.getLastName(), updateRequestDto.getPatronymic(),
                null , null, null,  updateRequestDto.getPosition());

        return new ResponseEntity<>(administratorResponse, HttpStatus.OK);
    }

}
