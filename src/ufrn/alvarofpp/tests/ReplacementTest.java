package ufrn.alvarofpp.tests;

import org.junit.jupiter.api.Test;
import ufrn.alvarofpp.replacement.Replacement;

import static org.junit.jupiter.api.Assertions.*;

class ReplacementTest {

    @Test
    void getValue() {
        Replacement repAleatorio = Replacement.ALEATORIO;
        Replacement repFifo = Replacement.FIFO;
        Replacement repLfu = Replacement.LFU;
        Replacement repLru = Replacement.LRU;

        // Testes iguais
        assertEquals(repAleatorio.getValue(), Replacement.ALEATORIO.getValue());
        assertEquals(repFifo.getValue(), Replacement.FIFO.getValue());
        assertEquals(repLfu.getValue(), Replacement.LFU.getValue());
        assertEquals(repLru.getValue(), Replacement.LRU.getValue());

        // Testes não iguas
        assertNotEquals(repAleatorio.getValue(), Replacement.LRU.getValue());
        assertNotEquals(repFifo.getValue(), Replacement.ALEATORIO.getValue());
        assertNotEquals(repLfu.getValue(), Replacement.FIFO.getValue());
        assertNotEquals(repLru.getValue(), Replacement.LFU.getValue());
    }

    @Test
    void define() {
        Replacement repAleatorio = Replacement.define(1);
        Replacement repFifo = Replacement.define(2);
        Replacement repLfu = Replacement.define(3);
        Replacement repLru = Replacement.define(4);

        // Testes iguais
        assertEquals(repAleatorio, Replacement.ALEATORIO);
        assertEquals(repFifo, Replacement.FIFO);
        assertEquals(repLfu, Replacement.LFU);
        assertEquals(repLru, Replacement.LRU);

        // Testes não iguas
        assertNotEquals(repAleatorio, Replacement.LRU);
        assertNotEquals(repFifo, Replacement.ALEATORIO);
        assertNotEquals(repLfu, Replacement.FIFO);
        assertNotEquals(repLru, Replacement.LFU);
    }
}