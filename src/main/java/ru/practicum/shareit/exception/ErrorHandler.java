package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("ru.practicum.shareit")
public class ErrorHandler {
    private static final String NOT_FOUND_MESSAGE = "Объект не найден: {}";
    private static final String VALIDATION_EXCEPTION_MESSAGE = "Ошибка валидации: {}";
    private static final String ERROR = "error";
    private static final String NOT_VALID_DATA_MESSAGE = "Объект недоступен для бронирования";

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(final RuntimeException e) {
        log.error("Произошала непредвиденная ошибка: {}", e.getMessage());
        return new ErrorResponse(ERROR, "Произошла непредвиденная ошибка");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(final ValidationException e) {
        log.error(VALIDATION_EXCEPTION_MESSAGE, e.getMessage());
        return new ErrorResponse(VALIDATION_EXCEPTION_MESSAGE, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(final UserNotFoundException e) {
        log.error(NOT_FOUND_MESSAGE, e.getMessage());
        return new ErrorResponse(NOT_FOUND_MESSAGE, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleItemNotFoundException(final ItemNotFoundException e) {
        log.error(NOT_FOUND_MESSAGE, e.getMessage());
        return new ErrorResponse(NOT_FOUND_MESSAGE, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserAlreadyExistsException(final UserAlreadyExistsException e) {
        log.error(VALIDATION_EXCEPTION_MESSAGE, e.getMessage());
        return new ErrorResponse(VALIDATION_EXCEPTION_MESSAGE, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBookingNotFoundException(final BookingNotFoundException e) {
        log.error(NOT_FOUND_MESSAGE, e.getMessage());
        return new ErrorResponse(NOT_FOUND_MESSAGE, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotValidDataException(final NotValidDataException e) {
        log.error(NOT_VALID_DATA_MESSAGE, e.getMessage());
        return new ErrorResponse(NOT_VALID_DATA_MESSAGE, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleStatusNotFoundException(final StatusNotFoundException e) {
        log.error(NOT_FOUND_MESSAGE, e.getMessage());
        return new ErrorResponse(e.getMessage(), ERROR);
    }
}
