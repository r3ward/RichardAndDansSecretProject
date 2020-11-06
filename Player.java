import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;  // Import the File class
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch; // maybe get rid

/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{

    /**
     * Constructor for objects of class Player
     */
    public CardDeck playerDeck;
    private ArrayList<Card> playerHand;
    private int favouriteCard;
    public int playerPosition;
    public int totalPlayers;
    public boolean win;
    private enum Task { PICKUP, DISCARD };
    private Task task;
    public Processor playerProcessor;

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public Player(int position, int totalPlayers)
    {
        // array of current hand
        //copy constructor
        win = false;
        playerPosition = position;
        favouriteCard = position + 1;
        setTotalPlayers(totalPlayers);
        playerHand = this.createPlayerHand();
        task = Task.DISCARD;
        createFile();
        
    }


    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
        
    public void makeProcessor(CountDownLatch latch){
        playerProcessor = new Processor(latch);
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    
    public Processor getProcessor(){
        return playerProcessor;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int[] getPlayerHandValues(){
        int[] playerHandValues = new int[playerHand.size()];
        for(int i=0; i < playerHand.size(); i++) {
            int value = playerHand.get(i).getCardValue();
            playerHandValues[i] = value;
        }
        return playerHandValues;
    }    
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    class Processor implements Runnable {
        private CountDownLatch latch;
    
        public Processor(CountDownLatch latch) {
            this.latch = latch;
        }
    
        public void run() {
            switch(task){
                case DISCARD:
                    checkForWin();
                    Card throwawayCard = playerDecision();
                    removeCard(throwawayCard, getTotalPlayers());
                    task = Task.PICKUP;
                    writeToFile("Player " + playerPosition + " current hand: " + Arrays.toString(getPlayerHandValues()));
                    System.out.println("Player " + playerPosition + " current hand: " + Arrays.toString(getPlayerHandValues()));
                    break;
                case PICKUP:
                    addCard();
                    checkForWin();
                    task = Task.DISCARD;
                    writeToFile("Player " + playerPosition + " current hand: " + Arrays.toString(getPlayerHandValues()));
                    System.out.println("Player " + playerPosition + " current hand: " + Arrays.toString(getPlayerHandValues()));
                    break;
            }
            latch.countDown();
        }
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setTotalPlayers (int totalPlayers){
        this.totalPlayers = totalPlayers;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int getTotalPlayers(){
        return this.totalPlayers;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void runThread(String methodName){
        // multi use system to run methods within the thread
        // playerThread(new MyRunnableObject(methodName)).start(); 
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public ArrayList<Card> createPlayerHand(){
        ArrayList<Card> playerHand = new ArrayList<Card>();
        return playerHand;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void initialiseHand(Card card){
        playerHand.add(0, card);
        this.checkForWin();
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void addCard(){
        // get deck on left
        // select top card
        // add to hand
        CardDeck[] deckArray = CardGame.getDeckArray();
        CardDeck leftDeck = deckArray[playerPosition];
        Card newCard = leftDeck.getTopCard();
        playerHand.add(newCard);
        writeToFile("Player " + playerPosition + " draws a " + newCard.getCardValue() + " from deck " + leftDeck.getDeckPosition());
        System.out.println("Player " + playerPosition + " draws a " + newCard.getCardValue() + " from deck " + leftDeck.getDeckPosition());
        this.checkForWin();
    }    
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void removeCard(Card card, int totalPlayers){        
        // remove card from player hand
        playerHand.remove(card);
        
        // get deck on right
        int rightDeckIndex = playerPosition + 1;
        if(playerPosition == totalPlayers - 1){
            rightDeckIndex = 0;
        } 
        
        // add card to bottom of deck
        CardDeck[] deckArray = CardGame.getDeckArray();
        deckArray[rightDeckIndex].addBottomCard(card);
        System.out.println("Player " + playerPosition + " discards a " + card.getCardValue() + " to deck " + rightDeckIndex);
        writeToFile("Player " + playerPosition + " discards a " + card.getCardValue() + " to deck " + rightDeckIndex);
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void checkForWin(){
        // check if you have won the game before continuing
        int tempCardValue = 0;
        boolean win = true;
        // need to test this
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
            CardGame.setWin(win, playerPosition);
            writeToFile("Player " + playerPosition + " has won.");
            writeToFile("Player " + playerPosition + " WINNING hand : " + Arrays.toString(getPlayerHandValues()));
            System.out.println("Player " + playerPosition + " has won.");
            System.out.println("Player " + playerPosition + " WINNING hand : " + Arrays.toString(getPlayerHandValues()));
        }
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public Card playerDecision(){
        // decide which card to remove based on strategy
        // use favouriteCard
        // go through playerHand, if no favourite card, keep the modal card
        // if no modal, remove at random
        Random random = new Random();
        ArrayList<Card> discardCards = this.createPlayerHand();

        for(int i = 0; i < playerHand.size(); i++){
            Card chosenCard = playerHand.get(i);
            if(chosenCard.getCardValue() != favouriteCard){
                Card tempCard = playerHand.get(i);
                discardCards.add(tempCard);
            }
        }
        
        Card discardCard = discardCards.get(random.nextInt(discardCards.size()));
        return discardCard;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void createFile(){
        try {
          File myObj = new File("player" + playerPosition  + ".txt");
          if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
          } else {
            System.out.println("File already exists.");
          }
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void writeToFile(String message){
        try {
          FileWriter myWriter = new FileWriter("player" + playerPosition  + ".txt", true);
          myWriter.write(message);
          myWriter.write("\n");
          myWriter.close();
        } catch (IOException e) {
          System.out.println("An error occurred whilst printing.");
          e.printStackTrace();
        }
    }    
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public ArrayList<Card> getPlayerHand(){
        return playerHand;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public CardDeck getPlayerDeck(){
        return playerDeck;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setPlayerPosition(int position){
        this.playerPosition = position;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setFavouriteCard(int favourite){ //may not need as favouriteCard may == playerPosition
        favouriteCard = favourite;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int getPlayerPosition(){
        return playerPosition;
    }

}
