package com.education.exceptions;

/**
 *  наследуемся от RuntimeException, чтобы запрашивалось явное указание в сигнатуре метода
 */
public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }
}
