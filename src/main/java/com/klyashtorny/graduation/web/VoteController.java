package com.klyashtorny.graduation.web;

import com.klyashtorny.graduation.AuthorizedUser;
import com.klyashtorny.graduation.model.Menu;
import com.klyashtorny.graduation.model.Vote;
import com.klyashtorny.graduation.service.MenuService;
import com.klyashtorny.graduation.service.VoteService;
import com.klyashtorny.graduation.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.klyashtorny.graduation.util.DateTimeUtil.dateTimeToday;
import static com.klyashtorny.graduation.util.DateTimeUtil.today;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    @Autowired
    private MenuService menuService;

    static final String REST_URL = "/rest/restaurants";

    @GetMapping
    public List<Vote> getAll() {
        log.info("getAll");
        LocalDateTime dateTime = dateTimeToday();
        return service.getAllByDate(dateTime);
    }

    @GetMapping("/history")
    public List<Vote> getAllByDate(@RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("getAll");
        return service.getAllByDate(date.atStartOfDay());
    }

    @GetMapping("/{id}/votes")
    public List<Vote> getAllByRestaurant(@PathVariable("id") int restaurantId) {
        log.info("getAllByRestaurant" + restaurantId);
        LocalDateTime dateTime = dateTimeToday();
        return service.getAllByRestaurantAndDate(restaurantId,dateTime);
    }

    @PostMapping("/{id}/vote")
    public ResponseEntity<Vote> save(@PathVariable("id") int restaurantId) throws Exception {
        log.info("create vote {} for restaurantId {}", AuthorizedUser.get().getUsername(), restaurantId);
        Menu menu = menuService.getActualWithDishes(restaurantId, today());
        if(menu == null) throw new NotFoundException("Menu is not existing");
        if(menu.getDishes().size()==0) throw new  NotFoundException("Dish list for menu is empty");

        Vote saveVote = service.save(AuthorizedUser.id(), restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(VoteController.REST_URL + "/{id}/vote")
                .buildAndExpand(saveVote.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(saveVote);

    }

}
