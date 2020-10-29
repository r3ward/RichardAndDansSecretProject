import java.util.ArrayList;
import java.util.Arrays;
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
        // get top card in array
        // check not empty
        if (cardArray.size() > 0){
            Card topCard = cardArray.get(0);
            cardArray.remove(0);
            return topCard;
        }
        // fix this later
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
}
