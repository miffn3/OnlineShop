package net.thumbtack.onlineshop.controller;

import net.thumbtack.onlineshop.exception.LoginDuplicateException;
import net.thumbtack.onlineshop.exception.LoginNotFoundException;
import net.thumbtack.onlineshop.exception.SessionException;
import net.thumbtack.onlineshop.validation.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({LoginDuplicateException.class, LoginNotFoundException.class})
    public void springHandleBadRequest(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(SessionException.class)
    public void springHandleSession(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.FORBIDDEN.value());
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                 HttpHeaders headers,
                                 HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<Error> errors = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        for (FieldError fieldError:fieldErrors) {
            Error error = new Error();
            error.setField(fieldError.getField());
            error.setMessage(fieldError.getDefaultMessage());
            error.setErrorCode(fieldError.getCode());
            errors.add(error);
        }
        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

    }
}
