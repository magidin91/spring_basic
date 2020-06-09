package com.education.exceptions;

import org.springframework.util.Assert;

/**
 * Исключение выбрасывается при попытке повторно вставить сущности с тем же ключом
 */
public class EntityAlreadyExistsException extends BaseException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(String type, Object id) {
        this("message");
    }

    private static String formatMessage(String type, Object id) {
        Assert.hasText(type, "Тип не может быть пустым");
        Assert.notNull(id, "Идентификатор не может быть null");
        Assert.hasText(id.toString(), "Идентификатор не может быть пустым");
        return String.format("%s с ключом %s уже существует", type, id);
    }
}
