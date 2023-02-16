package ru.practicum.shareit.item.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.booking.dto.BookingDtoForItem;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.dto.*;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@UtilityClass
public class ItemMapper {
    public Item itemDtoToItem(ItemDto itemDto) {
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setAvailable(itemDto.getAvailable());
        item.setOwner(itemDto.getOwner());
        return item;
    }

    public ItemDto itemToItemDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        itemDto.setAvailable(item.getAvailable());
        itemDto.setOwner(item.getOwner());
        return itemDto;
    }

    public ItemDtoGetResponse toItemDtoForBookingAndCommentShort(
            Item item, BookingDtoForItem lastBookingDto,
            BookingDtoForItem nextBookingDto, List<CommentDtoForResponse> commentDtoForResponses) {
        ItemDtoGetResponse itemDtoGetResponse =
                new ItemDtoGetResponse();
        itemDtoGetResponse.setId(item.getId());
        itemDtoGetResponse.setName(item.getName());
        itemDtoGetResponse.setDescription(item.getDescription());
        itemDtoGetResponse.setAvailable(item.getAvailable());
        itemDtoGetResponse.setLastBooking(lastBookingDto);
        itemDtoGetResponse.setNextBooking(nextBookingDto);
        itemDtoGetResponse.setComments(commentDtoForResponses);
        return itemDtoGetResponse;
    }

    public BookingDtoForItem toBookingDtoForItem(Booking booking) {
        BookingDtoForItem bookingDtoForItem = new BookingDtoForItem();
        bookingDtoForItem.setId(booking.getId());
        bookingDtoForItem.setBookerId(booking.getBooker().getId());
        return bookingDtoForItem;
    }

    public ItemDtoForRequest toItemDtoForRequest(Item item, ItemRequest itemRequest) {
        ItemDtoForRequest itemDtoOutForRequest = new ItemDtoForRequest();
        itemDtoOutForRequest.setId(item.getId());
        itemDtoOutForRequest.setName(item.getName());
        itemDtoOutForRequest.setDescription(item.getDescription());
        itemDtoOutForRequest.setAvailable(item.getAvailable());
        itemDtoOutForRequest.setRequestId(itemRequest.getId());
        return itemDtoOutForRequest;
    }

    public CommentDtoForResponse commentToCommentDtoForResponse(Comment comment) {
        CommentDtoForResponse commentDtoForResponse = new CommentDtoForResponse();
        commentDtoForResponse.setId(comment.getId());
        commentDtoForResponse.setText(comment.getText());
        commentDtoForResponse.setAuthorName(comment.getAuthor().getName());
        commentDtoForResponse.setCreated(comment.getCreated());
        return commentDtoForResponse;
    }

    public Comment commentDtoToComment(CommentDtoForResponse commentDtoForResponse, User author, Item item) {
        Comment comment = new Comment();
        comment.setId(commentDtoForResponse.getId());
        comment.setText(commentDtoForResponse.getText());
        comment.setItem(item);
        comment.setAuthor(author);
        comment.setCreated(commentDtoForResponse.getCreated());
        return comment;
    }

    public CommentDtoForResponse commentDtoToCommentDtoForResponse(CommentDto commentDto) {
        CommentDtoForResponse commentDtoForResponse = new CommentDtoForResponse();
        commentDtoForResponse.setId(commentDto.getId());
        commentDtoForResponse.setText(commentDto.getText());
        return commentDtoForResponse;
    }
}
