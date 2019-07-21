package net.thumbtack.onlineshop.validation.validator;


import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.ClientService;
import net.thumbtack.onlineshop.validation.annotation.IncorrectPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IncorrectPasswordConstraintValidator implements ConstraintValidator<IncorrectPassword, String> {

    private final AdministratorService administratorService;
    private final ClientService clientService;

    public IncorrectPasswordConstraintValidator(AdministratorService administratorService, ClientService clientService) {
        this.administratorService = administratorService;
        this.clientService = clientService;
    }

    @Override
    public void initialize(IncorrectPassword incorrectPassword) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext ctx) {
        boolean adminExist = administratorService.isPasswordCorrect(password);
        boolean clientExist = clientService.isPasswordCorrect(password);
        if (!adminExist && !clientExist) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(
                    "Incorrect password.")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}

