package ufrn.alvarofpp.replacement;

import ufrn.alvarofpp.memory.cache.Cache;
import ufrn.alvarofpp.memory.cache.MissHit;

/**
 * Mapeamento direto
 */
public class Direct extends ReplacementBase {
    @Override
    public int execute(Cache cache, int address) {
        // Bloco que o conteudo esta
        int block = cache.getBlock(address);
        // Linha na cache que o conteudo pode estar
        int lineCache = Integer.parseInt(String.valueOf(block % cache.qtdeBlocos));

        // Verifica se o conteudo já está na cache
        if (cache.dataLines[lineCache] == block) {
            cache.addMissHit(MissHit.HIT);
        } else {
            cache.dataLines[lineCache] = block;
            cache.addMissHit(MissHit.MISS);
        }

        return lineCache;
    }
}
