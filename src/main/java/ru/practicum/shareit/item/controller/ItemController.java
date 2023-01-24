package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private static final String HEADER_USER_ID = "X-Sharer-User-Id";

    @GetMapping
    public Collection<ItemDto> getAllItems(@RequestHeader(HEADER_USER_ID) Long ownerId) {
        return itemService.getAllItemsForOwner(ownerId);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }

    @GetMapping("/search")
    public Collection<ItemDto> findItems(@RequestParam String text) {
        return itemService.findItems(text);
    }

    @PostMapping
    public ItemDto createItem(@RequestHeader(HEADER_USER_ID) Long ownerId,
                              @Valid @RequestBody ItemDto itemDto) {
        return itemService.createItem(itemDto, ownerId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@PathVariable Long itemId,
                              @RequestHeader(HEADER_USER_ID) Long ownerId,
                              @Valid @RequestBody ItemDto itemDto) {
        return itemService.updateItem(itemDto, ownerId, itemId);
    }
}
