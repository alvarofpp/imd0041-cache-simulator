package ufrn.alvarofpp.replacement;

import ufrn.alvarofpp.memory.cache.Cache;
import ufrn.alvarofpp.memory.cache.MissHit;

/**
 * Algoritmo LRU (Least-Recently Used).
 */
public class LRU extends ReplacementBase {
    @Override
    public int execute(Cache cache, int address) {
        // Bloco que o conteudo esta
        int block = cache.getBlock(address);
        // Indice do último usado
        int lastUsed = 0;

        // Verifica se o bloco já está na cache
        int line = cache.search(address);
        if (line > -1) {
            cache.addMissHit(MissHit.HIT);
            cache.aux[line] += 1;
            return line;
        }

        // Pega o último usado
        for (int l = 1; l < cache.dataLines.length; l++) {
            if (cache.aux[l] < cache.aux[lastUsed]) {
                lastUsed = l;
            }
        }

        // Pega o mais usado
        int maxValue = 0;
        for (int l = 0; l < cache.dataLines.length; l++) {
            if (cache.aux[l] > maxValue) {
                maxValue = cache.aux[l];
            }
        }

        cache.dataLines[lastUsed] = block;
        cache.aux[lastUsed] = maxValue + 1;
        cache.addMissHit(MissHit.MISS);

        return lastUsed;
    }
}
