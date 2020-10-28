import java.util.ArrayList;
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
        ArrayList<Card> cardArray = new ArrayList<Card>();
        deckPosition = position;
        cardArray = createCardDeck();
    }

    public synchronized void addTopCard(Card card){
        // add to list array
        cardArray.add(0, card);
        // --- 
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
}
