package ru.practicum.shareit.request.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.shareit.item.dto.ItemDtoForRequest;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestDtoForGet;
import ru.practicum.shareit.request.dto.ItemRequestDtoForResponse;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemRequestMapper {

    @Mapping(target = "requestor", source = "requestor")
    @Mapping(target = "id", ignore = true)
    ItemRequest toItemRequest(ItemRequestDto itemRequestDto, User requestor);

    ItemRequestDtoForResponse toItemRequestDtoForResponse(ItemRequestDto itemRequestDto);

    @Mapping(target = "items", source = "items")
    ItemRequestDtoForGet toItemRequestDtoForGet(ItemRequest itemRequest, List<ItemDtoForRequest> items);

}
