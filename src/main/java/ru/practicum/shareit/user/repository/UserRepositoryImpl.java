package ru.practicum.shareit.user.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();
    private Long userIdGenerate = 0L;

    @Override
    public Collection<User> getAllUsers() {
        log.info("Запрос списка пользователей");
        return users.values();
    }

    @Override
    public User update(User user) {
        validateId(user.getId());
        users.put(user.getId(), user);
        log.info("Обновлен пользователь с id = {}", user.getId());
        return user;
    }

    @Override
    public User create(User user) {
        user.setId(++userIdGenerate);
        users.put(user.getId(), user);
        log.info("Создан пользователь с id = {}", user.getId());
        return user;
    }

    @Override
    public void delete(Long userId) {
        validateId(userId);
        users.remove(userId);
        log.info("Пользователь с id = {} удален", userId);
    }

    @Override
    public User getUserById(Long userId) {
        validateId(userId);
        log.info("Поиск пользователя с id = {}", userId);
        return users.get(userId);
    }

    private void validateId(Long userId) {
        if (!users.containsKey(userId)) {
            throw new UserNotFoundException(String.format("Пользователь с id = %d не найден", userId));
        }
    }
}
