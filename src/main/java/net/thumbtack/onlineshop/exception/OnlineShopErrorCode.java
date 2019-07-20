package net.thumbtack.onlineshop.exception;

public enum OnlineShopErrorCode {

    USER_WRONG_LOGIN("Wrong login %s"),
    USER_LOGIN_DUPLICATE("User %s is already exist"),
    SESSION_WRONG_COOKIE("Session with this cookie doesn't exist"),;


    private String errorString;

    OnlineShopErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
