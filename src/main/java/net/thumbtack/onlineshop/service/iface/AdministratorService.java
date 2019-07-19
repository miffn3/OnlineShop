package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.dto.request.AdministratorDto;
import net.thumbtack.onlineshop.entity.Administrator;

public interface AdministratorService {
    Administrator addAdministrator(AdministratorDto administratorDto);
}
