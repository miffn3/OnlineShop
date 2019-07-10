package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.service.iface.SessionService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sessions")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

//    @PostMapping("/")
//    public ResponseEntity<Session> logIn(@RequestBody Session session){
//        return new ResponseEntity<>(this.sessionService.logIn(session), HttpStatus.OK);
//    }
}
