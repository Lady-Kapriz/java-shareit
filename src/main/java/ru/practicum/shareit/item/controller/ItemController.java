package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.CommentDtoForResponse;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoGetResponse;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.markers.Marker;

import java.util.Collection;

import static ru.practicum.shareit.util.Constants.HEADER_USER_ID;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public Collection<ItemDtoGetResponse> getAll(@RequestHeader(HEADER_USER_ID) Long ownerId,
                                                 @RequestParam(defaultValue = "0") Integer from,
                                                 @RequestParam(defaultValue = "10") Integer size) {
        return itemService.getAllForOwnerId(ownerId, from, size);
    }

    @GetMapping("/{itemId}")
    public ItemDtoGetResponse getById(@RequestHeader(HEADER_USER_ID) Long ownerId,
                                      @PathVariable Long itemId) {
        return itemService.getItemByIdForBooking(itemId, ownerId);
    }

    @GetMapping("/search")
    public Collection<ItemDto> findByText(@RequestParam String text,
                                          @RequestParam(defaultValue = "0") Integer from,
                                          @RequestParam(defaultValue = "10") Integer size) {
        return itemService.findByText(text, from, size);
    }

    @PostMapping
    public ItemDto create(@RequestHeader(HEADER_USER_ID) Long ownerId,
                          @RequestBody @Validated(Marker.Create.class) ItemDto itemDto) {
        return itemService.create(itemDto, ownerId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestHeader(HEADER_USER_ID) Long ownerId,
                              @PathVariable Long itemId,
                              @RequestBody @Validated(Marker.Update.class) ItemDto itemDto) {
        return itemService.updateItem(itemDto, ownerId, itemId);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDtoForResponse createComment(@RequestHeader(HEADER_USER_ID) Long ownerId,
                                               @PathVariable Long itemId,
                                               @RequestBody @Validated(Marker.Create.class) CommentDto commentDto) {
        return itemService.createComment(itemId, ownerId, commentDto);
    }
}
