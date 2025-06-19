package org.example.studysearch;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SearchLogTest {
    SearchLog searchLog = null;

    @BeforeEach
    void setUp() {
        this.searchLog = new SearchLog("Test Log");
    }

    @Test
    @Order(1)
    @DisplayName("Add Search History Test")
    void addSearchHistory() {
        this.searchLog.addSearchHistory("Test History");
        List<String> history = this.searchLog.getSearchHistory();
        assertEquals(history.size(), 1);
        assertEquals("Test History", history.getFirst());
    }

    @Test
    @Order(2)
    @DisplayName("Get Search History Test")
    void getSearchHistory() {
        List<String> history = this.searchLog.getSearchHistory();
        assertNotNull(history);
        assertEquals(history.size(), 0);
        this.searchLog.addSearchHistory("Test History");
        history = this.searchLog.getSearchHistory();
        assertEquals(history.size(), 1);
        assertEquals("Test History", history.getFirst());
    }
}