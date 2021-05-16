package movies;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;

public class MovieSessionGUI {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private int seatRow = 0; // Variables used for the for loop.
	private int seatCol = 0;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<MovieSession> sessions = new ArrayList<MovieSession>();
					MovieSessionGUI window;					
					
					// Add the movie sessions
					sessions.add(new MovieSession("Queck", 'R', new Time(10, 0, 0)));
					sessions.add(new MovieSession("Queck2", 'R', new Time(10, 20, 0)));
					sessions.add(new MovieSession("Queck3", 'R', new Time(10, 50, 0)));
					Collections.sort(sessions); // Sort the sessions from earliest to latest.
					
					window = new MovieSessionGUI(sessions);
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		});
	}
	

	/**
	 * Create the application.
	 */
	public MovieSessionGUI(List<MovieSession> sessions) {
		initialize(sessions); // Pass the list into the frame	
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(List<MovieSession> sessions) {
		ArrayList<SeatReservation> currentReservation = new ArrayList<SeatReservation>(); // Current reservation of seats
		JButton[][] seats = new JButton[MovieSession.NUM_ROWS][MovieSession.NUM_COLS];
		
		
		frame = new JFrame();
		frame.setResizable(true);
		frame.setBounds(100, 100, 1313, 743);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DefaultListModel<String> DLM = new DefaultListModel<String>();
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		for (MovieSession session : sessions)
		{
			DLM.addElement(session.toString()); // Add all movie sessions to JList
		}
		
		JPanel southPanel = new JPanel();
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
		
		// Radio buttons
		JRadioButton rdbtnChild = new JRadioButton("Child");
		southPanel.add(rdbtnChild);
		rdbtnChild.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonGroup.add(rdbtnChild);
		
		JRadioButton rdbtnAdult = new JRadioButton("Adult");
		southPanel.add(rdbtnAdult);
		rdbtnAdult.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonGroup.add(rdbtnAdult);
		
		JRadioButton rdbtnElderly = new JRadioButton("Elderly");
		southPanel.add(rdbtnElderly);
		rdbtnElderly.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonGroup.add(rdbtnElderly);
		
		// Checkbox
		JCheckBox chckbxComplementary = new JCheckBox("Complementary");
		southPanel.add(chckbxComplementary);
		chckbxComplementary.setFont(new Font("Arial", Font.PLAIN, 12));
		
		
		// Bottom label
		JLabel lblLabel1 = new JLabel("Please click New for a new booking.");
		southPanel.add(lblLabel1);
		lblLabel1.setFont(new Font("Arial", Font.PLAIN, 12));
		
		// Exit
		JButton btnExit = new JButton("Exit");
		southPanel.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);; // Dispose the frame once the user presses exit
			}
		});
		btnExit.setFont(new Font("Arial", Font.PLAIN, 11));
		
		// Book tickets button
		
		JButton btnBookTickets = new JButton("Book tickets");
		southPanel.add(btnBookTickets);
		btnBookTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBookTickets.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JPanel seatGrid = new JPanel();
		frame.getContentPane().add(seatGrid, BorderLayout.CENTER);
		seatGrid.setLayout(new GridLayout(0, 5, 0, 0));
		
		// TODO: Use a for loop to create the rest of the buttons
		// Nested for loop for multi dimesional button
		
		for (int i = 0; i < MovieSession.NUM_ROWS; i++)
		{
			for (int i2 = 0; i2 < MovieSession.NUM_COLS; i2++)
			{
				// Create the button
				seats[i][i2] = new JButton(String.format(MovieSession.convertIndexToRow(i) + "." + (i2+1)));
				seats[i][i2].setForeground(Color.BLACK);
				seats[i][i2].addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						JButton seat = (JButton) e.getSource();
						String seatNumber = seat.getText(); // Get the button text
						int row = MovieSession.convertRowToIndex(seatNumber.charAt(0)); // get the row
						int col = Character.getNumericValue(seatNumber.charAt(2)) - 1; // get the column
						
						if (seats[row][col].getForeground() == Color.BLACK)
						{
							if (rdbtnChild.isSelected())
							{
								seats[row][col].setForeground(Color.YELLOW); // Selected
								currentReservation.add(new ChildReservation(MovieSession.convertIndexToRow(row), col));
							}
							else if (rdbtnAdult.isSelected())
							{
								seats[row][col].setForeground(Color.BLUE); // Selected
								currentReservation.add(new AdultReservation(MovieSession.convertIndexToRow(row), col));
							}
							else if (rdbtnElderly.isSelected())
							{
								seats[row][col].setForeground(Color.WHITE); // Selected
								currentReservation.add(new ElderlyReservation(MovieSession.convertIndexToRow(row), col));
							}
							
						}
						else
						{
							seats[row][col].setForeground(Color.BLACK); // Unselected
							for (int i = 0; i < currentReservation.size(); i++) // Loop through the list to find the element
							{
								if (currentReservation.get(i).getRow() == MovieSession.convertIndexToRow(row) && currentReservation.get(i).getCol() == col)
								{
									currentReservation.remove(i); // Remove the seat.
									break;
								}
							}
						}
							
						
					}});

				seats[i][i2].setFont(new Font("Arial", Font.PLAIN, 14));
				seatGrid.add(seats[i][i2]);
			}
		}
		
		
		
		
		JButton btnNew = new JButton("New"); // Clears out the current reservation
		southPanel.add(btnNew);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblLabel1.setText("Select the seats you want to book.");
				currentReservation.clear();
				// TODO: For loop on buttons for color change
				for (int i = 0; i < MovieSession.NUM_ROWS; i++)
				{
					for (int i2 = 0; i2 < MovieSession.NUM_COLS; i2++)
					{
						seats[i][i2].setForeground(Color.BLACK);
					}
				}
			}
		});
		btnNew.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JPanel northPanel = new JPanel();
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		
		// Title
		JLabel lblPsyduckCinema = new JLabel("Psyduck Cinema");
		northPanel.add(lblPsyduckCinema);
		lblPsyduckCinema.setFont(new Font("Arial Black", Font.BOLD, 14));
		
		JList list = new JList();
		frame.getContentPane().add(list, BorderLayout.EAST);
		list.setBackground(Color.LIGHT_GRAY);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(DLM);
		
		JScrollPane scrollPane = new JScrollPane(list);
		frame.getContentPane().add(scrollPane, BorderLayout.EAST);
	}
}
