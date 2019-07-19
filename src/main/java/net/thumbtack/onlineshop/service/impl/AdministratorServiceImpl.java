package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.dto.request.AdministratorDto;
import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.repository.iface.AdministratorRepository;
import net.thumbtack.onlineshop.service.iface.AdministratorService;

public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorRepository administratorRepository;

    public AdministratorServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public Administrator addAdministrator(AdministratorDto administratorDto) {
        Administrator administrator = new Administrator();
        administrator.setPassword(administratorDto.getPassword());
        administrator.setLogin(administratorDto.getLogin());
        administrator.setPatronymic(administratorDto.getPatronymic());
        administrator.setLastName(administratorDto.getLastName());
        administrator.setFirstName(administratorDto.getFirstName());
        administrator.setPosition(administratorDto.getPosition());
        return administratorRepository.save(administrator);
    }
}
