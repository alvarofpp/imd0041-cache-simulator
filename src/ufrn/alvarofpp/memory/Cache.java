package ufrn.alvarofpp.memory;

import java.util.ArrayList;

public class Cache {
    /**
     * Quantidade de linhas que a cache conterá
     */
    int qtdeLinhas;
    /**
     * Quantidade de palavras para cada linha da cache
     */
    int qtdePalavras;
    /**
     * Tipo de mapeamento
     * 1 - Direto
     * 2 - Totalmente Associativo
     * 3 - Parcialmente Associativo
     */
    int mapeamento;
    /**
     * Para caso seja parcialmente associativo
     */
    int associativo;
    /**
     * Algoritmo de substituição
     * 1 - Aleatório
     * 2 - FIFO
     * 3 - LFU
     * 4 - LRU
     */
    int substituicao;
    /**
     * Linhas da memória cache
     */
    Integer[] lines;
    /**
     * Memória principal
     */
    Memory memory;
    /**
     * Salvar quando for HIT ou MISS
     * 0 - MISS
     * 1 - HIT
     */
    ArrayList<Integer> missHit;

    public Cache(Memory memory, int qtdePalavras, int qtdeLinhas,
                 int mapeamento, int associativo, int substituicao) {
        this.qtdeLinhas = qtdeLinhas;
        this.qtdePalavras = qtdePalavras;
        this.mapeamento = mapeamento;
        this.associativo = associativo;
        this.substituicao = substituicao;
        this.missHit = new ArrayList<>();

        this.lines = new Integer[qtdeLinhas];

        for (int l = 0; l < this.qtdeLinhas; l++) {
            this.lines[l] = -1;
        }

        this.memory = memory;
    }

    /**
     * Mostra o conteúdo de toda a memória cache
     */
    public void show() {
        System.out.println("CACHE L1");
        System.out.println("Linha-Bloco-Endereço-Conteúdo");
        int auxEnd = 0;

        for (int l = 0; l < this.qtdeLinhas; l++) {
            // Caso seja lixo
            if (this.lines[l] == -1) {
                for (int e = 0; e < this.qtdePalavras; e++) {
                    System.out.println(l + " -  -  - ");
                }
                continue;
            }

            auxEnd = this.qtdePalavras*this.lines[l];
            for (int e = auxEnd; e < (auxEnd+this.qtdePalavras); e++) {
                System.out.println(l + " - " + this.lines[l] + " - " + e + " - " + this.memory.getContent(e));
            }
        }
    }

    /**
     * Ler o conteudo que está no endereço index
     * @param index Endereço do conteudo que se quer ler
     */
    public void read(Integer index) {
        // Bloco que o conteudo estar
        int block = Integer.parseInt(String.valueOf(index/this.qtdePalavras));
        // Linha na cache que o conteudo pode estar
        int lineCache = Integer.parseInt(String.valueOf(index%this.qtdePalavras));

        // Verifica se o conteudo já está na cache
        if (this.lines[lineCache] == block) {
            System.out.println("HIT linha " + lineCache);
            this.missHit.add(1);
        } else {
            this.lines[lineCache] = block;
            System.out.println("MISS -> alocado na linha " + lineCache
                    + " -> bloco " + this.lines[lineCache] + " substituido");
            this.missHit.add(0);
        }
    }

    /**
     * Escreve na memória o valor "value" no endereço "address"
     * @param address Endereço
     * @param value Valor
     */
    public void write(int address, int value) {
        this.memory.setContent(address, value);
    }
}
