package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.Collection;

public interface UserService {
    Collection<UserDto> getAllUsers();

    UserDto update(UserDto userDto, Long userId);

    UserDto create(UserDto userDto);

    void delete(Long id);

    UserDto getUserById(Long id);
}
