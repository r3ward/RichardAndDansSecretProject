
/**
 * Write a description of class Card here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Card
{
    // instance variables - replace the example below with your own
    public final int value;

    /**
     * Constructor for objects of class Card
     */
    //if (beanBagObject != null) every object maybe??
    public Card(int x)
    {
        // initialise instance variables
        value = x;
    }
    
    public int getCardValue(){
        return this.value;
    }
    
    //getters
    
    //checks value is int

}
