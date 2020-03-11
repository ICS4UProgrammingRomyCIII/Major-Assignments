import java.io.*;				 // This import allows me to use the BufferReader.
import javax.swing.JFileChooser; // This import is used for the file dialog prompt.
public class WillowsWildRide {
	public void Run_Willows_Wild_Ride(JFileChooser file) {
		// There is a double try catch loop because this is the only way the FileWriter will actually write to the file.
		try {
			FileWriter fileOut = new FileWriter("WWRoutput.txt"); 			   		//This will create an output file or overwrite the previous one if it already exists.
			try {
				BufferedReader in = new BufferedReader(new FileReader(file.getSelectedFile())); // This will read selected file from the dialog.
				// As a note, the buffer reader's readLine() method will always read the next line in the file...
				// removing the need to use a for-loop to iterate through the lines.
				for (int cases = 0; cases < 100; cases++) {							   // For every case, up to 100, do the following...
					String lines[] = in.readLine().split(" ");						   // Read the next line (the first line of the test case), split it, and put it into an array.
					int T = Integer.parseInt(lines[0]);								   // Parse the first index into an int.
					int N = Integer.parseInt(lines[1]);								   // Parse the second index into an int.
					int delayCount = 0;												   // This is a counter for the number of days her project will be delayed by.
					for (int x = 0; x < N; x++) {									   // This for-loop will iterate through every line in the test case. 
						String input = in.readLine();								   // Read the next line.
						if (input.equals("B"))									       // If the line is a 'B'...
							delayCount += T;										   // Add T to the delay counter.
						if (delayCount > 0) 										   // If the delay count is greater than 0...
							delayCount--;											   // Subtract 1 from the delay counter...
						}
					fileOut.write(delayCount + "\n");								   // This will print the delayCount to a text file.
				}
				in.close();															   // Stops the buffer reader.
				fileOut.close();													   // The FileWriter will not write unless it is closed (This is here in case 100 test cases).
			} catch(Exception exc){
				fileOut.close();													   // The FileWriter will not write unless it is closed (This is here in case there are less than 100 test cases).
				System.out.println("End of File Reached!");							   // Tell the user that they have reached the end of the file. 		
			}
		} catch (Exception thisWillNeverActuallyHappen) {
		}
	}
}