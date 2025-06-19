package org.example.studyplanner;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HabitTrackerTest {
    HabitTracker habitTracker = HabitTracker.getHabitTracker();
    List<Integer> habitIds = new ArrayList<>();

    @BeforeEach
    void setUp() {
        habitIds.add(habitTracker.addHabit("Test Search Name Habit", "Test Search Habit Motivation"));
    }

    boolean verifyCardStringInList(String searching, List<String> response) {
        for(String habit : response){
            if(habit.contains(searching)){
                return true;
            }
        }
        return false;
    }

    @Test
    @Order(1)
    @DisplayName("Search Name In Habits Test")
    void searchNameInHabits() {
        List<String> response = habitTracker.searchInHabits("Search Name Habit");
        assertTrue(verifyCardStringInList("Search Name Habit", response));
    }

    @Test
    @Order(2)
    @DisplayName("Search Motivation In Habits Test")
    void searchMotivationInHabits() {
        List<String> response = habitTracker.searchInHabits("Search Habit Motivation");
        assertTrue(verifyCardStringInList("Search Habit Motivation", response));
    }

    void verifyAddHabitProperties(Habit habit){
        assertEquals("Add Habit Name Test", habit.getName());
        assertEquals("Add Habit Motivation Test", habit.getMotivation());
        assertEquals(30, habit.getDailyDedicationTime().getMinute());
        assertEquals(2, habit.getDailyDedicationTime().getHour());
        assertEquals(2024, habit.getStartDate().getYear());
        assertEquals(2, habit.getStartDate().getMonthValue());
        assertEquals(4, habit.getStartDate().getDayOfMonth());
        assertEquals(8, habit.getStartDate().getHour());
        assertEquals(16, habit.getStartDate().getMinute());
        assertEquals(32, habit.getStartDate().getSecond());
    }

    @Test
    @Order(3)
    @DisplayName("Add Habit Test")
    void addHabitTest() {
        List<String> stringProperties = List.of("Add Habit Name Test", "Add Habit Motivation Test");
        List<Integer> intProperties = List.of(30, 2, 2024, 2, 4, 8, 16, 32);
        boolean isConcluded = false;
        int id = habitTracker.handleAddHabitAdapter(stringProperties, intProperties, isConcluded);
        Habit habit = habitTracker.getHabitById(id);
        if(habit == null){
            fail();
        }
        assertFalse(habit.getIsConcluded());
        verifyAddHabitProperties(habit);
    }


}