/**
 * 
 */
package movies;

import java.time.LocalTime;

/**
 * @author Justin Yeung
 *
 */
public class Time implements Comparable<Time>{

	private int hours;
	private int mins;
	private int secs;
	
	public Time()
	{
		hours = 0;
		mins = 0;
		secs = 0;
	}
	
	public Time(int hours)
	{
		this.hours = hours;
	}
	
	public Time(int hours, int mins)
	{
		this.hours = hours;
		this.mins = mins;
	}
	
	public Time(int hours, int mins, int secs)
	{
		this.hours = hours;
		this.mins = mins;
		this.secs = secs;
	}
	
	public void setSeconds(int secs)
	{
		if (secs < 0 || secs > 59)
		{
			System.out.println("Cannot use seconds less than 0 or greater than 59");
		}
		else
		{
			this.secs = secs;
		}
	}
	
	public void setMinutes(int mins)
	{
		if (mins < 0 || mins > 59)
		{
			System.out.println("Cannot use minutes less than 0 or greater than 59");
		}
		else
		{		
			this.mins = mins;
		}
	}
	
	public void setHours(int hours)
	{
		if (hours < 0 || hours > 23)
		{
			System.out.println("Cannot use hours less than 0 or greater than 23");
		}
		else
		{
			this.hours = hours;
		}
		
	}
	
	public int getSeconds()
	{
		return secs;
	}
	
	public int getMinutes()
	{
		return mins;
	}
	
	public int getHours()
	{
		return hours;
	}
	
	public boolean equals(Object otherTime)
	{
		return false;
	}
	
	public String toString()
	{
		LocalTime time = LocalTime.of(hours, mins, secs);
		if (secs == 0)
		{
			return time.toString() + ":00"; // Add zeros to end if secs == 0
		}

		return time.toString();
		//return String.format("Time in 24 hour format: %s%nTime in 12 hour format: %s", time.toString(), result);
	}
	
	@Override
	public int compareTo(Time t) {
		// TODO Auto-generated method stub
		float compare = (hours + (mins/60) + (secs/60/60)) -(t.hours + (t.mins/60) + (t.secs/60/60));
		
		if (compare < 0)
		{
			return -1;
		}
		else if (compare > 0)
		{
			return 1;
		}
		return 0;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Time time = new Time();
		time.setHours(15);
		time.setMinutes(34);
		time.setSeconds(0);
		
		System.out.println(time.toString());
	}
}
