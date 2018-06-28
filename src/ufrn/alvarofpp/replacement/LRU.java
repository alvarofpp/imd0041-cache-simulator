package ufrn.alvarofpp.replacement;

import ufrn.alvarofpp.memory.cache.Cache;

/**
 * Algoritmo LRU (Least-Recently Used).
 */
public class LRU extends ReplacementBase {
    @Override
    public int execute(Cache cache, int address) {
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
