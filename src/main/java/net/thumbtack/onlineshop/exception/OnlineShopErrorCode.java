package net.thumbtack.onlineshop.exception;

public enum OnlineShopErrorCode {

    USER_WRONG_LOGIN("Wrong login %s"),
    USER_WRONG_ITEM("Incorrect count of product or client doesn't have enough money"),
    SESSION_ACCESS_COOKIE("User doesn't have permission to do this"),
    SESSION_WRONG_COOKIE("Session with this cookie doesn't exist"),
    ;


    private String errorString;

    OnlineShopErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
