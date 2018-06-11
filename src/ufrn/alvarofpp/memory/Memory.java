package ufrn.alvarofpp.memory;

public class Memory {
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
    Integer[][] blocks;

    public Memory(int qtdePalavras, int qtdeBlocos) {
        this.qtdeBlocos = qtdeBlocos;
        this.qtdePalavras = qtdePalavras;
        this.blocks = new Integer[qtdeBlocos][qtdePalavras];

        for (int b = 0; b < this.qtdeBlocos; b++) {
            for (int e = 0; e < this.qtdePalavras; e++) {
                this.blocks[b][e] = 0;
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
                System.out.println(b + " - " + count + " - " + this.blocks[b][e]);
                count++;
            }
        }
    }

    /**
     * Modifica o valor que está no endereço "address" por "value"
     * @param address Endereço
     * @param value Valor
     */
    public void setContent(int address, int value) {
        int block = Integer.parseInt(String.valueOf(address/this.qtdePalavras));
        this.blocks[block][(address%this.qtdePalavras)] = value;
    }

    /**
     * Pega o conteudo que está no endereço "address"
     * @param address Endereço
     * @return Retorna o conteúdo que o endereço contêm
     */
    public int getContent(int address) {
        // Bloco que o endereço está
        int block = Integer.parseInt(String.valueOf(address/this.qtdePalavras));

        return this.blocks[block][(address%this.qtdePalavras)];
    }
}
