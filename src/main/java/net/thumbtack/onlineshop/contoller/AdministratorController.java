package net.thumbtack.onlineshop.contoller;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admins")
public class AdministratorController {
    private final AdministratorService administrationService;

    public AdministratorController(AdministratorService administrationService) {
        this.administrationService = administrationService;
    }

    @PostMapping("/")
    public void registerAdmin(@RequestBody Administrator administrator){
        this.administrationService.registry(administrator);
    }

    //Пример
//    @PostMapping("/createuser")
//    public void createUser(){
//
//    }
}
