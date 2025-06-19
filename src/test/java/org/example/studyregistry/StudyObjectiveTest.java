package org.example.studyregistry;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyObjectiveTest {
    StudyObjective studyObjective = new StudyObjective("Test Study Objective", "Test Study Objective Description");


    int setStudyObjectiveTest(){
        // id, priority, practicedDays, day, month, year
        List<Integer> intProperties = List.of(1, 2, 12, 3, 4, 2024);
        List<String> stringProperties = List.of("SetObjectiveNameTest", "SetObjectiveTitleTest", "SetObjectiveDescriptionTest", "SetObjectiveTopicTest",  "SetObjectiveObjectiveInOneLine", "SetObjectiveObjectiveFullDescription", "SetObjectiveMotivation");
        Double duration = 19.1;
        boolean isActive = false;
        return studyObjective.handleSetObjectiveAdapter(intProperties,  stringProperties, duration, isActive);
    }

    void verifyStringProperties(){
        assertEquals("SetObjectiveNameTest", studyObjective.getName());
        assertEquals("SetObjectiveTitleTest", studyObjective.getTitle());
        assertEquals("SetObjectiveDescriptionTest", studyObjective.getDescription());
        assertEquals("SetObjectiveTopicTest", studyObjective.getTopic());
        assertEquals("SetObjectiveObjectiveInOneLine", studyObjective.getObjectiveInOneLine());
        assertEquals("SetObjectiveObjectiveFullDescription", studyObjective.getObjectiveFullDescription());
        assertEquals("SetObjectiveMotivation", studyObjective.getMotivation());
    }

    void verifyIntegerProperties(){
        assertEquals(1, studyObjective.getId());
        assertEquals(2, studyObjective.getPriority());
        assertEquals(12, studyObjective.getPracticedDays());
        assertEquals(3, studyObjective.getStartDate().getDayOfMonth());
        assertEquals(4, studyObjective.getStartDate().getMonthValue());
        assertEquals(2024, studyObjective.getStartDate().getYear());
    }

    @Test
    @Order(1)
    @DisplayName("Handle Set Objective Test")
    void handleSetObjectiveTest() {
        int id = setStudyObjectiveTest();
        verifyStringProperties();
        verifyIntegerProperties();
        assertEquals(19.1, studyObjective.getDuration());
        assertFalse(studyObjective.isActive());
    }
}