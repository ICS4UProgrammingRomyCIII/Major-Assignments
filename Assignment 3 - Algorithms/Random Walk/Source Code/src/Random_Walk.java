import java.util.*;  			// Used for the ArrayList
import javax.swing.JOptionPane;	// Used for the pop-up windows
public class Random_Walk { 
	static Random rn = new Random();
	static ArrayList<IntPair> targetpos = new ArrayList<IntPair>();    // Creates a list of integer pairs (Refer to class below), will be used to store the target's positions.
	static String ENCORE = "Would you like to do another random run?"; // Will be used later to ask the user if they want to do it again.				
	
	public static void main(String[] args) {
		Run_Program();
    }
	
	static void Run_Program() {
		try {
			int tRad = 0;
			// Get the user's desired grid size, target size, and target shape
			int xAxis = Integer.parseInt(JOptionPane.showInputDialog("How large do you want the X-Axis (-x --> 0 --> +x)?"));
			int yAxis = Integer.parseInt(JOptionPane.showInputDialog("How large do you want the Y-Axis (-y --> 0 --> +y)?"));
			// Continue asking the user to input a number until it conforms to the bounds.
			while(tRad < 1 || tRad > 101) { tRad = Integer.parseInt(JOptionPane.showInputDialog("How large is the radius(integer) of the target(min = 1, max = 100)?")); }
	        StdDraw.setXscale(-xAxis, +xAxis);   // Creates the X-Axis of the 2D space.
	        StdDraw.setYscale(-yAxis, +yAxis);   // Creates the Y-Axis of the 2D space.
	        
	        StdDraw.clear(StdDraw.GRAY); // Clears the screen to the specified colour.
	        
	        GetTargetPosition(xAxis, yAxis, tRad); // Gets the target's positions.
	       
	        // Generates a random start point anywhere on the grid, that is not touching the target.
	        int x = rn.nextInt(xAxis) * (rn .nextBoolean() ? -1 : 1); // Generate a random number in range of the +x axis and generate a random sign [(-) or (+)]
	        int y = rn.nextInt(yAxis) * (rn .nextBoolean() ? -1 : 1); // Generate a random number in range of the +x axis and generate a random sign [(-) or (+)]
	        // This will keep generating a new position until it finds one that isn't touching the target.
	        while(TouchingTarget(x,y)) {
	            x = rn.nextInt(xAxis) * (rn .nextBoolean() ? -1 : 1); // Generate a random number in range of the +x axis and generate a random sign [(-) or (+)]
	            y = rn.nextInt(yAxis) * (rn .nextBoolean() ? -1 : 1); // Generate a random number in range of the +x axis and generate a random sign [(-) or (+)]
	        }
	        
	        int steps = 0;		// Used to count the total steps.
	        
	        // While the current point isn't touching the target...
	        while(!TouchingTarget(x, y)) {
	        	StdDraw.setPenColor(StdDraw.WHITE); // Set the pen colour to white.
	            StdDraw.filledSquare(x, y, 0.45);	// Fill the square at the current position to the specified colour above. (This is to visualize the path that was taken)
	        	if (WithinBounds(xAxis, yAxis, x, y)) {          
	                // If the current position is within the bounds, it will move in a random direction.
	        		double rDir = Math.random(); // This will generate a random double 'x' where 0.0 <= 'x' < 1.0.
	                if      (rDir < 0.25) x--; // if the number is less than 0.25... move 1 unit left.
	                else if (rDir < 0.50) x++; // if the number is less than 0.50... move 1 unit right.
	                else if (rDir < 0.75) y--; // if the number is less than 0.75... move 1 unit down.
	                else if (rDir < 1.00) y++; // if the number is less than 1.00... move 1 unit up.
	            } else {
	            	//If the current position is not within the bounds, it will move towards the origin (0,0).
	            	if (x < 0) {	// If the x is less than 0...
	            		if (y < 0) { x++; y++; }		// and the y is less than 0... move to (x + 1, y + 1).
	            		else if (y > 0) { x++; y--; }	// and the y is greater than 0... move to (x + 1, y - 1).
	            		else if (y == 0) { x++; }		// and the y is equal to 0... move to (x + 1, y).
	            	}
	            	else if (x > 0) {	// If the x is greater than 0...
	            		if (y < 0) { x--; y++; }		// and the y is less than 0... move to (x - 1, y + 1).
	            		else if (y > 0) { x--; y--; }	// and the y is greater than 0... move to (x - 1, y - 1).
	            		else if (y == 0) { x--; }		// and the y is equal to 0... move to (x - 1).
	            	}
	            	else if (x == 0) { // If the x equals 0...
	            		if (y < 0) { y++; }				// and the y is less than 0... move to (x, y + 1).
	            		else if (y > 0) { y--; }		// and the y is greater than 0... move to (x, y - 1).
	            	}
	            }
	        	steps++; // Increment the step counter.
	            StdDraw.setPenColor(StdDraw.RED); // Set the pen colour to red.
	            StdDraw.filledSquare(x, y, 0.43); // Fill the square at the current position to the specified colour above. (This is to visualize the current position).
	            StdDraw.show();		// This shows updates the path / shows the path taken.
	            StdDraw.pause(40);	// Pause the drawing(So its not completed instantly).
	        }
	        
	        String out = "It took... " + steps + " steps to reach the target. ";						// This string tells the user the total amount of steps taken to complete the run.
	        int reply = JOptionPane.showConfirmDialog(null, out, ENCORE, JOptionPane.YES_NO_OPTION);	// This pop-up asks the user if they want to do another random run.
	        if (reply == JOptionPane.YES_OPTION) { Run_Program(); } 					// If the user says yes... run the program again.
	        else { JOptionPane.showMessageDialog(null, "GOODBYE"); System.exit(0); }	// If the user says no.. say goodbye and close the program.
		} 
		// When an exception occurs...
		catch(Exception exc) {
			JOptionPane.showMessageDialog(null, "YOU HAVE ENTERED AN INVALID NUMBER!!!! ONLY INPUT INTEGERS!!!");		
			Run_Program();
		}
	}
	// These overloaded functions check if the target or the runner is within the set bounds.
	// Taking the absolute values for these functions is better than having a case for each quadrant[(+x,+y),(+x,-y),(-x,+y),(-x,-y)].
	static Boolean WithinBounds(int xBound, int yBound) {
		Boolean within = false;
		// For every position that the target is on... get the absolute values and check if they are within the bounds.
        for(IntPair p : targetpos) { if(Math.abs(p.i) < Math.abs(xBound) && Math.abs(p.j) < Math.abs(yBound)) { within = true; } else { return false; } }
		return within;
	}
	static Boolean WithinBounds(int xBound, int yBound, int curX, int curY) {
		// Get the absolute value of the current position and check if it is within the set bounds.
        if(Math.abs(curX) < Math.abs(xBound) && Math.abs(curY) < Math.abs(yBound)) { return true; }
		return false;
	}
	// This function is used to check if the runner is touching the target.
	static Boolean TouchingTarget(int curX, int curY) {
		// For every targetPosition, check if the runner is on any of the positions.
        for(IntPair p : targetpos) { if(p.i == curX && p.j == curY) { return true; } }
		return false;
	}	
	// This function generates the position of the target.
	static void GetTargetPosition(int gridX, int gridY, int tRadius) {
        // Generates a random position 
        int xT = rn.nextInt(gridX) * (rn .nextBoolean() ? -1 : 1); // Generate a random number in range of the +x axis and generate a random sign [(-) or (+)]
        int yT = rn.nextInt(gridY) * (rn .nextBoolean() ? -1 : 1); // Generate a random number in range of the +x axis and generate a random sign [(-) or (+)]
        
        // These are counters for the integer pairs, to track the position on the 2D plane.
        int counterY = tRadius;
        int counterX = -tRadius;
        // Adds all of the coordinates to the list.
        for(int i = 0; i < (tRadius * 2) + 1; i++) {
        	for(int j = 0; j < (tRadius * 2) + 1; j++) {
        		targetpos.add(new IntPair(xT + counterX, yT + counterY));
        		counterX ++;
        	}
        	counterX = -tRadius;
        	counterY--;
        }
        
        // This loop will keep retrying to draw the target until all points are within the grid area.
        if (WithinBounds(gridX, gridY)) {
        	StdDraw.setPenColor(StdDraw.BLACK);
        	StdDraw.filledSquare(xT, yT, tRadius); // Draws the target.
        } else {
        	targetpos.clear();
        	GetTargetPosition(gridX, gridY, tRadius);
        }
   	}
}

// This class will be used in conjunction with ArrayList to store pairs of integers.
class IntPair{
	int i;
	int j;
	public IntPair(int first, int secnd) { this.i = first; this.j = secnd; }
}