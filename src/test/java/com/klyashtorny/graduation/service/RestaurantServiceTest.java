package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Restaurant;
import com.klyashtorny.graduation.util.exception.ErrorType;
import com.klyashtorny.graduation.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.klyashtorny.graduation.RestaurantTestData.*;
import static com.klyashtorny.graduation.UserTestData.*;
import static org.hamcrest.core.StringContains.containsString;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService service;

    @Test
    public void delete() throws Exception {
        service.delete(RESTAURANT_ID, ADMIN_1.getId());
        assertMatch(service.getAllByUser(ADMIN_1.getId()), RESTAURANT_2);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(RESTAURANT_ID, 1);
    }

    @Test
    public void create() throws Exception {
        Restaurant created = getCreated();
        service.create(created, ADMIN_1.getId());
        assertMatch(service.getAll(), RESTAURANT_3, RESTAURANT_4, created, RESTAURANT_1, RESTAURANT_2);
}

    @Test
    public void update() throws Exception {
        Restaurant updated = getUpdated();
        service.update(updated, ADMIN_1.getId());
        assertMatch(service.get(RESTAURANT_ID, ADMIN_1.getId()), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_FOUND.name()));
        thrown.expectMessage(containsString(NotFoundException.NOT_FOUND_EXCEPTION));
        thrown.expectMessage(containsString(String.valueOf(RESTAURANT_ID)));
        service.update(RESTAURANT_1, ADMIN_2.getId());
    }

    @Test
    public void get() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT_ID, ADMIN_1.getId());
        assertMatch(restaurant, RESTAURANT_1);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(RESTAURANT_ID, ADMIN_2.getId());
    }

    @Test
    public void testGetAllByUser() throws Exception {
        List<Restaurant> all = service.getAllByUser(ADMIN_1.getId());
        assertMatch(all, RESTAURANTS_ADMIN_1);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Restaurant> all = service.getAll();
        assertMatch(all, RESTAURANTS);
    }

}
