package ru.practicum.shareit.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.markers.Marker;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Collection<UserDto> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public UserDto getById(@PathVariable Long userId) {
        return userService.getById(userId);
    }

    @PatchMapping("/{userId}")
    public UserDto updateUserById(@RequestBody @Validated(Marker.Update.class) UserDto userDto,
                                  @PathVariable Long userId) {
        return userService.update(userDto, userId);
    }

    @PostMapping
    public UserDto createUser(@RequestBody @Validated(Marker.Create.class) UserDto userDto) {
        return userService.create(userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.delete(userId);
    }


}
