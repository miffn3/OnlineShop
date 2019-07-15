package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.AdministratorUpdateRequestDto;
import net.thumbtack.onlineshop.dto.response.AdministratorResponseDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.ServerException;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity registrationAdmin(
            @RequestBody Administrator administrator) {

        try {
            this.administratorService.registration(administrator);
        } catch (ServerException ex) {
            System.out.println(ex.getServerErrorCode());
            return null;
        }

        AdministratorResponseDto administratorResponseDto =
                new AdministratorResponseDto(1, administrator.getFirstName(),
                        administrator.getLastName(), administrator.getPatronymic(), administrator.getPosition());

        Session session;
        try {
            session = sessionService.logIn(administrator.getLogin(), administrator.getPassword());
        } catch (ServerException ex) {
            System.out.println(ex.getServerErrorCode());
            return null;
        }

        HttpCookie cookie = ResponseCookie.from("JAVASESSIONID", session.getCookie())
                .path("**/api/**")
                .maxAge(30*60)
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(administratorResponseDto);
    }

    @PutMapping("/")
    public ResponseEntity editAdmin(
            @CookieValue(value = "JAVASESSIONID", defaultValue="none") String cookie,
            @RequestBody AdministratorUpdateRequestDto updateRequestDto) {

        Session session;
        try {
             session = sessionService.getSession(cookie);
        } catch (ServerException ex) {
            System.out.println(ex.getServerErrorCode());
            return null;
        }

        try {
            this.administratorService.editAdmin(updateRequestDto, session.getUserId());
        } catch (ServerException ex) {
            System.out.println(ex.getServerErrorCode());
            return null;
        }


        AdministratorResponseDto administratorResponseDto =
                new AdministratorResponseDto(session.getUserId(), updateRequestDto.getFirstName(), updateRequestDto.getLastName(),
                        updateRequestDto.getPatronymic(), updateRequestDto.getPosition());
        return new ResponseEntity<>(administratorResponseDto, HttpStatus.OK);
    }

    //Этого метода в условии нет, просто как пример для себя сделал, потом уберу
    @GetMapping("/")
    public ResponseEntity<List<Administrator>> getAllAdmins(
            @CookieValue("JAVASESSIONID") String cookie) {
        List<Administrator> allAdministrators = this.administratorService.getAllAdministrators();
        return new ResponseEntity<>(allAdministrators, HttpStatus.OK);
    }

}
