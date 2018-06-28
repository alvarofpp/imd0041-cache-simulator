package ufrn.alvarofpp.replacement;

import ufrn.alvarofpp.memory.cache.Cache;

/**
 * Algoritmo LFU (Least-Frequently Used).
 */
public class LFU extends ReplacementBase {
    @Override
    public int execute(Cache cache, int address) {
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
}
