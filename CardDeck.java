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
    }

    public void addTopCard(Card card){
        // add to list array
        // cardArray.add(card);
        System.out.println("Hey friend :)");
    }

    public void initialiseHand(Card card){
        //  WE NEED TO TEST THIS
        System.out.println("Hey friend :)");
    }

    public String getWord(){
        return "This should be returned";
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
