package org.example.studymaterial;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReferenceTest {
    Reference tReference = null;
    @BeforeEach
    void setUp() {
        tReference = new TextReference("Book", "English", 500, "pdf", "Open");
    }

    @Test
    @DisplayName("Set Title Test")
    @Order(1)
    void setTitle() {
        this.tReference.setTitle("Book 2");
        assertEquals("Book 2", this.tReference.getTitle());
    }

    @Test
    @DisplayName("Get Title Test")
    @Order(2)
    void getTitle() {
        assertEquals("Book", this.tReference.getTitle());
    }

    @Test
    @DisplayName("Set Description Test")
    @Order(3)
    void setDescription() {
        this.tReference.setDescription("A short book");
        assertEquals("A short book", this.tReference.getDescription());
    }

    @Test
    @DisplayName("Get Description Test")
    @Order(4)
    void getDescription() {
        assertNull(this.tReference.getDescription());
        this.tReference.setDescription("A long description");
        assertEquals("A long description", this.tReference.getDescription());
    }
}