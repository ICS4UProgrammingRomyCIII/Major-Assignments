import java.io.*;				 // This import allows me to use the BufferReader.
import javax.swing.JFileChooser; // This import is used for the file dialog prompt.
public class MartianTime {
	public void Run_Martian_Time(JFileChooser file) {
		// There is a double try catch loop because this is the only way the FileWriter will actually write to the file.
		try {
			FileWriter fileOut = new FileWriter("MRToutput.txt"); 			   		//This will create an output file or overwrite the previous one if it already exists.
			try {
	        	BufferedReader in = new BufferedReader(new FileReader(file.getSelectedFile())); // This will read selected file from the dialog.
				// As a note, the buffer reader's readLine() method will always read the next line in the file...
				// removing the need to use a for-loop to iterate through the lines.
	        	double ratio = 86400 / 88642.663;			  // This is the ratio between Earth and Mars seconds.
	        	for(int cases = 0; cases < 100; cases++) {    // For every case, up to 100, do the following...
	        		String[] line = in.readLine().split(" "); // Read the next line (the first line of the test case), split it, and put it into an array.
	        		int day = Integer.parseInt(line[0]);	  // Parse the first index into an int for the days.
	                int hour = Integer.parseInt(line[1]);	  // Parse the first index into an int for the hours.
	                int minutes = Integer.parseInt(line[2]);  // Parse the first index into an int for the seconds.
	                
	                double eSecs = (day * 86400) + (hour * 3600) + (minutes * 60);		 	  // This converts the days, hours, and minutes into seconds.
	                double mSecs = eSecs * ratio;								   		 	  // Convert the Earth seconds into Mars seconds.
	                
	                int days = Math.floorDiv((int)mSecs, 86400);						 	  // Gets the number of whole days.
	                int hours = Math.floorDiv((int)(mSecs - (days * 86400)), 3600);			  // Gets the number of whole hours.
	                double mins = Math.round((mSecs - (days * 86400) - hours * 3600) / 60.0); // Gets the rounded number of minutes.
	                if (day != 0 && hour != 0 && minutes != 0) mins += 36; 					  // Adding 36 because that's the difference, in minutes, between Earth and Mars.
	                if (mins > 59) {														  // If there are 60 or more minutes...
	                    hours += 1;															  // Increment the number of hours.
	                    mins -= 60;															  // Decrease the number of minutes by 60.
	                }
	                if(mins < 10) fileOut.write(String.format("Day %d, %d:0%.0f", days, hours, mins) + "\n"); // Print the output into the console.;
	                else fileOut.write(String.format("Day %d, %d:%2.0f", days, hours, mins) + "\n"); 		   // Print the output into the console.  
	        	}
	        	in.close();											// Stops the buffer reader.
	        	fileOut.close();									// The FileWriter will not write unless it is closed (This is here in case 100 test cases).
			} catch(Exception exc) {
				fileOut.close();									// The FileWriter will not write unless it is closed (This is here in case there are less than 100 test cases).
				System.out.println("End of File Reached!");			// Tell the user that they have reached the end of the file.
			}
		} catch(Exception thisWillNeverActuallyHappen) {
		}
	}
}