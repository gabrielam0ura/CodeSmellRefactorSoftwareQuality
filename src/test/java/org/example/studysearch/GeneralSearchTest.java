package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studymaterial.AudioReference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GeneralSearchTest {
    static CardManager cardManager = CardManager.getCardManager();
    static HabitTracker habitTracker = HabitTracker.getHabitTracker();
    static TodoTracker todoTracker = TodoTracker.getInstance();
    static StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    static GeneralSearch generalSearch = new GeneralSearch();
    static StudyTaskManager studyTaskManager = StudyTaskManager.getStudyTaskManager();

    @BeforeAll
    static void setUp() {
        Task task = new Task("GeneralSearchTestTask Test", "Test", "Author Test", LocalDateTime.now());
        studyTaskManager.addRegistry(task);
        StudyObjective studyObjective = new StudyObjective("GeneralSearchStudyObjective Test",
                "Objective Description Search Test");
        StudyPlan studyPlan = new StudyPlan("GeneralSearchStudyPlan Test", studyObjective, new ArrayList<>());
        StudyGoal studyGoal = new StudyGoal("GeneralSearchStudyGoal Test", studyObjective, studyPlan);
        addRegistries(task, studyObjective, studyPlan, studyGoal);

    }
    static void addRegistries(Task task, StudyObjective studyObjective, StudyPlan studyPlan, StudyGoal studyGoal){
        studyTaskManager.addRegistry(task);
        studyTaskManager.addRegistry(studyObjective);
        studyTaskManager.addRegistry(studyPlan);
        studyTaskManager.addRegistry(studyGoal);
    }

    static void addCards(){
        cardManager.addCard("GeneralSearchTestCard Test", "Test");
        cardManager.addCard("Test", "GeneralSearchTestCard2 Test");
    }

    static void addHabits(){
        List<Integer> ids = new ArrayList<>();
        habitTracker.addHabit("GeneralSearchTestHabit Test", "Test");
        habitTracker.addHabit("Test", "GeneralSearchTestHabit2 Test");
    }

    static void addToDo(){
        todoTracker.addToDo("GeneralSearchTestToDo Test", "Test", 2);
        todoTracker.addToDo("Test", "GeneralSearchTestToDo2 Test", 2);
    }

    static void addAudioReference(){
        AudioReference audioReference = new AudioReference(AudioReference.AudioQuality.LOW);
        audioReference.editBasic("GeneralSearchTestAudio Test", "Test", "Test");
        AudioReference audioReference2 = new AudioReference(AudioReference.AudioQuality.MEDIUM);
        audioReference2.editBasic("Test", "GeneralSearchTestAudio2 Test", "Test");
        AudioReference audioReference3 = new AudioReference(AudioReference.AudioQuality.HIGH);
        audioReference3.editBasic("Test", "Test", "GeneralSearchTestAudio3 Test");
        studyMaterial.addReference(audioReference);
        studyMaterial.addReference(audioReference2);
        studyMaterial.addReference(audioReference3);
    }

    static void addVideoReference(){
        VideoReference videoReference = new VideoReference("GeneralSearchTestVideo Test", "Test");
        VideoReference videoReference2 = new VideoReference("Test", "GeneralSearchTestVideo2 Test");
        studyMaterial.addReference(videoReference);
        studyMaterial.addReference(videoReference2);
    }

    static void addTextReference(){
        TextReference textReference = new TextReference("GeneralSearchTestText Test", "English", 200, "pdf", "Closed");
        studyMaterial.addReference(textReference);
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
    @Order(1)
    @DisplayName("General Search Cards Test")
    void generalSearchCardsTest() {
        addCards();
        SearchLog searchLog = generalSearch.getSearchLog();
        int previousNumUsages = searchLog.getNumUsages();
        List<String> response = generalSearch.search("TestCard");
        assertTrue(verifySearchResponse("Card T", response));
        assertTrue(verifySearchResponse("Card2 T", response));
        assertEquals((int) searchLog.getNumUsages(), previousNumUsages + 1);
        assertTrue(searchLog.getSearchHistory().contains("TestCard"));
        assertTrue(verifySearchResponse(searchLog.getLogName(), response));
    }

    @Test
    @Order(2)
    @DisplayName("General Search Habits Test")
    void generalSearchHabitsTest() {
        addHabits();
        SearchLog searchLog = generalSearch.getSearchLog();
        int previousNumUsages = searchLog.getNumUsages();
        List<String> response = generalSearch.search("SearchTestHabit");
        assertTrue(verifySearchResponse("GeneralSearchTestHabit Test", response));
        assertTrue(verifySearchResponse("GeneralSearchTestHabit2 Test", response));
        assertEquals((int) searchLog.getNumUsages(), previousNumUsages + 1);
        assertTrue(searchLog.getSearchHistory().contains("SearchTestHabit"));
        assertTrue(verifySearchResponse(searchLog.getLogName(), response));
    }

    @Test
    @Order(3)
    @DisplayName("General Search ToDo Test")
    void generalSearchToDoTest() {
        addToDo();
        SearchLog searchLog = generalSearch.getSearchLog();
        int previousNumUsages = searchLog.getNumUsages();
        List<String> response = generalSearch.search("SearchTestToDo");
        assertTrue(verifySearchResponse("GeneralSearchTestToDo Test", response));
        assertTrue(verifySearchResponse("GeneralSearchTestToDo2 Test", response));
        assertEquals((int) searchLog.getNumUsages(), previousNumUsages + 1);
        assertTrue(searchLog.getSearchHistory().contains("SearchTestToDo"));
        assertTrue(verifySearchResponse(searchLog.getLogName(), response));
    }


    @Test
    @Order(4)
    @DisplayName("General Search Audio Reference Test")
    void generalSearchAudioReferenceTest() {
        addAudioReference();
        SearchLog searchLog = generalSearch.getSearchLog();
        int previousNumUsages = searchLog.getNumUsages();
        List<String> response = generalSearch.search("SearchTestAudio");
        assertTrue(verifySearchResponse("GeneralSearchTestAudio Test", response));
        assertFalse(verifySearchResponse("GeneralSearchTestAudio2 Test", response));
        assertFalse(verifySearchResponse("GeneralSearchTestAudio3 Test", response));
        assertEquals((int) searchLog.getNumUsages(), previousNumUsages + 1);
        assertTrue(searchLog.getSearchHistory().contains("SearchTestAudio"));
        assertTrue(verifySearchResponse(searchLog.getLogName(), response));
    }

    @Test
    @Order(5)
    @DisplayName("General Search Video Reference Test")
    void generalSearchVideoReferenceTest() {
        addVideoReference();
        SearchLog searchLog = generalSearch.getSearchLog();
        int previousNumUsages = searchLog.getNumUsages();
        List<String> response = generalSearch.search("SearchTestVideo");
        assertTrue(verifySearchResponse("GeneralSearchTestVideo Test", response));
        assertFalse(verifySearchResponse("GeneralSearchTestVideo2 Test", response));
        assertEquals((int) searchLog.getNumUsages(), previousNumUsages + 1);
        assertTrue(searchLog.getSearchHistory().contains("SearchTestVideo"));
        assertTrue(verifySearchResponse(searchLog.getLogName(), response));
    }

    @Test
    @Order(6)
    @DisplayName("General Search Text Reference Test")
    void generalSearchTextReferenceTest() {
        addTextReference();
        SearchLog searchLog = generalSearch.getSearchLog();
        int previousNumUsages = searchLog.getNumUsages();
        List<String> response = generalSearch.search("SearchTestText");
        assertTrue(verifySearchResponse("GeneralSearchTestText Test", response));
        assertEquals((int) searchLog.getNumUsages(), previousNumUsages + 1);
        assertTrue(searchLog.getSearchHistory().contains("SearchTestText"));
        assertTrue(verifySearchResponse(searchLog.getLogName(), response));
    }

    @Test
    @Order(7)
    @DisplayName("General Search Task Test")
    void generalSearchTaskTest() {
        SearchLog searchLog = generalSearch.getSearchLog();
        int previousNumUsages = searchLog.getNumUsages();
        List<String> response = generalSearch.search("SearchTestTask");
        assertTrue(verifySearchResponse("GeneralSearchTestTask Test", response));
        assertEquals((int) searchLog.getNumUsages(), previousNumUsages + 1);
        assertTrue(searchLog.getSearchHistory().contains("SearchTestTask"));
        assertTrue(verifySearchResponse(searchLog.getLogName(), response));
    }

    @Test
    @Order(8)
    @DisplayName("General Study Objective Test")
    void generalSearchStudyObjectiveTest() {
        SearchLog searchLog = generalSearch.getSearchLog();
        int previousNumUsages = searchLog.getNumUsages();
        List<String> response = generalSearch.search("SearchStudyObjective");
        assertTrue(verifySearchResponse("GeneralSearchStudyObjective Test", response));
        assertEquals((int) searchLog.getNumUsages(), previousNumUsages + 1);
        assertTrue(searchLog.getSearchHistory().contains("SearchStudyObjective"));
        assertTrue(verifySearchResponse(searchLog.getLogName(), response));
    }

    @Test
    @Order(9)
    @DisplayName("General Study Plan Test")
    void generalSearchStudyPlanTest() {
        SearchLog searchLog = generalSearch.getSearchLog();
        int previousNumUsages = searchLog.getNumUsages();
        List<String> response = generalSearch.search("SearchStudyPlan");
        assertTrue(verifySearchResponse("GeneralSearchStudyPlan Test", response));
        assertEquals((int) searchLog.getNumUsages(), previousNumUsages + 1);
        assertTrue(searchLog.getSearchHistory().contains("SearchStudyPlan"));
        assertTrue(verifySearchResponse(searchLog.getLogName(), response));
    }

    @Test
    @Order(9)
    @DisplayName("General Study Goal Test")
    void generalSearchStudyGoalTest() {
        SearchLog searchLog = generalSearch.getSearchLog();
        int previousNumUsages = searchLog.getNumUsages();
        List<String> response = generalSearch.search("SearchStudyGoal");
        assertTrue(verifySearchResponse("GeneralSearchStudyGoal Test", response));
        assertEquals((int) searchLog.getNumUsages(), previousNumUsages + 1);
        assertTrue(searchLog.getSearchHistory().contains("SearchStudyGoal"));
        assertTrue(verifySearchResponse(searchLog.getLogName(), response));
    }


}