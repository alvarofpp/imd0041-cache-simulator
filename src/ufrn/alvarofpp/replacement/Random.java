package ufrn.alvarofpp.replacement;

import ufrn.alvarofpp.memory.cache.Cache;
import ufrn.alvarofpp.memory.cache.MissHit;

/**
 * Algoritmo de substituição randômico
 */
public class Random extends ReplacementBase {
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

        // Verifica se existe algum espaço vazio na cache
        for (int i = 0; i < cache.qtdeBlocos; i++) {
            if (cache.dataLines[i] == -1) {
                cache.addMissHit(MissHit.MISS);
                cache.dataLines[i] = block;
                return i;
            }
        }

        // Número aleatório
        java.util.Random random = new java.util.Random();
        int randomLine = random.nextInt(cache.qtdeBlocos);

        cache.dataLines[randomLine] = block;
        cache.addMissHit(MissHit.MISS);

        return randomLine;
    }
}
