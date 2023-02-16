package ru.practicum.shareit.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDtoForRequest {
    Long id;
    String name;
    String description;
    Boolean available;
    Long requestId;
}
