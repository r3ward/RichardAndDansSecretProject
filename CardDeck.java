import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;  
/**
 * Write a description of class CardDeck here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CardDeck
{
    public ArrayList<Card> cardArray;
    public int deckPosition;
    
    /**
     * Constructor for objects of class CardDeck
     */
    //card deck needs to know what number it is assigned
    public CardDeck(int position)
    {
        // initialise instance variables
        cardArray = new ArrayList<Card>();
        deckPosition = position;
        cardArray = createCardDeck();
    }
    
    public int getDeckPosition(){
        return this.deckPosition;
    }

    public synchronized void addTopCard(Card card){
        cardArray.add(0, card);
        System.out.println("deck at "+ deckPosition + " : " + Arrays.toString(getCardDeckValues()));
    }
    
    public ArrayList<Card> createCardDeck(){
        ArrayList<Card> cardArray = new ArrayList<Card>();
        return cardArray;
    }
  
    public synchronized Card getTopCard(){

        if (cardArray.size() > 0){
            Card topCard = cardArray.get(0);
            cardArray.remove(0);
            return topCard;
        }
        return null;
    }
    
    public synchronized void addBottomCard(Card card) {
        // add card to bottom
        cardArray.add(card);
    }
    
    public ArrayList<Card> getCardArray(){
        return cardArray;
    }
    
    public int[] getCardDeckValues(){
        int[] playerDeckValues = new int[cardArray.size()];
        for(int i=0; i < cardArray.size(); i++) {
            int value = cardArray.get(i).getCardValue();
            playerDeckValues[i] = value;
        }
        return playerDeckValues;
    }
    
    //new method
    public void deckTerminate(){
        createFile();
        String deckValues = Arrays.toString(this.getCardDeckValues());
        String fileText = "deck " + deckPosition + " contents: " + deckValues;
        writeToFile("deck" + deckPosition  + ".txt", fileText);
    }
    
    public void createFile(){
        try {
          File myObj = new File("deck" + deckPosition  + ".txt");
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
    
    public void writeToFile(String fileName, String message){
        try {
          FileWriter myWriter = new FileWriter(fileName, true);
          myWriter.write(message);
          myWriter.write("\n");
          myWriter.close();
        } catch (IOException e) {
          System.out.println("An error occurred whilst printing.");
          e.printStackTrace();
        }
    } 
}
