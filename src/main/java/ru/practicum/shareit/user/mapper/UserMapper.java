package ru.practicum.shareit.user.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapToUser(UserDto userDto);

    UserDto mapToUserDto(User user);
}
