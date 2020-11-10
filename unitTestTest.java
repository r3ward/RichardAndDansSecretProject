

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException; 
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * The test class unitTestTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class unitTestTest
{
     @BeforeClass
    public static void beforeAllTestMethods() {
        System.out.println("Invoked once before all test methods");
        CardGame CardGame = new CardGame();
        //CardGame.get
    }
 
    @Before
    public void beforeEachTestMethod() {
        System.out.println("Invoked before each test method");
    }
 
    @After
    public void afterEachTestMethod() {
        System.out.println("Invoked after each test method");
    }
 
    @AfterClass
    public static void afterAllTestMethods() {
        System.out.println("Invoked once after all test methods");
    }
    
    @Test
    public void testMain() throws IOException {
        System.out.println("main");
        String[] args = null;
        CardGame.main(args);
        System.out.println("Main initialised");
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream players = new ByteArrayInputStream("4".getBytes());
        System.setIn(players);
        ByteArrayInputStream file = new ByteArrayInputStream("CAtest.txt".getBytes());
        System.setIn(file);
        // do your thing
        
        // optionally, reset System.in to its original
        System.setIn(sysInBackup);
    }
 
    @Test
    public void playerGenerator() {
        //CardGame.generatePeople();
        System.out.println("Test One");
    }
   
    @Test
    public void testFour() {
        System.out.println("Test Two");
    }
    
 
}
