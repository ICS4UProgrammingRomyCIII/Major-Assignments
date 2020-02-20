/*
 * Created By: Romy I. Chu III
 * Due on: 19-02-2020 
 * Created For: ICS4U Programming
 * Assignment #1 - String Blowup
 * This program... Blows up a string.
*/


//Imports for the GUI
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

//Import for the lists
import java.util.*;
import java.util.stream.Stream;

//Imports for the listeners.
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

//Imports for file handling
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;



public class main {
	/*GUI OBJECTS*/
	protected Shell frmStringBlowup;							// The GUI form.
	public static Text txtInput;								// The Input text box.
	private Button btnBlowup;									// The "String Blow Up" button.
	private Button btnClearList;								// The button that will clear the text box.
	private Button btnOpenOutputFile;							// A button to open the output file.
	private org.eclipse.swt.widgets.List lstPastStrings;		// A list box to hold past strings.
	private Button btnExitProgram;								// A Button that will exit the program.
	
	/*OTHER VARIABLES*/
	public static List<String> pastStrings = new ArrayList<String>(); // A list of strings for past string blow ups
	public static boolean isAutomatic = true;						  // A boolean to change from auto to manual and vise versa.
	public static String filePath;									  // The path of the file that will be read.
	public static String fileName;									  // The name of the file that will be read.
	
	
	
