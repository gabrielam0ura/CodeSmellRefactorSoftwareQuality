package org.example.studyplanner;

import java.time.LocalTime;

public class DedicationTime {
    private final int hours;
    private final int minutes;

    public DedicationTime(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public LocalTime toLocalTime() {
        return LocalTime.of(hours, minutes);
    }
}
