package com.klyashtorny.graduation.util;

import org.springframework.util.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final LocalDateTime TEST_TIME = LocalDateTime.of(2018, 01, 15, 11, 0);

    private static Clock clock = Clock.systemDefaultZone();

    private static ZoneId zoneId = ZoneId.systemDefault();

    public static LocalDate today() {
        return LocalDate.now(getClock());
    }

    public static LocalDateTime dateTimeToday() {
        return LocalDateTime.now(getClock());
    }

    private DateTimeUtil() {
    }

    public static <T extends Comparable<? super T>> boolean isBetween(T value, T start, T end) {
        return value.compareTo(start) >= 0 && value.compareTo(end) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate parseLocalDate(String str) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }

    public static LocalTime parseLocalTime(String str) {
        return StringUtils.isEmpty(str) ? null : LocalTime.parse(str);
    }

    public static void testFixedTime(LocalDateTime date) {
        clock = Clock.fixed(date.atZone(zoneId).toInstant(), zoneId);
    }

    private static Clock getClock() {
        return clock;
    }
}
