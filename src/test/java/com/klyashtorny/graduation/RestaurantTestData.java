package com.klyashtorny.graduation;

import com.klyashtorny.graduation.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static com.klyashtorny.graduation.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final int RESTAURANT_ID = START_SEQ + 2;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID, "Gold Fish", "Зеленая 30" );
    public static final Restaurant RESTAURANT_2 =  new Restaurant(RESTAURANT_ID + 1, "Astoria", "Набережная фонтанки" );

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT_2, RESTAURANT_1);

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
