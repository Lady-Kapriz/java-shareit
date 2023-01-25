package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.UserAlreadyExistsException;
import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.Collection;
import java.util.List;
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
        validateId(userId);
        userDto.setId(userId);
        User userOld = findUserById(userId);
        User userNew = userMapper.mapToUser(userDto);
        userNew = updateUserData(userNew, userOld);
        return userMapper.mapToUserDto(userRepository.update(userNew));
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        checkUniqueEmail(user);
        userDto.setId(userRepository.create(user).getId());
        return userDto;
    }

    @Override
    public void delete(Long userId) {
        validateId(userId);
        userRepository.delete(userId);
    }

    @Override
    public UserDto getUserById(Long userId) {
        validateId(userId);
        return userMapper.mapToUserDto(userRepository.getUserById(userId));
    }

    private User updateUserData(User userNew, User userOld) {
        if (userNew.getName() != null) {
            userOld.setName(userNew.getName());
        }
        if (userNew.getEmail() != null) {
            checkUniqueEmail(userNew);
            userOld.setEmail(userNew.getEmail());
        }
        return userOld;
    }

    private User findUserById(Long userId) {
        validateId(userId);
        User user = userRepository.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException(
                    String.format("Пользователь с id = %s не найден", userId));
        }
        return user;
    }

    private void checkUniqueEmail(User user) {
        List<String> userEmails = userRepository.getAllUsers().stream()
                .map(User::getEmail)
                .collect(Collectors.toList());
        if (userEmails.contains(user.getEmail())) {
            throw new UserAlreadyExistsException(
                    "Пользователь с email = " + user.getEmail() + " уже существует");
        }
    }

    private void validateId(Long userId) {
        if (userId == null) {
            throw new ValidationException("id не может быть пустым");
        }
        if (userId <= 0) {
            throw new ValidationException("id должен быть больше нуля");
        }
    }
}
