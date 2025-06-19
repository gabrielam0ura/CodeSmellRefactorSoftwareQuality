package org.example.controllers;

import org.example.studycards.CardManager;
import org.example.studycards.LeitnerSystem;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyCardsControllerTest {
    static private CardManager cardManager = CardManager.getCardManager();
    static private LeitnerSystem leitnerSystem = new LeitnerSystem("Test LeitnerSystem");
    static private StudyCardsController studyCardsController = new StudyCardsController(leitnerSystem);
    static private List<Integer> cardIds = new ArrayList<>();

    @BeforeAll
    static public void setUp(){
        addCards();
        addCardsToLeitnerBox();

    }

    static void addCards(){
        cardIds.add(cardManager.addCard("Test Random Leitner Question 1", "Test Random Leitner Answer 1"));
        cardIds.add(cardManager.addCard("Test Random Leitner Question 2", "Test Random Leitner Answer 2"));
    }

    static void addCardsToLeitnerBox(){
        for (Integer cardId : cardIds){
            if(cardId % 2 == 0){
                leitnerSystem.addCardToBox(cardId, 1);
            } else {
                leitnerSystem.addCardToBox(cardId, 4);
            }
        }
    }

    @Test
    @Order(1)
    @DisplayName("Get Random Card From Box Test")
    void getRandomCardFromBox() {
        String response = studyCardsController.getRandomCardFromBox();
        if(response == null){
            fail();
        }
        assertTrue(response.contains("Test LeitnerSystem"));
        if(response.contains("Test Random Leitner Question 1")){
            assertTrue(response.contains("Test Random Leitner Answer 1"));
        } else {
            assertTrue(response.contains("Test Random Leitner Answer 2"));
        }

    }



}