package ru.practicum.shareit.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserDto {
    private Long id;
    @NotBlank(message = "Имя пользователя не может быть пустым")
    private String name;
    @NotNull
    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Указан некорректный email")
    private String email;
}
