package DVD;

import javax.swing.*;
import java.io.File;

/**
 *  This class is an implementation of DVDUserInterface
 *  that uses JOptionPane to display the menu of command choices. 
 */

public class DVDGUI implements DVDUserInterface {
	
	private DVDCollection dvdlist;
	private DVDCollection favorites;
	
	public DVDGUI(DVDCollection dl)
	{
		dvdlist = dl;
		favorites = new DVDCollection();
	}
	
	
	public void processCommands()
	{
		String[] commands = {"Add/Modify DVD",
					"Remove DVD",
					"Get DVDs By Rating",
					"Get Total Running Time",
					"Edit DVDs",
					"View DVD Details",
					"View Favorites",
					"Exit and Save"};
		 
		int choice;
		 
		do {
			choice = JOptionPane.showOptionDialog(null,
					"Select a command", 
					"DVD Collection", 
					JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					commands,
					commands[commands.length - 1]);
		 
			switch (choice) {
				case 0: doAddOrModifyDVD(); break;
				case 1: doRemoveDVD(); break;
				case 2: doGetDVDsByRating(); break;
				case 3: doGetTotalRunningTime(); break;
				case 4: doEditDVDs(); break;
				case 5: doViewDVDDetails(); break;
				case 6: doViewFavorites(); break;
				case 7: doSave(); break;
				default:  // do nothing
			}
			
		} while (choice != commands.length-1);
		System.exit(0);
	}
	
	// TODO
	private void doEditSingleDVD(DVD dvd) {
		// after selecting a dvd, this method allows user to easily edit the rating, runtime
		// or remove the dvd (or cancel)
		String[] commands = {"Edit Rating",
				"Edit Running Time",
				"Remove this DVD",
				"Add To Favorites",
				"Back"};
	 
		int choice;
		 
		do {
			choice = JOptionPane.showOptionDialog(null,
					"What would yo like to do?", 
					dvd.getTitle(), 
					JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					commands,
					commands[commands.length - 1]);
		 
			switch (choice) {
				case 0: editRatingSelected(dvd); break;
				case 1: editRunningTimeSelected(dvd); break;
				case 2: removeSelected(dvd);
						choice = 4; break;
				case 3: favorites.addOrModifyDVD(dvd.getTitle(), dvd.getRating(), String.valueOf(dvd.getRunningTime()));
				case 4: doSave(); break;
				default:  // do nothing
			}
			
		} while (choice != commands.length-1);
		
}
	
	// edits the rating of a selected dvd
	private void editRatingSelected(DVD dvd) {
		
		String rating;
		String[] commands = {"G",
				"PG",
				"PG-13",
				"R",
				"Save and Quit"};
	 
		int choice;
		 
		do {
			choice = JOptionPane.showOptionDialog(null,
					"Select the new rating for " + dvd.getTitle(), 
					dvd.getTitle(), 
					JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					commands,
					commands[commands.length - 1]);
		 
			// Changes the rating to the one selected
			if (choice != commands.length-1) {
				rating = commands[choice];
				dvdlist.addOrModifyDVD(dvd.getTitle(), rating, String.valueOf(dvd.getRunningTime()));
			}
			
			
		} while (choice != commands.length-1);
		
	}
	
	// edits the running time of the selected dvd
	private void editRunningTimeSelected(DVD dvd) {
		
		String runTime = JOptionPane.showInputDialog("Enter running time");
		if (runTime == null) {
			return;		// dialog was cancelled
		}
		
		dvdlist.addOrModifyDVD(dvd.getTitle(), dvd.getRating(), runTime);
	}
	
	// removes the selected dvd
	private void removeSelected(DVD dvd) {
		int choice;
		String[] commands = {"Continue", "Cancel"};
		
		choice = JOptionPane.showOptionDialog(null,
				"Continuing will permanently remove " + dvd.getTitle(),
				dvd.getTitle(),
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				commands,
				commands[commands.length - 1]);
	
	if (choice == 1) {
		return;		// action cancelled
	}
	
	// this removes the dvd from dvd list
	dvdlist.removeDVD(dvd.getTitle());
	
	}
	
	// displays all movie titles as buttons to individually edit
	private void doEditDVDs() {
		
		String[] titles = dvdlist.getTitles();
		int choice;
		
		choice = JOptionPane.showOptionDialog(null,
				"Select a movie to edit", 
				"DVD Collection", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				titles,
				titles[titles.length - 1]);
		
		// passes the index of the dvd you want to edit
		doEditSingleDVD(dvdlist.getDVDs()[choice]);

	}
	
