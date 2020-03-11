import java.io.*;				 // This import allows me to use the BufferReader.
import java.util.*;				 // This import is used for the lists
import javax.swing.JFileChooser; // This import is used for the file dialog prompt.
public class RailwaySort {
	public void Run_Railway_Sort(JFileChooser file) {
		// There is a double try catch loop because this is the only way the FileWriter will actually write to the file.
		try {
			FileWriter fileOut = new FileWriter("RWSoutput.txt"); 			   		//This will create an output file or overwrite the previous one if it already exists.
			try {
				BufferedReader in = new BufferedReader(new FileReader(file.getSelectedFile())); // This will read selected file from the dialog.
				// As a note, the buffer reader's readLine() method will always read the next line in the file...
				// removing the need to use a for-loop to iterate through the lines.
	        	for(int cases = 0; cases < 100; cases++) {    		// For every case, up to 100, do the following...
	        		int N = Integer.parseInt(in.readLine());		// Read a line (the first line of the test case) and parse it as an integer N.
	        		String[] sCars = in.readLine().split(" ");		// Read the next line, split the string by whitespace characters, and put all the values in an array.
	        		List<Integer> cars = new ArrayList<Integer>();	// Create a list of integers.
	        		for(String s : sCars) {							// For each string in sCars, ...
	        			cars.add(Integer.parseInt(s));				// Parse it as an integer and put it in the list cars.
	        		}
	        		int right = 0, left = 0, cost = 0;				// Create integers right, left, and cost. These are to track the movement and cost of the train cars.
	        		for (int next = N; next > 1; next--) {			// This for loop will iterate through each train car...
	                    left = cars.indexOf(next - 1);				// Get the car number at the index of one less than int next.
	                    right = cars.indexOf(next);					// Get the car number at the index of  int next.
	                    if(left > right) {							// If the car at the left has a greater value than the car to the right...
	                        cost += left;							// Add the value of the left car to the cost.
	                        int add = cars.get(left);				// Store the index of the left car in a separate integer.
	                        cars.remove(left);						// Remove the left car from the list.
	                        cars.add(0, add);						// Add the car again to the top of the list.
	                    }
	        		}
	        		fileOut.write(cost + "\n");						// This will print the cost to a text file.
	        	}
	        	in.close();											// Stops the buffer reader.
				fileOut.close();									// The FileWriter will not write unless it is closed (This is here in case 100 test cases).
			} catch(Exception exc) {
				fileOut.close();									// The FileWriter will not write unless it is closed (This is here in case there are less than 100 test cases).
				System.out.println("End of File Reached!");			// Tell the user that they have reached the end of the file.
			}
		} catch(Exception thisWillNeverActuallyHappen){
		}
	}
}