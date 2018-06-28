package ufrn.alvarofpp.memory.cache;

import ufrn.alvarofpp.memory.MainMemory;
import ufrn.alvarofpp.memory.MemoryBase;
import ufrn.alvarofpp.replacement.*;

import java.util.ArrayList;

/**
 * Serve para simular o comportamento de uma memória cache
 */
public class Cache extends MemoryBase {
    /**
     * Tipo de mapeamento
     */
    private Mapeamento mapeamento;
    /**
     * Para caso seja parcialmente associativo
     */
    private int associativo;
    /**
     * Algoritmo/politica de substituição
     */
    private ReplacementBase substituicao;
    /**
     * Memória principal
     */
    private MainMemory mainMemory;
    /**
     * Salvar quando for HIT ou MISS
     */
    public ArrayList<MissHit> missHit;
    /**
     * LFU: Conterá a quantidade de vezes que cada linha foi usada.
     * LRU: Conterá a ordem de frequencia das linhas
     */
    public Integer[] aux;
    /**
     * Linhas de dados
     */
    public Integer[] dataLines;

    public Cache(MainMemory mainMemory, int qtdePalavras, int qtdeBlocos,
                 int mapeamento, int associativo, int substituicao) {
        super(qtdePalavras, qtdeBlocos);
        this.mapeamento = Mapeamento.define(mapeamento);
        this.associativo = associativo;

        // Define o algoritmo de subsituição
        if (this.mapeamento.equals(Mapeamento.DIRETO)) {
            this.substituicao = new Direct();
        } else {
            Replacement replacement = Replacement.define(substituicao);
            switch (replacement) {
                case FIFO:
                    this.substituicao = new FIFO();
                    break;
                case LFU:
                    this.substituicao = new LFU();
                    break;
                case LRU:
                    this.substituicao = new LRU();
                    break;
                default:
                    this.substituicao = new Random();
                    break;
            }
        }

        this.missHit = new ArrayList<>();
        this.dataLines = new Integer[qtdeBlocos];
        this.aux = new Integer[qtdeBlocos];

        for (int l = 0; l < this.qtdeBlocos; l++) {
            this.aux[l] = 0;
            this.dataLines[l] = -1;
        }

        this.mainMemory = mainMemory;
    }

    /**
     * Mostra o conteúdo de toda a memória cache
     */
    public void show() {
        System.out.println("CACHE L1");
        System.out.println("Linha-Bloco-Endereço-Conteúdo");
        int auxEnd;

        for (int l = 0; l < this.qtdeBlocos; l++) {
            // Caso seja lixo
            if (this.dataLines[l] == -1) {
                for (int e = 0; e < this.qtdePalavras; e++) {
                    System.out.println(l + " -  -  - ");
                }
                continue;
            }

            auxEnd = this.qtdePalavras*this.dataLines[l];
            for (int e = auxEnd; e < (auxEnd+this.qtdePalavras); e++) {
                System.out.println(l + " - " + this.dataLines[l] + " - " + e + " - "
                        + this.mainMemory.getContent(e));
            }
        }
    }

    /**
     * Ler o conteudo que está no endereço index
     * @param address Endereço de memória
     */
    public void read(Integer address) {
        // Variável para pegar linha atingida na cache
        int returnLine = this.replacement(address);

        if (this.missHit.get( this.missHit.size()-1 ).equals(MissHit.HIT)) {
            System.out.println("HIT linha " + returnLine);
        } else {
            System.out.println("MISS -> alocado na linha " + returnLine
                    + " -> bloco " + this.dataLines[returnLine] + " substituido");
        }

    }

    /**
     * Escreve na memória o valor "value" no endereço "address"
     * @param address Endereço de memória
     * @param value Valor
     */
    public void write(int address, int value) {
        int line = this.search(address);

        if (line != -1) {
            this.missHit.add(MissHit.HIT);
            this.aux[line] += 1;
        }

        this.mainMemory.setContent(address, value);

        // Variável que terá a linha da cache atingida
        int returnLine = this.replacement(address);

        if (this.missHit.get( this.missHit.size()-1 ).equals(MissHit.HIT)) {
            System.out.println("HIT linha " + returnLine
            + " -> novo valor do endereço " + address + "=" + value);
        } else {
            System.out.println("MISS -> alocado na linha " + returnLine
                    + " -> bloco " + this.dataLines[returnLine] + " substituido"
                    + " -> novo valor do endereço " + address + "=" + value);
        }
    }

    /**
     * Procura o endereço "address" na cache
     * @param address Endereço
     * @return Retorna a linha que o endereço se encontra ou -1 caso ela não esteja na cache
     */
    public int search(int address) {
        // Bloco que o conteudo esta
        int block = Integer.parseInt(String.valueOf(address/this.qtdePalavras));
        // Verifica se o bloco esta na cache
        for (int l = 0; l < this.qtdeBlocos; l++) {
            if (this.dataLines[l] == block) {
                return l;
            }
        }

        // Retorna -1 caso o bloco não esteja na cache
        return -1;
    }

    /**
     * Adiciona um valor ao ArrayList que contêm os Miss e Hit
     * @param mh Miss ou Hit
     */
    public void addMissHit(MissHit mh) {
        this.missHit.add(mh);
    }

    /**
     * Calcula a taxa de hit até o momento solicitado
     * @return Retorna a taxa de hit
     */
    public double hitPorcentagem() {
        double count = 0;

        for (MissHit mh : this.missHit) {
            if (mh.equals(MissHit.HIT)) {
                count++;
            }
        }

        return (count*100)/this.missHit.size();
    }

    /**
     * Realiza a operação de substituição de acordo com as configurações da cache.
     * @param address Endereço de memória.
     * @return Retorna a linha da cache que sofreu a ação.
     */
    private int replacement(int address) {
        return this.substituicao.execute(this, address);
    }
}