	// shows the details of a single DVD
	private void doShowDetails(DVD dvd) {
		
		// shows an the movie image file with -> Title (Rated: <rating>, Running Time: <runningTime> minutes <- next to it
		String choices[] = {"Back"};
		File imageFile = new File(dvd.getImageFile()); // will be used to check if the file exists or not
		ImageIcon icon;
		
		if (imageFile.exists()) {
			icon = new ImageIcon(dvd.getImageFile());
		} else { // otherwise, use default image
			icon = new ImageIcon("defaultDVDimage.jpg");
		}
		
	    
	    JOptionPane.showOptionDialog(null,
	    		dvd.getTitle() + " (Rated " + dvd.getRating() + ". Running Time: " + dvd.getRunningTime() + " minutes)",
	    		dvd.getTitle(),
	    		JOptionPane.YES_NO_CANCEL_OPTION,
	    		JOptionPane.PLAIN_MESSAGE,
	    		icon,
	    		choices,
	    		choices[choices.length - 1]);
		
	}
	
	
	// allows user to select a dvd to view the details of
	private void doViewDVDDetails() {
		String[] titles = dvdlist.getTitles();
		int choice;
		
		choice = JOptionPane.showOptionDialog(null,
				"Select a movie to view", 
				"DVD Collection", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				titles,
				titles[titles.length - 1]);
		
		// passes the index of the dvd you want to edit
		doShowDetails(dvdlist.getDVDs()[choice]);
	}
	
	// shows a list of favorite dvd titles and allows user to remove from favorites
	private void doViewFavorites() {
		String[] titles = favorites.getTitles();
		int choice;
		
		try {
		choice = JOptionPane.showOptionDialog(null,
				"Click to View Details", 
				"Favorites", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				titles,
				titles[titles.length - 1]);
		
		// passes the index of the dvd you want to edit
		doShowDetails(favorites.getDVDs()[choice]);
		} catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "ERROR: No movies in Favorites.");
		}
		
	}

	private void doAddOrModifyDVD() {

		// Request the title
		String title = JOptionPane.showInputDialog("Enter title");
		if (title == null) {
			return;		// dialog was cancelled
		}
		title = title.toUpperCase();
		
		// Request the rating
		String rating = JOptionPane.showInputDialog("Enter rating for " + title);
		if (rating == null) {
			return;		// dialog was cancelled
		}
		rating = rating.toUpperCase();
		
		// Request the running time
		String time = JOptionPane.showInputDialog("Enter running time for " + title);
		if (time == null) {
		}
		
                // Add or modify the DVD (assuming the rating and time are valid
                dvdlist.addOrModifyDVD(title, rating, time);
                
                // Display current collection to the console for debugging
//                System.out.println("Adding/Modifying: " + title + "," + rating + "," + time);
//                System.out.println(dvdlist);
                
                // TODO: Replace console output with a new window that displays the new list of movies
                JOptionPane.showMessageDialog(null, dvdlist.getDVDs(), "DVD Collection", JOptionPane.PLAIN_MESSAGE);
                
		
	}
	
	private void doRemoveDVD() {

		// Request the title
		String title = JOptionPane.showInputDialog("Enter title");
		if (title == null) {
			return;		// dialog was cancelled
		}
		title = title.toUpperCase();
		
                // Remove the matching DVD if found
                dvdlist.removeDVD(title);
                
                // Display current collection to the console for debugging
//                System.out.println("Removing: " + title);
//                System.out.println(dvdlist);
                
                // TODO
                JOptionPane.showMessageDialog(null, dvdlist.getDVDs(), "DVD Collection", JOptionPane.PLAIN_MESSAGE);

	}
	
	private void doGetDVDsByRating() {

		// Request the rating
		String rating = JOptionPane.showInputDialog("Enter rating");
		if (rating == null) {
			return;		// dialog was cancelled
		}
		rating = rating.toUpperCase();
		
                String results = dvdlist.getDVDsByRating(rating);
                String title = "DVDs with rating " + rating;
//                System.out.println("DVDs with rating " + rating);
//                System.out.println(results);
                
                // TODO
                JOptionPane.showMessageDialog(null, results, title, JOptionPane.PLAIN_MESSAGE);

	}

        private void doGetTotalRunningTime() {
                 
                int total = dvdlist.getTotalRunningTime();
                String totalMsg = total + " minutes";
//                System.out.println("Total Running Time of DVDs: ");
//                System.out.println(total);
                JOptionPane.showMessageDialog(null, totalMsg, "Total Running Time of DVDs", JOptionPane.PLAIN_MESSAGE);
                
        }

	private void doSave() {
		
		dvdlist.save();
		
	}
		
}
