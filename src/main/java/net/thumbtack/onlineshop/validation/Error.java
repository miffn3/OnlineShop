package net.thumbtack.onlineshop.validation;

import lombok.Data;

@Data
public class Error {

    private String errorCode;

    private String field;

    private String message;
}
