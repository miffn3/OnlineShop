package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.AdministratorUpdateRequestDto;
import net.thumbtack.onlineshop.dto.response.AdministratorResponseDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
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
    public ResponseEntity<AdministratorResponseDto> registrationAdmin(@RequestBody Administrator administrator){
        AdministratorResponseDto administratorResponseDto =
                new AdministratorResponseDto(this.administratorService.registration(administrator), administrator.getFirstName(),
                        administrator.getLastName(), administrator.getPatronymic(), administrator.getPosition());
        Session session = sessionService.logIn(administrator.getLogin(), administrator.getPassword());
        HttpCookie cookie = ResponseCookie.from("JAVASESSIONID", session.getCookie())
                .path("/")
                .maxAge(30*60)
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(administratorResponseDto);
    }

    @PutMapping("/")
    public ResponseEntity<AdministratorResponseDto> editAdmin(@CookieValue("JAVASESSIONID") String cookie,
            @RequestBody AdministratorUpdateRequestDto administratorUpdateRequestDto) {

        Session session = this.sessionService.validateCookie(cookie);
        Administrator administrator = this.administratorService.getAdminByLogin(session.getLogin());

        administrator.setPatronymic(administratorUpdateRequestDto.getPatronymic());
        administrator.setLastName(administratorUpdateRequestDto.getLastName());
        administrator.setFirstName(administratorUpdateRequestDto.getFirstName());
        administrator.setPassword(administratorUpdateRequestDto.getNewPassword());
        administrator.setPosition(administratorUpdateRequestDto.getPosition());

        this.administratorService.editAdmin(administrator);

        AdministratorResponseDto administratorResponseDto =
                new AdministratorResponseDto(administrator.getId(), administrator.getFirstName(), administrator.getLastName(),
                        administrator.getPatronymic(), administrator.getPosition());
        return new ResponseEntity<>(administratorResponseDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Administrator>> getAllAdmins(@CookieValue("JAVASESSIONID") String cookie) {
        List<Administrator> allAdministrators = this.administratorService.getAllAdministrators();
        return new ResponseEntity<>(allAdministrators, HttpStatus.OK);
    }

}
