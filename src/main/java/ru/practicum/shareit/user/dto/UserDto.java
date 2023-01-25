package ru.practicum.shareit.user.dto;

import lombok.*;
import ru.practicum.shareit.markers.Marker;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserDto {
    private Long id;
    @NotBlank(groups = {Marker.Create.class},
            message = "Имя пользователя не может быть пустым")
    private String name;
    @NotBlank(groups = {Marker.Create.class},
            message = "Email не может быть пустым")
    @Email(groups = {Marker.Create.class, Marker.Update.class},
            message = "Указан некорректный email")
    private String email;
}
