package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HabitTracker {
    private List<Habit> habits;
    private Map<Integer, List<LocalDateTime>> tracker;
    private Integer nextId;

    private static HabitTracker instance;

    public static HabitTracker getHabitTracker() {
        if (instance == null) {
            instance = new HabitTracker();
        }
        return instance;
    }

    private HabitTracker(){
        this.habits = new ArrayList<>();
        this.tracker = new HashMap<>();
        this.nextId = 1;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        for (Habit habit : habits) {
            response.append(habit.toString()).append(", ");
        }
        return "Habits: " + response.toString();
    }

    public Habit getHabitById(Integer id){
        return this.habits.stream()
                .filter(habit -> Objects.equals(habit.getId(), id))
                .findFirst().orElse(null);
    }

    public List<Habit> getHabits() {
        return this.habits;
    }

    public String formatHabitDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return date.format(formatter);
    }

    public List<Integer> getTrackerKeys(){
        return this.tracker.keySet().stream().toList();
    }

    public int addHabit(HabitCreationData data) {
        Habit habit = data.toHabit(this.nextId);
        this.habits.add(habit);
        this.tracker.put(nextId, new ArrayList<>());
        return nextId++;
    }

    public int handleAddHabitAdapter(List<String> stringProperties, List<Integer> intProperties, boolean isConcluded){
        DedicationTime dedication = new DedicationTime(intProperties.get(1), intProperties.get(0));
        StartDateTime startDateTime = new StartDateTime(
                intProperties.get(2), intProperties.get(3), intProperties.get(4),
                intProperties.get(5), intProperties.get(6), intProperties.get(7)
        );
        HabitCreationData data = new HabitCreationData(
                stringProperties.get(0), stringProperties.get(1), dedication, startDateTime, isConcluded
        );
        return addHabit(data);
    }

    public String getFormattedHabitSummary(Habit habit) {
        StringBuilder response = new StringBuilder();
        response.append("[ Habit: ")
                .append(habit.getName())
                .append(". Records: ");

        List<LocalDateTime> records = getHabitRecords(habit.getId());
        for (LocalDateTime record : records) {
            response.append(formatHabitDate(record)).append(", ");
        }

        if (!records.isEmpty()) {
            response.setLength(response.length() - 2); // remove trailing comma and space
        }

        response.append("]\n");
        return response.toString();
    }

    public int addHabit(String name, String motivation) {

        Habit habit = new Habit(name, motivation, this.nextId);
        this.habits.add(habit);
        int response = nextId;
        this.tracker.put(nextId, new ArrayList<>());
        this.nextId++;
        return response;
    }

    public void addHabitRecord(Integer id){
        tracker.get(id).add(LocalDateTime.now());
    }

    public void toggleConcludeHabit(Integer id) {
        for (Habit habit : this.habits) {
            if (habit.getId().equals(id)) {
                habit.setIsConcluded(!habit.getIsConcluded());
            }
        }
    }

    public void removeHabit(Integer id) {
        this.habits.removeIf(habit -> habit.getId().equals(id));
        this.tracker.remove(id);
    }

    public String getTimelineView() {
        StringBuilder response = new StringBuilder();

        for (Habit habit : this.habits) {
            List<LocalDateTime> records = this.getHabitRecords(habit.getId());
            response.append(habit.formatWithRecords(records, this::formatHabitDate));
        }

        return response.toString();
    }


    public List<LocalDateTime> getHabitRecords(Integer id) {
        return this.tracker.get(id);
    }

    public List<String> searchInHabits(String search){
        List<String> habits = new ArrayList<>();
        for (Habit habit : this.habits) {
            if (habit.getName().toLowerCase().contains(search.toLowerCase()) || habit.getMotivation().toLowerCase().contains(search.toLowerCase())) {
                habits.add(habit.toString());
            }
        }
        return habits;
    }

}
