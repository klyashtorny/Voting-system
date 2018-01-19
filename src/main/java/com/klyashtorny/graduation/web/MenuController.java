package com.klyashtorny.graduation.web;

import com.klyashtorny.graduation.model.Menu;
import com.klyashtorny.graduation.service.MenuService;
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
import java.time.LocalDate;
import java.util.List;

import static com.klyashtorny.graduation.util.DateTimeUtil.today;
import static com.klyashtorny.graduation.util.ValidationUtil.assureIdConsistent;
import static com.klyashtorny.graduation.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService service;

    static final String REST_URL = "/rest/profile/restaurants";

    @GetMapping("/{restaurantId}/menus")
    @Secured("ROLE_ADMIN")
    public List<Menu> getAllByRestaurant(@PathVariable("restaurantId") int restaurantId) {
        log.info("getAll menu by restaurant {}", restaurantId);
        return service.getAllByRestaurant(restaurantId);
    }

    @GetMapping("/{restaurantId}/menu")
    public Menu getActualWithDishes(@PathVariable("restaurantId") int restaurantId){
        log.info("getActualWithDishes menu by restaurant {}", restaurantId);
        LocalDate date = today();
        return service.getActualWithDishes(restaurantId, date);
    }

    @GetMapping("/{restaurantId}/menu/{menuId}")
    @Secured("ROLE_ADMIN")
    public Menu get(@PathVariable("restaurantId") int restaurantId, @PathVariable("menuId") int menuId){
        log.info("get menu by restaurant {} and menu {}", restaurantId, menuId);
        return service.get(menuId, restaurantId);
    }

    @PostMapping(value = "/{restaurantId}/menu",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu, @PathVariable("restaurantId") int restaurantId) {
        checkNew(menu);
        log.info("create Menu {} for restaurant {}", menu, restaurantId);
        Menu created = service.create(menu, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}/menu")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurantId}/menu/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public void update(@RequestBody Menu menu,
                       @PathVariable("restaurantId") int restaurantId,
                       @PathVariable("menuId") int menuId) {
        assureIdConsistent(menu, menuId);
        log.info("update menu {} for restaurant {}", menu, restaurantId);
        service.update(menu, restaurantId);
    }

    @DeleteMapping("/{restaurantId}/menu/{menuId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable("restaurantId") int restaurantId, @PathVariable("menuId") int menuId) {
        log.info("delete menu {} for restaurant {}", menuId, restaurantId);
        service.delete(menuId, restaurantId);
    }

}
