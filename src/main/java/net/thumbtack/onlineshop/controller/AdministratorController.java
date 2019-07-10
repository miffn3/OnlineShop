package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admins")
public class AdministratorController {
    private final AdministratorService administratorService;

    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @PostMapping("/")
    public void registerAdmin(@RequestBody Administrator administrator){
        this.administratorService.registry(administrator);
    }

    //Пример
//    @PostMapping("/createuser")
//    public void createUser(){
//
//    }
}
