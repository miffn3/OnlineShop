package net.thumbtack.onlineshop.exception;

public class LoginNotFoundException extends OnlineShopException {

    public LoginNotFoundException(String login) {
        super(OnlineShopErrorCode.USER_WRONG_LOGIN, login);
    }
}
