package ufrn.alvarofpp.tests;

import org.junit.jupiter.api.Test;
import ufrn.alvarofpp.memory.cache.Mapeamento;

import static org.junit.jupiter.api.Assertions.*;

class MapeamentoTest {

    @Test
    void getValue() {
        Mapeamento mapDireto = Mapeamento.DIRETO;
        Mapeamento mapTotalmente = Mapeamento.TOTALMENTE;
        Mapeamento mapParcialmente = Mapeamento.PARCIALMENTE;

        // Testes iguais
        assertEquals(mapDireto.getValue(), Mapeamento.DIRETO.getValue());
        assertEquals(mapTotalmente.getValue(), Mapeamento.TOTALMENTE.getValue());
        assertEquals(mapParcialmente.getValue(), Mapeamento.PARCIALMENTE.getValue());

        // Testes não iguas
        assertNotEquals(mapDireto.getValue(), Mapeamento.TOTALMENTE.getValue());
        assertNotEquals(mapTotalmente.getValue(), Mapeamento.PARCIALMENTE.getValue());
        assertNotEquals(mapParcialmente.getValue(), Mapeamento.DIRETO.getValue());
    }

    @Test
    void define() {
        Mapeamento mapDireto = Mapeamento.define(1);
        Mapeamento mapTotalmente = Mapeamento.define(2);
        Mapeamento mapParcialmente = Mapeamento.define(3);

        // Testes iguais
        assertEquals(mapDireto, Mapeamento.DIRETO);
        assertEquals(mapTotalmente, Mapeamento.TOTALMENTE);
        assertEquals(mapParcialmente, Mapeamento.PARCIALMENTE);

        // Testes não iguas
        assertNotEquals(mapDireto, Mapeamento.TOTALMENTE);
        assertNotEquals(mapTotalmente, Mapeamento.PARCIALMENTE);
        assertNotEquals(mapParcialmente, Mapeamento.DIRETO);
    }
}