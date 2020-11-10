import org.junit.Assert;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardGameTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        CardGame CardGame = new CardGame();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void main() {

    }

    @org.junit.jupiter.api.Test
    void fileReader() {

    }

    @org.junit.jupiter.api.Test
    void cardArrayGenerator() {

        String[] expected = {"1", "2", "3"};

        String[] arrayGeneratorTest = CardGame.cardArrayGenerator("1/n2/n3/n");
        Assert.assertArrayEquals(expected, arrayGeneratorTest);
    }

    @org.junit.jupiter.api.Test
    void generatePeople() {
    }

    @org.junit.jupiter.api.Test
    void generateDecks() {
    }

    @org.junit.jupiter.api.Test
    void getDeckArray() {
    }

    @org.junit.jupiter.api.Test
    void getPlayerArray() {
    }

    @org.junit.jupiter.api.Test
    void getAtomicBool() {
    }

    @org.junit.jupiter.api.Test
    void cardDistribute() {
    }

    @org.junit.jupiter.api.Test
    void terminateDecks() {
    }
}