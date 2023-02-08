package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
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
    public Collection<ItemDtoGetResponse> getAllItems(@RequestHeader(HEADER_USER_ID) Long ownerId) {
        return itemService.getAllForOwnerId(ownerId);
    }

    @GetMapping("/{itemId}")
    public ItemDtoGetResponse getItemByIdForBooking(@RequestHeader(HEADER_USER_ID) Long ownerId,
                                         @PathVariable Long itemId) {
        return itemService.getItemByIdForBooking(itemId, ownerId);
    }

    @GetMapping("/search")
    public Collection<ItemDto> findItems(@RequestParam String text) {
        return itemService.findItems(text);
    }

    @PostMapping
    public ItemDto createItem(@RequestHeader(HEADER_USER_ID) Long ownerId,
                              @RequestBody @Validated(Marker.Create.class) ItemDto itemDto) {
        return itemService.createItem(itemDto, ownerId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@PathVariable Long itemId,
                              @RequestHeader(HEADER_USER_ID) Long ownerId,
                              @RequestBody @Validated(Marker.Update.class) ItemDto itemDto) {
        return itemService.updateItem(itemDto, ownerId, itemId);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto createComment(@RequestHeader(HEADER_USER_ID) Long ownerId,
                                    @PathVariable Long itemId,
                                    @RequestBody @Validated(Marker.Create.class) CommentDto commentDto) {
        return itemService.createComment(itemId, ownerId, commentDto);
    }
}
