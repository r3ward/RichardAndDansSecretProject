
/**
 * Write a description of class CardDeck here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CardDeck
{
    public Card cardArray[];
    public int deckPosition;
    
    /**
     * Constructor for objects of class CardDeck
     */
    //card deck needs to know what number it is assigned
    public CardDeck(int position)
    {
        // initialise instance variables
        Card cardArray[] = new Card[] {};
        deckPosition = position;
        
    }
    
    public void getTopCard(){
        // get top card in array
        // check not empty
        // return card
    }
    
    public void addBottomCard() {
        // add card to bottom 
    }
    
}
