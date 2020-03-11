import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;;

public class Assignment2GUI extends JFrame {

	// Objects that will be created later.
	private JTabbedPane contentPane;
	
	// Some variables
	WillowsWildRide runWWR = new WillowsWildRide(); // This is for the WWR class.
	JFileChooser WWRfile = new JFileChooser();	    // This creates a new File Dialog.

	RailwaySort runRWS = new RailwaySort(); 		// This is for the RWS class.
	JFileChooser RWSfile = new JFileChooser();	    // This creates a new File Dialog.
	
	MartianTime runMRT = new MartianTime(); 		// This is for the MRT class.
	JFileChooser MRTfile = new JFileChooser();	    // This creates a new File Dialog.
	
	MountainView runMTV = new MountainView();		// This is for the MRT class.
	JFileChooser MTVfile = new JFileChooser();	    // This creates a new File Dialog.
	
	// Auto-Generated
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assignment2GUI frame = new Assignment2GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Mostly auto-generated
	public Assignment2GUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("VisibleFear.png"));
		setTitle("Assignment 2B - A bunch of the programs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 175);
		
		JTabbedPane mainContentPane = new JTabbedPane(JTabbedPane.TOP);
		setContentPane(mainContentPane);
		
		
		JPanel WWRTab = new JPanel();	// A tab for Willow's Wild Ride.
		JPanel RWSTab = new JPanel();	// A tab for Railway Sort.
		JPanel SPDTab = new JPanel();	// A tab for Spindie.
		JPanel MRTTab = new JPanel();	// A tab for Martian Time.
		JPanel MTVTab = new JPanel();	// A tab for Mountain View.
		
		
		
		
		
		
		
		
		// These blocks of code is for Willow's Wild Ride
		mainContentPane.addTab("Willow's Wild Ride", WWRTab);
		WWRTab.setLayout(null);
		
		JLabel lblWWRFileName = new JLabel("File Name : null");
		lblWWRFileName.setBounds(160, 10, 190, 20);
		WWRTab.add(lblWWRFileName);
		
		JButton btnWWRGetFile = new JButton("Get Test Case File");
		btnWWRGetFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WWRfile.showOpenDialog(null);				// This opens the file dialog.
				lblWWRFileName.setText("File Name : " + WWRfile.getSelectedFile().getName()); // Changes the label to the file name.
			}
		});
		btnWWRGetFile.setBounds(10, 10, 140, 20);
		WWRTab.add(btnWWRGetFile);
		
		JButton btnWWRRunProgram = new JButton("Run Program");
		btnWWRRunProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runWWR.Run_Willows_Wild_Ride(WWRfile); // Runs the program 
			}
		});
		btnWWRRunProgram.setBounds(10, 35, 140, 20);
		WWRTab.add(btnWWRRunProgram);
		
		JButton btnWWROpenOutput = new JButton("Open Output File");
		btnWWROpenOutput.setBounds(10, 60, 140, 20);
		btnWWROpenOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProcessBuilder WWRpb = new ProcessBuilder("Notepad.exe", "WWROutput.txt"); //Open the output file with notepad.
				try {
					WWRpb.start();														  //Try to start notepad...
				} catch (IOException e1) {
				}
			}
		});
		WWRTab.add(btnWWROpenOutput);
		
		
		
		
		
		
		
		
		// These blocks of code is for Railway Sort.
		mainContentPane.addTab("Railway Sort", RWSTab);
		RWSTab.setLayout(null);

		JLabel lblRWSFileName = new JLabel("File Name : null");
		lblRWSFileName.setBounds(160, 10, 190, 20);
		RWSTab.add(lblRWSFileName);
		
		JButton btnRWSGetFile = new JButton("Get Test Case File");
		btnRWSGetFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RWSfile.showOpenDialog(null);				// This opens the file dialog.
				lblRWSFileName.setText("File Name : " + RWSfile.getSelectedFile().getName()); // Changes the label to the file name.
			}
		});
		btnRWSGetFile.setBounds(10, 10, 140, 20);
		RWSTab.add(btnRWSGetFile);
		
		JButton btnRWSRunProgram = new JButton("Run Program");
		btnRWSRunProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runRWS.Run_Railway_Sort(RWSfile);	// Runs the program
			}
		});
		btnRWSRunProgram.setBounds(10, 35, 140, 20);
		RWSTab.add(btnRWSRunProgram);
		
		JButton btnRWSOpenOutput = new JButton("Open Output File");
		btnRWSOpenOutput.setBounds(10, 60, 140, 20);
		btnRWSOpenOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProcessBuilder RWSpb = new ProcessBuilder("Notepad.exe", "RWSOutput.txt"); //Open the output file with notepad.
				try {
					RWSpb.start();														  //Try to start notepad...
				} catch (IOException e1) {
				}
			}
		});
		RWSTab.add(btnRWSOpenOutput);
		
		// These blocks of code is for Spindie.
		mainContentPane.addTab("Spindie", SPDTab);
		SPDTab.setLayout(null);
		
		JLabel lblComingSoon = new JLabel("Coming Soon to ICS4U!! ");
		lblComingSoon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblComingSoon.setBounds(118, 36, 314, 38);
		SPDTab.add(lblComingSoon);
		
		
		
		
		
		
		
		
		// These blocks of code is for Martian Time
		mainContentPane.addTab("Martian Time", MRTTab);
		MRTTab.setLayout(null);

		JLabel lblMRTFileName = new JLabel("File Name : null");
		lblMRTFileName.setBounds(160, 10, 190, 20);
		MRTTab.add(lblMRTFileName);
		
		JButton btnMRTGetFile = new JButton("Get Test Case File");
		btnMRTGetFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MRTfile.showOpenDialog(null);				// This opens the file dialog.
				lblMRTFileName.setText("File Name : " + MRTfile.getSelectedFile().getName()); // Changes the label to the file name.
			}
		});
		btnMRTGetFile.setBounds(10, 10, 140, 20);
		MRTTab.add(btnMRTGetFile);
		
		JButton btnMRTRunProgram = new JButton("Run Program");
		btnMRTRunProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runMRT.Run_Martian_Time(MRTfile);	// Runs the program
			}
		});
		btnMRTRunProgram.setBounds(10, 35, 140, 20);
		MRTTab.add(btnMRTRunProgram);
		
		JButton btnMRTOpenOutput = new JButton("Open Output File");
		btnMRTOpenOutput.setBounds(10, 60, 140, 20);
		btnMRTOpenOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProcessBuilder MRTpb = new ProcessBuilder("Notepad.exe", "MRTOutput.txt"); //Open the output file with notepad.
				try {
					MRTpb.start();														  //Try to start notepad...
				} catch (IOException e1) {
				}
			}
		});
		MRTTab.add(btnMRTOpenOutput);
		
		
		
		
		
		
		
		
		// These blocks of code is for Mountain View
		mainContentPane.addTab("Mountain View", MTVTab);
		MTVTab.setLayout(null);

		JLabel lblMTVFileName = new JLabel("File Name : null");
		lblMTVFileName.setBounds(160, 10, 190, 20);
		MTVTab.add(lblMTVFileName);
		
		JButton btnMTVGetFile = new JButton("Get Test Case File");
		btnMTVGetFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MTVfile.showOpenDialog(null);				// This opens the file dialog.
				lblMTVFileName.setText("File Name : " + MTVfile.getSelectedFile().getName()); // Changes the label to the file name.
			}
		});
		btnMTVGetFile.setBounds(10, 10, 140, 20);
		MTVTab.add(btnMTVGetFile);
		
		JButton btnMTVRunProgram = new JButton("Run Program");
		btnMTVRunProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runMTV.Run_Mountain_View(MTVfile);	// Runs the program
			}
		});
		btnMTVRunProgram.setBounds(10, 35, 140, 20);
		MTVTab.add(btnMTVRunProgram);
		
		JButton btnMTVOpenOutput = new JButton("Open Output File");
		btnMTVOpenOutput.setBounds(10, 60, 140, 20);
		btnMTVOpenOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProcessBuilder MTVpb = new ProcessBuilder("Notepad.exe", "MTVOutput.txt"); //Open the output file with notepad.
				try {
					MTVpb.start();														  //Try to start notepad...
				} catch (IOException e1) {
				}
			}
		});
		MTVTab.add(btnMTVOpenOutput);
	}
}
