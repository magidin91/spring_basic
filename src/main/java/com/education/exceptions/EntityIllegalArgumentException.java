package com.education.exceptions;

/**
 * Исключение пробрасывается при вызове метода сервиса с некорректными парметрами
 */
public class EntityIllegalArgumentException extends BaseException {
    public EntityIllegalArgumentException(String message) {
        super(message);
    }
}
