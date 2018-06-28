package ufrn.alvarofpp.memory;

/**
 * Caracteristicas básicas de toda memória
 */
public abstract class MemoryBase {
    /**
     * Quantidade de blocos que a memória conterá
     */
    public int qtdeBlocos;
    /**
     * Quantidade de palavras para cada bloco da memória
     */
    protected int qtdePalavras;

    public MemoryBase(int qtdePalavras, int qtdeBlocos) {
        this.qtdePalavras = qtdePalavras;
        this.qtdeBlocos = qtdeBlocos;
    }

    /**
     * Pega o indice do bloco que o endereço de memória se encontra
     *
     * @param address Endereço de memória
     * @return Indice do bloco
     */
    public int getBlock(int address) {
        return Integer.parseInt(String.valueOf(address / this.qtdePalavras));
    }
}
