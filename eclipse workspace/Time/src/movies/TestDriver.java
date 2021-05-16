/**
 * 
 */
package movies;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Justin Yeung
 * A program to test the Time class
 */
public class TestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MovieSession session1 = new MovieSession("Queck", 'G', new Time(6, 0, 0));
		MovieSession session2 = new MovieSession("Queck2", 'G', new Time(4, 0, 0));
		MovieSession session3 = new MovieSession("Queck3", 'G', new Time(2, 0, 0));
		
		ArrayList<MovieSession> sessions = new ArrayList<MovieSession>();
		sessions.add(session1);
		sessions.add(session2);
		sessions.add(session3);
		System.out.println(session2.compareTo(session1));
		System.out.println(session3.compareTo(session2));
		
		Collections.sort(sessions);
		
		
		for (MovieSession test : sessions)
		{
			
			System.out.println(test.toString());
		}
	}

}
