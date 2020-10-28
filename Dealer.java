import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;  // Import the File class
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.CyclicBarrier;
import java.util.Set;

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
    
    public Dealer()
    {
        // initialise instance variables
        int x = 0;
    }
    
    public void gameStateIterate(){
        CardGame.runPlayerThreads(CardGame.playerArray);
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
    
}
