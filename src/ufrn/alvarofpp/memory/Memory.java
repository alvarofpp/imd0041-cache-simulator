package ufrn.alvarofpp.memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

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

    public int getContent(int block, int address) {
        return this.blocks[block][address];
    }
}
