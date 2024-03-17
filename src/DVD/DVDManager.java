package DVD;

import java.util.*;

/**
 * 	Program to display and modify a simple DVD collection
 */

public class DVDManager {

	public static void main(String[] args) {
		
		DVDUserInterface dlInterface;
		DVDCollection dl = new DVDCollection();

		String filename = "dvddata.txt";			
		dl.loadData(filename);

		// opens GUI window
		dlInterface = new DVDGUI(dl); // keep these
		dlInterface.processCommands();
	
		
	}

}
