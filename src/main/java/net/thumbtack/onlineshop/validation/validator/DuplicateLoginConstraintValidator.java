package net.thumbtack.onlineshop.validation.validator;


import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.ClientService;
import net.thumbtack.onlineshop.validation.annotation.DuplicateLogin;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DuplicateLoginConstraintValidator implements ConstraintValidator<DuplicateLogin, String> {

    private final AdministratorService administratorService;
    private final ClientService clientService;

    public DuplicateLoginConstraintValidator(AdministratorService administratorService, ClientService clientService) {
        this.administratorService = administratorService;
        this.clientService = clientService;
    }

    @Override
    public void initialize(DuplicateLogin duplicateLogin) {
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext ctx) {
        boolean adminExist = administratorService.isLoginExist(login);
        boolean clientExist = clientService.isLoginExist(login);
        if (adminExist || clientExist) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(
                    "User with login " + login + " is already exist.")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
