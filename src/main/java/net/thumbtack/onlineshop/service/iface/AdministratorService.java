package net.thumbtack.onlineshop.service.iface;

import net.thumbtack.onlineshop.dto.request.AdministratorDto;
import net.thumbtack.onlineshop.dto.request.AdministratorUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Administrator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface AdministratorService {
    Administrator addAdministrator(AdministratorDto administratorDto);

    Administrator getAdministratorById(Long id);

    Administrator editAdmin(AdministratorUpdateRequestDto updateRequestDto, Long id);

    Administrator getAdminByLogin(String login);

    Set<Administrator> getAllAdmins();

    boolean isLoginExist(String login);

    boolean isUserExist(String login, String password);

    boolean isPasswordCorrect(String password);
}
