/*
package ru.practicum.shareit.booking.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDtoForItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ru.practicum.shareit.booking.state.BookingState.CURRENT;
import static ru.practicum.shareit.item.utils.ItemUtil.getItemDtoShort;
import static ru.practicum.shareit.user.utils.UserUtil.getUserDtoShort;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingDtoUtil {
    static final BookingDto BOOKING_DTO_OUT = new BookingDto();
    static final BookingDtoForItem BOOKING_DTO_OUT_FOR_ITEM = new BookingDtoForItem();
    static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss");
    static final Long MINUTES = 2L;
    static final Long DAYS = 2L;
    static final LocalDateTime CURRENT_DATA_TIME =
            LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter)
                    .plusMinutes(MINUTES);
    static final LocalDateTime END_DATA_TIME = CURRENT_DATA_TIME.plusDays(DAYS);
    static final String START = CURRENT_DATA_TIME.toString();
    static final String END = END_DATA_TIME.toString();
    static final Long BOOKING_ID = 100L;
    static final Long BOOKING_ID_ONE = 1L;
    static final Long BOOKING_ID_TWO = 2L;
    static final Long BOOKER_ID = 2L;
    static final String BOOKING_DTO_OUT_JSON =
            "{\"id\": \"100\", \"start\": \"" + START + "\", \"end\": \"" + END + "\"," +
                    " \"status\": \"CURRENT\", \"booker\": {\"id\": \"3\"}," +
                    " \"item\": {\"id\": \"2\", \"name\": \"Автомойка\"}}";

    public static BookingDto getBookingDtoOut() {
        BOOKING_DTO_OUT.setId(BOOKING_ID);
        BOOKING_DTO_OUT.setStart(CURRENT_DATA_TIME);
        BOOKING_DTO_OUT.setEnd(END_DATA_TIME);
        BOOKING_DTO_OUT.setStatus(CURRENT);
        BOOKING_DTO_OUT.setBooker(getUserDtoShort());
        BOOKING_DTO_OUT.setItem(getItemDtoShort());
        return BOOKING_DTO_OUT;
    }

    public static BookingDtoForItem getLastBooking() {
        BOOKING_DTO_OUT_FOR_ITEM.setId(BOOKING_ID_ONE);
        BOOKING_DTO_OUT_FOR_ITEM.setBookerId(BOOKER_ID);
        return BOOKING_DTO_OUT_FOR_ITEM;
    }

    public static BookingDtoForItem getNextBooking() {
        BOOKING_DTO_OUT_FOR_ITEM.setId(BOOKING_ID_TWO);
        BOOKING_DTO_OUT_FOR_ITEM.setBookerId(BOOKER_ID);
        return BOOKING_DTO_OUT_FOR_ITEM;
    }

    public static List<BookingDto> getBookingDtoUtList() {
        return List.of(getBookingDtoOut());
    }

    public static String getBookingDtoOutJson() {
        return BOOKING_DTO_OUT_JSON;
    }
}
*/
