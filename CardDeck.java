import java.util.ArrayList;
/**
 * Write a description of class CardDeck here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CardDeck
{
    public ArrayList<Card> cardDeck;
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
        cardDeck = createCardDeck();
    }

    public void addTopCard(Card card){
        // add to list array
        cardDeck.add(0, card);
        // --- 
    }
    
    public ArrayList<Card> createCardDeck(){
        ArrayList<Card> cardDeck = new ArrayList<Card>();
        return cardDeck;
    }

    public String getWord(){
        return "This should be returned";
    }
    
    public void getTopCard(){
        // get top card in array
        // check not empty
        // return card
    }
    
    public void addBottomCard(Card card) {
        // add card to bottom
        cardDeck.add(card);
    }
}
