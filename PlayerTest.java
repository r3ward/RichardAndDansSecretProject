import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;


class PlayerTest {


    @Test
    void makeProcessor() {
        Player player = new Player(0, 1);
        CountDownLatch latch = new CountDownLatch(1);
        player.makeProcessor(latch);
        Assert.assertEquals(player.getProcessor(), player.playerProcessor);
    }

    @Test
    void getProcessor() {
        Player player = new Player(0, 1);
        CountDownLatch latch = new CountDownLatch(1);
        player.makeProcessor(latch);
        Assert.assertEquals(player.playerProcessor, player.getProcessor());
    }

    @Test
    void getPlayerHandValues() {
        Player player = new Player(0, 1);
        Card card = new Card(1);
        player.initialiseHand(card);
        String playerHandGet = player.getPlayerHandValues();
        Assert.assertEquals(" 1", playerHandGet);
    }

    @Test
    void setTotalPlayers() {
        Player player = new Player(0, 1);
        player.setTotalPlayers(1);
        int totalPlayers = player.getTotalPlayers();
        Assert.assertEquals(1, totalPlayers);
    }

    @Test
    void getTotalPlayers() {
        Player player = new Player(0, 1);
        player.setTotalPlayers(1);
        int totalPlayers = player.getTotalPlayers();
        Assert.assertEquals(1, totalPlayers);
    }

    @Test
    void createPlayerHand() {
        Player player = new Player(0, 1);
        ArrayList<Card> playerHand = player.createPlayerHand();
        ArrayList<Card> playerHandGet = player.getPlayerHand();
        Assert.assertArrayEquals(playerHand.toArray(), playerHandGet.toArray());
    }

    @Test
    void initialiseHand() {
        Player player = new Player(0, 1);
        Card card = new Card(1);
        player.initialiseHand(card);
        String playerHandGet = player.getPlayerHandValues();
        Assert.assertEquals(" 1", playerHandGet);
    }

    @Test
    void addCard() {
        CardDeck[] deckArray = new CardDeck[1];
        CardGame.deckArray = deckArray;

        CardGame.generateDecks(deckArray);
        Card card = new Card(1);
        CardDeck deck = deckArray[0];
        deck.addTopCard(card);

        Player player = new Player(0, 1);
        player.addCard();
        String playerHandString = player.getPlayerHandValues();
        Assert.assertEquals(playerHandString, " 1");
    }

    @Test
    void removeCard() {
        Player player = new Player(0, 1);
        ArrayList<Card> playerHand = player.createPlayerHand();
        Card card = new Card(0);
        player.initialiseHand(card);
        player.removeCard(card, 1);

        Player playerCompare = new Player(0, 1);
        ArrayList<Card> playerHandCompare = playerCompare.createPlayerHand();

        Assert.assertArrayEquals(playerHand.toArray(), playerHandCompare.toArray());
    }

    @Test
    void checkForWin() {
        Player player = new Player(0, 1);
        Card card = new Card(0);
        // add 4 identical cards, winning hand
        player.initialiseHand(card);
        player.initialiseHand(card);
        player.initialiseHand(card);
        player.initialiseHand(card);
        boolean win = player.checkForWin();
        Assert.assertTrue(win);
    }

    @Test
    void playerDecision() {
        Player player = new Player(0, 1);
        Card favouriteCard = new Card(0);
        Card otherCard = new Card(10);
        player.initialiseHand(favouriteCard);
        player.initialiseHand(favouriteCard);
        player.initialiseHand(favouriteCard);
        player.initialiseHand(otherCard);
        Card discardCard = player.playerDecision();
        Assert.assertEquals(discardCard.getCardValue(), otherCard.getCardValue());
    }

    @Test
    void createFile() {
        Player player = new Player(0, 1);
        player.createFile();
        boolean exists = false;
        try{
            File myObj = new File("testFileCreation.txt");
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
        Player player = new Player(0, 1);
        long currentTime = System.currentTimeMillis();
        System.out.println(currentTime);
        String testValue = "test" + currentTime;
        player.writeToFile("writeFileTest.txt", testValue);
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

    @Test
    void getPlayerHand() {
        Player player = new Player(0, 1);
        ArrayList<Card> playerHand = player.createPlayerHand();
        ArrayList<Card> playerHandGet = player.getPlayerHand();
        Assert.assertArrayEquals(playerHand.toArray(), playerHandGet.toArray());
    }

    @Test
    void getPlayerPosition() {
        Player player = new Player(0, 1);
        int position = player.getPlayerPosition();
        Assert.assertEquals(position, 0);
    }
}