import org.junit.Assert;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void setWin() {
        Dealer testDealer = new Dealer();
        testDealer.setWin(true, 0);
        boolean resultBool = testDealer.win.get();
        Assert.assertEquals(true, resultBool);
    }
}