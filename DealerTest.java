import org.junit.Assert;
import org.junit.jupiter.api.Test;

class DealerTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void setWin() {
        Dealer testDealer = new Dealer();
        testDealer.setWin(true, 0);
        boolean resultBool = testDealer.win.get();

        Assert.assertEquals(true, resultBool);

    }

}