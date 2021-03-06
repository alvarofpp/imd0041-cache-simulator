package ufrn.alvarofpp.memory.cache;

/**
 * Tipo de mapeamento
 */
public enum Mapeamento {
    /**
     * Entradas válidas:
     * 1 - Direto
     * 2 - Totalmente Associativo
     * 3 - Parcialmente Associativo
     */
    DIRETO(1),
    TOTALMENTE(2),
    PARCIALMENTE(3);
    /**
     * Tipo guardado
     */
    private int value;

    /**
     * Construtor
     *
     * @param value Tipo de mapeamento
     */
    Mapeamento(int value) {
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
     * Retorna o tipo de Mapeamento
     *
     * @param type Tipo que se deseja
     * @return Mapeamento
     */
    public static Mapeamento define(int type) {
        switch (type) {
            case 2:
                return TOTALMENTE;
            case 3:
                return PARCIALMENTE;
            default:
                return DIRETO;
        }
    }
}
