package net.thumbtack.onlineshop.exception;

public class BuyingException extends OnlineShopException {

    public BuyingException() {
        super(OnlineShopErrorCode.USER_WRONG_ITEM);
    }
}
