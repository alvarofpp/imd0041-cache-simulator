package ufrn.alvarofpp.replacement;

import ufrn.alvarofpp.memory.cache.Cache;

/**
 * Classe base para as classes que irão implementar os algoritmos de substituição
 */
public abstract class ReplacementBase {

    /**
     * Executa o algoritmo de substituição correspondente
     * @param cache Memória cache
     * @param address Endereço de memória
     * @return Linha da cache que sofrerá a substituição
     */
    public abstract int execute(Cache cache, int address);
}
