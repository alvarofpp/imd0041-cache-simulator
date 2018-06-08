package ufrn.alvarofpp.parse;

import java.util.HashMap;

public class Parse {
    /**
     * As entradas válidas. Elas estão organizadas da seguinte maneira:
     *   String: comando válido | Integer: quantidade de argumentos.
     *   Exemplo: o comando "Read" exige "2" argumentos, o próprio "Read" e um extra.
     */
    HashMap<String, Integer> comands;

    public Parse() {
        comands = new HashMap<String, Integer>() {{
            put("Read", 2);
            put("Write", 3);
            put("Show", 1);
        }};
    }

    /**
     * Valida o input
     * @param input
     * @return
     */
    public boolean validate(String input) {
        String inputs[] = input.split(" ");

        // Verifica se o comando existe
        if (!this.comands.containsKey(inputs[0])) {
            System.err.println("Comando '" + input + "' não existe!");
            return false;
        }

        // Verifica se a quantidade de argumentos extras está satisfazendo o comando
        if (inputs.length != this.comands.get(inputs[0])) {
            System.err.println("Comando '" + input + "' precisa de "
                    + (this.comands.get(inputs[0])-1) + " argumentos extras");
            return false;
        }

        // Verifica se os argumentos extras são válidos
        for (int i = 1; i < this.comands.get(inputs[0]); i++) {
            if (!this.isInteger(inputs[i])) {
                System.err.println("Argumento '" + inputs[i] + "' é inválido!");
                return false;
            }
        }

        return true;
    }

    /**
     * Verifica se o input é inteiro
     * @param input
     * @return
     */
    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
}
