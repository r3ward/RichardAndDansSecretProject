import org.junit.Assert;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void setWin() {
        Dealer.setWin(true, 0);
        boolean resultBool = Dealer.win.get();
        Assert.assertTrue(resultBool);
    }
}