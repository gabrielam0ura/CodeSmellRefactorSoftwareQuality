package org.example.studyregistry;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTaskManagerTest {
    static StudyTaskManager studyTaskManager = StudyTaskManager.getStudyTaskManager();
    static StudyObjective studyObjective = new StudyObjective("Objective Title Search Test",
            "Objective Description Search Test");
    static StudyPlan studyPlan = new StudyPlan("Plan Name Search Test", studyObjective, new ArrayList<>());
    static StudyGoal studyGoal = new StudyGoal("Study Task Manager Test", studyObjective, studyPlan);
    static Task task = new Task("Task Title Search Test", "Task Description Search Test", "Test Author", LocalDateTime.now());


    @BeforeAll
    static void setUpBeforeClass(){
        addWeekResponsibilities();
        addRegistries();
    }
    static void addWeekResponsibilities(){
        List<String> stringProperties = List.of("planName", "objectiveTitle", "objectiveDescription", "materialTopic",
                "materialFormat", "goal", "reminderTitle", "reminderDescription",
                "mainTaskTitle", "mainHabit", "mainCardStudy");
        studyTaskManager.handleSetUpWeek(stringProperties);
    }

    boolean verifyResponsibilityResponse(String res, List<String> response) {
        for(String object : response){
            if(object.contains(res)){
                return true;
            }
        }
        return false;
    }


    @Test
    @Order(1)
    @DisplayName("Handle Set Up Week Plan Name Test")
    void handleSetUpWeekPlanNameTest() {
        List<String> response = studyTaskManager.getWeekResponsibilities();
        assertTrue(verifyResponsibilityResponse("planName", response));
    }

    @Test
    @Order(2)
    @DisplayName("Handle Set Up Week Objective Title Test")
    void handleSetUpWeekObjectiveTitleTest(){
        List<String> response = studyTaskManager.getWeekResponsibilities();
        assertTrue(verifyResponsibilityResponse("objectiveTitle", response));
    }

    @Test
    @Order(3)
    @DisplayName("Handle Set Up Week Objective Description Test")
    void handleSetUpWeekObjectiveDescriptionTest(){
        List<String> response = studyTaskManager.getWeekResponsibilities();
        assertTrue(verifyResponsibilityResponse("objectiveDescription", response));
    }

    @Test
    @Order(4)
    @DisplayName("Handle Set Up Week Material Topic Test")
    void handleSetUpWeekMaterialTopicTest(){
        List<String> response = studyTaskManager.getWeekResponsibilities();
        assertTrue(verifyResponsibilityResponse("materialTopic", response));
    }

    @Test
    @Order(5)
    @DisplayName("Handle Set Up Week Material Format Test")
    void handleSetUpWeekMaterialFormatTest(){
        List<String> response = studyTaskManager.getWeekResponsibilities();
        assertTrue(verifyResponsibilityResponse("materialFormat", response));
    }

    @Test
    @Order(6)
    @DisplayName("Handle Set Up Week Goal Test")
    void handleSetUpWeekGoalTest(){
        List<String> response = studyTaskManager.getWeekResponsibilities();
        assertTrue(verifyResponsibilityResponse("goal", response));
    }

    @Test
    @Order(7)
    @DisplayName("Handle Set Up Week Reminder Title Test")
    void handleSetUpWeekReminderTitleTest(){
        List<String> response = studyTaskManager.getWeekResponsibilities();
        assertTrue(verifyResponsibilityResponse("reminderTitle", response));
    }

    @Test
    @Order(8)
    @DisplayName("Handle Set Up Week Reminder Description Test")
    void handleSetUpWeekReminderDescriptionTest(){
        List<String> response = studyTaskManager.getWeekResponsibilities();
        assertTrue(verifyResponsibilityResponse("reminderDescription", response));
    }

    @Test
    @Order(9)
    @DisplayName("Handle Set Up Week Main Task Title Test")
    void handleSetUpWeekMainTaskTitleTest(){
        List<String> response = studyTaskManager.getWeekResponsibilities();
        assertTrue(verifyResponsibilityResponse("mainTaskTitle", response));
    }

    @Test
    @Order(10)
    @DisplayName("Handle Set Up Week Main Habit Test")
    void handleSetUpWeekMainHabitTest(){
        List<String> response = studyTaskManager.getWeekResponsibilities();
        assertTrue(verifyResponsibilityResponse("mainHabit", response));
    }

    @Test
    @Order(11)
    @DisplayName("Handle Set Up Week Main Card Study Test")
    void handleSetUpWeekMainCardStudyTest(){
        List<String> response = studyTaskManager.getWeekResponsibilities();
        assertTrue(verifyResponsibilityResponse("mainCardStudy", response));
    }

    static void addRegistries(){
        studyTaskManager.addRegistry(studyGoal);
        studyTaskManager.addRegistry(studyObjective);
        studyTaskManager.addRegistry(studyPlan);
        studyTaskManager.addRegistry(task);
    }

    boolean verifySearchResponse(String searching, List<String> response) {
        for(String object : response){
            if(object.contains(searching)){
                return true;
            }
        }
        return false;
    }

    @Test
    @Order(12)
    @DisplayName("Search Objective In Registries Test")
    void searchObjectiveInRegistries(){
        List<String> response = studyTaskManager.searchInRegistries("Objective Title");
        assertTrue(verifySearchResponse("Objective Title Search Test", response));
    }

    @Test
    @Order(13)
    @DisplayName("Search Plan Name In Registries Test")
    void searchPlanNameInRegistries(){
        List<String> response = studyTaskManager.searchInRegistries("Plan Name");
        assertTrue(verifySearchResponse("Plan Name Search Test", response));
    }

    @Test
    @Order(14)
    @DisplayName("Search Study Task In Registries Test")
    void searchStudyTaskInRegistries(){
        List<String> response = studyTaskManager.searchInRegistries("Study Task");
        assertTrue(verifySearchResponse("Study Task Manager Test", response));
    }

    @Test
    @Order(15)
    @DisplayName("Search Task Title In Registries Test")
    void searchTaskTitleInRegistries(){
        List<String> response = studyTaskManager.searchInRegistries("Task Title");
        assertTrue(verifySearchResponse("Task Title Search Test", response));
    }
}