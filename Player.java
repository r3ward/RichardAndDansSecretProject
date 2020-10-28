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
    public CardDeck playerDeck;
    private ArrayList<Card> playerHand;
    private int favouriteCard;
    public int playerPosition;
    private int totalPlayers;
    public Thread playerThread;
    public boolean win;
    public Card throwawayCard;
    private int task; //set enum

    
    public Player(int position, int totalPlayers)
    {
        // array of current hand
        //copy constructor
        win = false;
        playerPosition = position;
        favouriteCard = position;
        totalPlayers = totalPlayers;
        playerHand = this.createPlayerHand();
        MyThread playerThread = new MyThread();
        
    }

    // methods
    // add commenting (like below) to all methods
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    
    class MyThread extends Thread {
        
        //maybe create all methods in here??
        public void run(){
            //CyclicBarrier temp = Dealer.gate;
            
            if(task == 0){
                playerDecision();
                removeCard(throwawayCard);
                task = 1;
            }
            else if(task == 1){
                addCard();
                task = 0;
            }
            
           Thread.yield();
        }
  
      }
      
     class MyThread2 implements Runnable
    {
        CountDownLatch latch;
        public MyThread2(CountDownLatch latch) 
        {
            this.latch = latch;
        }
        @Override
        public void run() 
        {
            try 
            {
                latch.await();          //The thread keeps waiting till it is informed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            if(task == 0){
                playerDecision();
                removeCard(throwawayCard);
                task = 1;
            }
            else if(task == 1){
                addCard();
                task = 0;
            }
        }
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
        //  WE NEED TO TEST THIS
        playerHand.add(card);
        this.checkForWin();
        System.out.println(Arrays.toString(playerHand.toArray()));
    }
    
    public void startThread(){
        this.playerThread.start();
    }
    
    public Thread getThread(){
        return playerThread;
    }
    
    public void addCard(){
        // get deck on left
        // select top card
        // add to hand
        CardDeck[] deckArray = CardGame.getDeckArray();
        CardDeck leftDeck = deckArray[playerPosition];
        Card newCard = leftDeck.getTopCard();
        playerHand.add(newCard);
        
        this.checkForWin();
    }    
    
    public void removeCard(Card card){        
        // remove card from player hand
        // get deck on right
        // add card to bottom of deck
        playerHand.remove(card);
        int rightDeckIndex = 0;
        if(playerPosition != totalPlayers){
            rightDeckIndex = playerPosition++;
        }
        
        CardDeck[] deckArray = CardGame.getDeckArray();
        deckArray[rightDeckIndex].addBottomCard(card);
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
                tempCardValue = chosenCard.getCardValue();
            }
        }
        
        this.win = win;
        if (win){
            CardGame.setWin(true, playerPosition);
        }
        
        // notify main thread a win has happened and end game
    }
    
    public void playerDecision(){
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
        this.throwawayCard = discardCard;
    }
    
    public void createFile(){
        // create file, may need to return file location after creating
        // call once in constructor
        // dynamically name file
        try {
          File myObj = new File("filename.txt");
          if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
          } else {
            System.out.println("File already exists.");
          }
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        // return filename;
    }
    
    public void writeToFile(){
        // dynamically name
        try {
          FileWriter myWriter = new FileWriter("filename.txt");
          myWriter.write("Files in Java might be tricky, but it is fun enough!");
          myWriter.close();
          System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
          System.out.println("An error occurred.");
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
        playerPosition = position;
    }
    
    public void setFavouriteCard(int favourite){ //may not need as favouriteCard may == playerPosition
        favouriteCard = favourite;
    }

    public int getPlayerPosition(){
        return playerPosition;
    }
    
    public Card getThrowawayCard(){
        return throwawayCard;
    }
    
    // method to check if won
    
    //card hierrachy
    //decision making

    //setters, getters
}
