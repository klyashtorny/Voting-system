package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Vote;
import com.klyashtorny.graduation.repository.RestaurantRepository;
import com.klyashtorny.graduation.repository.UserRepository;
import com.klyashtorny.graduation.repository.VoteRepository;
import com.klyashtorny.graduation.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.klyashtorny.graduation.util.DateTimeUtil.TEST_TIME;
import static com.klyashtorny.graduation.util.DateTimeUtil.dateTimeToday;
import static com.klyashtorny.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;


    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository, UserRepository userRepository,
                           RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @CacheEvict(value = "vote", allEntries = true)
    @Override
    public Vote save(int userId, int restaurantId) {

        Vote vote = get(userId, dateTimeToday());
        if(vote == null) {
            vote = new Vote(null, dateTimeToday());
            vote.setRestaurant(restaurantRepository.getOne(restaurantId));
            vote.setUser(userRepository.getOne(userId));
            return voteRepository.save(vote);
        }
        if (dateTimeToday().compareTo(TEST_TIME) > 0) {
            throw new NotFoundException("Change restaurant to vote is after 11:00 then it is too late, vote can't be changed");
        }
        if(vote.getUser().getId() == userId && vote.getRestaurant().getId() == restaurantId) {
            voteRepository.deleteByIdAndUserId(vote.getId(), userId);
            return vote;
        }
        vote.setRestaurant(restaurantRepository.getOne(restaurantId));
        return voteRepository.save(vote);
    }

    @CacheEvict(value = "vote", allEntries = true)
    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(voteRepository.deleteByIdAndUserId(id, userId)!=0, id);
    }

    @Cacheable("vote")
    @Override
    public Vote get(int userId, LocalDateTime dateTime) {
       return voteRepository.getByUserIdAndDate(userId, dateTime).orElse(null);
    }

    @Cacheable("vote")
    @Override
    public List<Vote> getAllByDate(LocalDateTime date) {
        return voteRepository.findAllByVoteTime(date);
    }

    @Cacheable("vote")
    @Override
    public List<Vote> getAllByRestaurantAndDate(int restaurantId, LocalDateTime date) {
        return voteRepository.findAllByRestaurantIdAndDate(restaurantId, date);
    }

}
