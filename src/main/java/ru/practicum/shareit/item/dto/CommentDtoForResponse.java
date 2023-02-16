package ru.practicum.shareit.item.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class CommentDtoForResponse {
    Long id;
    String text;
    String authorName;
    LocalDateTime created;
}
