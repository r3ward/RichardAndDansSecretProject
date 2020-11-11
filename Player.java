import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;  // Import the File class
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.CountDownLatch;
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


    private enum Task { PICKUP, DISCARD }

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


    public void makeProcessor(CountDownLatch latch){
        playerProcessor = new Processor(latch);
    }

    public Processor getProcessor(){
        return playerProcessor;
    }
    
    public String getPlayerHandValues(){
        String playerHandValues = ""; 
        for(int i=0; i < playerHand.size(); i++) {
            int value = playerHand.get(i).getCardValue();
            playerHandValues = playerHandValues + " " + value;
        }
        return playerHandValues;
    }    
    
    
    class Processor implements Runnable {
        private CountDownLatch latch;
    
        public Processor(CountDownLatch latch) {
            this.latch = latch;
        }
    
        public void run() {
            boolean stop = false;
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
                    writeToFile("Player " + playerPosition + " current hand: " + getPlayerHandValues());
                    System.out.println("Player " + playerPosition + " current hand: " + getPlayerHandValues());
                    break;
            }
            
            latch.countDown();
            playerProcessor = null;
        }
    }
    
    public void setTotalPlayers (int totalPlayers){
        this.totalPlayers = totalPlayers;
    }
    
    public int getTotalPlayers(){
        return this.totalPlayers;
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
        CardDeck[] deckArray = CardGame.getDeckArray();
        CardDeck leftDeck = deckArray[playerPosition];
        Card newCard = leftDeck.getTopCard();
        playerHand.add(newCard);
        writeToFile("Player " + playerPosition + " draws a " + newCard.getCardValue() + " from deck " + leftDeck.getDeckPosition());
        System.out.println("Player " + playerPosition + " draws a " + newCard.getCardValue() + " from deck " + leftDeck.getDeckPosition());
    }    
    
    public void removeCard(Card card, int totalPlayers){        
        playerHand.remove(card);
        
        int rightDeckIndex = playerPosition + 1;
        if(playerPosition == totalPlayers - 1){
            rightDeckIndex = 0;
        } 
        
        CardDeck[] deckArray = CardGame.getDeckArray();
        deckArray[rightDeckIndex].addBottomCard(card);
        System.out.println("Player " + playerPosition + " discards a " + card.getCardValue() + " to deck " + rightDeckIndex);
        writeToFile("Player " + playerPosition + " discards a " + card.getCardValue() + " to deck " + rightDeckIndex);
    }
    
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
            writeToFile("Player " + playerPosition + " wins");
            writeToFile("Player " + playerPosition + " exits.");
            writeToFile("Player " + playerPosition + " final hand: " + getPlayerHandValues());
            System.out.println("Player " + playerPosition + " wins");
            Dealer.setWin(win, playerPosition);
            return true;
        }
        else{
            return false;
        }
    }
    
    public Card playerDecision(){
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
    
    public ArrayList<Card> getPlayerHand(){
        return playerHand;
    }
    
    public CardDeck getPlayerDeck(){
        return playerDeck;
    }
    
    public void setPlayerPosition(int position){
        final int playerPosition = position;
    }
    
    public void setFavouriteCard(int favourite){ //may not need as favouriteCard may == playerPosition
        favouriteCard = favourite;
    }

    public int getPlayerPosition(){
        return playerPosition;
    }
    
    public void writeWinToFile(int winningPlayerPosition){
        writeToFile("Player " + winningPlayerPosition + " has informed player " + this.playerPosition + " that player "+ winningPlayerPosition + " has won.");
        writeToFile("Player " + this.playerPosition + " exits.");
        writeToFile("Player " + this.playerPosition + " hand: " + getPlayerHandValues());

        System.out.println("Player " + winningPlayerPosition + " has informed player " + this.playerPosition + " that they have won.");
    }

}
