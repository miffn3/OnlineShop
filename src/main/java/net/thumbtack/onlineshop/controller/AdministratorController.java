package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.dto.request.AdministratorDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.entity.Product;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/admins")
public class AdministratorController {
    private AdministratorService administratorService;

  @PostMapping("/")
  public ResponseEntity<Administrator> registration(
          @RequestBody @Valid AdministratorDto administratorDto) {

      Administrator admin = administratorService.addAdministrator(administratorDto);
      return new ResponseEntity<>(admin, HttpStatus.OK);
  }

}
