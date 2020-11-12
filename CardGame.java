import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CardGame class. Main program is executed from within.
 *
 * @author 046236, 004306
 */
public class CardGame
{
    
    public static CardDeck[] deckArray;
    public static Player[] playerArray;
    public static int numberOfPlayers;

    /**
     * Constructor for objects of class CardGame
     */
    public static void main(String[] args) throws InsufficientCardsException {
  
       while(true){
           Scanner myObj = new Scanner(System.in);
           System.out.println("Please enter number of players:");
           try{
               numberOfPlayers = myObj.nextInt();
               if (numberOfPlayers <= 0){
                   System.out.println("Please enter an Integer value greater than 0.");
               }
               else{
                   break;
               }
            } catch (InputMismatchException e){
                System.out.println("Please enter an Integer value greater than 0.");
           }
       }

       String fileName;
       while(true){
           Scanner myObj = new Scanner(System.in);
           System.out.println("Please enter file name:");
           try{
               fileName = myObj.nextLine();
               if (fileName.length() == 0){
                   System.out.println("Please enter the file name.");
               }
               else{
                   break;
               }
            } catch (InputMismatchException e){
                System.out.println("Please enter the file name.");
           }
       }

       String fileReaderTest = fileReader(fileName, numberOfPlayers);
        assert fileReaderTest != null;
        String[] cardArray = cardArrayGenerator(fileReaderTest);

       playerArray = new Player[numberOfPlayers];
       deckArray = new CardDeck[numberOfPlayers];
       
       // make decks
        generateDecks(deckArray);
        // make people
        generatePlayers(playerArray, numberOfPlayers);
        // distribute cards to decks and people
       cardDistribute(playerArray, cardArray, deckArray);
       
       Dealer dealer = new Dealer();

       // flag for which stage we are in
       dealer.gameStateIterate(numberOfPlayers);
       System.exit(0);
    }

    /**
     * Read 8n values from inputted file.
     *
     * @param           nameOfFile  name of file to be read
     * @param           numberOfPlayers total players in game
     *
     * @return          string buffer of cards from file
     */
    public static String fileReader(String nameOfFile, int numberOfPlayers) throws InsufficientCardsException
    {
        try {
            try {
                File file = new File(nameOfFile);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                StringBuilder sb = new StringBuilder();
                String line;
                // ensure no more than 8n cards are loaded in
                int counter = 8 * numberOfPlayers;
                while ((line = br.readLine()) != null || counter <= 0) {
                    sb.append(line);
                    sb.append("\n");
                    counter--;
                    if (counter == 0) {
                        break;
                    }
                }
                if (counter > 0) {
                    throw new InsufficientCardsException("Your file does not contain enough cards! Add " + counter + " more card(s) to your file.");
                }
                fr.close();
                return sb.toString();
            } catch (FileNotFoundException e) {
                System.out.println("The file could not be found. Please try again.");
                e.printStackTrace();
                return null;
            }
        }
        catch(IOException e)  
           {
               System.out.println("The file could not be found. Please try again.");
               e.printStackTrace();
               return null;
           } 
       
    }

    /**
     * Generate array of cards from string buffer of file.
     *
     * @param       sb string buffer of file
     *
     * @return      array of card values
     */
    public static String[] cardArrayGenerator(String sb){
        return sb.split("\n");
    }


    /**
     * Generates all player objects and stores in a player array.
     *
     * @param       playerArray empty array of type player
     * @param       numberOfPlayers number of players in game
     *
     */
    public static void generatePlayers(Player[] playerArray, int numberOfPlayers)
    {
        int id = 0;
        while(id < playerArray.length){
            Player tempPlayer = new Player(id, numberOfPlayers);
            playerArray[id] = tempPlayer;
            id++;
        }
    }

    /**
     * Generate array of deck objects.
     *
     * @param deckArray empty deck array
     */
    public static void generateDecks(CardDeck[] deckArray)
    {
        int id = 0;
        while(id < deckArray.length){
            CardDeck tempDeck = new CardDeck(id);
            deckArray[id] = tempDeck;
            id++;
        }
    }

    /**
     * @return deck array
     */
    public static CardDeck[] getDeckArray(){
        return deckArray;
    }

    /**
     * @return player array
     */
    public static Player[] getPlayerArray(){
        return playerArray;
    }

    /**
     * Distributes cards among player's hand and card decks.
     *
     * @param playerArray array of player objects
     * @param cardArray array of card values
     * @param deckArray array of deck objects
     */
    public static void cardDistribute(Player[] playerArray, String[] cardArray, CardDeck[] deckArray)
    {
        int cardCounter = 0;
        for(int c = 0; c < 4; c++){
            for (Player player : playerArray) {
                int cardValue = Integer.parseInt(cardArray[cardCounter]);
                final Card card = new Card(cardValue);
                player.initialiseHand(card);
                cardCounter++;
            }
        }
        for(int c = 0; c < 4; c++){
            for (CardDeck cardDeck : deckArray) {
                int cardValue = Integer.parseInt(cardArray[cardCounter]);
                final Card card = new Card(cardValue);
                cardDeck.addTopCard(card);
                cardCounter++;
            }
        }
    }
}
