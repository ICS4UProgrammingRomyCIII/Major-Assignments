import java.util.*;
public class Quadrilateral extends Shape {
	private float _sideLengthA;		// Side length.
	private float _sideLengthB;		// Side length.
	private float _sideLengthC;		// Side length.
	private float _sideLengthD;		// Side length.
	private String _type;			// Type of quadrilateral(Square Rectangle, etc.).
	private List<Float> duplicates = new ArrayList<Float>(); // Used to check type
	
	public Quadrilateral(float inA, float inB, float inC, float inD) {
		_numSides = 4;									// Set the number of sides to 3.
		// Initializes the side lengths.
		_sideLengthA = inA;		sideLengths.add(inA);
		_sideLengthB = inB;		sideLengths.add(inB);
		_sideLengthC = inC;		sideLengths.add(inC);
		_sideLengthD = inD; 	sideLengths.add(inD);
		
		_type = FindType(inA, inB, inC, inD);		// Get the type.
		_area = CalculateArea(inA, inB, inC, inD);	// Calculate area.					
		_perimeter = CalculatePerimeter();			// Calculate perimeter.				
									
	}
	
	private float CalculateArea(float inA, float inB, float inC, float inD) {
		if (_type == "Square")	{ return duplicates.get(0) * duplicates.get(0); }
		else if (_type == "Rectangle") { return duplicates.get(0) * duplicates.get(1); }
		else {
			// Formula for irregular quadrilateral A = sqrt((s-a)*(s-b)*(s-c)*(s-d)-abcd*cos^2(angle/2)), Where s = 0.5 * (a+b+c+d).
			float s = (inA + inB + inC + inD) / (float)2;							 // Calculate s (semiperimeter.)
			float area = (float) Math.sqrt((s-inA) * (s-inB) * (s-inC) * (s-inD)); // Calculate the area using Heron's formula
			return area;
		}
	}
	
	private String FindType(float inA, float inB, float inC, float inD) {
		for (float f : sideLengths) {
			if(!duplicates.contains(f)) { duplicates.add(f); }
		}
		if(duplicates.size() == 1) { return "Square"; }
		else if (duplicates.size() == 2) { return "Rectangle"; }
		else { return "Quadrilateral";}
	}
	
	public float getArea() { return _area; }						// Gets the triangle's area.
	public float getPerimeter() { return _perimeter; }				// Gets the triangle's perimeter.
	public String getType() { return _type; }						// Gets the triangle's type.
}
