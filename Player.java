import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;  // Import the File class
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends Thread
{

    /**[https://github.com/richardpeterwardl/ConcurrencyCardGame.git](https://github.com/richardpeterwardl/ConcurrencyCardGame.git)
     * Constructor for objects of class Player
     */
    public CardDeck playerDeck;
    private ArrayList<Card> playerHand;
    private int favouriteCard;
    public int playerPosition;
    public Thread personThread;
    
    public Player(int position)
    {
        // array of current hand
        //copy constructor
        playerPosition = position;
        favouriteCard = position;
        playerHand = this.createPlayerHand();
    }

    // methods
    
    // add commenting (like below) to all methods
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    
    public ArrayList<Card> createPlayerHand(){
        ArrayList<Card> playerHand = new ArrayList<Card>();
        //playerHand = new Card[4]; //array for 4 cards
        return playerHand;
    }

    public void initialiseHand(Card card){
        //  WE NEED TO TEST THIS
        playerHand.add(card);
        System.out.println(Arrays.toString(playerHand.toArray()));
    }
    
    public void addThread(Thread inputThread){
        this.personThread = inputThread;
        // get deck on left
        // select top card
        // add to hand
    }   
    
    public void addCard(){
        // get deck on left
        // select top card
        // add to hand
    }    
    
    public void removeCard(Card card){        
        //remove card from player hand
        // get deck on right
        // add card to bottom of deck
    }
    
    public boolean checkForWin(){
        // check if you have won the game before continuing
        return true; //or false
    }
    
    public Card playerDecision(){
        // decide which card to remove based on strategy
        // use favouriteCard
        // go through playerHand, if no favourite card, keep the modal card
        // if no modal, remove at random
        Card card = new Card(5);
        return card;
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
    
    // method to check if won
    
    //card hierrachy
    //decision making

    //setters, getters
    
    
    
}
