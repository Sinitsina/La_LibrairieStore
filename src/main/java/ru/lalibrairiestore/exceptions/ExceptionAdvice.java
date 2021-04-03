package ru.lalibrairiestore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    /**
     * EntityNotFoundException
     */
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String EntityNotFoundHandler(EntityNotFoundException ex) {
        return ex.getMessage();
    }

    /**
     * BadRequestException
     */
    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String BadRequestHandler(BadRequestException ex) {
        return ex.getMessage();
    }

    /**
     * NullPointerException
     */
    @ResponseBody
    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String InternalServerHandler(InternalServerException ex) {
        return ex.getMessage();
    }

    /**
     * AuthenticationServiceException
     */
    @ResponseBody
    @ExceptionHandler(AuthenticationServiceException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String AuthenticationServiceHandler(AuthenticationServiceException ex) {
        return ex.getMessage();
    }

}
