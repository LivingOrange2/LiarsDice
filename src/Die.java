import java.util.Random;

public class Die
{
	private final int SIDES = 6;
	
	int value;
	
	public Die()
	{
		value = 0;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public int roll()
	{
		Random rand = new Random();
		
		value = rand.nextInt(SIDES) + 1;
		
		return value;
	} // roll
	
}
