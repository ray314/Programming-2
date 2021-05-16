/**
 * 
 */
package movies;

/**
 * @author Justin Yeung
 *
 */
public abstract class SeatReservation {

	private char row;
	private int col;
	protected boolean complementary;
	
	public SeatReservation(char row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	public float getTicketPrice()
	{
		return 0.0f;
	}
	
	public void setComplementary(boolean complementary)
	{
		this.complementary = complementary;
	}
	
	public char getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
}
