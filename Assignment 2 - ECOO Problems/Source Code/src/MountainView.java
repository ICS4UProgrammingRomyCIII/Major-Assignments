import java.io.*;				 // This import allows me to use the BufferReader.
import javax.swing.JFileChooser; // This import is used for the file dialog prompt.
public class MountainView {
	public void Run_Mountain_View(JFileChooser file) {
		// There is a double try catch loop because this is the only way the FileWriter will actually write to the file.
		try {
			FileWriter fileOut = new FileWriter("MTVoutput.txt"); 			   		//This will create an output file or overwrite the previous one if it already exists.
			try {
				BufferedReader in = new BufferedReader(new FileReader(file.getSelectedFile())); // This will read selected file from the dialog.
				// As a note, the buffer reader's readLine() method will always read the next line in the file...
				// removing the need to use a for-loop to iterate through the lines.
				for(int cases = 0; cases < 100; cases++) {    		// For every case, up to 100, do the following...
		            int N = Integer.parseInt(in.readLine());		// Read a line (the first line of the test case) and parse it as an integer N.
		            int[] heights = new int[N];						// Create an array to hold the mountains heights.
		            String[] sHeights = in.readLine().split(" ");	// Get all of the mountains heights from the text file.
		            for (int i = 0; i < heights.length; i++)		// This for loop parses the string heights into integers.
		            	heights[i] = Integer.parseInt(sHeights[i]);
		            int best = 0;									// This is used to track the best view (greatest number of mountains in view).
		            int idx = 0;									// This used to track the index of the mountain.
		            for(int j = 0; j < N; j++){						// This for loop will calculate the slope from each mountain and find the best view.
		                int view = visible(heights, j);				// Find the slopes for the current mountain (This for loop will iterate through every mountain).
		                if(view > best){							// If the current view is better than the current best...
		                    best = view;							// Make the current view the new best.
		                    idx = j;								// Make the current index the index of the best mountain.
		                }
		            }
		            fileOut.write((idx + 1) + "\n");				// This will print the index of the best mountain to a text file.
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
	
	// This function will calculate the slope to mountains left and right of the current mountain.
	static int visible(int heights[], int mountain) {
		int view = 0;									// Set current view to 0;
        double mInRange = -10000;								// Used to track the number of mountains in view (I need to use a large negative number in case of a large negative slope, i.e. a tall mountain to the left). 
        for(int i = mountain + 1; i < heights.length; i++){					// This for loop will calculate the slope for all mountains to the RIGHT of the current mountain.
            double slope = (heights[i] - heights[mountain]) / (double)(i - mountain);		// Calculate slope.
            if(slope > mInRange){								// If the calculated slope is greater than mInRange...
            	mInRange = slope;								// Change the value of mInRange to slope.
                view++;										// Increment the view count.
            }
        }
        mInRange = -10000;									// Used to track the number of mountains in view (I need to use a large negative number in case of a large negative slope, i.e. a tall mountain to the left).
        for(int i = mountain-1; i >= 1; i--){							// This for loop will calculate the slope for all mountains to the RIGHT of the current mountain.
            double slope = -(heights[i] - heights[mountain]) / (double)(i - mountain);		// Calculate slope.
            if(slope > mInRange) {								// If the calculated slope is greater than mInRange...
            	mInRange = slope;								// Change the value of mInRange to slope.
                view++;										// Increment the view count.
            }
        }
        return view;										// Return the number of mountains the current mountain can see.
	}
}
