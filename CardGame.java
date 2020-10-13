import java.util.Scanner;  // Import the Scanner class
/**
 * Write a description of class CardGame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CardGame
{
    // instance variables - replace the example below with your own
    
    // playerArray for order of players
    // deckArray for order of decks
    
    //card game asks players
    //card game inits players and puts them on the table

    /**
     * Constructor for objects of class CardGame
     */
    public static void main(String[] args){
    
       // brain of the game
       Scanner myObj = new Scanner(System.in);
       
       System.out.println("Please enter number of players:");
       int numberOfPlayers = myObj.nextInt();
       
       System.out.println("Please enter file name:");
       String nameOfFile = myObj.nextLine();
      
       // GAME SETUP
       // get file.
       // make array of cards (directly from file, check 8n cards ONLY).
       // make array of decks (empty).
       // make array of people.
       // distribute cards round robin into people's hands (4 cards each).
       // distribute cards to decks round robin, until card array empty (4 cards each).
       
       // BEGIN GAME
       // open n threads, one for each player.
       // ----while !player won-----
       // check if player has won. Change win flag.
       // run player strategy on player hand, determine which to discard. Wait to be synchronised.
       // all players discard card to deck on their right (player n+1).
       // all players select card from deck to their left (player n-1).
       // print moves to file (playerX_output.txt)
       // ----loop----
       
       // END THE GAME
       // print results to console
       // terminate threads
      
      
    
    }
    
    public void cardDistribute()
    {
        // initialise instance variables
    }
}
