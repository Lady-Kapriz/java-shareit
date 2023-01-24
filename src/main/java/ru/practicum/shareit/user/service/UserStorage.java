package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserStorage implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Collection<UserDto> getAllUsers() {
        return userRepository.getAllUsers().stream()
                .map(userMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto update(UserDto userDto, Long userId) {
        userDto.setId(userId);
        //User userOld = userRepository.getUserById(userId);
        User userNew = userMapper.mapToUser(userDto);
        userRepository.update(userNew);
        return userDto;
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userDto.setId(userRepository.create(user).getId());
        return userDto;
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userMapper.mapToUserDto(userRepository.getUserById(id));
    }
}
