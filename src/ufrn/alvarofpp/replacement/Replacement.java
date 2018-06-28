package ufrn.alvarofpp.replacement;

/**
 * Tipos de algoritmos de substituição válidos
 */
public enum Replacement {
    /**
     * Entradas válidas:
     * 1 - Aleatório
     * 2 - FIFO
     * 3 - LFU
     * 4 - LRU
     */
    ALEATORIO(1),
    FIFO(2),
    LFU(3),
    LRU(4);
    /**
     * Tipo guardado
     */
    private int value;

    /**
     * Construtor
     *
     * @param value Tipo de mapeamento
     */
    Replacement(int value) {
        this.value = value;
    }

    /**
     * Retorna o tipo guardado
     *
     * @return Tipo guardado
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Retorna o tipo de algoritmo de substituição
     *
     * @param type Tipo que se deseja
     * @return Algoritmo de substituição
     */
    public static Replacement define(int type) {
        switch (type) {
            case 2:
                return FIFO;
            case 3:
                return LFU;
            case 4:
                return LRU;
            default:
                return ALEATORIO;
        }
    }
}
