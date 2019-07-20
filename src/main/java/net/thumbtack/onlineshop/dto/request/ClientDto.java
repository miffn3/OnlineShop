package net.thumbtack.onlineshop.dto.request;

import lombok.Data;
import net.thumbtack.onlineshop.validation.DuplicateLogin;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ClientDto {

    @NotBlank(message = "First name is mandatory")
    @Size(max = 50 , message = "First name should be less than 50 symbols")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 50, message = "Last name should be less than 50 symbols")
    private String lastName;

    @Size(max = 50, message = "Patronymic should be less than 50 symbols")
    private String patronymic;

    @NotBlank(message = "Login is mandatory")
    @Size(max = 50, message = "Login should be less than 50 symbols")
    @DuplicateLogin
    private String login;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 50, message = "Password should be between 8 and 50")
    private String password;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Phone is mandatory")
    private String phone;
}
