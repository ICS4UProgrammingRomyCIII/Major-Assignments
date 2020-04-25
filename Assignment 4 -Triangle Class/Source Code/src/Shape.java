import java.util.*;
public abstract class Shape{
	// Protected variables for the properties of a GENERIC SHAPE.
	// Protected so extended / child classes can access them.
	protected int _numSides;	// The number of sides of the GENERIC SHAPE.
	protected float _area;		// Area of the GENERIC SHAPE.
	protected float _perimeter;	// Perimeter of the GENERIC SHAPE.
	protected List<Float> sideLengths = new ArrayList<Float>();	// A list to store the side lengths of the GENERIC SHAPE.
	
	// Empty Constructor for Shape Class.
	public Shape() { }
	
	// Protected function to calculate perimeter. 
	// This is in the shape class as it is the same equation for every type of shape, the sum of the side lengths
	protected float CalculatePerimeter() {
		float sum = 0;							// Variables to store the sum of the sides.
		for(float f : sideLengths) { sum += f; }// For each side in sideLengths, add them up.
		return sum;								// Return the sum.
	}
	
	// This public procedure will display the generic properties to the console.
	public void DisplayProperties() {
		System.out.println("Num Sides :" + _numSides);
		System.out.println("Area:" +_area);
		System.out.println("Perimeter:" + _perimeter);
		System.out.println("Side Lengths : ");
		for(float f : sideLengths) { System.out.print(f + ' '); }
	}
}