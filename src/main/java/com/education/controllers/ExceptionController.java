package com.education.controllers;

import com.education.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityIllegalArgumentException.class) // исключение, которое должны обработать - EntityIllegalArgumentException
    @ResponseStatus(HttpStatus.BAD_REQUEST) // статус, который должны вернуть
    @ResponseBody //указываем, что в ответе придет не пустота, а body ErrorResponseEntity
    private ErrorResponseEntity handleEntityIllegalArgumentException(EntityIllegalArgumentException e) {
        return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    private ErrorResponseEntity handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        return createErrorResponseEntity(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    private ErrorResponseEntity handleEntityConflictException(EntityConflictException e) {
        return createErrorResponseEntity(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityHasDetailsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private ErrorResponseEntity handleEntityHasDetailsException(EntityHasDetailsException e) {
        return createErrorResponseEntity(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    private ErrorResponseEntity handleEntityNotFoundException(EntityNotFoundException e) {
        return createErrorResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    /**
     * Фабрика для удобного создания объекта ErrorResponseEntity
     * @param e перехватываемая ошибка
     * @param httpStatus
     * @return ErrorResponseEntity
     */
    private static ErrorResponseEntity createErrorResponseEntity(BaseException e, HttpStatus httpStatus) {
        return new ErrorResponseEntity(e.getMessage(), httpStatus.getReasonPhrase(), httpStatus.value());
    }
}
