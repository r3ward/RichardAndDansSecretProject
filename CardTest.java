import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getCardValue() {

    Card newCard = new Card(1);

    Assert.assertEquals(1, newCard.getCardValue());

    }
}