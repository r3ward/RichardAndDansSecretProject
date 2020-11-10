import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;
import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Write a description of class CardGame here.
 *
 * @author (your name)
 * @version (8/11/2020 - 18:00)
 */
public class CardGame
{
    
    public static CardDeck[] deckArray;
    public static Player[] playerArray;
    public static AtomicBoolean win;
    public static int numberOfPlayers;

    /**
     * Constructor for objects of class CardGame
     */
    public static void main(String[] args){
  
       while(true){
           Scanner myObj = new Scanner(System.in);
           System.out.println("Please enter number of players:");
           try{
               numberOfPlayers = myObj.nextInt();
               if (numberOfPlayers <= 0){
                   // maybe just continue loop and don't throw an exception
                   System.out.println("Please enter an Integer value greater than 0.");
               }
               else{
                   break;
               }
            } catch (InputMismatchException e){
                System.out.println("Please enter an Integer value greater than 0.");
           }
       }

       //String nameOfFile = myObj.nextLine();
       win = new AtomicBoolean(false);
       
       String fileName;
       while(true){
           Scanner myObj = new Scanner(System.in);
           System.out.println("Please enter file name:");
           try{
               fileName = myObj.nextLine();
               if (fileName.length() == 0){
                   // maybe just continue loop and don't throw an exception
                   System.out.println("Please enter the file name.");
               }
               else{
                   break;
               }
            } catch (InputMismatchException e){
                System.out.println("Please enter the file name.");
           }
       }
       
       
       // -- CAtest.txt
       String fileReaderTest = fileReader(fileName, numberOfPlayers);
       String[] cardArray = cardArrayGenerator(fileReaderTest);
       playerArray = new Player[numberOfPlayers];
       deckArray = new CardDeck[numberOfPlayers];
       
       // make decks
       generateDecks(deckArray);
       // make people
       generatePeople(playerArray, numberOfPlayers);
       // distribute cards to decks and people
       cardDistribute(playerArray, cardArray, deckArray);
       
       Dealer dealer = new Dealer();
       
       int counter = 0;
       
       // flag for which stage we are in
       dealer.gameStateIterate(numberOfPlayers);
 
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
                System.out.println("Reading file...");
                while((line=br.readLine())!=null || counter <= 0) {  
                    sb.append(line);      //appends line to string buffer  
                    sb.append("\n");      //line feed  
                    counter--;
                    System.out.println(counter);
                    if(counter == 0){
                        break;
                    }
                }  
                if (counter > 0){
                    System.out.println("Whoops, your file does not contain enough cards!");
                    System.out.println("Add " + counter + " more card(s)");
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
        return cardArray;
    }
    
    public static void generatePeople(Player[] playerArray, int numberOfPlayers)
    {
        int id = 0;
        while(id < playerArray.length){
            Player tempPlayer = new Player(id, numberOfPlayers);
            playerArray[id] = tempPlayer;
            id++;
        }
    }
    
    public static void generateDecks(CardDeck[] deckArray)
    {
        // initialise instance variables
        int id = 0;
        while(id < deckArray.length){
            CardDeck tempDeck = new CardDeck(id); //input id
            deckArray[id] = tempDeck;
            id++;
        } 
    }
    
    public static CardDeck[] getDeckArray(){
        return deckArray;
    }
    
    public static Player[] getPlayerArray(){
        return playerArray;
    }
    
    public static boolean getAtomicBool(){
        return win.get();
    }
    
    public static void cardDistribute(Player[] playerArray, String[] cardArray, CardDeck[] deckArray)
    {

        int cardCounter = 0;
        
        for(int c = 0; c < 4; c++){
            for(int i = 0; i < playerArray.length; i++){
                Player player = playerArray[i];
                int cardValue = Integer.parseInt(cardArray[cardCounter]);
                System.out.println(cardValue);
                final Card card = new Card(cardValue);
                player.initialiseHand(card);
                cardCounter++;
            }
        }
        
        //distribute cards for decks
        for(int c = 0; c < 4; c++){    
            for(int i = 0; i < deckArray.length; i++){
                CardDeck cardDeck = deckArray[i];
                int cardValue = Integer.parseInt(cardArray[cardCounter]);
                System.out.println(cardValue);
                final Card card = new Card(cardValue);
                cardDeck.addTopCard(card);
                cardCounter++;
            }
        }
        
        System.out.println("Total cards added : " + cardCounter);
    }
    
    //new method
    public static void terminateDecks(){
        for(int c = 0; c < 4; c++){    
            for(int i = 0; i < deckArray.length; i++){
                CardDeck cardDeck = deckArray[i];
                cardDeck.deckTerminate();
            }
        }
    }
}
