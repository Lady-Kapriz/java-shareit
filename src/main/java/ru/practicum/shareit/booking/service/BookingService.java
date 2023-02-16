package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingCreateDto;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.state.BookingState;

import java.util.List;

public interface BookingService {
    BookingDto getById(Long bookingId, Long ownerId);

    List<BookingDto> getUserByStateParam(Long bookerId, BookingState bookingState,
                                         Integer from, Integer size);

    BookingDto create(BookingCreateDto bookingCreateDto, Long userId);

    BookingDto confirmation(Long ownerId, Long bookingId, Boolean approved);

    List<BookingDto> getItemByStateParam(Long ownerId, BookingState bookingState,
                                         Integer from, Integer size);
}
