package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Vote;
import com.klyashtorny.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteService {

    Vote save(int userId, int restaurantId);

    void delete(int id, int userId) throws NotFoundException;

    Vote get(int userId, LocalDateTime localDate);

    List<Vote> getAllByDate(LocalDateTime date);

    List<Vote> getAllByRestaurantAndDate(int restaurantId, LocalDateTime date);
}
