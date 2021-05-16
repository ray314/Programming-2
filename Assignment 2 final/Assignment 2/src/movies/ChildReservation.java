/**
 * 
 */
package movies;

/**
 * @author Justin Yeung
 *
 */
public class ChildReservation extends SeatReservation {

	/**
	 * @param row
	 * @param col
	 */
	public ChildReservation(char row, int col) {
		super(row, col);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public float getTicketPrice()
	{
		if (complementary == true)
		{
			return 0.0f;
		}
		
		return 8.0f;
	}
	
	public String toString()
	{
		return "C";
	}

}
