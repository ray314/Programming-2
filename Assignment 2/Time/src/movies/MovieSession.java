/**
 * 
 */
package movies;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Justin Yeung
 *
 */
public class MovieSession implements Comparable<MovieSession> {

	private String movieName; // movie name
	private char rating; // age rating
	private Time sessionTime; // session time
	private SeatReservation[][] sessionSeats;
	public final static int NUM_ROWS = 8;
	public final static int NUM_COLS = 6;
	
	
	/**
	 * Creates a MovieSession instance.
	 * @param movieName
	 * @param rating
	 * @param sessionTime
	 */
	public MovieSession(String movieName, char rating, Time sessionTime)
	{
		this.movieName = movieName;
		this.rating = rating;
		this.sessionTime = sessionTime;
		sessionSeats = new SeatReservation[NUM_ROWS][NUM_COLS];
	}
	
	/**
	 * Converts a row letter to an index
	 * @param rowLetter
	 * @return int
	 */
	public static int convertRowToIndex(char rowLetter)
	{
		switch (rowLetter)
		{
		case 'A':
			return 0;
		case 'B':
			return 1;
		case 'C':
			return 2;
		case 'D':
			return 3;
		case 'E':
			return 4;
		case 'F':
			return 5;
		case 'G':
			return 6;
		case 'H':
			return 7;
		}
		
		
		return 0;
	}
	
	public static char convertIndexToRow(int rowIndex)
	{
		switch (rowIndex)
		{
		case 0:
			return 'A';
		case 1:
			return 'B';
		case 2:
			return 'C';
		case 3:
			return 'D';
		case 4:
			return 'E';
		case 5:
			return 'F';
		case 6:
			return 'G';
		case 7:
			return 'H';
		}
		
		return '0';
	}
	
	public char getRating()
	{
		return rating;
	}
	
	public String getMovieName()
	{
		return movieName;
	}
	
	public Time getSessionTime()
	{
		return sessionTime;
	}
	
	public SeatReservation getSeat(char row, int col)
	{
		return sessionSeats[convertRowToIndex(row)][col];
	}
	
	public boolean isSeatAvailable(char row, int col)
	{
		if (sessionSeats[convertRowToIndex(row)][col] == null)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean applyBookings(List<SeatReservation> reservations)
	{
		int col = 0;
		int row = 0;
		boolean accompanied = false;
		// Loop through all the SeatReservation objects
		for (int i = 0; i < reservations.size(); i++)
		{
			col = reservations.get(i).getCol(); // get the column of the index
			row = convertRowToIndex(reservations.get(i).getRow()); // get the row of the index
			if (sessionSeats[row][col] != null) // If the selected seat is already occupied, return false
			{
				return false;
			}
			if (rating == 'R' && reservations.get(i).toString() == "C")
			{
				return false; // Return false if there's a child in a R rated movie.
			}
			else if (rating == 'M' && (reservations.get(i).toString() == "C") && accompanied == false) // If the movie is M rated with a child...
			{
				for (int i2 = 0; i2 < reservations.size(); i2++) // Loop through list to check if there's an adult.
				{
					if (reservations.get(i2).toString() == "A")
					{
						accompanied = true;
						break; // Break the loop if an adult is accompanying the child.
					}
				}
				if (accompanied == false) // If no adult then return false
				{
					return false;
				}
			}
			// If all conditions are met then select the seats:
			sessionSeats[row][col] = reservations.get(i);
		}
		
		return true;
	}
	
	public void printSeats()
	{
		for (int i = 0; i < NUM_ROWS; i++)
		{
			for (int i2 = 0; i2 < NUM_COLS; i2++)
			{
				if (sessionSeats[i][i2] == null)
				{
					System.out.print("|_|");
				}
				else
				{
					System.out.printf("|%s|", sessionSeats[i][i2].toString());
				}
			}
			System.out.println("");
		}
	}
	
	public String toString()
	{
		return String.format("%s(%s) - %s", movieName, rating, sessionTime.toString());
	}
	
	public int compareTo(MovieSession session)
	{
		if (sessionTime.compareTo(session.sessionTime) > 0)
		{
			return 1; // if this session is later then return 1
		}
		else if (sessionTime.compareTo(session.sessionTime) < 0)
		{
			return -1; // else if it's earlier than return -1.
		}
		
		return 0; // both are equal, return 0.
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MovieSession session = new MovieSession("Queck", 'M', null);
		ArrayList<SeatReservation> seats = new ArrayList<SeatReservation>();
		
		seats.add(new ChildReservation('A', 1));
		//seats.add(new AdultReservation('B', 2));
		if (session.applyBookings(seats) == false)
		{
			System.out.println("Failed to book. Either a child is in an R rated movie or an adult is not accompanying with the child in a M rated movie.");
		}
		else
		{
			System.out.println("Successful booking.");
		}
		
		session.printSeats();
	}

}
