/**
 * 
 */
package movies;

/**
 * @author Justin Yeung
 *
 */
public class AdultReservation extends SeatReservation {

	public AdultReservation(char row, int col) {
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
		
		return 12.5f;
	}
	
	public String toString()
	{
		return "A";
	}

}
