package ufrn.alvarofpp;

import ufrn.alvarofpp.memory.MainMemory;
import ufrn.alvarofpp.memory.cache.Cache;
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
        MainMemory mainMemory;
        Cache cache;

        final String CONFIG = "/home/roleta/IdeaProjects/imd0041_cache_simulator/src/ufrn/alvarofpp/memory/configs/config.txt";

        BufferedReader buffer = new BufferedReader(new FileReader(CONFIG));
        String line;
        ArrayList<Integer> configNumbers = new ArrayList<>();

        while ((line = buffer.readLine()) != null) {
            configNumbers.add(Integer.parseInt(line));
        }

        mainMemory = new MainMemory(configNumbers.get(0), configNumbers.get(2));
        cache = new Cache(mainMemory, configNumbers.get(0), configNumbers.get(1),
                configNumbers.get(3), configNumbers.get(4), configNumbers.get(5));

        // Recebendo entradas do usuário
        String input = "";
        String inputs[];

        while (!input.equals("Exit")) {
            input = scanner.nextLine();

            // Sair do simulador
            if (input.equals("Exit")) {
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
                        mainMemory.show();
                        System.out.println();
                        System.out.println("Taxa de hit: " + cache.hitPorcentagem() + "%");
                        break;
                }
            }
        }

    }
}
