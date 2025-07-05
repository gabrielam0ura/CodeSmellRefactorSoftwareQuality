package org.example.studyregistry;

public record WeekSetupParameters(
        String planName,
        String objectiveTitle,
        String objectiveDescription,
        String materialTopic,
        String materialFormat,
        String goal,
        String reminderTitle,
        String reminderDescription,
        String mainTaskTitle,
        String mainHabit,
        String mainCardStudy
) {}
