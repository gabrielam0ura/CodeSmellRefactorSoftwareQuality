package org.example.studycards;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LeitnerSystemTest {
    static LeitnerSystem leitnerSystem = null;
    static CardManager manager = null;
    static Integer card1Id = null;
    static Integer card2Id = null;

    @BeforeAll
    static void setUp() {
        leitnerSystem = new LeitnerSystem("LeitnerSystemTest");
        manager = CardManager.getCardManager();
        card1Id = manager.addCard("Question 1", "Answer 1");
        card2Id = manager.addCard("Question 2", "Answer 2");

    }

    @BeforeEach
    void setUpEach() {
        leitnerSystem.clearBoxes();
    }

    @Test
    @DisplayName("Get Random Card No Cards Test")
    @Order(1)
    void testGetRandomCardNoCards() {
        Box box = new Box();
        String response = leitnerSystem.getRandomCard(List.of(box));
        assertNotNull(response);
        assertEquals("No card found", response);
    }

    @Test
    @DisplayName("Get Random Card Test")
    @Order(2)
    void getRandomCard() {
        Box box = new Box();
        Box box2 = new Box();
        box.addCards(List.of(card1Id));
        box2.addCards(List.of(card2Id));
        String randomCard = leitnerSystem.getRandomCard(Arrays.asList(box, box2));
        assertNotNull(randomCard);
        if(randomCard.contains("Question 1")) {
            assertTrue(randomCard.contains("Answer 1"));
        } else {
            assertTrue(randomCard.contains("Answer 2"));
        }
    }
}