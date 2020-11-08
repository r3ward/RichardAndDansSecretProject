import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;  // Import the File class
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.CountDownLatch; // maybe get rid

/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{

    /**[https://github.com/richardpeterwardl/ConcurrencyCardGame.git](https://github.com/richardpeterwardl/ConcurrencyCardGame.git)
     * Constructor for objects of class Player
     */
    private CountDownLatch latch;
    public CardDeck playerDeck;
    private ArrayList<Card> playerHand;
    private int favouriteCard;
    public int playerPosition;
    public int totalPlayers;
    public boolean win;
    private enum Task { PICKUP, DISCARD };
    private Task task;
    public Processor playerProcessor;

    
    public Player(int position, int totalPlayers)
    {
        // array of current hand
        //copy constructor
        win = false;
        playerPosition = position;
        favouriteCard = position;
        setTotalPlayers(totalPlayers);
        playerHand = this.createPlayerHand();
        task = Task.DISCARD;
        createFile();
        
    }

    // methods
    // add commenting (like below) to all methods
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
        
    public void makeProcessor(CountDownLatch latch){
        playerProcessor = new Processor(latch);
    }
    
    
    public Processor getProcessor(){
        return playerProcessor;
    }
    
    public int[] getPlayerHandValues(){
        int[] playerHandValues = new int[playerHand.size()];
        for(int i=0; i < playerHand.size(); i++) {
            int value = playerHand.get(i).getCardValue();
            playerHandValues[i] = value;
        }
        return playerHandValues;
    }    
    
    
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
                    writeToFile("Player " + playerPosition  + " current hand: " + Arrays.toString(getPlayerHandValues()));
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
    
    public void setTotalPlayers (int totalPlayers){
        this.totalPlayers = totalPlayers;
    }
    
    public int getTotalPlayers(){
        return this.totalPlayers;
    }
      
    public void runThread(String methodName){
        // multi use system to run methods within the thread
        // playerThread(new MyRunnableObject(methodName)).start(); 
    }
    
    public ArrayList<Card> createPlayerHand(){
        ArrayList<Card> playerHand = new ArrayList<Card>();
        return playerHand;
    }

    public void initialiseHand(Card card){
        playerHand.add(0, card);
        this.checkForWin();
    }
    
    
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
            writeToFile("Player " + playerPosition + " has won.");
            writeToFile("Player " + playerPosition + " WINNING hand : " + Arrays.toString(getPlayerHandValues()));
            System.out.println("Player " + playerPosition + " has won.");
            System.out.println("Player " + playerPosition + " WINNING hand : " + Arrays.toString(getPlayerHandValues()));
            CardGame.setWin(win, playerPosition);
        }
        
        // notify main thread a win has happened and end game
    }
    
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
    
    // getters
    
    public ArrayList<Card> getPlayerHand(){
        return playerHand;
    }
    
    public CardDeck getPlayerDeck(){
        return playerDeck;
    }
    
    // setters
    
    public void setPlayerPosition(int position){
        final int playerPosition = position;
    }
    
    public void setFavouriteCard(int favourite){ //may not need as favouriteCard may == playerPosition
        favouriteCard = favourite;
    }

    public int getPlayerPosition(){
        return playerPosition;
    }
    
    
    // method to check if won
    
    //card hierrachy
    //decision making

    //setters, getters
}
