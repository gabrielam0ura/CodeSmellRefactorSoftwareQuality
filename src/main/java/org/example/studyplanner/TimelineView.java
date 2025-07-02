package org.example.studyplanner;

import java.time.LocalDateTime;
import java.util.List;

public class TimelineView {

    public String habitDateViewAll(HabitTracker ht) {
        List<Habit> habits = ht.getHabits();
        StringBuilder response = new StringBuilder();
        for (Habit habit : habits) {
            appendHabitWithRecords(ht, response, habit);
        }
        return response.toString();
    }

    private void appendHabitWithRecords(HabitTracker ht, StringBuilder response, Habit habit) {
        response.append("[ Habit: ")
                .append(habit.getName())
                .append(". Records: ");
        appendFormattedRecords(ht, response, habit);
        response.append("]");
    }

    private void appendFormattedRecords(HabitTracker ht, StringBuilder response, Habit habit) {
        List<LocalDateTime> records = ht.getHabitRecords(habit.getId());
        for (LocalDateTime record : records) {
            response.append(ht.formatHabitDate(record)).append(", ");
        }
    }
}
