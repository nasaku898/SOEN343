package com.soen343.shs.dal.service.helpers;

import com.soen343.shs.dal.model.User;
import com.soen343.shs.dto.UserDTO;

import static com.soen343.shs.dal.model.UserRole.PARENT;

public class UserTestHelper {
    public static final String USERNAME = "someDude";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "someemail@email.com";
    public static final String FIRST_NAME = "Joe";
    public static final String LAST_NAME = "Smith";

    public static User createUser() {
        return User.builder()
                .username(USERNAME)
                .email(EMAIL)
                .firstName("Daddy")
                .lastName("Chill")
                .password("password")
                .role(PARENT)
                .build();
    }

    public static UserDTO createUserDTO() {
        return UserDTO.builder()
                .username(USERNAME)
                .email(EMAIL)
                .firstName("Daddy")
                .lastName("Chill")
                .role(PARENT)
                .build();
    }
}
