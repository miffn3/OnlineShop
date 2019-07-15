package net.thumbtack.onlineshop.exception;

public enum ServerErrorCode {

    USER_WRONG_LOGIN("Wrong login \"%s\""),
    USER_WRONG_PASSWORD("Wrong password"),
    USER_EMPTY_PASSWORD("Empty password"),
    DUPLICATE_USER_LOGIN("This login \"%s\" is already exist");


    private String errorString;

    ServerErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
