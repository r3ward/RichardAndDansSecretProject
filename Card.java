
/**
 * Simple object class. The card object requires a single parameter, the value of the card.
 *
 * @author 046236, 004306
 */
public class Card
{
    public final int value;

    /**
     * Constructor for objects of class Card
     */
    public Card(int cardValue)
    {
        value = cardValue;
    }
    
    public int getCardValue(){
        return this.value;
    }
}
