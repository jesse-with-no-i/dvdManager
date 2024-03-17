package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.RepeatedTest;
import DVD.*;

//import org.junit.jupiter.api.Test;
import org.junit.Test;

public class DVDTester {
	
	@Test
	public void testDVDConstructor() {
		
		// tests default constructor
		DVD dvd1 = new DVD();
		
		assertEquals(dvd1.getTitle(), "");
		assertEquals(dvd1.getRating(), "");
		assertEquals(dvd1.getRunningTime(), 0);
		assertEquals(dvd1.getImageFile(), "");
		
		String title = "Forrest Gump";
		String rating = "PG-13";
		int runningTime = 120;
		String imageFile = "gump.jpg";
		
		// tests second constructor
		DVD dvd2 = new DVD(title, rating, runningTime, imageFile);
		
		assertEquals(dvd2.getTitle(), title);
		assertEquals(dvd2.getRating(), rating);
		assertEquals(dvd2.getRunningTime(), runningTime);
		assertEquals(dvd2.getImageFile(), imageFile);
	}
	
	@Test
	public void testDVDgetTitle() {
		
		// create a DVD object with the following information
		String title = "Forrest Gump";
		String rating = "PG-13";
		int runningTime = 120;
		String imageFile = "gump.jpg";
		
		DVD testDVD = new DVD(title, rating, runningTime, imageFile);
		
		assertEquals(testDVD.getTitle(), title);
	}
	
	@Test
	public void testDVDgetRating() {
		
		// create a DVD object with the following information
		String title = "Forrest Gump";
		String rating = "PG-13";
		int runningTime = 120;
		String imageFile = "gump.jpg";
		
		DVD testDVD = new DVD(title, rating, runningTime, imageFile);
		
		assertEquals(testDVD.getRating(), rating);
	}
	
	@Test
	public void testDVDgetRunningTime() {
		
		// create a DVD object with the following information
		String title = "Forrest Gump";
		String rating = "PG-13";
		int runningTime = 120;
		String imageFile = "gump.jpg";
		
		DVD testDVD = new DVD(title, rating, runningTime, imageFile);
		
		assertEquals(testDVD.getRunningTime(), runningTime);
	}
	
	@Test
	public void testDVDgetImageFile() {
		
		// create a DVD object with the following information
		String title = "Forrest Gump";
		String rating = "PG-13";
		int runningTime = 120;
		String imageFile = "gump.jpg";
		
		DVD testDVD = new DVD(title, rating, runningTime, imageFile);
		
		assertEquals(testDVD.getImageFile(), imageFile);
	}
	
	@Test
	public void testDVDsetTitle() {
		
		// create a DVD object with the following information
		String title = "Forrest Gump";
		String rating = "PG-13";
		int runningTime = 120;
		String imageFile = "gump.jpg";
		
		DVD testDVD = new DVD(title, rating, runningTime, imageFile);
		
		String newTitle = "Willy Wonka";
		
		// use setter to change attribute
		testDVD.setTitle(newTitle);
		
		assertEquals(testDVD.getTitle(), newTitle);
	}
	
	@Test
	public void testDVDsetRating() {
		
		// create a DVD object with the following information
		String title = "Forrest Gump";
		String rating = "PG-13";
		int runningTime = 120;
		String imageFile = "gump.jpg";
		
		DVD testDVD = new DVD(title, rating, runningTime, imageFile);
		
		String newRating = "G";
		
		// use setter to change attribute
		testDVD.setRating(newRating);
		
		assertEquals(testDVD.getRating(), newRating);
	}
	
	@Test
	public void testDVDsetRunningTime() {
		
		// create a DVD object with the following information
		String title = "Forrest Gump";
		String rating = "PG-13";
		int runningTime = 120;
		String imageFile = "gump.jpg";
		
		DVD testDVD = new DVD(title, rating, runningTime, imageFile);
		
		int newRunningTime = 95;
		
		// use setter to change attribute
		testDVD.setRunningTime(newRunningTime);
		
		assertEquals(testDVD.getRunningTime(), newRunningTime);
	}
	
	@Test
	public void testDVDsetImageFile() {
		
		// create a DVD object with the following information
		String title = "Forrest Gump";
		String rating = "PG-13";
		int runningTime = 120;
		String imageFile = "gump.jpg";
		
		DVD testDVD = new DVD(title, rating, runningTime, imageFile);
		
		String newImageFile = "chocolate.jpg";
		
		// use setter to change attribute
		testDVD.setImageFile(newImageFile);
		
		assertEquals(testDVD.getImageFile(), newImageFile);
	}
	
	@Test
	public void testDVDtoString() {
		
		// create a DVD object with the following information
		String title = "Forrest Gump";
		String rating = "PG-13";
		int runningTime = 120;
		String imageFile = "gump.jpg";
		
		DVD testDVD = new DVD(title, rating, runningTime, imageFile);
		
		// test if toString returns the correct string
		
		String expected = "Forrest Gump,PG-13,120";
		
		assertEquals(testDVD.toString(), expected);
	}
}




