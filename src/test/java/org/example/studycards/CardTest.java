package org.example.studycards;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CardTest {
    private static CardManager manager = null;
    private static int cardId1;
    private static int cardId2;

    @BeforeAll
    static void setUp() {
        manager = CardManager.getCardManager();
        cardId1 = manager.addCard("Question?", "Answer!");
    }

    @Test
    @DisplayName("Create Card Test")
    @Order(0)
    void createCard() {
        cardId2 = manager.addCard("Question 2?", "Answer 2!");
        List<Card> cards = manager.getCards();
        Card card1 = manager.getCard(cardId1);
        Card card2 = manager.getCard(cardId2);
        assertTrue(cards.contains(card1));
        assertTrue(cards.contains(card2));

    }

    @Test
    @DisplayName("Get Question Test")
    @Order(1)
    void getQuestion() {
        Card card = manager.getCard(cardId1);
        assertEquals("Question?", card.getQuestion());
    }

    @Test
    @DisplayName("Set Question Test")
    @Order(2)
    void setQuestion() {
        Card card = manager.getCard(cardId1);
        card.setQuestion("Question 3");
        assertEquals("Question 3", card.getQuestion());
    }

    @Test
    @DisplayName("Get Answer Test")
    @Order(3)
    void getAnswer() {
        Card card = manager.getCard(cardId1);
        assertEquals("Answer!", card.getAnswer());
    }

    @Test
    @DisplayName("Set Answer Test")
    @Order(4)
    void setAnswer() {
        Card card = manager.getCard(cardId1);
        card.setAnswer("Answer 3");
        assertEquals("Answer 3", card.getAnswer());
    }

    @Test
    @DisplayName("Edit Card Test")
    @Order(5)
    void edit() {
        Card card = manager.getCard(cardId1);
        card.edit("Question 4", "Answer 4");
        assertEquals("Question 4", card.getQuestion());
        assertEquals("Answer 4", card.getAnswer());
    }
}