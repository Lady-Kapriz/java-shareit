package ru.practicum.shareit.user.repository;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();
    private Long userIdGenerate = 0L;

    @Override
    public Collection<User> getAllUsers() {
        return users.values();
    }

    @Override
    public User update(User user) {
        validateId(user.getId());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User create(User user) {
        user.setId(++userIdGenerate);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void delete(Long userId) {
        validateId(userId);
        users.remove(userId);
    }

    @Override
    public User getUserById(Long userId) {
        validateId(userId);
        return users.get(userId);
    }

    private void validateId(Long userId) {
        if (userId == null) {
            throw new ValidationException("id не может быть пустым");
        }
        if (userId <= 0) {
            throw new ValidationException("id должн быть больше нуля");
        }
        if (!users.containsKey(userId)) {
            throw new UserNotFoundException(String.format("Пользователь с id = %d не найден", userId));
        }
    }
}
