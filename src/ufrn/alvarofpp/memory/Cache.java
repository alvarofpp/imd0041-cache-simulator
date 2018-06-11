package ufrn.alvarofpp.memory;

import ufrn.alvarofpp.algorithm.Replacement;

import java.util.ArrayList;
import java.util.Stack;

public class Cache {
    /**
     * Quantidade de linhas que a cache conterá
     */
    public int qtdeLinhas;
    /**
     * Quantidade de palavras para cada linha da cache
     */
    public int qtdePalavras;
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
     * Algoritmo/politica de substituição
     * 1 - Aleatório
     * 2 - FIFO
     * 3 - LFU
     * 4 - LRU
     */
    int substituicao;
    /**
     * Linhas da memória cache
     */
    public Integer[] lines;
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
    /**
     * LFU: Conterá a quantidade de vezes que cada linha foi usada.
     * LRU: Conterá a ordem de frequencia das linhas
     */
    public Integer[] aux;

    public Cache(Memory memory, int qtdePalavras, int qtdeLinhas,
                 int mapeamento, int associativo, int substituicao) {
        this.qtdeLinhas = qtdeLinhas;
        this.qtdePalavras = qtdePalavras;
        this.mapeamento = mapeamento;
        this.associativo = associativo;
        this.substituicao = substituicao;

        this.missHit = new ArrayList<>(qtdeLinhas);
        this.lines = new Integer[qtdeLinhas];
        this.aux = new Integer[qtdeLinhas];

        for (int l = 0; l < this.qtdeLinhas; l++) {
            this.aux[l] = 0;
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
                System.out.println(l + " - " + this.lines[l] + " - " + e + " - "
                        + this.memory.getContent(e));
            }
        }
    }

    /**
     * Ler o conteudo que está no endereço index
     * @param address Endereço do conteudo que se quer ler
     */
    public void read(Integer address) {
        // Algoritmo de substituição
        Replacement replacement = new Replacement();

        // Variável para pegar linha atingida na cache
        int returnLine = 0;

        if (this.mapeamento == 1) {
            returnLine = replacement.direct(this, address);
        } else if (this.mapeamento == 2) {
            switch (this.substituicao) {
                case 1:
                    returnLine = replacement.random(this, address);
                    break;
                case 2:
                    returnLine = replacement.FIFO(this, address);
                    break;
                case 3:
                    returnLine = replacement.LFU(this, address);
                    break;
                case 4:
                    returnLine = replacement.LRU(this, address);
                    break;
                default:break;
            }
        }

        if (this.missHit.get( this.missHit.size()-1 ) == 1) {
            System.out.println("HIT linha " + returnLine);
        } else {
            System.out.println("MISS -> alocado na linha " + returnLine
                    + " -> bloco " + this.lines[returnLine] + " substituido");
        }

    }

    /**
     * Escreve na memória o valor "value" no endereço "address"
     * @param address Endereço
     * @param value Valor
     */
    public void write(int address, int value) {
        int line = this.search(address);

        if (line != -1) {
            this.missHit.add(1);
            this.aux[line] += 1;
        } else {
            this.missHit.add(0);
        }

        this.memory.setContent(address, value);

        // Substituição
        Replacement replacement = new Replacement();

        // Variável que terá a linha da cache atingida
        int returnLine = 0;

        if (this.mapeamento == 1) {
            returnLine = replacement.direct(this, address);
        } else if (this.mapeamento == 2) {
            switch (this.substituicao) {
                case 1:
                    returnLine = replacement.random(this, address);
                    break;
                case 2:
                    returnLine = replacement.FIFO(this, address);
                    break;
                case 3:
                    returnLine = replacement.LFU(this, address);
                    break;
                case 4:
                    returnLine = replacement.LRU(this, address);
                    break;
                default:break;
            }
        }

        if (this.missHit.get( this.missHit.size()-1 ) == 1) {
            System.out.println("HIT linha " + returnLine
            + " -> novo valor do endereço " + address + "=" + value);
        } else {
            System.out.println("MISS -> alocado na linha " + returnLine
                    + " -> bloco " + this.lines[returnLine] + " substituido"
                    + " -> novo valor do endereço " + address + "=" + value);
        }
    }

    /**
     * Procura o endereço "address" na cache
     * @param address Endereço
     * @return Retorna a linha que o endereço se encontra ou -1 caso ela não esteja na cache
     */
    public int search(int address) {
        // Bloco que o conteudo esta
        int block = Integer.parseInt(String.valueOf(address/this.qtdePalavras));
        // Verifica se o bloco esta na cache
        for (int l = 0; l < this.qtdeLinhas; l++) {
            if (this.lines[l] == block) {
                return l;
            }
        }

        // Retorna -1 caso o bloco não esteja na cache
        return -1;
    }

    /**
     * Adiciona um valor ao ArrayList que contêm os Miss e Hit
     * @param value
     */
    public void addMissHit(int value) {
        this.missHit.add(value);
    }

    /**
     * Calcula a taxa de hit até o momento solicitado
     * @return Retorna a taxa de hit
     */
    public double hitPorcentagem() {
        double count = 0;

        for (int h = 0; h < this.missHit.size(); h++) {
            if (this.missHit.get(h) == 1) {
                count++;
            }
        }

        return (count*100)/this.missHit.size();
    }
}
