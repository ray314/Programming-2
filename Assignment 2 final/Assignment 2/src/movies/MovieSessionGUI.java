package movies;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * A front-end GUI of MovieSession
 */
public class MovieSessionGUI {

	/*
	 * Initialize all fields
	 */
	
	private JFrame frame;
	private DefaultListModel<MovieSession> DLM = new DefaultListModel<MovieSession>(); // List model
	private JList<MovieSession> list = new JList<MovieSession>(); // JList
	private boolean firstSelection = false; // boolean used to check if the user made the first selection on the JList
	private boolean isSelecting = false; // Check whether the user is currently selecting the session.
	
	private JPanel southPanel = new JPanel(); // South panel
	private JRadioButton rdbtnChild = new JRadioButton("Child"); // Child radio button
	private JRadioButton rdbtnAdult = new JRadioButton("Adult"); // Adult radio button
	private JRadioButton rdbtnElderly = new JRadioButton("Elderly"); // Elderly radio button
	private final ButtonGroup buttonGroup = new ButtonGroup(); // Radio button group
	private JCheckBox chckbxComplementary = new JCheckBox("Complementary");
	private JLabel lblTitle = new JLabel("Please select the session.");
	private JButton btnExit = new JButton("Exit");
	private JButton btnNew = new JButton("New"); // Clears out the current reservation
	private JButton btnBookTickets = new JButton("Book tickets"); // Book tickets
	
	private ArrayList<SeatReservation> currentReservation = new ArrayList<SeatReservation>(); // Current reservation of seatingButtons
	private JButton[][] seatingButtons = new JButton[MovieSession.NUM_ROWS][MovieSession.NUM_COLS];
	private JPanel seatGrid = new JPanel(); // Seat grid.
	
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
					sessions.add(new MovieSession("Sqwippy Coming Through :worryWheelchair:", 'M', new Time(10, 0, 0)));
					sessions.add(new MovieSession("NotLikeDuck", 'R', new Time(11, 20, 0)));
					sessions.add(new MovieSession("DuckSuperSpin", 'G', new Time(8, 50, 0)));
					sessions.add(new MovieSession("AUTISTICDUCKSTRIKE", 'R', new Time(6, 30, 0)));
					sessions.add(new MovieSession("Quackin in the 90s - 10 Hours", 'G', new Time(18, 00, 0)));
					sessions.add(new MovieSession("PSYAYAYAYA", 'M', new Time(13, 30, 0)));
					
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
	
	/*
	 * Sets the seat background colors for reserved
	 */
	private void setSeatColoursReserved(SeatReservation seat, int row, int col)
	{
		if (seat.toString() == "C")
		{
			seatingButtons[row][col].setBackground(Color.YELLOW);
			seatingButtons[row][col].setForeground(Color.BLACK);
		}
		else if (seat.toString() == "A")
		{
			seatingButtons[row][col].setBackground(Color.BLUE);
			seatingButtons[row][col].setForeground(Color.WHITE);
		}
		else if (seat.toString() == "E")
		{
			seatingButtons[row][col].setBackground(Color.WHITE);
			seatingButtons[row][col].setForeground(Color.BLACK);
		}
	}
	
	/*
	 * Updates the seating grid when the user selects another session,
	 */
	private void changeSession()
	{
		MovieSession session = list.getSelectedValue();
		SeatReservation seat;
		
		currentReservation.clear(); // clear the current reservation
		for (int row = 0; row < MovieSession.NUM_ROWS; row++) // Change all button colors to the default value and enable them
		{
			for (int col = 0; col < MovieSession.NUM_COLS; col++)
			{
				seatingButtons[row][col].setBackground(Color.LIGHT_GRAY);
				seatingButtons[row][col].setForeground(Color.BLACK);
				seatingButtons[row][col].setEnabled(true);
				seat = session.getSeat(MovieSession.convertIndexToRow(row), col);
				
				if (seat != null) // If the seat is reserved then change the colors.
				{
					setSeatColoursReserved(seat, row, col); // Set the seat background colours
					
					seatingButtons[row][col].setEnabled(false); // Disable the ones that are already reserved.
				}
			}
		}
	}
	
	/*
	 * Disables the seatingButtons reserved after booking.
	 */
	private void disableReservedseatingButtons()
	{
		int row = 0;
		int col = 0;
		
		for (SeatReservation seat : currentReservation)
		{
			row = MovieSession.convertRowToIndex(seat.getRow());
			col = seat.getCol();
			
			setSeatColoursReserved(seat, row, col); // Set the seat background colours
			
			seatingButtons[row][col].setEnabled(false);
		}
	}
	
