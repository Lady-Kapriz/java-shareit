package ru.practicum.shareit.booking.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import ru.practicum.shareit.markers.Marker;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingCreateDto {
    @NotNull(groups = {Marker.Create.class},
            message = "id не может быть пустым")
    private Long itemId;

    @NotNull(groups = {Marker.Create.class},
            message = "Дата не может быть пустой")
    @FutureOrPresent(groups = {Marker.Create.class},
            message = "Дата не может быть в прошлом")
    private LocalDateTime start;

    @NotNull(groups = {Marker.Create.class},
            message = "Дата не может быть пустой")
    @Future(groups = {Marker.Create.class},
            message = "Дата не может быть в прошлом и равна текущей")
    private LocalDateTime end;

    @JsonIgnore
    private Long bookerId;
}
