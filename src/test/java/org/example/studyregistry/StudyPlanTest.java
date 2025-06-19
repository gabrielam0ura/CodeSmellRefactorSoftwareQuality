package org.example.studyregistry;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyPlanTest {
    StudyObjective studyObjective = new StudyObjective("Test Study Objective", "Test Study Objective");
    StudyPlan studyPlan = new StudyPlan("Test Plan", studyObjective, new ArrayList<>());
    LocalDateTime startDateTest = null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @BeforeEach
    void setUp() {
        assignSteps();
    }

    void assignSteps(){
        List<String> stringProperties = List.of("firstStep", "resetStudyMechanism", "consistentStep", "seasonalSteps",
                "basicSteps", "mainObjectiveTitle", "mainGoalTitle", "mainMaterialTopic", "mainTask");
        Integer numberOfSteps = 20;
        boolean isImportant = true;
        startDateTest = LocalDateTime.now();
        LocalDateTime startDate = startDateTest;
        LocalDateTime endDate = startDate.plusDays(10);
        studyPlan.handleAssignSteps(stringProperties, numberOfSteps, isImportant, startDate, endDate);
    }

    boolean verifyStepsResponse(String step, List<String> response) {
        for(String object : response){
            if(object.contains(step)){
                return true;
            }
        }
        return false;
    }


    @Test
    @Order(1)
    @DisplayName("Handle Assign First Step Test")
    void handleAssignStepsFirstStepTest() {
        List<String> steps = studyPlan.getSteps();
        assertTrue(verifyStepsResponse("firstStep", steps));
    }

    @Test
    @Order(2)
    @DisplayName("Handle Assign Reset Study Mechanism Test")
    void handleAssignStepsResetStudyMechanismTest() {
        List<String> steps = studyPlan.getSteps();
        assertTrue(verifyStepsResponse("resetStudyMechanism", steps));
    }

    @Test
    @Order(3)
    @DisplayName("Handle Assign Reset Consistent Step Test")
    void handleAssignStepsConsistentStepTest() {
        List<String> steps = studyPlan.getSteps();
        assertTrue(verifyStepsResponse("consistentStep", steps));
    }


    @Test
    @Order(4)
    @DisplayName("Handle Assign Reset Seasonal Steps Test")
    void handleAssignStepsSeasonalStepsTest() {
        List<String> steps = studyPlan.getSteps();
        assertTrue(verifyStepsResponse("seasonalSteps", steps));
    }

    @Test
    @Order(5)
    @DisplayName("Handle Assign Reset Basic Steps Test")
    void handleAssignStepsBasicStepsTest() {
        List<String> steps = studyPlan.getSteps();
        assertTrue(verifyStepsResponse("basicSteps", steps));
    }

    @Test
    @Order(6)
    @DisplayName("Handle Assign Reset Main Objective Title Test")
    void handleAssignStepsMainObjectiveTitleTest() {
        List<String> steps = studyPlan.getSteps();
        assertTrue(verifyStepsResponse("mainObjectiveTitle", steps));
    }

    @Test
    @Order(7)
    @DisplayName("Handle Assign Reset Main Goal Title Test")
    void handleAssignStepsMainGoalTitleTest() {
        List<String> steps = studyPlan.getSteps();
        assertTrue(verifyStepsResponse("mainGoalTitle", steps));
    }

    @Test
    @Order(8)
    @DisplayName("Handle Assign Reset Main Material Topic Test")
    void handleAssignStepsMainMaterialTopicTest() {
        List<String> steps = studyPlan.getSteps();
        assertTrue(verifyStepsResponse("mainMaterialTopic", steps));
    }

    @Test
    @Order(9)
    @DisplayName("Handle Assign Reset Main Task Test")
    void handleAssignStepsMainTaskTest() {
        List<String> steps = studyPlan.getSteps();
        assertTrue(verifyStepsResponse("mainTask", steps));
    }

    @Test
    @Order(10)
    @DisplayName("Handle Assign Number Of Steps Test")
    void handleAssignStepsNumberOfStepsTest() {
        List<String> steps = studyPlan.getSteps();
        assertTrue(verifyStepsResponse("20", steps));
    }

    @Test
    @Order(11)
    @DisplayName("Handle Assign Start Date Test")
    void handleAssignStepsStartDateTest() {
        List<String> steps = studyPlan.getSteps();
        assertTrue(verifyStepsResponse(startDateTest.format(formatter), steps));
    }

    @Test
    @Order(12)
    @DisplayName("Handle Assign End Date Test")
    void handleAssignStepsEndDateTest() {
        List<String> steps = studyPlan.getSteps();
        assertTrue(verifyStepsResponse(startDateTest.plusDays(10).format(formatter), steps));
    }

    @Test
    @Order(13)
    @DisplayName("Handle Assign Is Important Test")
    void handleAssignStepsIsImportantTest() {
        List<String> steps = studyPlan.getSteps();
        assertTrue(verifyStepsResponse("true", steps));
    }


}