	//Initialization of the program.
	public static void main(String[] args) {
		try {
			main window = new main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//The listener used for automatic string blowup.
	ModifyListener listener = new ModifyListener() {
	    public void modifyText(ModifyEvent e) { 				 //Everytime the listener is called (a specific event happens, in this case, a modification)...
	    	String outString = StringBlowUp(txtInput.getText()); //Call StringBlowUp() passing the Input text box's text as an argument.
	    	if(!pastStrings.contains(outString)) {				 //Check if this string already exists. If it doesn't...
	    		pastStrings.add(outString);						 //Add the string to the pastStrings...
	    		lstPastStrings.add(outString);					 //Add the string to the list box. 
	    	}
	    }
	};
	
	//This opens and displays the GUI.
	public void open() {
		Display display = Display.getDefault();
		Image img = new Image(display,"VisibleFear.png");
		createContents();
		frmStringBlowup.setImage(img);
		frmStringBlowup.open();
		frmStringBlowup.layout();
		while (!frmStringBlowup.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	//This creates the objects that are going to be displayed.
	protected void createContents() {
		frmStringBlowup = new Shell();
		frmStringBlowup.setSize(543, 434);
		frmStringBlowup.setText("String Blowup - Romy I. Chu III");
		
		// The input text box.
		txtInput = new Text(frmStringBlowup, SWT.BORDER);
		txtInput.setText("");
		txtInput.setBounds(10, 31, 258, 21);

		//This is a list box that will hold all past strings.
		lstPastStrings = new org.eclipse.swt.widgets.List(frmStringBlowup, SWT.BORDER | SWT.V_SCROLL);
		lstPastStrings.setBounds(10, 61, 258, 324);
		
		//This label will display the selected file name.
		Label lblFileName = new Label(frmStringBlowup, SWT.NONE);
		lblFileName.setBounds(274, 109, 150, 15);
		lblFileName.setText("File Name: null");
		
		Label lblInputAnyString = new Label(frmStringBlowup, SWT.NONE);
		lblInputAnyString.setBounds(10, 10, 258, 15);
		lblInputAnyString.setText("Input Any String in the Text Box Below : ");
		
		Button btnHELP = new Button(frmStringBlowup, SWT.NONE);
		btnHELP.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MessageDialog.openInformation(frmStringBlowup, "What does each button do?", 
						"\'Auto String Blowup\' : This button will change the program's mode from automatic to manual and vise versa \n\n"
						+ "Automatic = The string will automatically be blown up when the text box changes; Manual = You will have to press the \'String Blowup\' button to blowup the string \n\n"
						+ "\'String Blowup\' : This button will blow up the input string from the text box.\n\n"
						+ "\'Clear List\' : This button will clear the lsit box of any previous entries. \n\n"
						+ "\'Get File\' : This button will open a file dialog and allow you to choose a text file\n\n"
						+ "\'String Blowup : File\' : This button will blow up each line from the selected text file and output it to another text file \n\n"
						+ "\'Open Output File\' : This button will open the output file in notepad \n\n"
						+ "\'Exit Program\' : This button will close the application \n\n"
						+ "\'Help!!\' : This button will open this information box."
						);
			}
		});
		btnHELP.setFont(SWTResourceManager.getFont("Segoe UI", 53, SWT.NORMAL));
		btnHELP.setBounds(274, 178, 242, 155);
		btnHELP.setText("Help!!");
		
		
		//This button will clear the list box of all strings and the input text box.
		btnClearList = new Button(frmStringBlowup, SWT.NONE);
		btnClearList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				txtInput.setText("");
				lstPastStrings.removeAll();
			}
		});
		btnClearList.setBounds(274, 31, 75, 21);
		btnClearList.setText("Clear List");
		
		
		//This button is for the MANUAL string blow up.
		//It does the same thing as the AUTO listener, it just calls the StringBlowUp function.
		//By default, the program is set to automatic string blow up. So the btnBlowup object is disabled...
		//until the user presses the btnAuto object. 
		btnBlowup = new Button(frmStringBlowup, SWT.NONE);
		btnBlowup.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {			 //Call StringBlowUp() passing the Input text box's text as an argument.
		    	String outString = StringBlowUp(txtInput.getText());
		    	if(!pastStrings.contains(outString)) {				 //Check if this string already exists. If it doesn't...
		    		pastStrings.add(outString);						 //Add the string to the pastStrings...
		    		lstPastStrings.add(outString);					 //Add the string to the list box. 
		    	}
			}
		});
		btnBlowup.setEnabled(false);
		btnBlowup.setBounds(430, 7, 86, 21);
		btnBlowup.setText("String Blowup");
		
		
		//By default, the program is set to automatic string blow up. So the btnBlowup object is disabled...
		//until the user presses the btnAuto object. 
		Button btnAuto = new Button(frmStringBlowup, SWT.NONE);
		btnAuto.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {   //When the button is pressed...
				if(isAutomatic == true) {					 //If the program is already set to AUTOMATIC...
					isAutomatic = false;					 //Set StringBlowUp to be MANUAL.
					btnAuto.setText("Manual String Blowup"); //Change the button text to display the current mode.
					txtInput.removeModifyListener(listener); //Removes the ModifyListener
					btnBlowup.setEnabled(true);				 //Enable the MANUAL string blowup button.
				} else {									 //If the program is set to MANUAL...
					isAutomatic = true;						 //Set StringBlowUp to be AUTOMATIC.
					btnAuto.setText("Auto String Blowup");   //Change the button text to display the current mode.
					txtInput.addModifyListener(listener);    //Adds the ModifyListener.
					btnBlowup.setEnabled(false);			 //Disable the MANUAL string blowup button.
				}
			}
		});
		btnAuto.setBounds(274, 7, 150, 21);
		btnAuto.setText("Auto String Blowup");		

		
		//This button will open a file dialog that will allow the user to choose a file.
		Button btnGetFile = new Button(frmStringBlowup, SWT.NONE);
		btnGetFile.addSelectionListener(new SelectionAdapter() {
			//When the listener is called...
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(frmStringBlowup, SWT.MULTI);    //Create a new file dialog...
				fileDialog.setFilterExtensions(new String[]{"*.txt", "*.rtf", "*.*"}); //Set filter parameters...
				filePath = fileDialog.open();										   //Open the file dialog and get the file path...
				fileName = fileDialog.getFileName();								   //and the file name.
				lblFileName.setText("File Name: " + fileName);						   //Changes the file name label to display the file name.
			}
		});
		btnGetFile.setBounds(274, 82, 51, 21);
		btnGetFile.setText("Get File");
		
		
		//The button that will run StringBlowUp() on the lines from the text file.
		Button btnBlowUpFile = new Button(frmStringBlowup, SWT.NONE);
		btnBlowUpFile.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					PrintWriter fileOut = new PrintWriter("output.txt", "UTF-8"); //This will create an output file or overwrite the previous one if it already exists.
					Stream<String> stream = Files.lines(Paths.get(filePath));	  //Get the lines from the text file.
					String[] stringArray = stream.toArray(String[]::new);		  //Convert the stream object into an array of strings.
					for(String str : stringArray) {								  //For each string in stringArray...
						String outString = StringBlowUp(str);					  //Call the StringBlowUp() function...
						if(!pastStrings.contains(outString)) {					  //Check if this string already exists. If it doesn't...
				    		pastStrings.add(outString);							  //Add the string to the pastStrings..
				    		lstPastStrings.add(outString);						  //Add the string to the list box. 
				    		fileOut.write(outString + "\n");					  //Write the string to the output file.
				    	}
					}
					fileOut.close();											  //Close the PrintWriter (Saves what it writes).
				} catch(Exception exc) {										  //If none of the above worked...
					//Tell the user that they have chosen an invalid file.
					MessageDialog.openError(frmStringBlowup, "An Error had Occurred", "You have chosen an invalid File!!");
				}
			}
		});
		btnBlowUpFile.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnBlowUpFile.setBounds(331, 83, 116, 21);
		btnBlowUpFile.setText("String Blowup : File");
		
		
		//This button will open the output file in notepad.
		btnOpenOutputFile = new Button(frmStringBlowup, SWT.NONE);
		btnOpenOutputFile.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {							 //When the button is pressed...
				ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "output.txt"); //Open the output file with notepad.
				try {
					pb.start();														  //Try to start notepad...
				} catch (IOException e1) {
					e1.printStackTrace();											  //If notepad didn't start, return and error.
				}
			}
		});
		btnOpenOutputFile.setBounds(274, 130, 103, 21);
		btnOpenOutputFile.setText("Open Output File");
		
		
		//This button will close the program.
		btnExitProgram = new Button(frmStringBlowup, SWT.NONE);
		btnExitProgram.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {	//When the button is pressed...
				System.exit(0);								//Close the application.
			}
		});
		btnExitProgram.setBounds(441, 360, 75, 25);
		btnExitProgram.setText("Exit Program");
		
		
		txtInput.addModifyListener(listener); //Adds a ModifyListener to the input text box.
	}
	
	
	
	//This is a function that takes a String as an argument and returns a boolean.
	public static boolean isStringNullOrWhiteSpace(String str) {
	    if (str == null)									//If the string is null...
	        return true;									//Return true.
	    for (int i = 0; i < str.length(); i++) {			//For loop that will continue until i is equal to or greater than the length of str.
	        if (!Character.isWhitespace(str.charAt(i))) {	//If the character at [i] isn't whitespace...
	            return false;								//Return false.
	        }
	    }
	    return true;										//Otherwise, return true.
	}
	
	
	//This function is the heart of the program.
	//It is a function that takes a String as an argument and returns a String.
	public static String StringBlowUp(String inStr) {
		StringBuffer buffer = new StringBuffer();										//Create a temporary StringBuffer, to append characters to.
		if (isStringNullOrWhiteSpace(inStr)) {											//Calls isStringNullOrWhiteSpace() passing inStr as an argument. If true...
			return "Invalid String";													//return "Invalid String"
		} else {																	    //Otherwise...
			for (int i = 0; i < inStr.length(); i++) {									//For loop that will continue until i is equal to or greater than the length of inStr.
				char currentChar = inStr.charAt(i);										//Get the character of inStr at [i].
				if (Character.isDigit(currentChar) && i < inStr.length() - 1) {			//If the character is a digit(integer) AND isn't the last character...
					char nextChar = inStr.charAt(i + 1);								//Get the character after the integer.
					int multPrint = Integer.parseInt(Character.toString(currentChar));  //Parse the first character as an integer.
					for(int j = 0; j < multPrint; j++) {								//For loop that will continue until j is equal to or grater than the parsed integer.
						buffer.append(nextChar);									    //It will add the character to the buffer over and over again until the for loop ends.
					}
				} else {																//Otherwise(if the character wasn't an integer or if it's the last character)...
					buffer.append(currentChar);											//Add the character to the buffer.
				}
			}
			return buffer.toString();												    //Return the buffer as a string.
		}
	}
}
