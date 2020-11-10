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
    public static AtomicBoolean win;
    
    public Dealer()
    {
        win = new AtomicBoolean(false);
    }
    
    /**
     * gameStateIterate
     * Takes number of players, hands each player a thread to run their tasks on; terminates when all Players are done with their tasks
     * @author (Richard and Dan)
     * @version (a version number or a date)
     */
    public void gameStateIterate(int numberOfPlayers){
        
        CountDownLatch latch = new CountDownLatch(numberOfPlayers); 
        ExecutorService executor = Executors.newFixedThreadPool(numberOfPlayers); 
        Player[] playerArray = CardGame.getPlayerArray();
        
        int counter = 0;
        while(win.get() == false) {
            if (counter % 2 == 0){
               System.out.println("Round " + counter/2);
            }
            counter++;
            for(int i=0; i < playerArray.length; i++) {
                Player player = playerArray[i];
                player.makeProcessor(latch);
                executor.submit(player.playerProcessor); // ref to latch. each time call new Processes latch will countdown by 1
            }
            
            try {
                latch.await();
                latch = new CountDownLatch(numberOfPlayers); 
                // wait until latch counted down to 
            } catch (InterruptedException e) {
                //executor.shutdown(); // just added
            }
            
        }
    
    }
    
    public static void setWin(boolean winBool, int playerPosition){
        Player[] playerArray = CardGame.getPlayerArray();
        win.set(winBool);
        for(int i=0; i < playerArray.length; i++) {
                Player player = playerArray[i];
                if (playerPosition != player.getPlayerPosition()){
                    player.writeWinToFile(playerPosition);
                }
        }
//        ADD terminate decks
        System.out.println("Player " + playerPosition + " wins.");
    }
}
    

