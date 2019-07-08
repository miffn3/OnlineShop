package net.thumbtack.onlineshop.service.impl;

import net.thumbtack.onlineshop.entity.Administrator;
import net.thumbtack.onlineshop.repository.iface.AdministratorRepository;
import net.thumbtack.onlineshop.service.iface.AdministratorService;

public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorRepository administratorRepository;

    public AdministratorServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public void registry(Administrator administrator){

    }
}
