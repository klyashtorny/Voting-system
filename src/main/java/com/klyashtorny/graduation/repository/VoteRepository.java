package com.klyashtorny.graduation.repository;

import com.klyashtorny.graduation.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Override
    @Transactional
    Vote save(Vote item);

    @Transactional
    @Modifying
    int deleteByIdAndUserId(int id, int userId);

    default Optional<Vote> getByUserIdAndDate(int userId, LocalDateTime date){
       return getVoteByUserIdAndVoteTimeBetween(userId, date.toLocalDate().atStartOfDay(), date.plusDays(1).toLocalDate().atStartOfDay());
    }

    @Query("SELECT v FROM Vote v WHERE v.user.id =?1 AND v.voteTime BETWEEN ?2 AND ?3")
    Optional<Vote> getVoteByUserIdAndVoteTimeBetween(int userId, LocalDateTime from, LocalDateTime to);

    default List<Vote> findAllByVoteTime(LocalDateTime date) {
       return findAllByVoteTimeBetween(date.toLocalDate().atStartOfDay(), date.plusDays(1).toLocalDate().atStartOfDay() );
    }

    @Query("SELECT v FROM Vote v WHERE v.voteTime BETWEEN ?1 AND ?2")
    List<Vote> findAllByVoteTimeBetween(LocalDateTime from, LocalDateTime to);

    List<Vote> findAllByRestaurantId(int restaurantId);

    default List<Vote> findAllByRestaurantIdAndDate(int restaurantId, LocalDateTime date){
        return findAllByRestaurantIdAndVoteTimeBetween(restaurantId, date.toLocalDate().atStartOfDay(), date.plusDays(1).toLocalDate().atStartOfDay());
    }

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=?1 AND v.voteTime BETWEEN ?2 AND ?3")
    List<Vote> findAllByRestaurantIdAndVoteTimeBetween(int restaurantId, LocalDateTime from, LocalDateTime to);
}
