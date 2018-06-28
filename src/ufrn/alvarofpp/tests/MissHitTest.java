package ufrn.alvarofpp.tests;

import org.junit.jupiter.api.Test;
import ufrn.alvarofpp.memory.cache.MissHit;

import static org.junit.jupiter.api.Assertions.*;

class MissHitTest {

    @Test
    void getValue() {
        MissHit mhMiss = MissHit.MISS;
        MissHit mhHit = MissHit.HIT;

        // Testes iguais
        assertEquals(mhMiss.getValue(), MissHit.MISS.getValue());
        assertEquals(mhHit.getValue(), MissHit.HIT.getValue());

        // Testes não iguas
        assertNotEquals(mhMiss.getValue(), MissHit.HIT.getValue());
        assertNotEquals(mhHit.getValue(), MissHit.MISS.getValue());
    }
}