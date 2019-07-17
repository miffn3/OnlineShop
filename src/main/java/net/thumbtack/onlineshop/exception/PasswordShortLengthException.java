package net.thumbtack.onlineshop.exception;

public class PasswordShortLengthException extends OnlineShopException {

    public PasswordShortLengthException(int length) {
        super(OnlineShopErrorCode.USER_SHORT_PASSWORD, Integer.toString(length));
    }
}
