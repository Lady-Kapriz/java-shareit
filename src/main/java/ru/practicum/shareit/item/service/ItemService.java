package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoGetResponse;

import java.util.Collection;

public interface ItemService {
    Collection<ItemDtoGetResponse> getAllForOwnerId(Long ownerId);

    ItemDto getItemById(Long itemId);

    Collection<ItemDto> findItems(String text);

    ItemDto createItem(ItemDto itemDto, Long ownerId);

    ItemDto updateItem(ItemDto itemDto, Long ownerId, Long itemId);

    ItemDtoGetResponse getByIdForBooking(Long itemId, Long ownerId);

    CommentDto createComment(Long itemId, Long ownerId, CommentDto commentDto);
}
