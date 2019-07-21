package net.thumbtack.onlineshop.exception;

public class SessionDoesntExistException extends OnlineShopException {

    public SessionDoesntExistException() {
        super(OnlineShopErrorCode.SESSION_WRONG_COOKIE);
    }
}
