import org.junit.Assert;
import java.util.Arrays;

class CardGameTest {

    @org.junit.jupiter.api.Test
    void fileReader() {
        String buffer = CardGame.fileReader("fileReaderTest.txt", 1);
        Assert.assertEquals(buffer, "8\n1\n4\n6\n8\n4\n6\n9\n");
    }

    @org.junit.jupiter.api.Test
    void cardArrayGenerator() {
        String[] expected = {"1", "2", "3"};
        String[] arrayGeneratorTest = CardGame.cardArrayGenerator("1\n2\n3\n");
        Assert.assertArrayEquals(expected, arrayGeneratorTest);
    }

    @org.junit.jupiter.api.Test
    void generatePeople() {
        Player[] playerArray = new Player[4];
        Player[] emptyPlayerArray = new Player[4];
        playerArray = CardGame.generatePeople(playerArray, 4);
        Assert.assertNotEquals(playerArray, emptyPlayerArray);
    }

    @org.junit.jupiter.api.Test
    void generateDecks() {
        CardDeck[] cardDeckArray = new CardDeck[4];
        CardDeck[] emptyCardDeckArray = new CardDeck[4];
        cardDeckArray = CardGame.generateDecks(cardDeckArray);
        Assert.assertNotEquals(cardDeckArray, emptyCardDeckArray);
    }

    @org.junit.jupiter.api.Test
    void cardDistribute() {
        Player[] playerArray = new Player[1];
        playerArray = CardGame.generatePeople(playerArray, 1);

        CardDeck[] deckArray = new CardDeck[1];
        deckArray = CardGame.generateDecks(deckArray);
        String[] cardArray = {"1", "2", "3", "4", "5", "6", "7", "8"};
        CardGame.cardDistribute(playerArray, cardArray, deckArray);

        Player player = playerArray[0];
        String playerHand = player.getPlayerHandValues();
        Assert.assertEquals(playerHand, " 4 3 2 1");

        CardDeck cardDeck = deckArray[0];
        int[] cards = cardDeck.getCardDeckValues();
        int[] expectedCards = {8, 7, 6, 5};
        Assert.assertEquals(Arrays.toString(cards), Arrays.toString(expectedCards));
    }

    @org.junit.jupiter.api.Test
    void getDeckArray() {
        CardGame cardGame = new CardGame();

        CardDeck[] deckArray = new CardDeck[1];
        deckArray = cardGame.generateDecks(deckArray);
        cardGame.deckArray = deckArray;

        CardDeck[] deckArrayGet = cardGame.getDeckArray();
        Assert.assertArrayEquals(deckArray, deckArrayGet);
    }

    @org.junit.jupiter.api.Test
    void getPlayerArray() {
        CardGame cardGame = new CardGame();

        Player[] playerArray = new Player[1];
        playerArray = cardGame.generatePeople(playerArray, 1);
        cardGame.playerArray = playerArray;

        Player[] playerArrayGet = cardGame.getPlayerArray();
        Assert.assertArrayEquals(playerArray, playerArrayGet);
    }

}