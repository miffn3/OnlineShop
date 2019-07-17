package net.thumbtack.onlineshop.exception;

public class SessionException extends OnlineShopException {

    public SessionException() {
        super(OnlineShopErrorCode.SESSION_WRONG_COOKIE);
    }
}
