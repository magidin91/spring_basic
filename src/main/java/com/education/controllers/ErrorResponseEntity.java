package com.education.controllers;

import lombok.Getter;
import lombok.Setter;

/**
 * Сущность, возвращаемая при возникновении исключений
 */
@Getter
@Setter
public class ErrorResponseEntity {
    private final String message;
    private final String error;
    private final int status;

    public ErrorResponseEntity(String message, String error, int status) {
        this.message = message;
        this.error = error;
        this.status = status;
    }
}
