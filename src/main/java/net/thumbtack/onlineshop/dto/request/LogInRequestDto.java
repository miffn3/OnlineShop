package net.thumbtack.onlineshop.dto.request;

import lombok.Data;
import net.thumbtack.onlineshop.validation.annotation.IncorrectLoginPasswordPair;

@Data
@IncorrectLoginPasswordPair
public class LogInRequestDto {

    private String login;

    private String password;
}
