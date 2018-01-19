package com.klyashtorny.graduation.web;

import com.klyashtorny.graduation.AuthorizedUser;
import com.klyashtorny.graduation.model.Restaurant;
import com.klyashtorny.graduation.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.klyashtorny.graduation.util.ValidationUtil.assureIdConsistent;
import static com.klyashtorny.graduation.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    static final String REST_URL = "/rest/profile/restaurants";

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping("/admin/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        int userId = AuthorizedUser.id();
        log.info("get restaurant {} for user {}", id, userId);
        return service.get(id, userId);
    }

    @GetMapping("/admin/{id}/all")
    public List<Restaurant> getAllByUser(@PathVariable("id") int id) {
        int userId = AuthorizedUser.id();
        log.info("get restaurant {} for user {}", id, userId);
        return service.getAllByUser(userId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        int userId = AuthorizedUser.id();
        assureIdConsistent(restaurant, id);
        log.info("update restaurant {} for user {}", restaurant, userId);
        service.update(restaurant, userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable("id") int id) {
        int userId = AuthorizedUser.id();
        log.info("delete restaurant {} for user {}", id, userId);
        service.delete(id, userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        int userId = AuthorizedUser.id();
        checkNew(restaurant);
        log.info("create restaurant {} for user {}", restaurant, userId);
        Restaurant created = service.create(restaurant, AuthorizedUser.id());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
