package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.dto.request.AdministratorDto;
import net.thumbtack.onlineshop.dto.request.AdministratorUpdateRequestDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.repository.iface.AdministratorRepository;
import net.thumbtack.onlineshop.service.iface.AdministratorService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorRepository administratorRepository;

    public AdministratorServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public Administrator addAdministrator(AdministratorDto administratorDto) {
        Administrator administrator = new Administrator();
        administrator.setPassword(administratorDto.getPassword());
        administrator.setLogin(administratorDto.getLogin().toLowerCase());
        administrator.setPatronymic(administratorDto.getPatronymic());
        administrator.setLastName(administratorDto.getLastName());
        administrator.setFirstName(administratorDto.getFirstName());
        administrator.setPosition(administratorDto.getPosition());
        return administratorRepository.save(administrator);
    }

    @Override
    public Administrator getAdministratorById(Long id) {
        Optional<Administrator> administrator = administratorRepository.findById(id);
        Administrator admin = administrator.orElse(null);
        if (admin != null) {
            admin.setLogin(null);
            admin.setPassword(null);
        }
        return admin;
    }

    @Override
    public Administrator editAdmin(AdministratorUpdateRequestDto updateRequestDto, Long id) {
        Administrator administrator = administratorRepository.findById(id).get();
        administrator.setFirstName(updateRequestDto.getFirstName());
        administrator.setLastName(updateRequestDto.getLastName());
        administrator.setPatronymic(updateRequestDto.getPatronymic());
        administrator.setPosition(updateRequestDto.getPosition());
        administrator.setPassword(updateRequestDto.getNewPassword());
        return administratorRepository.save(administrator);
    }

    @Override
    public Administrator getAdminByLogin(String login) {
        Set<Administrator> administrators = new HashSet<>((Collection) administratorRepository.findAll());
        for (Administrator admin : administrators) {
            if (admin.getLogin().equals(login.toLowerCase())) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public Set<Administrator> getAllAdmins() {
        return new HashSet<>((Collection) administratorRepository.findAll());
    }

    @Override
    public boolean isLoginExist(String login) {
        Set<Administrator> administrators = getAllAdmins();
        return administrators.stream().anyMatch(o -> o.getLogin().toLowerCase().equals(login.toLowerCase()));
    }

    @Override
    public boolean isUserExist(String login, String password) {
        Set<Administrator> administrators = getAllAdmins();

        return administrators.stream().anyMatch(o -> o.getLogin().toLowerCase().equals(login.toLowerCase())
                && o.getPassword().equals(password));
    }

    @Override
    public boolean isPasswordCorrect(String password) {
        Set<Administrator> administrators = getAllAdmins();
        return administrators.stream().anyMatch(o -> o.getPassword().equals(password));
    }
}
