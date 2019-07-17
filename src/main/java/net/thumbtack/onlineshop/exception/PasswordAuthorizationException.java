package net.thumbtack.onlineshop.exception;

public class PasswordAuthorizationException extends OnlineShopException {

    public PasswordAuthorizationException() {
        super(OnlineShopErrorCode.USER_WRONG_PASSWORD);
    }
}
