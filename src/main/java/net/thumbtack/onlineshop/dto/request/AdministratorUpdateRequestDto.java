package net.thumbtack.onlineshop.dto.request;

import lombok.Data;
import net.thumbtack.onlineshop.validation.annotation.IncorrectPassword;

import javax.validation.constraints.Size;

@Data
public class AdministratorUpdateRequestDto {

    @Size(max = 50, message = "First name should be less than 50 symbols")
    private String firstName;

    @Size(max = 50, message = "Last name should be less than 50 symbols")
    private String lastName;

    @Size(max = 50, message = "Patronymic should be less than 50 symbols")
    private String patronymic;

    private String position;

    @IncorrectPassword
    private String oldPassword;

    @Size(min = 8, max = 50, message = "Password should be between 8 and 50")
    private String newPassword;
}
