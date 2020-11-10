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
    }

    @Test
    void removeCard() {
        // call method on hand with value
        // do get method before and after and do an assertNotEquals
    }

    @Test
    void checkForWin() {
        // fill player hand with winning cards, run checkForWin
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