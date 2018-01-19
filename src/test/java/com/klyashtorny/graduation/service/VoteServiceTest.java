package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Vote;
import com.klyashtorny.graduation.util.DateTimeUtil;
import com.klyashtorny.graduation.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static com.klyashtorny.graduation.RestaurantTestData.*;
import static com.klyashtorny.graduation.UserTestData.*;
import static com.klyashtorny.graduation.VoteTestData.*;
import static com.klyashtorny.graduation.VoteTestData.getUpdated;
import static com.klyashtorny.graduation.VoteTestData.getCreated;
import static com.klyashtorny.graduation.util.DateTimeUtil.today;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoteService voteService;

    @Test
    public void testGetAllByDate() throws Exception {
        List<Vote> all = voteService.getAllByDate(LocalDateTime.of(2018, 01, 6, 8, 0));
        assertMatch(all, VOTE_4, VOTE_5);
    }

    @Test
    public void testGetAllByRestaurant() throws Exception {
        List<Vote> all = voteService.getAllByRestaurantAndDate(RESTAURANT_4.getId(), LocalDateTime.of(2018, 01, 7, 9, 00, 00));
        assertMatch(all, VOTE_6, VOTE_7);
    }

    @Test
    public void save() throws Exception {
        Vote created = getCreated();
        voteService.save(USER_4.getId(), RESTAURANT_1.getId());
        assertMatch(voteService.getAllByRestaurantAndDate(RESTAURANT_ID, LocalDateTime.now()), VOTE_1, VOTE_2, VOTE_3, created);
    }

    @Test
    public void updateBefore1100() throws Exception {
        DateTimeUtil.testFixedTime(today().atTime(9, 20));
        Vote updated = getUpdated();
        voteService.save(ADMIN_1.getId(), RESTAURANT_2.getId());
        voteService.save(ADMIN_2.getId(), RESTAURANT_2.getId());
        voteService.save(USER_1.getId(), RESTAURANT_2.getId());
        assertMatch(voteService.getAllByRestaurantAndDate(RESTAURANT_2.getId(), LocalDateTime.now()), updated, VOTE_2, VOTE_3);
    }

    @Test
    public void reVoteDeleted() throws Exception {
        DateTimeUtil.testFixedTime(today().atTime(9, 20));
        voteService.save(USER_1.getId(), RESTAURANT_1.getId());
        assertMatch(voteService.getAllByRestaurantAndDate(RESTAURANT_1.getId(), LocalDateTime.now()), VOTE_1, VOTE_2);
    }

    @Test
    public void updateAfter1100() throws Exception {
        DateTimeUtil.testFixedTime(today().atTime(11, 20));
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Change restaurant to vote is after 11:00 then it is too late, vote can't be changed");
        voteService.save(USER_1.getId(), RESTAURANT_1.getId());
    }

}
