package org.example.studyplanner;

import java.time.LocalDateTime;

public class StartDateTime {
    private final int year;
    private final int month;
    private final int day;
    private final int hour;
    private final int minute;
    private final int second;

    public StartDateTime(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public LocalDateTime toLocalDateTime() {
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }
}
