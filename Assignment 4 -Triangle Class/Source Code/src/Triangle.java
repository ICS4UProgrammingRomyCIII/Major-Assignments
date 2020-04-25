// This is a child of the Shape Class.
public class Triangle extends Shape {
	// Private variables for the properties of a TRIANGLE.
	private float _sideLengthA;		// Side length.
	private float _sideLengthB;		// Side length.
	private float _sideLengthC;		// Side length.
	private String _type;			// Type of triangle(Right angle, acute, etc.).
	private float _rInscribed;		// Radius of the inscribed circle.
	private float _rCircumcircle;	// Radius of the circumcircle.
	private float _aCircumcircle;	// Area of the circumcircle.
	private float _height;			// Height of triangle, where B is the base.
	
	// Public constructor for Triangle that takes 3 floats, side lengths, as arguments.
	public Triangle(float inA, float inB, float inC) {
		_numSides = 3;									// Set the number of sides to 3.
		// Initializes the side lengths.
		_sideLengthA = inA;		sideLengths.add(inA);
		_sideLengthB = inB;		sideLengths.add(inB);
		_sideLengthC = inC;		sideLengths.add(inC);
		
		// Checks if the inputed side lengths are valid.
		if(isTriangleValid(inA, inB, inC)) {											// If the side lengths are valid...
			_area = CalculateArea(inA, inB, inC);						// Calculate area.
			_perimeter = CalculatePerimeter();							// Calculate perimeter.
			_type = FindType(inA, inB, inC);							// Get the type.
			_height = CalculateHeight(inB);								// Calculates the height, where B is the base.
			_rInscribed = CalculateRInscribedCircle(inA, inB, inC);		// Calculate the radius of the inscribed circle.
			_rCircumcircle = CalculateCircumcircleRadius(inA, inB, inC);// Calculate the radius of the circumcircle.
			_aCircumcircle = CalculateCircumcircleArea(_rCircumcircle); // Calculate the area of the circumcircle.
		} else {		// If the triangle isn't valid.
			_area = -1; // Set area to -1.
		}
	}
	
	// This function checks if the triangle is valid.
	protected boolean isTriangleValid(float inA, float inB, float inC) {
		// Formula to check for triangle = IF (sideC < (sideA + sideB)) THEN triangle = valid, Where sideC is the longest side.
		float largestSide; 		// Create a float to store the largest side length.
		float smallSideSum = 0;	// Create a float to store the sum of the smaller sides.
		
		// Find the longest side.
		// Check is B is larger than A
		largestSide = _sideLengthA; // Set side A as the largest side.
		if (_sideLengthB > largestSide) {	// If side B is longer than the current largest...
			smallSideSum += largestSide;	// Add the current largest side to the smaller sum.
			largestSide = _sideLengthB;  	// Make side B the new largest side.
		}		
		else { smallSideSum += _sideLengthB; }// Otherwise... Add side B to the smaller sum.
		
		// Check if C is larger than the previous largest (either A or B).
		if (_sideLengthC > largestSide) {	// If side C is longer than the current largest...
			smallSideSum += largestSide;	// Add the current largest side to the smaller sum.
			largestSide = _sideLengthC; 	// Make side C the new largest side.
		}
		else { smallSideSum += _sideLengthC; }// Otherwise... Add side C to the smaller sum.
		
		// If the triangle is valid, return true, else, return false.
		if(smallSideSum > largestSide) { return true; } else { return false; }
	}
	
	// This function calculates the area of the triangle.
	private float CalculateArea(float inA, float inB, float inC) {
		// Formula for A = sqrt(s * (s-a) * (s-b) * (s-c)), Where s = 0.5 * (a+b+c).
		float s = (inA + inB + inC) / (float)2;							 // Calculate s (semiperimeter.)
		float area = (float) Math.sqrt(s * (s-inA) * (s-inB) * (s-inC)); // Calculate the area using Heron's formula
		return area;													 // Return the area.
	}

	// This function calculates the height of the triangle.
	private float CalculateHeight(float inBase) {
		// Formula to calculate height = 2 * (area / base)
		float h =  2 * (_area / inBase);// Calculate the height
		return h;						// Return the height.
	}
	
	// This function finds the type of the triangle.
	private String FindType(float inA, float inB, float inC) {
		String out = "";			// String for the output.
		float longestSide = inA;	// float for the longest side.
		float otherSide, otherSide2;// floats for the other two sides.
		
		// These two if loops check if one of the values is larger than the other, finding the largest value.
		if (longestSide < inB) { otherSide = longestSide; longestSide = inB; }
		else { otherSide = inB; }
		if (longestSide < inC) { otherSide2 = longestSide; longestSide = inC; }
		else { otherSide2 = inC; }
		
		// Squares all of the values.
		float cSqrd = (longestSide * longestSide);
		float aSqrd = (otherSide * otherSide);
		float bSqrd = (otherSide2 * otherSide2);
		
		// Finds the type of angle that the triangle has.
		if (cSqrd == (aSqrd + bSqrd)) { out = "Right Angle"; }
		else if (cSqrd < (aSqrd + bSqrd)) { out = "Acute"; }
		else if (cSqrd > (aSqrd + bSqrd)) { out = "Obtuse"; }
		
		// Finds and returns the type of triangle.
		if(longestSide == otherSide && otherSide == otherSide2) { return "Equilateral"; }
		else if(otherSide == otherSide2 || otherSide == longestSide || otherSide2 == longestSide) { return (out + " Isosceles"); }
		else { return (out + " Scalene"); }
	}

	// This function calculates the radius of the inscribed circle.
	private float CalculateRInscribedCircle(float inA, float inB, float inC) {
		// Formula for inscribed circle in a triangle = area / semiperimeter
		float s = (inA + inB + inC) / (float)2;	// Calculate s (semiperimeter.)
		float r = _area / s; 					// Calculate the radius.
		return r;								// Return the Radius.
	}
	
	// This function calculates the radius of the Circumcircle.
	private float CalculateCircumcircleRadius(float inA, float inB, float inC) {
		// Formula to calculate circumcircle radius = (a * b *c) / (4 * area)
		float r = (inA * inB * inC) / (4 * _area); // Calculates the radius.	
		return r;								   // Returns the radius.
	}
	
	// This function calculates the area of the Circumcircle.
	private float CalculateCircumcircleArea(float radius) {
		// Formula to calculate circumcircle area = pi * r^2
		float a = (float) (Math.PI * (radius * radius)); // Calculates the area.
		return a;										 // Returns the area.
	}
	
	public float getArea() { return _area; }						// Gets the triangle's area.
	public float getPerimeter() { return _perimeter; }				// Gets the triangle's perimeter.
	public String getType() { return _type; }						// Gets the triangle's type.
	public float getHeight() { return _height; }					// Gets the triangle's area.
	public float getInscribedCircle() { return _rInscribed; }		// Gets the radius of the inscribed circle.
	public float getCircumcircleRadius() { return _rCircumcircle; } // Gets the triangle's circumcircle radius.
	public float getCircumcircleArea() { return _aCircumcircle; }	// Gets the triangle's circumcircle area.
}
