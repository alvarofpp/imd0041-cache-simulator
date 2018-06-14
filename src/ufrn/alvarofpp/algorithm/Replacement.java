package ufrn.alvarofpp.algorithm;

import ufrn.alvarofpp.memory.Cache;

import java.util.Random;

/**
 * Serve para realizar os algoritmos de substituição na memória cache.
 */
public class Replacement {
    /**
     * Construtor.
     * Como não possui variáveis, o construtor é vazio.
     */
    public Replacement(){}

    /**
     * Quando é mapeamento direto.
     * @param cache Memória cache
     * @param address Endereço de memória
     */
    public int direct(Cache cache, int address) {
        // Bloco que o conteudo esta
        int block = Integer.parseInt(String.valueOf(address/cache.qtdePalavras));
        // Linha na cache que o conteudo pode estar
        int lineCache = Integer.parseInt(String.valueOf(block%cache.qtdeLinhas));

        // Verifica se o conteudo já está na cache
        if (cache.lines[lineCache] == block) {
            cache.addMissHit(1);
        } else {
            cache.lines[lineCache] = block;
            cache.addMissHit(0);
        }

        return lineCache;
    }

    /**
     * Algoritmo de aleatoriedade.
     * @param cache Memória cache
     * @param address Endereço de memória
     * @return Retorna a linha da cache afetada
     */
    public int random(Cache cache, int address) {
        // Bloco que o conteudo esta
        int block = Integer.parseInt(String.valueOf(address/cache.qtdePalavras));

        // Verifica se o bloco já está na cache
        int line = cache.search(address);
        if (line > -1) {
            cache.addMissHit(1);
            cache.aux[line] += 1;
            return line;
        }

        // Verifica se existe algum espaço vazio na cache
        for (int i = 0; i < cache.qtdeLinhas; i++) {
            if (cache.lines[i] == -1) {
                cache.addMissHit(0);
                cache.lines[i] = block;
                return i;
            }
        }

        // Número aleatório
        Random random = new Random();
        int randomLine = random.nextInt(cache.qtdeLinhas);

        cache.lines[randomLine] = block;
        cache.addMissHit(0);

        return randomLine;
    }

    /**
     * Algoritmo FIFO (First-In First-Out).
     * @param cache Memória cache
     * @param address Endereço de memória
     * @return Retorna a linha da cache afetada
     */
    public int fifo(Cache cache, int address) {
        // Bloco que o conteudo esta
        int block = Integer.parseInt(String.valueOf(address/cache.qtdePalavras));

        // Verifica se o bloco já está na cache
        int line = cache.search(address);
        if (line > -1) {
            cache.addMissHit(1);
            cache.aux[line] += 1;
            return line;
        }

        // Indice do primeiro a entrar usado
        int firstInput = 0;

        // Pega o último usado
        for (int l = 1; l < cache.lines.length; l++) {
            if (cache.aux[l] < cache.aux[firstInput]) {
                firstInput = l;
            }
        }

        // Pega o último a entrar
        int maxValue = 0;
        for (int l = 0; l < cache.lines.length; l++) {
            if (cache.aux[l] > maxValue) {
                maxValue = cache.aux[l];
            }
        }

        cache.lines[firstInput] = block;
        cache.aux[firstInput] = maxValue+1;
        cache.addMissHit(0);

        return firstInput;
    }

    /**
     * Algoritmo LFU (Least-Frequently Used).
     * @param cache Memória cache
     * @param address Endereço de memória
     * @return Retorna a linha da cache afetada
     */
    public int lfu(Cache cache, int address) {
        // Bloco que o conteudo esta
        int block = Integer.parseInt(String.valueOf(address/cache.qtdePalavras));

        // Verifica se o bloco já está na cache
        int line = cache.search(address);
        if (line > -1) {
            cache.addMissHit(1);
            cache.aux[line] += 1;
            return line;
        }

        // Indice do menos usado
        int leastUsed = 0;

        // Pega o menos usado
        for (int l = 1; l < cache.qtdeLinhas; l++) {
            if (cache.aux[l] < cache.aux[leastUsed]) {
                leastUsed = l;
            }
        }

        cache.lines[leastUsed] = block;
        cache.aux[leastUsed] = 1;
        cache.addMissHit(0);

        return leastUsed;
    }

    /**
     * Algoritmo LRU (Least-Recently Used).
     * @param cache Memória cache
     * @param address Endereço de memória
     * @return Retorna a linha da cache afetada
     */
    public int lru(Cache cache, int address) {
        // Bloco que o conteudo esta
        int block = Integer.parseInt(String.valueOf(address/cache.qtdePalavras));
        // Indice do último usado
        int lastUsed = 0;

        // Verifica se o bloco já está na cache
        int line = cache.search(address);
        if (line > -1) {
            cache.addMissHit(1);
            cache.aux[line] += 1;
            return line;
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
        cache.addMissHit(0);

        return lastUsed;
    }
}
