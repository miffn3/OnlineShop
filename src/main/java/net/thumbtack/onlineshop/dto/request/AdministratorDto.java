package net.thumbtack.onlineshop.dto.request;

import lombok.Data;

@Data
public class AdministratorDto {

    private String firstName;

    private String lastName;

    private String patronymic;

    private String login;

    private String password;

    private String position;
}
