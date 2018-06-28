package ufrn.alvarofpp.memory.cache;

/**
 * Servirá para contar a taxa de miss e hit na memória cache
 */
public enum MissHit {
    /**
     * Entradas válidas:
     * 1 - Miss
     * 2 - Hit
     */
    MISS(1),
    HIT(2);
    /**
     * Valor guardado
     */
    private int value;

    /**
     * Construtor
     *
     * @param value Valor que se deseja guardar
     */
    MissHit(int value) {
        this.value = value;
    }

    /**
     * Retorna o valor guardado
     *
     * @return Valor guardado
     */
    public int getValue() {
        return this.value;
    }
}