	/*
	 * Create the south panel and its buttons 
	 */
	private void createSouthPanel()
	{
		// Radio buttons
		
		southPanel.add(rdbtnChild);
		rdbtnChild.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonGroup.add(rdbtnChild);
		
		
		rdbtnAdult.setSelected(true);
		southPanel.add(rdbtnAdult);
		rdbtnAdult.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonGroup.add(rdbtnAdult);
		
		
		southPanel.add(rdbtnElderly);
		rdbtnElderly.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonGroup.add(rdbtnElderly);
		///////////////////////////////////////////////////////////////////
		
		// Checkbox
		
		southPanel.add(chckbxComplementary);
		chckbxComplementary.setFont(new Font("Arial", Font.PLAIN, 12));
		
		
		// Bottom label
		
		southPanel.add(lblTitle);
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 12));
		
		// Exit
		
		southPanel.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);; // Dispose the frame once the user presses exit
			}
		});
		btnExit.setFont(new Font("Arial", Font.PLAIN, 11));
		
		// Book tickets button
		
		
		southPanel.add(btnBookTickets);
		btnBookTickets.setEnabled(false); // Disable button before the user selects an item from the list.
		
		// Book Listener
		btnBookTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MovieSession selectedSession = list.getSelectedValue();
				float cost = 0.0f;
				
				for (SeatReservation seat : currentReservation) // Calculate the cost
				{
					cost += seat.getTicketPrice();
				}
				
				if (currentReservation.isEmpty())
				{
					JOptionPane.showMessageDialog(frame, "Please select seats first before booking", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (selectedSession.applyBookings(currentReservation) == true)
				{
					disableReservedseatingButtons();
					JOptionPane.showMessageDialog(frame, "Ticket cost is: $" + cost , currentReservation.size() + " Tickets", JOptionPane.INFORMATION_MESSAGE);
					currentReservation.clear(); // Clear the current reservation
				}
				else 
				{		
					JOptionPane.showMessageDialog(frame, "Cannot book a child in a R rated movie or unsupervised in an M rated movie.", currentReservation.size() + " tickets not Booked", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnBookTickets.setFont(new Font("Arial", Font.PLAIN, 11));
		//////////////////////////////////////////////////////////////////////////////
		
		
		frame.getContentPane().add(seatGrid, BorderLayout.CENTER);
		seatGrid.setLayout(new GridLayout(0, 6, 0, 0));
		
		
		// New button
		
		southPanel.add(btnNew);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = 0;
				int col = 0;
				
				for (SeatReservation seat : currentReservation)
				{
					row = MovieSession.convertRowToIndex(seat.getRow());
					col = seat.getCol();
					seatingButtons[row][col].setForeground(Color.BLACK);
				}
				currentReservation.clear();
				
			}
		});
		btnNew.setFont(new Font("Arial", Font.PLAIN, 12));
	}
	
	/*
	 * Book seat based on the row, col,
	 */
	private void bookSeat(int row, int col, Color colour, String age)
	{
		seatingButtons[row][col].setForeground(colour); // Selected
		if (age == "Child")
		{
			currentReservation.add(new ChildReservation(MovieSession.convertIndexToRow(row), col));
		}
		else if (age == "Adult")
		{
			currentReservation.add(new AdultReservation(MovieSession.convertIndexToRow(row), col));
		}
		else if (age == "Elderly")
		{
			currentReservation.add(new ElderlyReservation(MovieSession.convertIndexToRow(row), col));
		}
		
	}
	
	private void createseatingButtons()
	{
		// Create the seatingButtons.
		Border thickBorder = new LineBorder(Color.BLACK, 2); // Border for the seatingButtons
		
		for (int i = 0; i < MovieSession.NUM_ROWS; i++)
		{
			for (int i2 = 0; i2 < MovieSession.NUM_COLS; i2++)
			{
				// Create the button
				seatingButtons[i][i2] = new JButton(String.format(MovieSession.convertIndexToRow(i) + "." + (i2+1)));
				seatingButtons[i][i2].setForeground(Color.BLACK);
				seatingButtons[i][i2].setBackground(Color.LIGHT_GRAY);
				seatingButtons[i][i2].setBorder(thickBorder);
				seatingButtons[i][i2].setContentAreaFilled(false);
				seatingButtons[i][i2].setOpaque(true);
				seatingButtons[i][i2].addActionListener(new ActionListener() {
					
				/*
				 * If the seating button is pressed then perform the following actions:(non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					JButton seat = (JButton) e.getSource(); // Get the JButton from the ActionEvent
					String seatNumber = seat.getText(); // Get the button text
					int row = MovieSession.convertRowToIndex(seatNumber.charAt(0)); // get the row from the first char in seatNumber
					int col = Character.getNumericValue(seatNumber.charAt(2)) - 1; // get the column from the third char in seatNumber
					
					// Select which color to use depending on which radio button was selected
					if (seatingButtons[row][col].getForeground() == Color.BLACK)
					{
						if (rdbtnChild.isSelected())
						{
							bookSeat(row, col, Color.YELLOW, "Child");
						}
						else if (rdbtnAdult.isSelected())
						{
							bookSeat(row, col, Color.BLUE, "Adult");		
						}
						else if (rdbtnElderly.isSelected())
						{
							bookSeat(row, col, Color.WHITE, "Elderly");
						}
						
						if (chckbxComplementary.isSelected()) // If this booking is complementary, set it to true
						{
							currentReservation.get(currentReservation.size() - 1).complementary = true;
						}
					}
					else
					{	// Remove the seat from the list.
						for (int i = 0; i < currentReservation.size(); i++) // Loop through the list to find the element
						{
							if (currentReservation.get(i).getRow() == MovieSession.convertIndexToRow(row) && currentReservation.get(i).getCol() == col)
							{
								currentReservation.remove(i); // Remove the seat.
								break; // Break the loop as the seat has already been removed.
							}
						}
						boolean unselected = false; // Check if the seat is getting unselected
						// Overwrite the seat if another radio button is selected and the foreground is not equal.
						if (rdbtnChild.isSelected() && seatingButtons[row][col].getForeground() != Color.YELLOW)
						{
							bookSeat(row, col, Color.YELLOW, "Child");
						}
						else if (rdbtnAdult.isSelected() && seatingButtons[row][col].getForeground() != Color.BLUE)
						{
							bookSeat(row, col, Color.BLUE, "Adult");
						}
						else if (rdbtnElderly.isSelected() && seatingButtons[row][col].getForeground() != Color.WHITE)
						{
							bookSeat(row, col, Color.WHITE, "Elderly");
						}
						else // Else unselect the seat.
						{
							seatingButtons[row][col].setForeground(Color.BLACK); // Unselected
							unselected = true;
						}

						if (chckbxComplementary.isSelected() && unselected == false) // If this booking is complementary and it is not unselecting, set it to true
						{
							currentReservation.get(currentReservation.size() - 1).complementary = true;
						}
					}			
				}});
				
				// Add the seatingButtons to the grid and change the font.
				seatingButtons[i][i2].setFont(new Font("Arial", Font.PLAIN, 14));
				seatingButtons[i][i2].setEnabled(false); // Disable all the buttons initially until the user makes a selection on the list.
				seatGrid.add(seatingButtons[i][i2]);
			}
		}
	}
	
	/*
	 * This method is used to enable all buttons on the grid when the user makes the first selection
	 */
	private void enableAllButtons()
	{
		for (int row = 0; row < MovieSession.NUM_ROWS; row++)
		{
			for (int col = 0; col < MovieSession.NUM_COLS; col++)
			{
				seatingButtons[row][col].setEnabled(true);
			}
		}
	}
	
	// Disable the ListSelectionListener when it selects the previous item.
	private void isSelecting(int previous)
	{
		isSelecting = true;
		list.setSelectedIndex(previous); 
		isSelecting = false;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(List<MovieSession> sessions) {
		// Create the frame.
		frame = new JFrame();
		frame.setResizable(true);
		frame.setBounds(100, 100, 1313, 743);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Collections.sort(sessions); // Sort the sessions from earliest to latest.
		for (MovieSession session : sessions)
		{
			DLM.addElement(session); // Add all movie sessions to JList
		}
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
		
		createseatingButtons(); // Create the seatingButtons.
		createSouthPanel(); // Create the south panel of buttons.
		
		
		JPanel northPanel = new JPanel();
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		
		// Title
		JLabel lblPsyduckCinema = new JLabel("Psyduck Cinema");
		northPanel.add(lblPsyduckCinema);
		lblPsyduckCinema.setFont(new Font("Arial Black", Font.BOLD, 14));
		
		// Add listener to list.
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
					
				int selected = list.getSelectedIndex();
				int previous = selected == e.getFirstIndex() ? e.getLastIndex() : e.getFirstIndex(); // Ternary condition
				if (e.getValueIsAdjusting() == false)
				{
					if (list.getSelectedIndex() >= 0 && firstSelection == false) // Check if the user has selected a session
					{
						btnBookTickets.setEnabled(true); // If yes then enable book button
						firstSelection = true;
						lblTitle.setText("Please select the seats you want to book. Press New to start over.");
						enableAllButtons();
					}
					// Change session when the user selects another session. 
					// Also check whether the current reservation has any elements in it
					// The last condition is used to prevent the dialog from popping twice since setSelectedIndex triggers the ListSelectionEvent.
					else if (currentReservation.isEmpty() == false && isSelecting == false) 
					{
						int reply = JOptionPane.showConfirmDialog(frame, "This will clear out your current reservation. Continue?", "Warning", JOptionPane.YES_NO_OPTION);
						
						if (reply == JOptionPane.YES_OPTION)
						{
							changeSession();
						}
						else
						{
							isSelecting(previous); // Else stay on the current selection.
						}
					}
					else if (currentReservation.isEmpty() == true)
					{
						changeSession(); // If current reservation is empty then change to a different session
					}
				}
			}
		});
		// Set the layouts
		frame.getContentPane().add(list, BorderLayout.EAST);
		list.setBackground(Color.LIGHT_GRAY);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(DLM);
		
		JScrollPane scrollPane = new JScrollPane(list);
		frame.getContentPane().add(scrollPane, BorderLayout.EAST);
	}
}
