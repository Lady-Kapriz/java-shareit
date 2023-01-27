package ru.practicum.shareit.exception;

public class UserAlreadyExistsException extends ValidationException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
