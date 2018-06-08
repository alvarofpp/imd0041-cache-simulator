package ufrn.alvarofpp.memory;

import java.util.HashMap;

public class Memory {
    HashMap<Integer, HashMap<Integer, Integer>> memory;
    int qtdeBlocos;
    int qtdePalavras;

    public Memory(int qtdeBlocos, int qtdePalavras) {
        this.qtdeBlocos = qtdeBlocos;
        this.qtdePalavras = qtdePalavras;
    }

}
