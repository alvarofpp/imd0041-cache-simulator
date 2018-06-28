package ufrn.alvarofpp.replacement;

import ufrn.alvarofpp.memory.cache.Cache;

/**
 * Algoritmo de substituição randômico
 */
public class Random extends ReplacementBase {
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

        // Verifica se existe algum espaço vazio na cache
        for (int i = 0; i < cache.qtdeLinhas; i++) {
            if (cache.lines[i] == -1) {
                cache.addMissHit(0);
                cache.lines[i] = block;
                return i;
            }
        }

        // Número aleatório
        java.util.Random random = new java.util.Random();
        int randomLine = random.nextInt(cache.qtdeLinhas);

        cache.lines[randomLine] = block;
        cache.addMissHit(0);

        return randomLine;
    }
}
