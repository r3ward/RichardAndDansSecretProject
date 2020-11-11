import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void makeProcessor() {
//        Player player = new Player(0, 1);
//
//        CountDownLatch latch = new CountDownLatch(1);
//        player.makeProcessor(latch);
//
//        player.Processor processor = getProcessor();
//        Assert.assertNot
    }

    @Test
    void getProcessor() {

    }

    @Test
    void getPlayerHandValues() {
        Player player = new Player(0, 1);
        ArrayList<Card> playerHand = player.createPlayerHand();
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
        Assert.assertEquals(totalPlayers, 1);
    }

    @Test
    void getTotalPlayers() {
        Player player = new Player(0, 1);
        player.setTotalPlayers(1);
        int totalPlayers = player.getTotalPlayers();
        Assert.assertEquals(totalPlayers, 1);
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
        ArrayList<Card> playerHand = player.createPlayerHand();
        Card card = new Card(1);
        player.initialiseHand(card);
        String playerHandGet = player.getPlayerHandValues();
        Assert.assertEquals(" 1", playerHandGet);
    }

    @Test
    void addCard() {
        // create deck array
        // create deck
        // put deck in array with card
        // call method on player

        CardGame cardGame = new CardGame();
        CardDeck[] deckArray = new CardDeck[1];
        deckArray = cardGame.generateDecks(deckArray);

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
        Dealer dealer = new Dealer();
        ArrayList<Card> playerHand = player.createPlayerHand();
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

    }

    @Test
    void createFile() {
    }

    @Test
    void writeToFile() {
    }

    @Test
    void getPlayerHand() {
        Player player = new Player(0, 1);
        ArrayList<Card> playerHand = player.createPlayerHand();
        ArrayList<Card> playerHandGet = player.getPlayerHand();
        Assert.assertArrayEquals(playerHand.toArray(), playerHandGet.toArray());
    }

    @Test
    void getPlayerDeck() {
    }

    @Test
    void setPlayerPosition() {
    }

    @Test
    void setFavouriteCard() {
    }

    @Test
    void getPlayerPosition() {
    }

    @Test
    void writeWinToFile() {
    }
}