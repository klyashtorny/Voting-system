package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Menu;
import com.klyashtorny.graduation.model.Vote;
import com.klyashtorny.graduation.repository.MenuRepository;
import com.klyashtorny.graduation.repository.RestaurantRepository;
import com.klyashtorny.graduation.repository.UserRepository;
import com.klyashtorny.graduation.repository.VoteRepository;
import com.klyashtorny.graduation.util.DateTimeUtil;
import com.klyashtorny.graduation.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.klyashtorny.graduation.util.DateTimeUtil.TEST_TIME;
import static com.klyashtorny.graduation.util.DateTimeUtil.dateTimeToday;
import static com.klyashtorny.graduation.util.DateTimeUtil.today;
import static com.klyashtorny.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    private final MenuRepository menuRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository, UserRepository userRepository,
                           RestaurantRepository restaurantRepository, MenuRepository menuRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public Vote save(int userId, int restaurantId) {
        Menu menu = menuRepository.getWithDishes(restaurantId, today()).orElse(null);
        if(menu == null) throw new  NotFoundException("Menu is not existing");
        if(menu.getDishes().size()==0) throw new  NotFoundException("Dish list for menu is empty");
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

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(voteRepository.deleteByIdAndUserId(id, userId)!=0, id);
    }

    @Override
    public Vote get(int userId, LocalDateTime dateTime) {
       return voteRepository.getByUserIdAndDate(userId, dateTime).orElse(null);
    }

    @Override
    public List<Vote> getAllByDate(LocalDateTime date) {
        return voteRepository.findAllByVoteTime(date);
    }


    public List<Vote> getAllByRestaurantAndDate(int restaurantId, LocalDateTime date) {
        return voteRepository.findAllByRestaurantIdAndDate(restaurantId, date);
    }

}
