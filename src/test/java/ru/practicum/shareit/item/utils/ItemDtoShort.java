/*
package ru.practicum.shareit.item.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoForRequest;
import ru.practicum.shareit.item.dto.ItemDtoShort;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ru.practicum.shareit.booking.utils.BookingDtoUtil.getLastBooking;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemUtil {
    static final ItemDtoShort ITEM_DTO_SHORT = new ItemDtoShort();
    static final ItemDtoOut ITEM_DTO_OUT = new ItemDtoOut();
    static final ItemDto ITEM_DTO = new ItemDto();
    static final Item ITEM = new Item();
    static final ItemDtoOutForRequest ITEM_DTO_OUT_FOR_REQUEST =
            new ItemDtoOutForRequest();
    static final CommentDtoOut COMMENT_DTO = new CommentDtoOut();
    static final CommentDtoIn COMMENT_DTO_IN = new CommentDtoIn();
    static final Long USER_ID = 2L;
    static final Long REQUEST_ID = 1L;
    static final Long COMMENT_ID = 1L;
    static final Long ITEM_ID_ONE = 1L;
    static final Long ITEM_ID = 2L;
    static final Boolean ITEM_AVAILABLE = true;
    static final String ITEM_NAME = "Автомойка";
    static final String ITEM_NAME_FOR_UPDATE = "Автомока Karсher";
    static final String ITEM_DESCRIPTION = "Автомойка высокого давления";
    static final Long MINUTES = 2L;
    static final Long DAYS = 2L;
    static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss");
    static final LocalDateTime CURRENT_DATA_TIME =
            LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter)
                    .plusMinutes(MINUTES);
    static final LocalDateTime CREATED = CURRENT_DATA_TIME.minusDays(DAYS);
    static final String COMMENT_TEXT = "Comment fo item :)";
    static final String AUTHOR_NAME = "Grigory Rasputin";

    public static ItemDtoShort getItemDtoShort() {
        ITEM_DTO_SHORT.setId(ITEM_ID);
        ITEM_DTO_SHORT.setName(ITEM_NAME);
        return ITEM_DTO_SHORT;
    }

    public static ItemDto getItemDtoOne() {
        ITEM_DTO.setId(1L);
        ITEM_DTO.setName("Дрель");
        ITEM_DTO.setDescription("Электрическая дрель");
        ITEM_DTO.setAvailable(true);
        ITEM_DTO.setRequestId(null);
        ITEM_DTO.setOwner(null);
        return ITEM_DTO;
    }

    public static ItemDto getItemDtoTwo() {
        ITEM_DTO.setId(2L);
        ITEM_DTO.setName("Фонарик");
        ITEM_DTO.setDescription("На батарейках");
        ITEM_DTO.setAvailable(true);
        ITEM_DTO.setRequestId(null);
        ITEM_DTO.setOwner(null);
        return ITEM_DTO;
    }

    public static ItemDto getItemDtoThree() {
        ITEM_DTO.setId(3L);
        ITEM_DTO.setName("Кофеварка");
        ITEM_DTO.setDescription("Капельная");
        ITEM_DTO.setAvailable(false);
        ITEM_DTO.setRequestId(null);
        ITEM_DTO.setOwner(null);
        return ITEM_DTO;
    }

    public static ItemDtoOut getItemDtoOut() {
        ITEM_DTO_OUT.setId(ITEM_ID);
        ITEM_DTO_OUT.setName(ITEM_NAME);
        ITEM_DTO_OUT.setDescription(ITEM_DESCRIPTION);
        ITEM_DTO_OUT.setAvailable(ITEM_AVAILABLE);
        ITEM_DTO_OUT.setLastBooking(getLastBooking());
        ITEM_DTO_OUT.setNextBooking(getNextBooking());
        ITEM_DTO_OUT.setComments(getListCommentDto());
        return ITEM_DTO_OUT;
    }

    public static ItemDtoOut getItemDtoOutTwo() {
        ITEM_DTO_OUT.setId(ITEM_ID);
        ITEM_DTO_OUT.setName(ITEM_NAME);
        ITEM_DTO_OUT.setDescription(ITEM_DESCRIPTION);
        ITEM_DTO_OUT.setAvailable(ITEM_AVAILABLE);
        ITEM_DTO_OUT.setLastBooking(null);
        ITEM_DTO_OUT.setNextBooking(null);
        ITEM_DTO_OUT.setComments(List.of());
        return ITEM_DTO_OUT;
    }

    public static CommentDtoOut getCommentDto() {
        COMMENT_DTO.setId(COMMENT_ID);
        COMMENT_DTO.setText(COMMENT_TEXT);
        COMMENT_DTO.setAuthorName(AUTHOR_NAME);
        COMMENT_DTO.setCreated(CREATED);
        return COMMENT_DTO;
    }

    public static CommentDtoIn getCommentDtoIn() {
        COMMENT_DTO_IN.setId(COMMENT_ID);
        COMMENT_DTO_IN.setText(COMMENT_TEXT);
        return COMMENT_DTO_IN;
    }

    public static ItemDto getItemDto() {
        ITEM_DTO.setId(ITEM_ID);
        ITEM_DTO.setName(ITEM_NAME);
        ITEM_DTO.setDescription(ITEM_DESCRIPTION);
        ITEM_DTO.setAvailable(ITEM_AVAILABLE);
        ITEM_DTO.setOwner(USER_ID);
        ITEM_DTO.setRequestId(REQUEST_ID);
        return ITEM_DTO;
    }

    public static Item getItem() {
        ITEM.setId(ITEM_ID);
        ITEM.setName(ITEM_NAME);
        ITEM.setDescription(ITEM_DESCRIPTION);
        ITEM.setAvailable(ITEM_AVAILABLE);
        ITEM.setOwner(USER_ID);
        ITEM.setRequest(getItemRequest());
        return ITEM;
    }

    public static ItemDto getItemDtoForUpdate() {
        ITEM_DTO.setName(ITEM_NAME_FOR_UPDATE);
        return ITEM_DTO;
    }

    private static ItemDtoOutForRequest getItemDtoOutForRequest() {
        ITEM_DTO_OUT_FOR_REQUEST.setId(ITEM_ID_ONE);
        ITEM_DTO_OUT_FOR_REQUEST.setDescription(ITEM_DESCRIPTION);
        ITEM_DTO_OUT_FOR_REQUEST.setName(ITEM_NAME);
        ITEM_DTO_OUT_FOR_REQUEST.setAvailable(ITEM_AVAILABLE);
        ITEM_DTO_OUT_FOR_REQUEST.setRequestId(REQUEST_ID);
        return ITEM_DTO_OUT_FOR_REQUEST;
    }

    public static List<ItemDtoOutForRequest> getItemDtoOutForRequestList() {
        return List.of(getItemDtoOutForRequest());
    }

    public static List<ItemDto> getListItemDto() {
        return List.of(getItemDto());
    }

    public static List<CommentDtoOut> getListCommentDto() {
        return List.of(getCommentDto());
    }

    public static List<ItemDtoOut> getListItemDtoOut() {
        return List.of(getItemDtoOutTwo());
    }
}*/
