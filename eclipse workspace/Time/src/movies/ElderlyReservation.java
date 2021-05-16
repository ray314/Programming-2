/**
 * 
 */
package movies;

/**
 * @author Justin Yeung
 *
 */
public class ElderlyReservation extends SeatReservation {

	/**
	 * @param row
	 * @param col
	 */
	public ElderlyReservation(char row, int col) {
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
		
		return 12.5f * 0.7f;
	}
	
	public String toString()
	{
		return "E";
	}
}
