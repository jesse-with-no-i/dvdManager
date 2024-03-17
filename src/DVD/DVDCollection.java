package DVD;

import java.io.*;
import java.util.Scanner;

public class DVDCollection {

	// Data fields
	
	/** The current number of DVDs in the array */
	private int numdvds;
	
	/** The array to contain the DVDs */
	private DVD[] dvdArray;
	
	/** The name of the data file that contains dvd data */
	private String sourceName;
	
	/** Boolean flag to indicate whether the DVD collection was
	    modified since it was last saved. */
	private boolean modified;
	
	/**
	 *  Constructs an empty directory as an array
	 *  with an initial capacity of 7. When we try to
	 *  insert into a full array, we will double the size of
	 *  the array first.
	 */
	public DVDCollection() {
		numdvds = 0;
		dvdArray = new DVD[7];
	}
	
	public String toString() {
		// Return a string containing all the DVDs in the
		// order they are stored in the array along with
		// the values for numdvds and the length of the array.
		// See homework instructions for proper format.
		String result = "";
		DVD curr = null;
		
		result = result + "numdvds = " + numdvds + "\n\n";
		result = result + "dvdArray.length = " + dvdArray.length + "\n\n";
		// get the info for each dvd in dvdArray
		for (int i = 0; i < numdvds; i++) {
			curr = dvdArray[i];
			result = result + "dvdArray[" + i + "] = " + curr.toString() + "\n\n";  
		}

		return result;
	}
	
	
	public void addOrModifyDVD(String title, String rating, String runningTime) {
		// NOTE: Be careful. Running time is a string here
		// since the user might enter non-digits when prompted.
		// If the array is full and a new DVD needs to be added,
		// double the size of the array first.
		
		// check if rating and runningTime are valid
		if (!rating.equals("G") && !rating.equals("PG") && !rating.equals("PG-13") && !rating.equals("R")) {
			return;
		}
		for (int i = 0; i < runningTime.length(); i++) {
			if (runningTime.charAt(i) < '0' || runningTime.charAt(i) > '9') {
				return;
			}
		}
		
		
		DVD dvd = new DVD(title, rating, Integer.parseInt(runningTime));
		
		// if the title is found in the collection, modify it, otherwise, add it
		for (int i = 0; i < numdvds; i++) {
			if (title.equals(dvdArray[i].getTitle())) {
				modifyHelper(dvd, i);
				return;
			}
		}
		
		addHelper(dvd, findIndex(title), dvdArray.length);
	}
	
	public void removeDVD(String title) {
		// check to see if the dvd title exists
		for (int i = 0; i < numdvds; i++) {
			if (title.equals(dvdArray[i].getTitle())) {
				removeHelper(i);
			}
		}
	}
	
	// method to return the array of DVDs
	public DVD[] getDVDs() {
		return dvdArray;
	}
	
	public String[] getTitles() {
		String titles[] = new String[numdvds];
		
		for (int i=0; i < numdvds; i++) {
			titles[i] = dvdArray[i].getTitle();
		}
		
		return titles;
	}
	
	public String getDVDsByRating(String rating) {
		String result = "";
		for (int i = 0; i < numdvds; i++) {
			// check if ratings match
			if(dvdArray[i].getRating().equals(rating)) {
				result = result + dvdArray[i].toString() + '\n';
			}
		}
		
		return result;	
	}

	public int getTotalRunningTime() {
		int total = 0;
		for (int i = 0; i < numdvds; i++) {
			total += dvdArray[i].getRunningTime();
		}
		
		return total;

	}
	
	public int getNumDVDs() {
		return numdvds;
	}

	
	public void loadData(String filename) {
		// get the directory from where this program was launched
		String dir = System.getProperty("user.dir");
		File file = new File(dir + "\\" + filename);
		
	    Scanner reader;
		try {
			reader = new Scanner(file);
			// save the filename
			sourceName = filename;
			
			String [] dvdInfo = new String[3];
			
			// read each line and use the data to add new dvds
		    while (reader.hasNextLine()) {
		    	String data = reader.nextLine();
		        dvdInfo = data.split(",", 3);
		        addOrModifyDVD(dvdInfo[0], dvdInfo[1], dvdInfo[2]);
		    }
		    reader.close();
		} catch (FileNotFoundException e) {
			return;
		}
	}
	
	public void save() {
		// get entire list onto one string
		String dvdList = "";
		
		for (int i = 0; i < numdvds; i++) {
			dvdList = dvdList + dvdArray[i].toString() + '\n';
		}
		
		// create a filewriter
		try {
			FileWriter writer = new FileWriter(sourceName);
			// write each dvd's info to the file
			writer.write(dvdList);
			writer.close();
		} catch (IOException e) {
			return;
		}
			
	}
	

	// Additional private helper methods go here:
	
	// returns the index of where the new dvd should go when sorted alphabetically
	private int findIndex(String title) {
		int index = -1;
		int maxSize;	
		String currTitle;
		
		// go through each title in collection
		for (int i = 0; i < numdvds; i++) {
			// if an index has been found, break
			if (index != -1) {
				break;
			}
			
			currTitle = dvdArray[i].getTitle();
			
			// figure out which title is shorter
			// the length of the shorter title will be the maxSize
			if (title.length() <= currTitle.length()) {
				maxSize = title.length();
			}
			else {
				maxSize = currTitle.length();
			}
			
			// now compare titles char by char
			for (int c = 0; c < maxSize; c++) {
				// if the title is alphabetically after currTitle, move on to next titles
				if (title.charAt(c) > currTitle.charAt(c)) {
					break;
				}
				// if the title is alphabetically before curr's title, we found our index
				if (title.charAt(c) < currTitle.charAt(c) || (maxSize == title.length() && c == maxSize - 1)) {
					index = i;
					break;
				}
				// if neither is true, move on to next characters
			}
		}
		// if no index was found, the new dvd belongs at the end of dvdArray
		if (index == -1) {
			index = numdvds;
		}
		
		return index; 
	}
	
	// adds dvd to given index, doubles array size if array is full
	private void addHelper(DVD dvd, int index, int size) {
		int arrLen;
		
		if (numdvds >= size) {
			arrLen = size*2;
		}
		else {
			arrLen = size;
		}
		
		// create a temporary array with new size
		DVD[] tempArray = new DVD[arrLen];
		
		// insert the dvds in alphabetical order
		// first, insert dvds that go before the new one
		for (int i = 0; i < index; i++) {
			tempArray[i] = dvdArray[i];
		}
		// next, add the new dvd
		tempArray[index] = dvd;
		numdvds++;
		// last, add the dvds that go after the new one
		for (int i = index+1; i < numdvds; i++) {
			tempArray[i] = dvdArray[i-1];
		}
		
		// assign dvdArray to tempArray
		dvdArray = tempArray;
	}
	
	// replaces the dvd at index with a dvd with the same title, but possibly different info
	private void modifyHelper(DVD dvd, int index) {
		dvdArray[index] = dvd;
	}
	
	// removes the dvd at the given index
	private void removeHelper(int index) {
		DVD[] tempArray = new DVD[dvdArray.length];
		
		// add all dvds to tempArray if they are not at index
		int tIndex = 0;
		for(int i = 0; i < numdvds; i++) {
			// if we reach the index of the removed element, dont add it to tempArray
			if (i == index) {
				continue;
			}
			
			tempArray[tIndex] = dvdArray[i];
			tIndex++;
		}
		
		dvdArray = tempArray;
		numdvds--;
	}

}



