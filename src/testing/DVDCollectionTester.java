package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.RepeatedTest;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import DVD.*;

public class DVDCollectionTester {

	@Test
	public void testDVDCollectionConstructor() {
		
		// tests default constructor
		DVDCollection dvdCollection1 = new DVDCollection();
		assertEquals(dvdCollection1.getNumDVDs(), 0);
		assertEquals(dvdCollection1.getDVDs().length, 7);
	}
	
	@Test
	public void testDVDCollectionAddOrModify() {
		// create a DVDCollection object to test
		DVDCollection testCollection = new DVDCollection();
		
		// add some movies to it
		testCollection.addOrModifyDVD("Up", "G", "90");
		testCollection.addOrModifyDVD("Forrest Gump", "PG-13", "120");
		testCollection.addOrModifyDVD("Saw", "R", "114");
		
		// test to see if they were added
		assertEquals(testCollection.getNumDVDs(), 3);
	}
	
	@Test
	public void testDVDCollectionGetDVDs() {
		// create a dvd collection to test
		DVDCollection testCollection = new DVDCollection();
		
		// add some movies to it
		testCollection.addOrModifyDVD("Up", "G", "90");
		testCollection.addOrModifyDVD("Forrest Gump", "PG-13", "120");
		testCollection.addOrModifyDVD("Saw", "R", "114");
		
		// gets the DVDs using the getDVDs method
		DVD [] dvdArray = testCollection.getDVDs();
		
		// make sure each DVD has correct info
		assertEquals(dvdArray[0].getTitle(), "Forrest Gump");
		assertEquals(dvdArray[0].getRating(), "PG-13");
		assertEquals(dvdArray[0].getRunningTime(), 120);
		
		assertEquals(dvdArray[1].getTitle(), "Saw");
		assertEquals(dvdArray[1].getRating(), "R");
		assertEquals(dvdArray[1].getRunningTime(), 114);
		
		assertEquals(dvdArray[2].getTitle(), "Up");
		assertEquals(dvdArray[2].getRating(), "G");
		assertEquals(dvdArray[2].getRunningTime(), 90);
	}
	
	@Test
	public void testDVDCollectionGetTitles() {
		DVDCollection testCollection = new DVDCollection();
		
		// array of titles to use
		String [] myTitles = { "Forrest Gump", "Saw", "Up"};
		
		testCollection.addOrModifyDVD("Up", "G", "90");
		testCollection.addOrModifyDVD("Forrest Gump", "PG-13", "120");
		testCollection.addOrModifyDVD("Saw", "R", "114");
		
		// retrieve all titles in an array
		String [] testTitles = testCollection.getTitles();
		
		for (int i=0; i<3; i++) {
			assertEquals(testTitles[i], myTitles[i]);
		}
			
	}
	
	@Test
	public void testDVDCollectionGetDVDsByRating() {
		DVDCollection testCollection = new DVDCollection();
		
		testCollection.addOrModifyDVD("Up", "G", "90");
		testCollection.addOrModifyDVD("Forrest Gump", "PG-13", "120");
		testCollection.addOrModifyDVD("Saw", "R", "114");
		
		// call getDVDsByRating with each existing rating
		assertEquals(testCollection.getDVDsByRating("G"), "Up,G,90\n");
		assertEquals(testCollection.getDVDsByRating("PG-13"), "Forrest Gump,PG-13,120\n");
		assertEquals(testCollection.getDVDsByRating("R"), "Saw,R,114\n");	
	}
	
	@Test 
	public void testDVDCollectionGetTotalRunningTime() {
		DVDCollection testCollection = new DVDCollection();
		
		testCollection.addOrModifyDVD("Up", "G", "90");
		testCollection.addOrModifyDVD("Forrest Gump", "PG-13", "120");
		testCollection.addOrModifyDVD("Saw", "R", "114");
		
		int expected = 90+120+114;
		
		assertEquals(testCollection.getTotalRunningTime(), expected);
	}
	
	@Test
	public void testDVDCollectionRemove() {
		DVDCollection testCollection = new DVDCollection();
		
		testCollection.addOrModifyDVD("Up", "G", "90");
		testCollection.addOrModifyDVD("Forrest Gump", "PG-13", "120");
		testCollection.addOrModifyDVD("Saw", "R", "114");
		
		// remove movie from the list
		testCollection.removeDVD("Saw");
		
		assertEquals(testCollection.getNumDVDs(), 2);
	}
	
	@Test
	public void testDVDCollectionToString() {
		DVDCollection testCollection = new DVDCollection();
		
		testCollection.addOrModifyDVD("Up", "G", "90");
		testCollection.addOrModifyDVD("Saw", "R", "114");
		
		// string representing the expected result
		String expected = "numdvds = 2\n\ndvdArray.length = 7\n\n"
				+ "dvdArray[0] = Saw,R,114\n\ndvdArray[1] = Up,G,90\n\n";
		
		assertEquals(testCollection.toString(), expected);
	}
}





