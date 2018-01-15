package com.klyashtorny.graduation;

import com.klyashtorny.graduation.model.Vote;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.klyashtorny.graduation.model.AbstractBaseEntity.START_SEQ;
import static com.klyashtorny.graduation.util.DateTimeUtil.today;
import static org.assertj.core.api.Assertions.assertThat;

public class VoteTestData {

    public static final int VOTE_ID = START_SEQ + 21;

    public static final Vote VOTE_1 = new Vote(VOTE_ID, LocalDateTime.now());
    public static final Vote VOTE_2 = new Vote(VOTE_ID + 1, LocalDateTime.now());
    public static final Vote VOTE_3 = new Vote(VOTE_ID + 2, LocalDateTime.now());

    public static final Vote VOTE_4 = new Vote(VOTE_ID + 3, LocalDateTime.of(2018, 01, 6, 9, 00));
    public static final Vote VOTE_5 = new Vote(VOTE_ID + 4, LocalDateTime.of(2018, 01, 6, 8, 40));
    public static final Vote VOTE_6 = new Vote(VOTE_ID + 5, LocalDateTime.of(2018, 01, 7, 10, 40));
    public static final Vote VOTE_7 = new Vote(VOTE_ID + 6, LocalDateTime.of(2018, 01, 7, 10, 55));

    public static final List<Vote> VOTE_ASTORIA = Arrays.asList(VOTE_1, VOTE_2 ,VOTE_3);
    public static final List<Vote> VOTE_GOLDFISH = Arrays.asList(VOTE_4, VOTE_5, VOTE_6);

    public static Vote getCreated() {
        return new Vote(VOTE_ID+7, today().atTime(9, 00));
    }

    public static Vote getUpdated() {
        return new Vote(VOTE_ID, today().atTime(10, 00));
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "user", "voteTime");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "user", "voteTime").isEqualTo(expected);
    }
}
