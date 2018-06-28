package ufrn.alvarofpp.memory;

/**
 * Caracteristicas básicas de toda memória
 */
abstract class MemoryBase {
    /**
     * Quantidade de blocos que a memória principal conterá
     */
    int qtdeBlocos;
    /**
     * Quantidade de palavras para cada bloco da memória principal
     */
    int qtdePalavras;
    /**
     * Blocos da memória principal
     */
    Integer[][] dataBlocks;

    MemoryBase(int qtdePalavras, int qtdeBlocos) {
        this.qtdePalavras = qtdePalavras;
        this.qtdeBlocos = qtdeBlocos;

        // Inicializa os dados na memória
        this.dataBlocks = new Integer[qtdeBlocos][qtdePalavras];

        for (int b = 0; b < this.qtdeBlocos; b++) {
            for (int e = 0; e < this.qtdePalavras; e++) {
                this.dataBlocks[b][e] = 0;
            }
        }
    }

    /**
     * Pega o indice do bloco que o endereço de memória se encontra
     * @param address Endereço de memória
     * @return Indice do bloco
     */
    int getIndexBlock(int address) {
        return Integer.parseInt(String.valueOf(address/this.qtdePalavras));
    }
}
