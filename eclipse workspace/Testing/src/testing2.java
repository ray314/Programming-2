import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Justin Yeung
 *
 */
public class testing2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Float> list = new ArrayList<Float>();
		
		int x = 0;
		int y = 0;
		int z = 0;
		int count = 5;
		float result = 0;
		
		for (int i = 0; i < 10; i++)
		{
			x = count;
			result = (5*x +1)/(x - 2);
			list.add(result);
			
			x = count * -1;
			result = (5*x +1)/(x - 2);
			list.add(result);
			count++;
		}
		
		for (Float numbers : list)
		{
			System.out.print(numbers + ", ");
		}
	}

}
