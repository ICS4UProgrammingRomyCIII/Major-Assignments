import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.Canvas;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;

public class main {
	// Objects used for displaying the form.
	private JFrame frmMainWindow;
	private JPanel pnlMainPanel;
	private JTextField txtLengthSideA;
	private JTextField txtLengthSideB;
	private JTextField txtLengthSideC;
	private JLabel lblShapeCreated;
	private JLabel lblArea;
	private JLabel lblPerimeter;
	private JLabel lblType;
	private JLabel lblRInscribed;
	private JLabel lblCircumcircleRadius;
	private JLabel lblCircumcircleArea;
	private JLabel lblHeight;
	private JLabel lblSideD;
	private JTextField txtLengthSideD;
	
	// Automatically generated...
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frmMainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	// The constructor for main that creates the objects.
	public main() {
		initialize();
	}

	// Creates the objects.
	private void initialize() {
		frmMainWindow = new JFrame();
		pnlMainPanel = new JPanel();
		pnlMainPanel.setBounds(0, 0, 450, 300);
		frmMainWindow.setTitle("Assignment #4 - Triangle Class");
		frmMainWindow.setBounds(100, 100, 331, 383);
		frmMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainWindow.setContentPane(pnlMainPanel);
		pnlMainPanel.setLayout(null);
		
		JLabel lblSideA = new JLabel("Length of Side A : ");
		lblSideA.setBounds(10, 10, 170, 20);
		frmMainWindow.getContentPane().add(lblSideA);
		
		JLabel lblSideB = new JLabel("Length of Side B (Triangle Base): ");
		lblSideB.setBounds(10, 35, 196, 20);
		frmMainWindow.getContentPane().add(lblSideB);
		
		JLabel lblSideC = new JLabel("Length of Side C : ");
		lblSideC.setBounds(10, 60, 170, 20);
		frmMainWindow.getContentPane().add(lblSideC);
		
		lblShapeCreated = new JLabel("");
		lblShapeCreated.setBounds(10, 159, 170, 20);
		pnlMainPanel.add(lblShapeCreated);
		
		lblArea = new JLabel("Area : ");
		lblArea.setBounds(10, 193, 295, 20);
		pnlMainPanel.add(lblArea);
		
		lblPerimeter = new JLabel("Perimeter : ");
		lblPerimeter.setBounds(10, 213, 295, 20);
		pnlMainPanel.add(lblPerimeter);
		
		lblType = new JLabel("Type : ");
		lblType.setBounds(10, 233, 295, 20);
		pnlMainPanel.add(lblType);
		
		lblRInscribed = new JLabel("Radius of Inscribed Circle : ");
		lblRInscribed.setBounds(10, 273, 295, 20);
		pnlMainPanel.add(lblRInscribed);
		
		lblCircumcircleRadius = new JLabel("Circumcircle Radius : ");
		lblCircumcircleRadius.setBounds(10, 293, 295, 20);
		pnlMainPanel.add(lblCircumcircleRadius);
		
		lblCircumcircleArea = new JLabel("Circumcircle Area : ");
		lblCircumcircleArea.setBounds(10, 313, 295, 20);
		pnlMainPanel.add(lblCircumcircleArea);
		
		lblHeight = new JLabel("Height (Side B is Base) : ");
		lblHeight.setBounds(10, 253, 295, 20);
		pnlMainPanel.add(lblHeight);
		
		lblSideD = new JLabel("Length of Side D (Optional) : ");
		lblSideD.setBounds(10, 85, 170, 20);
		pnlMainPanel.add(lblSideD);
		
		txtLengthSideD = new JTextField();
		txtLengthSideD.setColumns(10);
		txtLengthSideD.setBounds(216, 85, 89, 20);
		pnlMainPanel.add(txtLengthSideD);
		
		txtLengthSideA = new JTextField();
		txtLengthSideA.setBounds(216, 10, 89, 20);
		pnlMainPanel.add(txtLengthSideA);
		txtLengthSideA.setColumns(10);
		
		txtLengthSideB = new JTextField();
		txtLengthSideB.setColumns(10);
		txtLengthSideB.setBounds(216, 35, 89, 20);
		pnlMainPanel.add(txtLengthSideB);
		
		txtLengthSideC = new JTextField();
		txtLengthSideC.setColumns(10);
		txtLengthSideC.setBounds(216, 60, 89, 20);
		pnlMainPanel.add(txtLengthSideC);
		
		JButton btnCreateTriangle = new JButton("Create Shape");
		btnCreateTriangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Variables for each of the sides
					float sideD;
					float sideA = Float.parseFloat(txtLengthSideA.getText());
					float sideB = Float.parseFloat(txtLengthSideB.getText());
					float sideC = Float.parseFloat(txtLengthSideC.getText());
					// If the 3 required vars are not valid... throw an exception.
					if(sideA <= 0 || sideB <= 0 || sideC <= 0) { throw new Exception(); }
					
					// Try to parse the fourth, optional, side... if an exception occurs, set sideD to -1.
					try { sideD = Float.parseFloat(txtLengthSideD.getText()); } catch(Exception exc) { sideD = -1; }
					if(sideD <= 0) { CreateTriangle(sideA, sideB, sideC); }		// If sideD is less than or equal to 0... Create a triangle.
					else { CreateQuadrilateral(sideA, sideB, sideC, sideD); }	// Otherwise, create a Quadrilateral.
				} catch(Exception exc) {
					// If an exception occurs, tell the user the problem.
					JOptionPane.showMessageDialog(null, "Please enter positive integers or floats greater than 0 ONLY!!", "An Error Has Occurred", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCreateTriangle.setBounds(10, 116, 170, 23);
		frmMainWindow.getContentPane().add(btnCreateTriangle);
	}
	
	// This function will create a triangle and display its properties
	public void CreateTriangle(float sideA, float sideB, float sideC) {
		Triangle tri = new Triangle(sideA, sideB, sideC);
		
		if(tri.getArea() == -1) {
			lblShapeCreated.setText("Triangle Not Created!!");
			JOptionPane.showMessageDialog(null, "Triangle was not valid", "An Error Has Occurred", JOptionPane.ERROR_MESSAGE);
		} else {
			lblArea.setText("Area : " + tri.getArea() + "^2");
			lblPerimeter.setText("Perimeter : " + tri.getPerimeter());
			lblType.setText("Triangle Type : " + tri.getType());
			lblHeight.setText("Height (Side B is Base) : " + tri.getHeight());
			lblRInscribed.setText("Radius of Inscribed Circle : " + tri.getInscribedCircle());
			lblCircumcircleRadius.setText("Circumcircle Radius : " + tri.getCircumcircleRadius());
			lblCircumcircleArea.setText("Circumcircle Area : " + tri.getCircumcircleArea());
			lblShapeCreated.setText("Triangle Created!!");
		}
	}
	// This function will create a Quadrilateral and display its properties
	public void CreateQuadrilateral(float sideA, float sideB, float sideC, float sideD) {
		Quadrilateral quad = new Quadrilateral(sideA, sideB, sideC, sideD);
		
		if(quad.getArea() == -1) {
			lblShapeCreated.setText("Quadrilateral Not Created!!");
		} else {
			lblArea.setText("Area : " + quad.getArea() + "^2" + " (Assuming <cd + < ab  = 180)");
			lblPerimeter.setText("Perimeter : " + quad.getPerimeter());
			lblType.setText("Quadrilateral Type : " + quad.getType());
			lblHeight.setText("Height (Side B is Base) : " + "N/A");
			lblRInscribed.setText("Radius of Inscribed Circle : " + "N/A");
			lblCircumcircleRadius.setText("Circumcircle Radius : " + "N/A");
			lblCircumcircleArea.setText("Circumcircle Area : " + "N/A");
			lblShapeCreated.setText("Quadrilateral Created!!");
		}
	}
}
