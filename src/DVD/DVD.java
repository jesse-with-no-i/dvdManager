package DVD;

public class DVD {

	// Fields:

	private String title;		// Title of this DVD
	private String rating;		// Rating of this DVD
	private int runningTime;	// Running time of this DVD in minutes
	private String imageFile;	// name of file containing image
	
	public DVD() {
		title = "";
		rating = "";
		runningTime = 0;
		imageFile = "";
	}

	public DVD(String dvdTitle, String dvdRating, int dvdRunningTime) 
	{
		title = dvdTitle;
		rating = dvdRating;
		runningTime = dvdRunningTime;
		// by default, the image file will have the same name as the title, with no spaces
		imageFile = dvdTitle.replaceAll("\\s+","").toLowerCase() + ".jpg";
	}
	
	// TODO constructor that also accepts an string field for an image file
	public DVD(String dvdTitle, String dvdRating, int dvdRunningTime, String imageFile) {
		
		title = dvdTitle;
		rating = dvdRating;
		runningTime = dvdRunningTime;
		this.imageFile = imageFile;
		
	}
	
	public String getTitle() 
	{
		return title;
	}
	
	public String getRating() 
	{
		return rating;	
	}
	
	public int getRunningTime() 
	{
		return runningTime;	// STUB: Remove this line.
	}
	
	public String getImageFile() {
		return imageFile;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public void setRating(String newRating) {
		rating = newRating;
	}

	public void setRunningTime(int newRunningTime) {
		runningTime = newRunningTime;
	}
	
	public void setImageFile(String filename) {
		imageFile = filename;
	}

	public String toString() {

		String result = title + ',' + rating + ',' + runningTime;

		return result;	
	}
	
	
}
