package ru.practicum.shareit.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Collection<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PatchMapping("/{userId}")
    public UserDto updateUserById(@Valid @RequestBody UserDto userDto,
                                  @PathVariable Long userId) {
        return userService.update(userDto, userId);
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.delete(userId);
    }


}
