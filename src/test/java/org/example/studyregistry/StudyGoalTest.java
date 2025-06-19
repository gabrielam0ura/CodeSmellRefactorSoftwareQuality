package org.example.studyregistry;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyGoalTest {
    StudyGoal studyGoal = null;
    StudyObjective studyObjective = null;
    StudyPlan studyPlan = null;

    @BeforeEach
    void setUp() {
        studyObjective = new StudyObjective("Test Objective", "Test Objective Description");
        studyPlan = new StudyPlan("Test Plan", studyObjective, new ArrayList<>());
        studyGoal = new StudyGoal("Study Goal Test", studyObjective, studyPlan);
        setStudyGoal();
        setStudyPlan();
    }

    void setStudyPlan(){
        studyPlan.addSingleStep("Test Step 1");
    }

    void setStudyGoal(){
        studyGoal.setGoal("Test Goal");
        studyGoal.editActiveCompleted(true, true);
        studyGoal.addRequirement("Test Requirement");
        studyGoal.addRequirement("Test Requirement 2");
    }

    void verifyStudyObjective(String response){
        assertTrue(response.contains("Test Objective"));
        assertTrue(response.contains("Test Objective Description"));
    }

    void verifyStudyPlan(String response){
        assertTrue(response.contains("Test Step 1"));
        assertTrue(response.contains("Test Objective Description"));
        assertTrue(response.contains("Test Plan"));
    }

    void verifyStudyGoal(String response){
        assertTrue(response.contains("Test Goal"));
        assertTrue(response.contains("Test Requirement"));
        assertTrue(response.contains("Test Requirement 2"));
    }

    @Test
    @Order(1)
    @DisplayName("Set Goal Summary Test")
    void setGoalSummary() {
        String response = studyGoal.setGoalSummary();
        verifyStudyObjective(response);
        verifyStudyPlan(response);
        verifyStudyGoal(response);
    }
}