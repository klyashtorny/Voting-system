package com.klyashtorny.graduation.web;

import com.klyashtorny.graduation.model.Dish;
import com.klyashtorny.graduation.service.DishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.klyashtorny.graduation.util.ValidationUtil.assureIdConsistent;
import static com.klyashtorny.graduation.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(DishController.REST_URL)
public class DishController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    static final String REST_URL = "/rest/profile/restaurants/menu";

    @GetMapping("/{menuId}/dish/{dishId}")
    @Secured("ROLE_ADMIN")
    public Dish get(@PathVariable("menuId") int menuId, @PathVariable("dishId") int dishId){
        log.info("get dish by menu {} and dish {}", menuId, dishId);
        return service.get(dishId, menuId);
    }

    @GetMapping("/{menuId}/dishes")
    public List<Dish> getAllByMenu(@PathVariable("menuId") int menuId) {
        log.info("getAll dishes by menu {}", menuId);
        return service.getAllByMenu(menuId);
    }

    @PostMapping(value = "/{menuId}/dish",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Dish> createMenu(@RequestBody Dish dish, @PathVariable("menuId") int menuId) {
        checkNew(dish);
        log.info("create Dish {} for menu {}", dish, menuId);
        Dish created = service.create(dish, menuId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{menuId}/dish")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{menuId}/dish/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public void update(@RequestBody Dish dish, @PathVariable("menuId") int menuId, @PathVariable("dishId") int dishId) {
        assureIdConsistent(dish, dishId);
        log.info("update dish {} for menu {}", dish, menuId );
        service.update(dish, menuId);
    }

    @DeleteMapping("/{menuId}/dish/{dishId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable("menuId") int menuId, @PathVariable("dishId") int dishId) {
        log.info("delete dish {} for menu {}", dishId, menuId);
        service.delete(dishId, menuId);
    }

}
