package net.thumbtack.onlineshop.validation.validator;


import net.thumbtack.onlineshop.dto.request.LogInRequestDto;
import net.thumbtack.onlineshop.service.iface.AdministratorService;
import net.thumbtack.onlineshop.service.iface.ClientService;
import net.thumbtack.onlineshop.validation.annotation.IncorrectLoginPasswordPair;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IncorrectLoginPasswordConstraintValidator implements ConstraintValidator<IncorrectLoginPasswordPair, LogInRequestDto> {

    private final AdministratorService administratorService;
    private final ClientService clientService;

    public IncorrectLoginPasswordConstraintValidator(AdministratorService administratorService, ClientService clientService) {
        this.administratorService = administratorService;
        this.clientService = clientService;
    }

    @Override
    public void initialize(IncorrectLoginPasswordPair pair) {
    }

    @Override
    public boolean isValid(LogInRequestDto request, ConstraintValidatorContext ctx) {
        boolean adminLoginExist = administratorService.isLoginExist(request.getLogin());
        boolean clientLoginExist = clientService.isLoginExist(request.getLogin());

        if (!adminLoginExist && !clientLoginExist) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(
            "User with login "+ request.getLogin()+ " does not exist.")
                    .addPropertyNode("login")
                    .addConstraintViolation();
            return false;
        }

        boolean adminExist = administratorService.isUserExist(request.getLogin(), request.getPassword());
        boolean clientExist = clientService.isUserExist(request.getLogin(), request.getPassword());

        if (!adminExist && !clientExist) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(
                    "Incorrect password.")
                    .addPropertyNode("password")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
