package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Restaurant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.klyashtorny.graduation.RestaurantTestData.*;
import static com.klyashtorny.graduation.UserTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService service;

    @Test
    public void testGetAll() throws Exception {
        List<Restaurant> all = service.getAll(ADMIN_ID);
        assertMatch(all, RESTAURANTS);
    }

}
