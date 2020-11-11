/**
 * InsufficientCardException.
 *
 * @author 046236, 004306
 */
public class InsufficientCardsException extends Exception
{

    public InsufficientCardsException(){ }

    /**
     * Create exception with message body.
     *
     * @param message   message containing details of exception
     */
    public InsufficientCardsException(String message){
        super(message);
    }
}
