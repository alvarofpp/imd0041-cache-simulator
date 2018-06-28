package ufrn.alvarofpp.memory;

/**
 * Serve para simular o comportamento da memória principal
 */
public class MainMemory extends MemoryBase {
    /**
     * Blocos de dados
     */
    private Integer[][] dataBlocks;

    public MainMemory(int qtdePalavras, int qtdeBlocos) {
        super(qtdePalavras, qtdeBlocos);

        // Inicializa os dados na memória
        this.dataBlocks = new Integer[qtdeBlocos][qtdePalavras];

        for (int b = 0; b < this.qtdeBlocos; b++) {
            for (int e = 0; e < this.qtdePalavras; e++) {
                this.dataBlocks[b][e] = 0;
            }
        }
    }

    /**
     * Mostra o conteúdo de toda a memória principal
     */
    public void show() {
        System.out.println("MEMORIA PRINCIPAL");
        System.out.println("Bloco-Endereço-Conteúdo");
        int count = 0;

        for (int b = 0; b < this.qtdeBlocos; b++) {
            for (int e = 0; e < this.qtdePalavras; e++) {
                System.out.println(b + " - " + count + " - " + this.dataBlocks[b][e]);
                count++;
            }
        }
    }

    /**
     * Modifica o valor que está no endereço "address" por "value"
     *
     * @param address Endereço
     * @param value   Valor
     */
    public void setContent(int address, int value) {
        int block = this.getBlock(address);

        this.dataBlocks[block][(address % this.qtdePalavras)] = value;
    }

    /**
     * Pega o conteudo que está no endereço "address"
     *
     * @param address Endereço de memória
     * @return Retorna o conteúdo que o endereço contêm
     */
    public int getContent(int address) {
        int block = this.getBlock(address);

        return this.dataBlocks[block][(address % this.qtdePalavras)];
    }
}
