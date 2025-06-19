package org.example.studyplanner;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TimelineViewTest {
    static TimelineView timelineView;
    static HabitTracker habitTracker;

    @BeforeAll
    static void setUp() {
        habitTracker = HabitTracker.getHabitTracker();
        timelineView = new TimelineView();
        addHabits();
    }

    static void addHabits(){
        int habit1 = habitTracker.addHabit("Test 1", "Test 1 Motivation");
        int habit2 = habitTracker.addHabit("Test 2", "Test 2 Motivation");
        habitTracker.addHabit("Test 3", "Test 3 Motivation");
        addHabitRecord(habit1);
        addHabitRecord(habit2);
    }

    static void addHabitRecord(int id){
        habitTracker.addHabitRecord(id);
    }


    void verifyRegistryFormat(String response) {
        String regex = "\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(response);
        assertTrue(matcher.find());
    }

    void verifyDateHour(String response, LocalDateTime date) {
        assertTrue(response.contains(Integer.toString(date.getYear())));
        assertTrue(response.contains(Integer.toString(date.getMonthValue())));
        assertTrue(response.contains(Integer.toString(date.getDayOfMonth())));
        assertTrue(response.contains(Integer.toString(date.getHour())));
    }

    void verifyHabitNames(String response) {
        assertTrue(response.contains("Test 1"));
        assertTrue(response.contains("Test 2"));
        assertTrue(response.contains("Test 3"));
    }

    @Test
    @Order(1)
    @DisplayName("Habit Date View All Registry Test")
    void habitDateViewAllRegistry() {
        String response = timelineView.habitDateViewAll(habitTracker);

        verifyRegistryFormat(response);
    }

    @Test
    @Order(2)
    @DisplayName("Habit Date View All Date Hour Test")
    void habitDateViewAllDateHour() {
        LocalDateTime now = LocalDateTime.now();
        String response = timelineView.habitDateViewAll(habitTracker);

        verifyDateHour(response, now);
    }

    @Test
    @Order(3)
    @DisplayName("Habit Date View All Habit Names Test")
    void habitDateViewAllHabitNames() {
        String response = timelineView.habitDateViewAll(habitTracker);

        verifyHabitNames(response);
    }
}