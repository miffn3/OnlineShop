package net.thumbtack.onlineshop.exception;

public class OnlineShopException extends RuntimeException {
    OnlineShopErrorCode onlineShopErrorCode;

    public OnlineShopException(OnlineShopErrorCode onlineShopErrorCode) {
        super(onlineShopErrorCode.getErrorString());
        this.onlineShopErrorCode = onlineShopErrorCode;
    }

    public OnlineShopException(OnlineShopErrorCode onlineShopErrorCode, String param) {
        super(String.format(onlineShopErrorCode.getErrorString(), param));
        this.onlineShopErrorCode = onlineShopErrorCode;
    }

    public OnlineShopErrorCode getOnlineShopErrorCode() {
        return onlineShopErrorCode;
    }
}
