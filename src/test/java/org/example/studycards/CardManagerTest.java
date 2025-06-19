package org.example.studycards;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CardManagerTest {
    static CardManager manager = CardManager.getCardManager();
    static List<Integer> cardIds = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        cardIds.add(manager.addCard("Test Search", "Test Answer Search"));
    }

    boolean verifyCardQuestion(List<String> response){
        for(String card : response){
            if(card.contains("Test Search")){
                return true;
            }
        }
        return false;
    }

    boolean verifyCardAnswer(List<String> response){
        for(String card : response){
            if(card.contains("Test Answer Search")){
                return true;
            }
        }
        return false;
    }

    @Test
    @Order(1)
    @DisplayName("Search Card Question Test")
    void searchQuestionInCards() {
        List<String> response = manager.searchInCards("Search");
        assertTrue(verifyCardQuestion(response));

    }
    @Test
    @Order(2)
    @DisplayName("Search Card Answer Test")
    void searchAnswerInCards() {
        List<String> response = manager.searchInCards("Search");
        assertTrue(verifyCardAnswer(response));

    }
}