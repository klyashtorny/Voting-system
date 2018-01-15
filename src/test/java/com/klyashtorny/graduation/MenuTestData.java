package com.klyashtorny.graduation;

import com.klyashtorny.graduation.model.Menu;

import java.time.LocalDate;
import java.util.Arrays;

import static com.klyashtorny.graduation.model.AbstractBaseEntity.START_SEQ;
import static com.klyashtorny.graduation.util.DateTimeUtil.today;
import static org.assertj.core.api.Assertions.assertThat;


public class MenuTestData {

    public static final int MENU_ID = START_SEQ + 12;


    public static final Menu MENU_1 =  new Menu(MENU_ID, today(),"Launch menu Astoria");
    public static final Menu MENU_2 =  new Menu(MENU_ID + 1, LocalDate.of(2018, 01, 05), "Launch menu Astoria");
    public static final Menu MENU_3 = new Menu(MENU_ID + 2, LocalDate.of(2018, 01, 06), "Launch menu Gold Fish" );


    public static Menu getCreated() {
        return new Menu(MENU_ID + 16, today(),"New Menu");
    }

    public static Menu getUpdated() {
        return new Menu(MENU_ID, today(),  "Menu update");
    }

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dishes", "restaurant");
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dishes", "restaurant").isEqualTo(expected);
    }
}
