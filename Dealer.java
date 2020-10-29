import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Write a description of class Dealer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dealer
{

    //public static CyclicBarrier gate;
    //Set<Thread> threadSet;
    public AtomicBoolean stateFlag;
    
    public Dealer()
    {
        // initialise instance variables
       stateFlag = new AtomicBoolean(false);
        
    }
    
    public void gameStateIterate(int numberOfPlayers){
        
        CountDownLatch latch = new CountDownLatch(numberOfPlayers); // coundown from 3 to 0

        ExecutorService executor = Executors.newFixedThreadPool(numberOfPlayers); // 3 Threads in pool
        
        Player[] playerArray = CardGame.getPlayerArray();
        
        for(int i=0; i < playerArray.length; i++) {
            Player player = playerArray[i];
            player.makeProcessor(latch);
            executor.submit(player.playerProcessor); // ref to latch. each time call new Processes latch will count down by 1
        }

        try {
            latch.await();  // wait until latch counted down to 0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed.");
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     *
     *   public void gameStateIterate()
     *   {
     *       threadSet = Thread.getAllStackTraces().keySet();
     *       final CyclicBarrier gate = new CyclicBarrier(threadSet.size()+1);
     *       
     *   }
     */
    
    // https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CountDownLatch.html used for inspo
}
