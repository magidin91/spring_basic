package com.education.exceptions;

public class ExceptionThrower {
    public static void ifNullThrowEntityIllegalArgumentException(Object object, String message) {
        if (object == null) {
            throw new EntityIllegalArgumentException(message);
        }
    }

    public static void ifNullThrowEntityNotFoundException(Object object, String type, Object id) {
        if (object == null) {
            throw new EntityNotFoundException(type, id);
        }
    }

    public static void ifObjectIsNotNullThrowEntityAlreadyExistsException(Object object, String type, Object id) {
        if (object != null) {
            throw new EntityAlreadyExistsException(type, id);
        }
    }

    public static void ifSizeMore0ThrowEntityHasDetailsException(int size, String type, Object id) {
        if (size > 0) {
            throw new EntityHasDetailsException(type, id);
        }
    }

    public static Integer ifIdFormatIsWrongThrowEntityIllegalArgumentException(Object id) {
        try {
            return Integer.parseInt(id.toString());
        } catch (NumberFormatException ex) {
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификатор" +
                    " к нужному типу текст ошибки: %s", ex));
        }
    }
}
