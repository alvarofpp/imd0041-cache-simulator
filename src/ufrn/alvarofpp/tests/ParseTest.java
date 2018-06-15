package ufrn.alvarofpp.tests;

import org.junit.jupiter.api.Test;
import ufrn.alvarofpp.parse.Parse;

import static org.junit.jupiter.api.Assertions.*;

class ParseTest {

    @Test
    void validate() {
        Parse parse = new Parse();

        // Testes positivos
        assertTrue(parse.validate("Show"));
        assertTrue(parse.validate("Read 25"));
        assertTrue(parse.validate("Write 25 41"));

        // Testes negativos
        assertFalse(parse.validate("Debug"));
        assertFalse(parse.validate("Show 25"));
        assertFalse(parse.validate("Read 2t"));
        assertFalse(parse.validate("Write 25 xx"));
    }
}
