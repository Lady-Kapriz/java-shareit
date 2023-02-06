package ru.practicum.shareit.booking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.dto.BookingCreateDto;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.state.BookingState;
import ru.practicum.shareit.booking.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.exception.NotValidDataException;
import ru.practicum.shareit.exception.BookingNotFoundException;
import ru.practicum.shareit.exception.StatusNotFoundException;
import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;
import static ru.practicum.shareit.booking.state.BookingState.APPROVED;
import static ru.practicum.shareit.booking.state.BookingState.REJECTED;
import static ru.practicum.shareit.util.Constants.CURRENT_DATA_TIME;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final ItemService itemService;
    private final BookingMapper bookingMapper;

    @Override
    public BookingDto getById(Long bookingId, Long ownerId) {
        Booking booking = findById(bookingId);
        checkDataOf(booking, ownerId);
        return bookingMapper.bookingToBookingDto(booking);
    }

    @Transactional
    @Override
    public BookingDto create(BookingCreateDto bookingCreateDto, Long userId) {
        userService.getById(userId);
        ItemDto itemDto = itemService.getItemById(bookingCreateDto.getItemId());
        checkAvailableItem(itemDto);
        checkOwnerForItem(itemDto, userId);
        checkDate(bookingCreateDto);
        bookingCreateDto.setBookerId(userId);
        return bookingMapper.bookingToBookingDto(bookingRepository.save(
                bookingMapper.bookingCreateDtoToBooking(bookingCreateDto, itemDto)));
    }

    @Transactional
    @Override
    public BookingDto confirmation(Long ownerId, Long bookingId, Boolean approved) {
        Booking booking = findById(bookingId);
        checkOwnerForItem(booking, ownerId);
        checkApprove(booking);
        return bookingMapper.bookingToBookingDto(updateConfirmation(booking, approved));
    }

    @Override
    public List<BookingDto> getUserByStateParam(Long bookerId, BookingState bookingState) {
        userService.getById(bookerId);
        switch (bookingState) {
            case ALL:
                final Sort sort = Sort.by(Sort.Direction.DESC, "start");
                return bookingRepository.findUserBookingById(bookerId, sort).stream()
                        .map(bookingMapper::bookingToBookingDto).collect(Collectors.toList());
            case CURRENT:
                return bookingRepository.findUserBookingByCurrent(bookerId, CURRENT_DATA_TIME)
                        .stream().map(bookingMapper::bookingToBookingDto).collect(Collectors.toList());
            case PAST:
                return bookingRepository.findUserBookingByPast(bookerId, CURRENT_DATA_TIME)
                        .stream().map(bookingMapper::bookingToBookingDto).collect(Collectors.toList());
            case FUTURE:
                return bookingRepository.findUserBookingByFuture(bookerId, CURRENT_DATA_TIME)
                        .stream().map(bookingMapper::bookingToBookingDto).collect(Collectors.toList());
            case WAITING:
            case REJECTED:
                return bookingRepository.findUserBookingByStatus(bookerId, bookingState)
                        .stream().map(bookingMapper::bookingToBookingDto).collect(Collectors.toList());
            default:
                throw new StatusNotFoundException(String.format(
                        "Unknown state: %s", bookingState));
        }
    }

    @Override
    public List<BookingDto> getItemByStateParam(Long ownerId, BookingState bookingState) {
        userService.getById(ownerId);
        switch (bookingState) {
            case ALL:
                return bookingRepository.findItemBookingById(ownerId)
                        .stream().map(bookingMapper::bookingToBookingDto).collect(Collectors.toList());
            case CURRENT:
                return bookingRepository.findItemBookingByCurrent(ownerId, CURRENT_DATA_TIME)
                        .stream().map(bookingMapper::bookingToBookingDto).collect(Collectors.toList());
            case PAST:
                return bookingRepository.findItemBookingByPast(ownerId, CURRENT_DATA_TIME)
                        .stream().map(bookingMapper::bookingToBookingDto).collect(Collectors.toList());
            case FUTURE:
                log.info("future");
                return bookingRepository.findItemBookingByFuture(ownerId, CURRENT_DATA_TIME)
                        .stream().map(bookingMapper::bookingToBookingDto).collect(Collectors.toList());
            case WAITING:
            case REJECTED:
                return bookingRepository.findItemBookingByStatus(ownerId, bookingState)
                        .stream().map(bookingMapper::bookingToBookingDto).collect(Collectors.toList());
            default:
                throw new StatusNotFoundException(String.format(
                        "Unknown state: %s", bookingState));
        }
    }

    private Booking findById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(
                        String.format("Бронирование с id = %s не найдено", bookingId)));
    }

    private void checkDataOf(Booking booking, Long ownerId) {
        log.info("чк дата");
        if (!booking.getItem().getOwner().equals(ownerId) &&
                !booking.getBooker().getId().equals(ownerId)) {
            throw new BookingNotFoundException(String.format(
                    "Пользователь с id = %s не является автоом бронирования %s или владельцем", ownerId, booking));
        }
    }

    private void checkAvailableItem(ItemDto itemDto) {
        if (itemDto.getAvailable().equals(false)) {
            throw new NotValidDataException(String.format("Вещь %s недоступна для бронирования", itemDto));
        }
    }

    private void checkDate(BookingCreateDto bookingCreateDto) {
        if (bookingCreateDto.getEnd().isBefore(bookingCreateDto.getStart())) {
            throw new NotValidDataException(String.format(
                    "Ошибка валидации даты у бронирования = %s", bookingCreateDto));
        }
    }

    private void checkOwnerForItem(Booking booking, Long ownerId) {
        if (!booking.getItem().getOwner().equals(ownerId)) {
            throw new BookingNotFoundException(String.format(
                    "Пользователь с id = %s не является владельцем вещи %s", ownerId, booking.getItem()));
        }
    }

    private void checkOwnerForItem(ItemDto itemDto, Long userId) {
        if (itemDto.getOwner().equals(userId)) {
            throw new UserNotFoundException(String.format(
                    "Пользователь с id: %s не может забронировать свою вещь %s", userId, itemDto));
        }
    }

    private void checkApprove(Booking booking) {
        if (booking.getStatus().equals(APPROVED)) {
            throw new NotValidDataException(String.format(
                    "Статус APPROVED уже назначен на бронирование  %s", booking));
        }
    }

    private Booking updateConfirmation(Booking booking, Boolean approve) {
        booking.setStatus(REJECTED);
        if (approve) {
            booking.setStatus(APPROVED);
        }
        return booking;
    }

}
