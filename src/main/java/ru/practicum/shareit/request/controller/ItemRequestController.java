package ru.practicum.shareit.request.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.markers.Marker;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestDtoForGet;
import ru.practicum.shareit.request.dto.ItemRequestDtoOut;
import ru.practicum.shareit.request.service.ItemRequestService;

import java.util.Collection;

import static ru.practicum.shareit.util.Constants.HEADER_USER_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/requests")
@Slf4j
public class ItemRequestController {
    private final ItemRequestService itemRequestService;

    @PostMapping
    public ItemRequestDtoOut createItemRequest(@RequestHeader(HEADER_USER_ID) Long ownerId,
                                               @RequestBody @Validated(Marker.Create.class) ItemRequestDto itemRequestDto) {
        log.info("создаем запрос");
        return itemRequestService.createItemRequest(itemRequestDto, ownerId);
    }

    @GetMapping
    public Collection<ItemRequestDtoForGet> getAllByOwner(@RequestHeader(HEADER_USER_ID) Long ownerId) {
        log.info("запрос для пользователя с id = {}", ownerId);
        return itemRequestService.getAllByOwner(ownerId);
    }

    @GetMapping("/all")
    public Collection<ItemRequestDtoForGet> getAllItemRequests(@RequestHeader(HEADER_USER_ID) Long requestorId,
                                                         @RequestParam(defaultValue = "0") Integer from,
                                                         @RequestParam(defaultValue = "10") Integer size) {
        return itemRequestService.getAllItemRequests(requestorId, from, size);
    }

    @GetMapping("/{requestId}")
    public ItemRequestDtoForGet getItemRequestById(@RequestHeader(HEADER_USER_ID) Long userId,
                                                   @PathVariable Long requestId) {
        return itemRequestService.getById(requestId, userId);
    }
}
