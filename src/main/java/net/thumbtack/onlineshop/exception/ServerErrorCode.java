package net.thumbtack.onlineshop.exception;

public enum ServerErrorCode {

    USER_WRONG_LOGIN("Wrong login \"%s\""),
    USER_WRONG_FIRSTNAME("Wrong first name \"%s\""),
    USER_WRONG_LASTNAME("Wrong last name \"%s\""),
    USER_WRONG_PATRONYMIC("Wrong patronymic name \"%s\""),
    USER_WRONG_PASSWORD("Wrong password"),
    USER_SHORT_PASSWORD("Too short password, less than \"%s\""),
    USER_LONG_PASSWORD("Too long password, bigger than \"%s\""),
    USER_EMPTY_PASSWORD("Empty password"),
    USER_LOGIN_DUPLICATE("This login \"%s\" is already exist"),
    SESSION_WRONG_COOKIE("Session with this cookie doesn't exist"),;


    private String errorString;

    ServerErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
