package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.AdministratorDto;
import net.thumbtack.onlineshop.dto.request.AdministratorUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
import net.thumbtack.onlineshop.exception.SessionDoesntExistException;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
  public ResponseEntity<Administrator> registrationAdmin( @RequestBody @Valid AdministratorDto administratorDto) {

      Administrator admin = administratorService.addAdministrator(administratorDto);

      Session session = sessionService.logIn(admin.getLogin(), admin.getPassword());
      admin.setLogin(null);
      admin.setPassword(null);
      HttpCookie cookie = ResponseCookie.from("JAVASESSIONID", session.getCookie())
              .path("/api/")
              .maxAge(30*60)
              .build();

      return ResponseEntity.ok()
              .header(HttpHeaders.SET_COOKIE, cookie.toString())
              .body(admin);
  }

    @PutMapping("/")
    public ResponseEntity<Administrator> editAdmin(
            @CookieValue(value = "JAVASESSIONID", defaultValue="none") String cookie,
            @RequestBody @Valid AdministratorUpdateRequestDto updateRequestDto) {

        Session session = sessionService.getSession(cookie);
        if (session == null) {
            throw new SessionDoesntExistException();
        }

        Administrator administrator = this.administratorService.editAdmin(updateRequestDto, session.getUserId());

        return new ResponseEntity<>(administrator, HttpStatus.OK);
    }

}
