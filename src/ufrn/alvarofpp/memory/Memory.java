package ufrn.alvarofpp.memory;

public class Memory {
    int qtdeBlocos;
    int qtdePalavras;
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
     * Mostra toda a memória principal
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
     * @param address
     * @param value
     */
    public void setContent(int address, int value) {
        int block = Integer.parseInt(String.valueOf(address/this.qtdePalavras));
        this.blocks[block][(address%this.qtdePalavras)] = value;
    }

    /**
     * Pega o conteudo que está no endereço "address"
     * @param address
     * @return
     */
    public int getContent(int address) {
        // Bloco que o endereço está
        int block = Integer.parseInt(String.valueOf(address/this.qtdePalavras));
        
        return this.blocks[block][(address%this.qtdePalavras)];
    }
}
