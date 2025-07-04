package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class HabitCreationData {
    private final String name;
    private final String motivation;
    private final DedicationTime dedicationTime;
    private final StartDateTime startDateTime;
    private final boolean isConcluded;

    public HabitCreationData(String name, String motivation, DedicationTime dedicationTime,
                             StartDateTime startDateTime, boolean isConcluded) {
        this.name = name;
        this.motivation = motivation;
        this.dedicationTime = dedicationTime;
        this.startDateTime = startDateTime;
        this.isConcluded = isConcluded;
    }

    public Habit toHabit(int id) {
        LocalTime time = dedicationTime.toLocalTime();
        LocalDateTime start = startDateTime.toLocalDateTime();
        return new Habit(name, motivation, time, id, start, isConcluded);
    }

    public String getName() { return name; }
    public String getMotivation() { return motivation; }
    public boolean isConcluded() { return isConcluded; }
}
