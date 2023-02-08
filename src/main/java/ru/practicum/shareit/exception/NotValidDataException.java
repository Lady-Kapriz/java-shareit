package ru.practicum.shareit.exception;

public class NotValidDataException extends RuntimeException {
    public NotValidDataException(String message) {
        super(message);
    }
}
