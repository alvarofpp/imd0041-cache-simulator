package ufrn.alvarofpp.replacement;

import ufrn.alvarofpp.memory.cache.Cache;

/**
 * Mapeamento direto
 */
public class Direct extends ReplacementBase {
    @Override
    public int execute(Cache cache, int address) {
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
}
