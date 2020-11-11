import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;  
/**
 * Class built for the card deck. Responsible for storing the cards not in player's hands.
 *
 * @author 046236, 004306
 */
public class CardDeck
{
    public ArrayList<Card> cardArray;
    public final int deckPosition;
    
    /**
     * Constructor for objects of class CardDeck
     */
    public CardDeck(int position)
    {
        cardArray = new ArrayList<>();
        deckPosition = position;
        cardArray = createCardDeck();
    }

    /**
     * @return deck position
     */
    public final int getDeckPosition(){ return this.deckPosition; }

    /**
     * @return card array
     */
    public ArrayList<Card> getCardArray(){ return this.cardArray; }

    /**
     * Get method to return deck values.
     *
     * @return         integer array of card values.
     */
    public int[] getCardDeckValues(){
        int[] cardDeckValues = new int[cardArray.size()];
        for(int i=0; i < cardArray.size(); i++) {
            int value = cardArray.get(i).getCardValue();
            cardDeckValues[i] = value;
        }
        return cardDeckValues;
    }

    /**
     * Returns top card of card deck.
     *
     * @return         top card of type Card.
     */
    public synchronized Card getTopCard(){
        if (cardArray.size() > 0){
            Card topCard = cardArray.get(0);
            cardArray.remove(0);
            return topCard;
        }
        return null;
    }

    /**
     * Adds card to front (index 0) of array list.
     */
    public synchronized void addTopCard(Card card){
        cardArray.add(0, card);
    }
    
    public ArrayList<Card> createCardDeck(){
        return new ArrayList<>();
    }

    /**
     * Adds card to rear of array list.
     */
    public synchronized void addBottomCard(Card card) {
        cardArray.add(card);
    }

    /**
     * Creates file for each deck once game has terminated.
     */
    public void deckTerminate(){
        createFile();
        int[] deckValues = this.getCardDeckValues();
        String deckValuePrint = "";
        for(int value : deckValues){
            deckValuePrint = deckValuePrint + " " + value;
        }
        String fileText = "deck" + deckPosition + " contents:" + deckValuePrint;
        writeToFile("deck" + deckPosition  + ".txt", fileText);
    }

    /**
     * Creates deck file at root.
     */
    public void createFile(){
        File myObj = new File("deck" + deckPosition  + ".txt");
    }

    /**
     * Get method to return deck values.
     * @param          fileName the name of file to write to
     * @param          message the desired message to write
     * @return         integer array of card values
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
}
