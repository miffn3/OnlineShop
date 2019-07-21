package net.thumbtack.onlineshop.exception;

public class SessionAccessDeniedException extends OnlineShopException {

    public SessionAccessDeniedException() {
        super(OnlineShopErrorCode.SESSION_ACCESS_COOKIE);
    }
}
