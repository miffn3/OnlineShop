package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.AdministratorUpdateRequestDto;
import net.thumbtack.onlineshop.dto.response.AdministratorResponseDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admins")
public class AdministratorController {
    private final AdministratorService administratorService;

    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @PostMapping("/")
    public ResponseEntity<AdministratorResponseDto> registrationAdmin(@RequestBody Administrator administrator){
        AdministratorResponseDto administratorResponseDto =
                new AdministratorResponseDto(this.administratorService.registration(administrator), administrator.getFirstName(),
                        administrator.getLastName(), administrator.getPatronymic(), administrator.getPosition());

        return new ResponseEntity<>(administratorResponseDto, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<AdministratorResponseDto> editAdmin(
            @RequestBody AdministratorUpdateRequestDto administratorUpdateRequestDto) {

        AdministratorResponseDto administratorResponseDto = new AdministratorResponseDto();

        return new ResponseEntity<>(administratorResponseDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Administrator>> getAllAdmins() {
        List<Administrator> allAdministrators = this.administratorService.getAllAdministrators();
        
        return new ResponseEntity<>(allAdministrators, HttpStatus.OK);
    }

}
