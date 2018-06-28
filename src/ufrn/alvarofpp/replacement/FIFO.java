package ufrn.alvarofpp.replacement;

import ufrn.alvarofpp.memory.cache.Cache;

/**
 * Algoritmo FIFO (First-In First-Out)
 */
public class FIFO extends ReplacementBase {
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
}
