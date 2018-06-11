package ufrn.alvarofpp.memory;

import java.util.*;

public class Cache {
    int qtdeLinhas;
    int qtdePalavras;
    Integer[] lines;
    Memory memory;

    public Cache(int qtdePalavras, int qtdeLinhas, Memory memory) {
        this.qtdeLinhas = qtdeLinhas;
        this.qtdePalavras = qtdePalavras;

        this.lines = new Integer[qtdeLinhas];

        for (int l = 0; l < this.qtdeLinhas; l++) {
            this.lines[l] = 0;
        }

        this.memory = memory;
    }

    public void show() {
        System.out.println("CACHE L1");
        System.out.println("Linha-Bloco-Endereço-Conteúdo");
        int auxEnd = 0;

        for (int l = 0; l < this.qtdeLinhas; l++) {
            auxEnd = this.qtdePalavras*this.lines[l];
            for (int e = auxEnd; e < (auxEnd+this.qtdePalavras); e++) {
                System.out.println(l + " - " + this.lines[l] + " - " + e + " - " + this.getContent(l, e));
            }
        }
    }

    private int getContent(int block, int address) {
        return this.memory.getContent(block, address);
    }
}
