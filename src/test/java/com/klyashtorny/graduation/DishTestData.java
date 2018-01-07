package com.klyashtorny.graduation;

import com.klyashtorny.graduation.model.Dish;

import java.util.Arrays;
import java.util.List;

import static com.klyashtorny.graduation.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class DishTestData {

    public static final int DISH_ID = START_SEQ + 9;
    
    public static final Dish DISH_1 =  new Dish(DISH_ID, "Fresh", 100);
    public static final Dish DISH_2 = new Dish(DISH_ID + 1, "Soup", 300);
    public static final Dish DISH_3 = new Dish(DISH_ID + 2, "Main of the day", 500);
    public static final Dish DISH_4 = new Dish(DISH_ID + 3, "Starter", 100);
    public static final Dish DISH_5 = new Dish(DISH_ID + 4, "Fish", 400);
    public static final Dish DISH_6 = new Dish(DISH_ID + 5, "Salad", 300);

    public static final List<Dish> DISHES_ASTORIA = Arrays.asList(DISH_1, DISH_2 ,DISH_3);
    public static final List<Dish> DISHES_GOLDFISH = Arrays.asList(DISH_4, DISH_5, DISH_6);

    public static Dish getCreated() {
        return new Dish(DISH_ID + 6, "New Dish", 100);
    }

    public static Dish getUpdated() {
        return new Dish(DISH_ID, "Dish update", 150);
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menu");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("menu").isEqualTo(expected);
    }
}
