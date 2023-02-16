package ru.practicum.shareit.user.utils;

import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserDtoShort;
import ru.practicum.shareit.user.model.User;

public class UserUtil {
    private static final User USER_ONE = new User();
    private static final UserDto USER_DTO = new UserDto();
    private static final UserDto USER_DTO_TWO = new UserDto();
    private static final UserDtoShort USER_DTO_SHORT = new UserDtoShort();
    private static final Long USER_ID = 1L;
    private static final Long USER_ID_TWO = 2L;
    private static final Long USER_ID_THREE = 3L;
    private static final String USER_NAME = "Gregory Rasputin";
    private static final String USER_EMAIL = "gregory.rasputin@ya.ru";
    private static final String USER_NAME_TWO = "Vasily Chapaev";
    private static final String USER_EMAIL_TWO = "vasily.chapaev@ya.ru";
    private static final String USER_EMAIL_UPDATE = "vasily.chapaev@mydomen.ru";
    private static final String USER_DTO_JSON =
            "{\"id\": \"1\", \"name\": \"Gregory Rasputin\", \"email\": \"gregory.rasputin@ya.ru\"}";

    public static UserDto getUserDtoOne() {
        USER_DTO.setId(USER_ID);
        USER_DTO.setName(USER_NAME);
        USER_DTO.setEmail(USER_EMAIL);
        return USER_DTO;
    }

    public static UserDto getUserDtoTwo() {
        USER_DTO_TWO.setId(USER_ID_TWO);
        USER_DTO_TWO.setName(USER_NAME_TWO);
        USER_DTO_TWO.setEmail(USER_EMAIL_TWO);
        return USER_DTO_TWO;
    }

    public static UserDto getUserDtoUpdateName() {
        USER_DTO.setName(USER_EMAIL_UPDATE);
        return USER_DTO;
    }

    public static User getUserOne() {
        USER_ONE.setId(USER_ID);
        USER_ONE.setName(USER_NAME_TWO);
        USER_ONE.setEmail(USER_EMAIL_TWO);
        return USER_ONE;
    }

    public static String getUserDtoJson() {
        return USER_DTO_JSON;
    }

    public static UserDtoShort getUserDtoShort() {
        USER_DTO_SHORT.setId(USER_ID_THREE);
        return USER_DTO_SHORT;
    }
}