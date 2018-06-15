package ufrn.alvarofpp.tests;

import org.junit.jupiter.api.Test;
import ufrn.alvarofpp.memory.Cache;
import ufrn.alvarofpp.memory.Memory;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    @Test
    void search() {
        Memory memory = new Memory(4, 16);
        Cache cache = new Cache(memory, 4, 8, 1, 1, 1);

        cache.read(0);
        cache.read(4);
        cache.read(8);

        // Testando endereços inválidos
        assertEquals(cache.search(20), -1);

        // Testando endereços validos
        assertEquals(cache.search(1), 0);
        assertEquals(cache.search(5), 1);
        assertEquals(cache.search(9), 2);

        // Testando endereços validos, mas em linhas diferentes
        assertNotEquals(cache.search(0), 1);
        assertNotEquals(cache.search(4), 2);
        assertNotEquals(cache.search(8), 3);
    }

    @Test
    void hitPorcentagem() {
        Memory memory = new Memory(4, 16);
        Cache cache = new Cache(memory, 4, 8, 1, 1, 1);

        cache.write(4, 20);
        assertEquals(cache.hitPorcentagem(), 0.0);
        assertNotEquals(cache.hitPorcentagem(), 75.0);
        cache.write(1, 30);
        assertEquals(cache.hitPorcentagem(), 0.0);
        assertNotEquals(cache.hitPorcentagem(), 50.0);
        cache.read(4);
        assertNotEquals(cache.hitPorcentagem(), 25.0);
        cache.read(1);
        assertEquals(cache.hitPorcentagem(), 50.0);
        assertNotEquals(cache.hitPorcentagem(), 0.0);
    }
}