package ufrn.alvarofpp.replacement;

import ufrn.alvarofpp.memory.cache.Cache;
import ufrn.alvarofpp.memory.cache.MissHit;

/**
 * Algoritmo FIFO (First-In First-Out)
 */
public class FIFO extends ReplacementBase {
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

        // Indice do primeiro a entrar usado
        int firstInput = 0;

        // Pega o último usado
        for (int l = 1; l < cache.dataLines.length; l++) {
            if (cache.aux[l] < cache.aux[firstInput]) {
                firstInput = l;
            }
        }

        // Pega o último a entrar
        int maxValue = 0;
        for (int l = 0; l < cache.dataLines.length; l++) {
            if (cache.aux[l] > maxValue) {
                maxValue = cache.aux[l];
            }
        }

        cache.dataLines[firstInput] = block;
        cache.aux[firstInput] = maxValue + 1;
        cache.addMissHit(MissHit.MISS);

        return firstInput;
    }
}
