package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RegistrySearchTest {
    static private CardManager cardManager = CardManager.getCardManager();
    static private HabitTracker habitTracker = HabitTracker.getHabitTracker();
    static private TodoTracker todoTracker = TodoTracker.getInstance();
    static private RegistrySearch registrySearch = new RegistrySearch();

    static void addCards(){
        cardManager.addCard("RegistrySearchTestCard Test", "Test");
        cardManager.addCard("Test", "RegistrySearchTestCard2 Test");
    }

    static void addHabits(){
        List<Integer> ids = new ArrayList<>();
        habitTracker.addHabit("RegistrySearchTestHabit Test", "Test");
        habitTracker.addHabit("Test", "RegistrySearchTestHabit2 Test");
    }

    static void addToDo(){
        todoTracker.addToDo("RegistrySearchTestToDo Test", "Test", 2);
        todoTracker.addToDo("Test", "RegistrySearchTestToDo2 Test", 2);
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
    @DisplayName("Registry Search Cards Test")
    void generalSearchCardsTest() {
        addCards();
        List<String> response = registrySearch.search("TestCard");
        assertTrue(verifySearchResponse("Card T", response));
        assertTrue(verifySearchResponse("Card2 T", response));
    }

    @Test
    @Order(2)
    @DisplayName("Registry Search Habits Test")
    void registrySearchHabitsTest() {
        addHabits();
        List<String> response = registrySearch.search("SearchTestHabit");
        assertTrue(verifySearchResponse("RegistrySearchTestHabit Test", response));
        assertTrue(verifySearchResponse("RegistrySearchTestHabit2 Test", response));
    }

    @Test
    @Order(3)
    @DisplayName("Registry Search ToDo Test")
    void registrySearchToDoTest() {
        addToDo();
        List<String> response = registrySearch.search("SearchTestToDo");
        assertTrue(verifySearchResponse("RegistrySearchTestToDo Test", response));
        assertTrue(verifySearchResponse("RegistrySearchTestToDo2 Test", response));
    }

}