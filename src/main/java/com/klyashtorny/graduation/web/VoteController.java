package com.klyashtorny.graduation.web;

import com.klyashtorny.graduation.AuthorizedUser;
import com.klyashtorny.graduation.model.Vote;
import com.klyashtorny.graduation.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static com.klyashtorny.graduation.util.DateTimeUtil.dateTimeToday;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    static final String REST_URL = "/rest/restaurants";

    @GetMapping
    public List<Vote> getAll() {
        log.info("getAll");
        LocalDateTime dateTime = dateTimeToday();
        return service.getAllByDate(dateTime);
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
        Vote newVote = service.save(AuthorizedUser.id(), restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(VoteController.REST_URL + "/{id}/vote")
                .buildAndExpand(newVote.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(newVote);

    }

}
