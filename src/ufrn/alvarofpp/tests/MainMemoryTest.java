package ufrn.alvarofpp.tests;

import org.junit.jupiter.api.Test;
import ufrn.alvarofpp.memory.Memory;

import static org.junit.jupiter.api.Assertions.*;

class MemoryTest {

    @Test
    void getContent() {
        Memory memory = new Memory(4, 16);
        // Alimenta a memória principal
        memory.setContent(0, 20);
        memory.setContent(5, 55);
        memory.setContent(4, 30);

        // Testes iguais
        assertEquals(memory.getContent(0), 20);
        assertEquals(memory.getContent(5), 55);
        assertEquals(memory.getContent(4), 30);

        // Testes não iguas
        assertNotEquals(memory.getContent(0), 1);
        assertNotEquals(memory.getContent(5), 2);
        assertNotEquals(memory.getContent(4), 3);
    }
}