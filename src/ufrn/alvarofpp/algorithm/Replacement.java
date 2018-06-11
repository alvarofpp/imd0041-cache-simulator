package ufrn.alvarofpp.algorithm;

import ufrn.alvarofpp.memory.Cache;

public class Replacement {
    public Replacement(){}

    /**
     * Quando é mapeamento direto
     * @param cache
     * @param address
     */
    public void direct(Cache cache, int address) {
        // Bloco que o conteudo esta
        int block = Integer.parseInt(String.valueOf(address/cache.qtdePalavras));
        // Linha na cache que o conteudo pode estar
        int lineCache = Integer.parseInt(String.valueOf(block%cache.qtdeLinhas));

        // Verifica se o conteudo já está na cache
        if (cache.lines[lineCache] == block) {
            System.out.println("HIT linha " + lineCache);
            cache.addMissHit(1);
        } else {
            cache.lines[lineCache] = block;
            System.out.println("MISS -> alocado na linha " + lineCache
                    + " -> bloco " + cache.lines[lineCache] + " substituido");
            cache.addMissHit(0);
        }
    }

    /**
     * Algoritmo LFU
     * @param cache
     * @param address
     */
    public void LFU(Cache cache, int address) {
        // Bloco que o conteudo esta
        int block = Integer.parseInt(String.valueOf(address/cache.qtdePalavras));
        // Indice do menos usado
        int leastUsed = 0;

        // Verifica se o bloco já está na cache
        int line = cache.search(address);
        if (line > -1) {
            System.out.println("HIT linha " + line);
            cache.addMissHit(1);
            cache.aux[line] += 1;
            return;
        }

        // Pega o menos usado
        for (int l = 1; l < cache.qtdeLinhas; l++) {
            if (cache.lines[l] < cache.lines[leastUsed]) {
                leastUsed = l;
            }
        }

        cache.lines[leastUsed] = block;
        cache.aux[leastUsed] = 1;
        System.out.println("MISS -> alocado na linha " + leastUsed
                + " -> bloco " + cache.lines[leastUsed] + " substituido");
        cache.addMissHit(0);
    }

    /**
     * Algoritmo LRU
     * @param cache
     * @param address
     */
    public void LRU(Cache cache, int address) {
        // Bloco que o conteudo esta
        int block = Integer.parseInt(String.valueOf(address/cache.qtdePalavras));
        // Indice do último usado
        int lastUsed = 0;

        // Verifica se o bloco já está na cache
        int line = cache.search(address);
        if (line > -1) {
            System.out.println("HIT linha " + line);
            cache.addMissHit(1);
            cache.aux[line] += 1;
            return;
        }

        // Pega o último usado
        for (int l = 1; l < cache.lines.length; l++) {
            if (cache.aux[l] < cache.aux[lastUsed]) {
                lastUsed = l;
            }
        }

        // Pega o mais usado
        int maxValue = 0;
        for (int l = 0; l < cache.lines.length; l++) {
            if (cache.aux[l] > maxValue) {
                maxValue = cache.aux[l];
            }
        }

        cache.lines[lastUsed] = block;
        cache.aux[lastUsed] = maxValue+1;
        System.out.println("MISS -> alocado na linha " + lastUsed
                + " -> bloco " + cache.lines[lastUsed] + " substituido");
        cache.addMissHit(0);
    }
}
