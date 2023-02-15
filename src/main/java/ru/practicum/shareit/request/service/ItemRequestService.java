package ru.practicum.shareit.request.service;

import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestDtoForGet;
import ru.practicum.shareit.request.dto.ItemRequestDtoOut;

import java.util.Collection;

public interface ItemRequestService {
    Collection<ItemRequestDtoForGet> getAllByOwner(Long ownerId);

    Collection<ItemRequestDtoForGet> getAllItemRequests(Long requestorId, Integer from, Integer size);

    ItemRequestDtoForGet getById(Long requestId, Long userId);

    ItemRequestDtoOut createItemRequest(ItemRequestDto itemRequestDto, Long ownerId);
}
