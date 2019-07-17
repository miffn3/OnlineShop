package net.thumbtack.onlineshop.exception;

public class PasswordLongLengthException extends OnlineShopException {

    public PasswordLongLengthException(int length) {
        super(OnlineShopErrorCode.USER_LONG_PASSWORD, Integer.toString(length));
    }
}
