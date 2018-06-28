package ufrn.alvarofpp.tests;

import org.junit.jupiter.api.Test;
import ufrn.alvarofpp.memory.MainMemory;

import static org.junit.jupiter.api.Assertions.*;

class MainMemoryTest {

    @Test
    void getContent() {
        MainMemory mainMemory = new MainMemory(4, 16);
        // Alimenta a memória principal
        mainMemory.setContent(0, 20);
        mainMemory.setContent(5, 55);
        mainMemory.setContent(4, 30);

        // Testes iguais
        assertEquals(mainMemory.getContent(0), 20);
        assertEquals(mainMemory.getContent(5), 55);
        assertEquals(mainMemory.getContent(4), 30);

        // Testes não iguas
        assertNotEquals(mainMemory.getContent(0), 1);
        assertNotEquals(mainMemory.getContent(5), 2);
        assertNotEquals(mainMemory.getContent(4), 3);
    }
}