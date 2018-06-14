package ufrn.alvarofpp;

import ufrn.alvarofpp.memory.Cache;
import ufrn.alvarofpp.memory.Memory;
import ufrn.alvarofpp.parse.Parse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // Recolher as entradas
        Scanner scanner = new Scanner(System.in);
        // Para validar as entradas
        Parse parse = new Parse();
        // Mémoria principal e cache
        Memory memory;
        Cache cache;

        final String CONFIG = "/home/roleta/IdeaProjects/imd0041_cache_simulator/src/ufrn/alvarofpp/memory/config.txt";

        BufferedReader buffer = new BufferedReader(new FileReader(CONFIG));
        String line;
        ArrayList<Integer> configNumbers = new ArrayList<>();

        while ((line = buffer.readLine()) != null) {
            configNumbers.add(Integer.parseInt(line));
        }

        memory = new Memory(configNumbers.get(0), configNumbers.get(2));
        cache = new Cache(memory, configNumbers.get(0), configNumbers.get(1),
                configNumbers.get(3), configNumbers.get(4), configNumbers.get(5));

        // Recebendo entradas do usuário
        String input = "";
        String inputs[];

        while (!input.equals("Exit")) {
            input = scanner.nextLine();

            // Sair do simulador
            if (input.equals("Exit")){
                continue;
            }

            if (parse.validate(input)) {
                inputs = input.split(" ");
                switch (inputs[0]) {
                    case "Read":
                        cache.read(Integer.parseInt(inputs[1]));
                        break;
                    case "Write":
                        cache.write(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]));
                        break;
                    case "Show":
                        cache.show();
                        System.out.println();
                        memory.show();
                        System.out.println();
                        System.out.println("Taxa de hit: " + cache.hitPorcentagem() + "%");
                        break;
                }
            }
        }

    }
}
