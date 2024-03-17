package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.RepeatedTest;
//import org.junit.jupiter.api.Test;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import DVD.*;

public class DVDConsoleUITester {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    
	private DVDConsoleUI createDVDConsoleUI() {
		// create a DVDCollection object to test
		DVDCollection collection = new DVDCollection();
		
		// add some movies to it
		collection.addOrModifyDVD("Up", "G", "90");
		collection.addOrModifyDVD("Forrest Gump", "PG-13", "120");
		collection.addOrModifyDVD("Saw", "R", "114");
		
		DVDConsoleUI consoleUI = new DVDConsoleUI(collection);
		
		return consoleUI;
	}
	
//    @Before
//    public void setUpStreams() {
//        System.setOut(new PrintStream(outContent));
//        System.setErr(new PrintStream(errContent));
//    }
//
//    @After
//    public void restoreStreams() {
//        System.setOut(System.out);
//        System.setErr(System.err);
//    }
//    
//    @Test
//    public void testDVDConsoleUIProcessCommands() {
//    	DVDConsoleUI consoleUI = createDVDConsoleUI();
//    	
//    	consoleUI.processCommands();
//    	
//    	// create an object to act as console input
//    	InputStream in = new ByteArrayInputStream("1".getBytes());
//        System.setIn(in);
//    }
}



