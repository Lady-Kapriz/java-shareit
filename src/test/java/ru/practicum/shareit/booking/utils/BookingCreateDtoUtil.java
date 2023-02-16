package ru.practicum.shareit.booking.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.booking.dto.BookingCreateDto;

import java.time.LocalDateTime;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingCreateDtoUtil {
    static final BookingCreateDto BOOKING_DTO_IN_ONE = new BookingCreateDto();
    static final BookingCreateDto BOOKING_DTO_IN_TWO = new BookingCreateDto();
    static final Long TWO_DAYS = 2L;
    static final Long ONE_DAYS = 1L;
    static final Long ONE_MINUTES = 1L;
    static final Long ITEM_ID_ONE = 1L;
    static final Long ITEM_ID_TWO = 2L;
    static final Long BOOKING_ID_ONE = 1L;
    static final LocalDateTime CURRENT_TIME = LocalDateTime.now();
    static final LocalDateTime END_IN_PAST = CURRENT_TIME.minusDays(ONE_DAYS);
    static final LocalDateTime START = CURRENT_TIME.plusMinutes(ONE_MINUTES);
    static final LocalDateTime END = CURRENT_TIME.plusDays(TWO_DAYS);
    static final LocalDateTime START_AFTER_END = END.plusDays(TWO_DAYS);
    static final LocalDateTime START_IN_PAST = CURRENT_TIME.minusDays(TWO_DAYS);
    static final String BOOKING_DTO_IN_JSON =
            "{\"itemId\": \"" + BOOKING_ID_ONE + "\", \"start\": \"" + START + "\", \"end\": \"" + END + "\"}";

    public static BookingCreateDto getNullValueBookingDtoIn() {
        BOOKING_DTO_IN_ONE.setItemId(null);
        BOOKING_DTO_IN_ONE.setStart(null);
        BOOKING_DTO_IN_ONE.setEnd(null);
        return BOOKING_DTO_IN_ONE;
    }

    public static BookingCreateDto getStartInPastBookingDtoIn() {
        BOOKING_DTO_IN_ONE.setItemId(ITEM_ID_TWO);
        BOOKING_DTO_IN_ONE.setStart(START_AFTER_END);
        BOOKING_DTO_IN_ONE.setEnd(END);
        return BOOKING_DTO_IN_ONE;
    }

    public static BookingCreateDto getStartInPastAndEndInPast() {
        BOOKING_DTO_IN_ONE.setItemId(ITEM_ID_TWO);
        BOOKING_DTO_IN_ONE.setStart(START_IN_PAST);
        BOOKING_DTO_IN_ONE.setEnd(END_IN_PAST);
        return BOOKING_DTO_IN_ONE;
    }

    public static BookingCreateDto getBookingDtoInOne() {
        BOOKING_DTO_IN_ONE.setItemId(ITEM_ID_ONE);
        BOOKING_DTO_IN_ONE.setStart(START);
        BOOKING_DTO_IN_ONE.setEnd(END);
        return BOOKING_DTO_IN_ONE;
    }

    public static BookingCreateDto getBookingDtoInTwo() {
        BOOKING_DTO_IN_TWO.setItemId(ITEM_ID_TWO);
        BOOKING_DTO_IN_TWO.setStart(START_IN_PAST);
        BOOKING_DTO_IN_TWO.setEnd(END_IN_PAST);
        return BOOKING_DTO_IN_TWO;
    }

    public static String getBookingDtoInJson() {
        return BOOKING_DTO_IN_JSON;
    }
}
