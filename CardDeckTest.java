import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class CardDeckTest {

    @Test
    void getDeckPosition() {
        CardDeck deck = new CardDeck(0);
        Assert.assertEquals(0, deck.getDeckPosition());
    }

    @Test
    void addTopCard() {
        CardDeck deck = new CardDeck(0);
        Card card = new Card(1);
        deck.addTopCard(card);
        String values = Arrays.toString(deck.getCardDeckValues());
        String expected = "[1]";
        Assert.assertEquals(expected, values);
    }

    @Test
    void createCardDeck() {
        CardDeck deck = new CardDeck(0);
        ArrayList<Card> cardArray = deck.createCardDeck();
        deck.cardArray = cardArray;
        ArrayList<Card> cardArrayCompare = deck.getCardArray();
        Assert.assertEquals(cardArrayCompare, cardArray);
    }

    @Test
    void getTopCard() {
        CardDeck deck = new CardDeck(0);
        Card card = new Card(1);
        Card wrongCard = new Card(9);
        deck.addTopCard(wrongCard);
        deck.addTopCard(card);
        int[] cardArray = deck.getCardDeckValues();
        int[] expected = {1, 9};
        Assert.assertArrayEquals(expected, cardArray);
    }

    @Test
    void addBottomCard() {
        CardDeck deck = new CardDeck(0);
        Card card = new Card(1);
        Card wrongCard = new Card(9);
        deck.addBottomCard(wrongCard);
        deck.addBottomCard(card);
        int[] cardArray = deck.getCardDeckValues();
        int[] expected = {9, 1};
        Assert.assertArrayEquals(expected, cardArray);
    }

    @Test
    void getCardArray() {
        CardDeck deck = new CardDeck(0);
        Card card = new Card(1);
        deck.addTopCard(card);
        Assert.assertEquals(0, deck.getDeckPosition());
    }

    @Test
    void getCardDeckValues() {
        CardDeck deck = new CardDeck(0);
        ArrayList<Card> cardArray = deck.createCardDeck();
        deck.cardArray = cardArray;
        ArrayList<Card> cardArrayCompare = deck.getCardArray();
        Assert.assertEquals(cardArray, cardArrayCompare);
    }

    @Test
    void createFile() {
        CardDeck deck = new CardDeck(0);
        deck.createFile();
        boolean exists = false;
        try{
            File myObj = new File("testCreateFile.txt");
            if (!myObj.createNewFile()) {
                exists = true;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Assert.assertTrue(exists);
    }

    @Test
    void writeToFile() {
        CardDeck deck = new CardDeck(0);
        long currentTime = System.currentTimeMillis();
        System.out.println(currentTime);
        String testValue = "test" + currentTime;
        deck.writeToFile("writeFileTest.txt", testValue);
        try {
            File file = new File("writeFileTest.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            String[] cardArray = sb.toString().split("\n");
            System.out.println(Arrays.toString(cardArray));
            Assert.assertEquals(testValue, cardArray[cardArray.length - 1]);
        }
        catch(IOException ignored) { }
    }
}