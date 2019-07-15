package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.repository.iface.AdministratorRepository;
import net.thumbtack.onlineshop.service.iface.AdministratorService;

import java.util.List;

public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorRepository administratorRepository;

    public AdministratorServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public int registration(Administrator administrator){
        return administratorRepository.addAdministrator(administrator);
    }

    @Override
    public List<Administrator> getAllAdministrators() {
        return administratorRepository.getAllAdmins();
    }

    @Override
    public void editAdmin(Administrator administrator) {
        this.administratorRepository.updateAdministrator(administrator);
    }

    @Override
    public boolean isAdminExist(String login) {
        List<Administrator> administrators = getAllAdministrators();
        return administrators.stream().filter(o -> o.getLogin().equals(login)).findFirst().isPresent();
    }

    @Override
    public Administrator getAdminByLogin(String login) {
        List<Administrator> administrators = getAllAdministrators();
        try {
            return administrators.stream().filter(o -> o.getLogin().equals(login)).findFirst().get();
        } catch (NullPointerException ex) {
            return null;
        }
    }
}
