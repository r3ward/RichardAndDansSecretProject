import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Class for player objects.
 *
 * @author 046236, 004306
 *
 */
public class Player
{
    private final ArrayList<Card> playerHand;
    private final int favouriteCard;
    public int playerPosition;
    public int totalPlayers;
    public boolean win;
    private enum Task { PICKUP, DISCARD }
    private Task task;
    public Processor playerProcessor;
    
    public Player(int position, int totalPlayers)
    {
        win = false;
        playerPosition = position;
        favouriteCard = position;
        setTotalPlayers(totalPlayers);
        playerHand = this.createPlayerHand();
        task = Task.DISCARD;
        createFile();
    }

    /**
     * Creates processor and assigns attribute.
     *
     * @param latch releases all threads once all players ready.
     */
    public void makeProcessor(CountDownLatch latch){
        playerProcessor = new Processor(latch);
    }

    /**
     *
     * @return string form of player hand values
     */
    public Processor getProcessor(){ return playerProcessor; }

    /**
     * Returns player hand values in a string
     *
     * @return string form of player hand values
     */
    public String getPlayerHandValues(){
        StringBuilder playerHandValues = new StringBuilder();
        for (Card card : playerHand) {
            int value = card.getCardValue();
            playerHandValues.append(" ").append(value);
        }
        return playerHandValues.toString();
    }

    /**
     * Processor class, responsible for thread creation and assignment to player.
     * Once players are finished with task, gameStateIterate in dealer class is alerted.
     * Threads are reallocated once all players have finished their task.
     *
     */
    class Processor implements Runnable {
        private final CountDownLatch latch;
    
        public Processor(CountDownLatch latch) {
            this.latch = latch;
        }

        /**
         * Responsible for coordinating task to be completed
         */
        public void run() {
            boolean stop;
            switch(task){
                case DISCARD:
                    stop = checkForWin();
                    Card throwawayCard = playerDecision();
                    removeCard(throwawayCard, getTotalPlayers());
                    if (!stop){
                        task = Task.PICKUP;
                    }
                    break;
                case PICKUP:
                    addCard();
                    stop = checkForWin();
                    if (!stop){
                        task = Task.DISCARD;
                    }
                    writeToFile("player" + playerPosition  + ".txt", "Player " + playerPosition + " current hand: " + getPlayerHandValues());
                    break;
            }
            
            latch.countDown();
            playerProcessor = null;
        }
    }

    /**
     * Set method.
     *
     * @param totalPlayers total players in game
     */
    public void setTotalPlayers (int totalPlayers){
        this.totalPlayers = totalPlayers;
    }

    /**
     * @return total players
     */
    public int getTotalPlayers(){
        return this.totalPlayers;
    }

    /**
     * @return player hand
     */
    public ArrayList<Card> createPlayerHand(){
        return new ArrayList<>();
    }

    /**
     * Method to add cards to hand during dealing.
     *
     * @param card card to be added to player hand.
     */
    public void initialiseHand(Card card){
        playerHand.add(0, card);
        this.checkForWin();
    }

    /**
     * Add card to player hand from the deck on the left.
     */
    public void addCard(){
        CardDeck[] deckArray = CardGame.getDeckArray();
        CardDeck leftDeck = deckArray[playerPosition];
        Card newCard = leftDeck.getTopCard();
        playerHand.add(newCard);
        writeToFile("player" + playerPosition  + ".txt", "Player " + playerPosition + " draws a " + newCard.getCardValue() + " from deck " + leftDeck.getDeckPosition());
    }

    /**
     * Method to remove card and add to deck on right of player.
     *
     * @param card card to be removed
     * @param totalPlayers total players in game
     */
    public void removeCard(Card card, int totalPlayers){        
        playerHand.remove(card);
        
        int rightDeckIndex = playerPosition + 1;
        if(playerPosition == totalPlayers - 1){
            rightDeckIndex = 0;
        } 
        
        CardDeck[] deckArray = CardGame.getDeckArray();
        deckArray[rightDeckIndex].addBottomCard(card);
        writeToFile("player" + playerPosition  + ".txt", "Player " + playerPosition + " discards a " + card.getCardValue() + " to deck " + rightDeckIndex);
    }

    /**
     * Check if player has a winning hand.
     *
     * @return boolean result
     */
    public boolean checkForWin(){
        int tempCardValue = 0;
        boolean win = true;
        if (playerHand.size() == 4){
            for(int i = 0; i < playerHand.size(); i++){
                Card chosenCard = playerHand.get(i);
                if (i != 0){
                    if (tempCardValue != chosenCard.getCardValue()){
                        win = false;
                        break;
                    }
                }
                else{
                    tempCardValue = chosenCard.getCardValue();
                }
            }
        }
        else{
            win = false;
        }
        this.win = win;
        if (win){
            writeToFile("player" + playerPosition  + ".txt", "Player " + playerPosition + " wins");
            writeToFile("player" + playerPosition  + ".txt", "Player " + playerPosition + " exits.");
            writeToFile("player" + playerPosition  + ".txt", "Player " + playerPosition + " final hand: " + getPlayerHandValues());
            Dealer.setWin(true, playerPosition);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Responsible for player decision making. It will always hold the player's favourite card.
     *
     * @return card to be discarded.
     */
    public Card playerDecision(){
        Random random = new Random();
        ArrayList<Card> discardCards = this.createPlayerHand();

        for (Card chosenCard : playerHand) {
            if (chosenCard.getCardValue() != favouriteCard) {
                discardCards.add(chosenCard);
            }
        }
        return discardCards.get(random.nextInt(discardCards.size()));
    }

    /**
     * Responsible for creating file to output results into
     */
    public void createFile(){
    }

    /**
     * Write string to file.
     *
     * @param fileName name of file
     * @param message string to be written
     */
    public void writeToFile(String fileName, String message){
        try {
          FileWriter myWriter = new FileWriter(fileName, true);
          myWriter.write(message);
          myWriter.write("\n");
          myWriter.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

    /**
     * @return player's hand
     */
    public ArrayList<Card> getPlayerHand(){
        return playerHand;
    }

    /**
     * @return return position of player
     */
    public int getPlayerPosition(){
        return playerPosition;
    }

    /**
     * Write the winning player to non-winning player's files.
     *
     * @param winningPlayerPosition position of winning player.
     */
    public void writeWinToFile(int winningPlayerPosition){
        writeToFile("player" + playerPosition  + ".txt", "Player " + winningPlayerPosition + " has informed player " + this.playerPosition + " that player "+ winningPlayerPosition + " has won.");
        writeToFile("player" + playerPosition  + ".txt", "Player " + this.playerPosition + " exits.");
        writeToFile("player" + playerPosition  + ".txt", "Player " + this.playerPosition + " hand: " + getPlayerHandValues());
    }
}
