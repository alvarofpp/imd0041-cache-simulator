package ufrn.alvarofpp;

import ufrn.alvarofpp.memory.Cache;
import ufrn.alvarofpp.memory.Memory;
import ufrn.alvarofpp.parse.Parse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
        Memory memory = null;
        Cache cache = null;

        final String CONFIG = "/home/roleta/IdeaProjects/imd0041_cache_simulator/src/ufrn/alvarofpp/memory/config.txt";

        BufferedReader buffer = new BufferedReader(new FileReader(CONFIG));
        String line;
        ArrayList<Integer> configNumbers = new ArrayList<>();

        while ((line = buffer.readLine()) != null) {
            configNumbers.add(Integer.parseInt(line));
        }

        memory = new Memory(configNumbers.get(0), configNumbers.get(2));
        cache = new Cache(configNumbers.get(0), configNumbers.get(1), memory);

        cache.show();
        memory.show();

    }
}
