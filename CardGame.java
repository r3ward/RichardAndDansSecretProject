// last worked on : 21/10/2020
// next time: line 53
import java.util.Scanner;
import java.util.Arrays;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;
import java.io.*;
// Import the Scanner class

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
    
    public static CardDeck[] deckArray;
    //card game asks players
    //card game inits players and puts them on the table

    /**
     * Constructor for objects of class CardGame
     */
    public static void main(String[] args){
    
       // brain of the game
       Scanner myObj = new Scanner(System.in);
       //Create dealer to house all this bs in
       System.out.println("Please enter number of players:");
       int numberOfPlayers = myObj.nextInt();
       
       //System.out.println("Please enter file name:");
       //String nameOfFile = myObj.nextLine();
       
       String[] cardArray = cardArrayGenerator(CardGame.fileReader("CAtest.txt", numberOfPlayers)); //generates and stores card
       Player[] playerArray = new Player[numberOfPlayers];
       CardDeck[] deckArray = new CardDeck[numberOfPlayers];
       
       generateDecks(deckArray);
       generatePeople(playerArray, numberOfPlayers);
       cardDistribute(playerArray, cardArray, deckArray);

       System.out.println("Threads complete");
       
       //no requirement, can keep running
       //
       // GAME SETUP
       // get file. tick  
       // make array of decks (empty). tick
       // make array of people. tick
       // make threads  - DO THIS NEXT SESSION
       // distribute cards round robin into people's hands (4 cards each). tick
       // distribute cards to decks round robin, until card array empty (4 cards each). tick
       // add a check to see if file has less than 8n cards. tick 
       
       // BEGIN GAME
       // open n threads, one for each player.
       // ----while !player won-----
       // check if player has won. Change win flag.
       // run player strategy on player hand, determine which to discard. (randomly non preferantial card) Wait to be synchronised.
       // 
       // all players discard card to deck on their right (player n+1).
       // all players select card from deck to their left (player n-1).
       // print moves to file (playerX_output.txt)
       // ----loop----
       
       // END THE GAME
       // print results to console
       // terminate threads
      
      
    }
    public static String fileReader(String nameOfFile, int numberOfPlayers) //load just 8n cards, no more, also check for non negative int
    {
        try  
            {  
                File file=new File(nameOfFile);    //creates a new file instance  
                FileReader fr=new FileReader(file);   
                BufferedReader br=new BufferedReader(fr);  
                StringBuffer sb=new StringBuffer();    
                String line;  
                // ensure no more than 8n cards are loaded in
                int counter = 8 * numberOfPlayers;
                while((line=br.readLine())!=null || counter <= 0) {  
                    sb.append(line);      //appends line to string buffer  
                    sb.append("\n");      //line feed  
                    counter--;
                }  
                if (counter > 0){
                    // System.out.println("Whoops, your file does not contain enough cards!");
                    // throw exception for not enough cards.
                }
                fr.close();    //closes the stream and release the resources
                return sb.toString();
                }  
        catch(IOException e)  
           {  
                e.printStackTrace();  
                return null; ///add check for loader
           } 
       
    }
    
    public static String[] cardArrayGenerator(String sb){
     
        String[] cardArray = sb.split("\n");
        // System.out.println(cardArray.length); 
        // System.out.println("Array:");
        // System.out.println(Arrays.toString(cardArray));
        // contains all of the cards
        return cardArray;
    }
    
    public static void generatePeople(Player[] playerArray, int numberOfPlayers) //maybe generate both
    {
        // initialise instance variables
        int id = 0;
        System.out.println("Lenght of PA: "+playerArray.length);
        
        while(id < playerArray.length){
            System.out.println(id);
            Player tempPlayer = new Player(id,numberOfPlayers);
            playerArray[id] = tempPlayer;
            id++;
        }
 
    }
    
    public static void generateDecks(CardDeck[] deckArray) //maybe generate both
    {
        // initialise instance variables
        int id = 0;
        while(id < deckArray.length){
            CardDeck tempDeck = new CardDeck(id); //input id
            deckArray[id] = tempDeck;
            id++;
        }
 
    }
    
    public static void cardDistribute(Player[] playerArray, String[] cardArray, CardDeck[] deckArray)
    {
        // get cards from card array
        // distribute cards to all players, 4 cards to each hand, 4 cards to deck
     
        for(int c = 1; c < 5; c++){    
            for(int i = 0; i < playerArray.length; i++){
                Player player = playerArray[i];
                int cardValue = Integer.parseInt(cardArray[i * c]);
                Card card = new Card(cardValue);
                player.initialiseHand(card);
                System.out.println(player.getPlayerPosition());
            }
        }  
        
        for(int c = 1; c < 5; c++){    
            for(int i = 0; i < playerArray.length; i++){
                CardDeck cardDeck = deckArray[i];
                int cardValue = Integer.parseInt(cardArray[i * c]);
                Card card = new Card(cardValue);
                cardDeck.addTopCard(card);
            }
        }

    }
    
    public static void runPlayerThreads(Player[] playerArray){
        for(int i = 0; i < playerArray.length; i++){
            Player player = playerArray[i];
            player.runThread();
        }
    }
    
    public static CardDeck[] getDeckArray(){
        return deckArray;
    }

}
