package net.thumbtack.onlineshop.exception;

public class LoginDuplicateException extends OnlineShopException{

    public LoginDuplicateException(String login) {
        super(OnlineShopErrorCode.USER_LOGIN_DUPLICATE,login);
    }
}
