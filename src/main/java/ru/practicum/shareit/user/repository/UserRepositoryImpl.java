package ru.practicum.shareit.user.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
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
        users.remove(userId);
        log.info("Пользователь с id = {} удален", userId);
    }

    @Override
    public User getUserById(Long userId) {
        log.info("Поиск пользователя с id = {}", userId);
        return users.get(userId);
    }
}
