package com.klyashtorny.graduation;

import com.klyashtorny.graduation.model.Role;
import com.klyashtorny.graduation.model.User;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static com.klyashtorny.graduation.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 7;

    public static final User ADMIN_1 = new User(USER_ID, "Admin1", "admin1@gmail.com", "admin1", Role.ROLE_ADMIN);
    public static final User ADMIN_2 = new User(USER_ID + 1, "Admin2", "admin2@gmail.com", "admin2", Role.ROLE_ADMIN);
    public static final User USER_1 = new User(USER_ID + 2, "User1", "user1@yandex.ru", "password1", Role.ROLE_USER);
    public static final User USER_2 = new User(USER_ID + 3, "User2", "user2@yandex.ru", "password2", Role.ROLE_USER);
    public static final User USER_3 = new User(USER_ID + 4, "User3", "user3@yandex.ru", "password3", Role.ROLE_USER);
    public static final User USER_4 = new User(USER_ID + 5, "User4", "user4@yandex.ru", "password4", Role.ROLE_USER);
    public static final User USER_5 = new User(USER_ID + 6, "User5", "user5@yandex.ru", "password5", Role.ROLE_USER);
    public static final User USER_6 = new User(USER_ID + 7, "User6", "user6@yandex.ru", "password6", Role.ROLE_USER);

    public static final List<User> USER_LIST = Arrays.asList(ADMIN_1, ADMIN_2,  USER_1, USER_2, USER_3, USER_4, USER_5, USER_6);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "password").isEqualTo(expected);
    }
}
