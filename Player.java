
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
    
    private CardDeck playerDeck;
    private Card[] playerHand;
    private int favouriteCard;
    
    
    public Player()
    {
        // array of current hand
        //copy constructor
        playerDeck = this.createDeck();
        playerHand = this.createPlayerHand();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int cardChoice(int y)
    {
        // put your code here
        return  y;
    }
    
    public CardDeck createDeck(){
        CardDeck playerDeck = new CardDeck();
        return playerDeck;
    }
    
    public Card[] createPlayerHand(){
        Card[] playerHand = new Card[4]; //array for 4 cards
        return playerHand;
    }
    
    public void addCard(int card){
        //append to player hand from ?deck?
        
    }
    
    public void removeCard(int card){
        //remove card from player hand to nearby neck?
        
    }
    
    //create getPlayerHand Method
    
    //card hierrachy
    //decision making

    //setters, getters
    
    
    
}
