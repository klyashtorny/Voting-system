package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Restaurant;
import com.klyashtorny.graduation.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.klyashtorny.graduation.RestaurantTestData.*;
import static com.klyashtorny.graduation.UserTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService service;

    @Test
    public void delete() throws Exception {
        service.delete(RESTAURANT_ID, ADMIN_ID);
        assertMatch(service.getAllByUser(ADMIN_ID), RESTAURANT_2);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(RESTAURANT_ID, 1);
    }

    @Test
    public void create() throws Exception {
        Restaurant created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(), RESTAURANT_1, RESTAURANT_2, created, RESTAURANT_3, RESTAURANT_4);
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = getUpdated();
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(RESTAURANT_ID, ADMIN_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + RESTAURANT_ID);
        service.update(RESTAURANT_1, USER_ID);
    }

    @Test
    public void get() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT_ID + 1, ADMIN_ID);
        assertMatch(restaurant, RESTAURANT_2);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(RESTAURANT_ID, USER_ID);
    }

    @Test
    public void testGetAllByUser() throws Exception {
        List<Restaurant> all = service.getAllByUser(ADMIN_ID);
        assertMatch(all, RESTAURANTS_ADMIN);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Restaurant> all = service.getAll();
        assertMatch(all, RESTAURANTS);
    }

}
