import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    public void getCardValue() {
        Card newCard = new Card(1);
        Assert.assertEquals(1, newCard.getCardValue());
    }
}