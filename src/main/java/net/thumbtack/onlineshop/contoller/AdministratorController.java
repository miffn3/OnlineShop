package net.thumbtack.onlineshop.contoller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/admins")
public class AdministratorController {

    @PostMapping("/createuser")
    public void createUser(){

    }
}
