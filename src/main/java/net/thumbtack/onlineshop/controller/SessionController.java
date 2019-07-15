package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.LogInRequestDto;
import net.thumbtack.onlineshop.dto.response.AdministratorResponseDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Session;
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

//    @PostMapping("/")
//    public ResponseEntity<AdministratorResponseDto> logIn(@RequestBody LogInRequestDto loginData){
//        Session session = sessionService.logIn(loginData.getLogin(),loginData.getPassword());
//        Administrator administrator = administratorService.getAdminByLogin(loginData.getLogin());
//        AdministratorResponseDto administratorResponseDto =
//                new AdministratorResponseDto(administrator.getId(), administrator.getFirstName(), administrator.getLastName(),
//                        administrator.getPatronymic(),administrator.getPosition());
//        HttpCookie cookie =  ResponseCookie.from("JAVASESSIONID", session.getCookie())
//                .path("/")
//                .maxAge(30*60)
//                .build();
//        return ResponseEntity
//                .ok()
//                .header(HttpHeaders.SET_COOKIE, cookie.toString())
//                .body(administratorResponseDto);
//    }

//    @DeleteMapping("/")
//    public String logOut(@CookieValue(value = "JAVASESSIONID", defaultValue= "None") String cookie) {
//        Session session = sessionService.validateCookie(cookie);
//        if (session == null) {
//            return "error";
//        }
//        sessionService.logOut(cookie);
//        return "";
//    }
}
