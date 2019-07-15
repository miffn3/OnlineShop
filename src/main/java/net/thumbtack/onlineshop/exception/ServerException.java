package net.thumbtack.onlineshop.exception;

public class ServerException extends Exception {
    ServerErrorCode serverErrorCode;

    public ServerException(ServerErrorCode serverErrorCode) {
        super(serverErrorCode.getErrorString());
        this.serverErrorCode = serverErrorCode;
    }

    public ServerException(ServerErrorCode serverErrorCode, String param) {
        super(String.format(serverErrorCode.getErrorString(), param));
        this.serverErrorCode = serverErrorCode;
    }

    public ServerErrorCode getServerErrorCode() {
        return serverErrorCode;
    }
}
