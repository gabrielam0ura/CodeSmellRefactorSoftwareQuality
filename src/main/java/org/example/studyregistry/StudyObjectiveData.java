package org.example.studyregistry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class StudyObjectiveData {
    private final Integer id;
    private final Integer priority;
    private final Integer practicedDays;
    private final int day;
    private final int month;
    private final int year;
    private final String name;
    private final String title;
    private final String description;
    private final String topic;
    private final String objectiveInOneLine;
    private final String objectiveFullDescription;
    private final String motivation;
    private final Double duration;
    private final boolean isActive;

    public LocalDateTime getStartDate() {
        return LocalDateTime.of(year, month, day, 0, 0);
    }
}