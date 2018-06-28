package ufrn.alvarofpp.replacement;

import ufrn.alvarofpp.memory.cache.Cache;
import ufrn.alvarofpp.memory.cache.MissHit;

/**
 * Algoritmo LFU (Least-Frequently Used).
 */
public class LFU extends ReplacementBase {
    @Override
    public int execute(Cache cache, int address) {
        // Bloco que o conteudo esta
        int block = cache.getBlock(address);

        // Verifica se o bloco já está na cache
        int line = cache.search(address);
        if (line > -1) {
            cache.addMissHit(MissHit.HIT);
            cache.aux[line] += 1;
            return line;
        }

        // Indice do menos usado
        int leastUsed = 0;

        // Pega o menos usado
        for (int l = 1; l < cache.qtdeBlocos; l++) {
            if (cache.aux[l] < cache.aux[leastUsed]) {
                leastUsed = l;
            }
        }

        cache.dataLines[leastUsed] = block;
        cache.aux[leastUsed] = 1;
        cache.addMissHit(MissHit.MISS);

        return leastUsed;
    }
}
