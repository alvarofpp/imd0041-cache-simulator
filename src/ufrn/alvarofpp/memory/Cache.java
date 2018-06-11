package ufrn.alvarofpp.memory;

import java.util.ArrayList;

public class Cache {
    int qtdeLinhas;
    int qtdePalavras;
    int mapeamento;
    int associativo;
    int substituicao;
    Integer[] lines;
    Memory memory;
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

    public void show() {
        System.out.println("CACHE L1");
        System.out.println("Linha-Bloco-Endereço-Conteúdo");
        int auxEnd = 0;

        for (int l = 0; l < this.qtdeLinhas; l++) {
            if (this.lines[l] == -1) {
                for (int e = 0; e < this.qtdePalavras; e++) {
                    System.out.println(l + " - null - null - null");
                }
                continue;
            }

            auxEnd = this.qtdePalavras*this.lines[l];
            for (int e = auxEnd; e < (auxEnd+this.qtdePalavras); e++) {
                System.out.println(l + " - " + this.lines[l] + " - " + e + " - " + this.getContent(e));
            }
        }
    }

    public void read(Integer index) {
        int block = Integer.parseInt(String.valueOf(index/this.qtdePalavras));
        int lineCache = Integer.parseInt(String.valueOf(index%this.qtdePalavras));

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

    public void write(int index, int value) {
        this.memory.setContent(index, value);
    }
    private int getContent(int address) {
        return this.memory.getContent(address);
    }
}
