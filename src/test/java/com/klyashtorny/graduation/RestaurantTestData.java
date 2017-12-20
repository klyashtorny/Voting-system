package com.klyashtorny.graduation;

import com.klyashtorny.graduation.model.Restaurant;
import com.sun.org.apache.regexp.internal.RE;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static com.klyashtorny.graduation.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final int RESTAURANT_ID = START_SEQ + 2;


    public static final Restaurant RESTAURANT_1 =  new Restaurant(RESTAURANT_ID, "Astoria", "Набережная фонтанки" );
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID + 1, "Gold Fish", "Зеленая 30" );
    public static final Restaurant RESTAURANT_3 =  new Restaurant(RESTAURANT_ID + 2,  "Steakhouse", "Дворцовая площадь");
    public static final Restaurant RESTAURANT_4 =  new Restaurant(RESTAURANT_ID + 3,  "VasilyOstrovsky", "Васильевский остров");

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT_1, RESTAURANT_2 ,RESTAURANT_3, RESTAURANT_4);
    public static final List<Restaurant> RESTAURANTS_ADMIN = Arrays.asList(RESTAURANT_1, RESTAURANT_2);

    public static Restaurant getCreated() {
        return new Restaurant(RESTAURANT_ID + 4, "New Restaurant", "Новый адрес" );
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "Astoria Обновленная", "Набережная фонтанки" );
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "registered");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user", "registered").isEqualTo(expected);
    }

}
