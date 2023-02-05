package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.exception.UserAlreadyExistsException;
import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long userId) {
        validateId(userId);
        return userMapper.mapToUserDto(findById(userId));
    }

    @Transactional
    @Override
    public UserDto create(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        checkUniqueEmail(user);
        userDto.setId(userRepository.save(user).getId());
        return userDto;
    }

    @Transactional
    @Override
    public UserDto update(UserDto userDto, Long userId) {
        validateId(userId);
        userDto.setId(userId);
        User userOld = findById(userId);
        User userNew = userMapper.mapToUser(userDto);
        userNew = updateUserData(userNew, userOld);
        return userMapper.mapToUserDto(userNew);
    }

    @Transactional
    @Override
    public void delete(Long userId) {
        validateId(userId);
        findById(userId);
        userRepository.deleteById(userId);
    }

    private User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("Пользователь с id: %s не найден", userId)));
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

    private void checkUniqueEmail(User user) {
        List<String> userEmails = userRepository.findAll().stream()
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
