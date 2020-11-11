import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class to create Dealer objects. It distributes the threads and manages them until a player has one.
 *
 * @author 046236, 004306
 */
public class Dealer {
    public static AtomicBoolean win;

    public Dealer() {
        win = new AtomicBoolean(false);
    }

    /**
     * Set atomic bool once a player has been confirmed to have won.
     *
     * @param winBool win state
     * @param playerPosition position of winning player
     */
    public static void setWin(boolean winBool, int playerPosition) {
        Player[] playerArray = CardGame.getPlayerArray();
        win.set(winBool);
        for (Player player : playerArray) {
            if (playerPosition != player.getPlayerPosition()) {
                player.writeWinToFile(playerPosition);
            }
        }
        terminateDecks();
        System.out.println("Player " + playerPosition + " wins.");
    }

    /**
     * Goes through every deck and calls deckTerminate. This will print the ending deck values.
     */
    public static void terminateDecks() {
        CardDeck[] tempDeck = CardGame.getDeckArray();
        for (CardDeck cardDeck : tempDeck) {
            cardDeck.deckTerminate();
        }
    }

    /**
     * Takes number of players, hands each player a thread to run their tasks on;
     * terminates when all Players are done with their tasks
     *
     * @param numberOfPlayers the number of players in the game
     */
    public void gameStateIterate(int numberOfPlayers) {

        CountDownLatch latch = new CountDownLatch(numberOfPlayers);
        ExecutorService executor = Executors.newFixedThreadPool(numberOfPlayers);
        Player[] playerArray = CardGame.getPlayerArray();

        while (!win.get()) {
            for (Player player : playerArray) {
                player.makeProcessor(latch);
                executor.submit(player.playerProcessor);
            }
            try {
                latch.await();
                latch = new CountDownLatch(numberOfPlayers);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
    

