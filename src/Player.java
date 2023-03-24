import java.util.ArrayList;

public abstract class Player
{
	private ArrayList<Die> dice;
	
	private int[] values;
	public Player()
	{
		values = new int[6];
		reset();
	}
	
	public void reset()
	{
		dice.clear();
		
		for(int x = 0; x < 5; x++)
		{
			dice.add(new Die());
		}
	}
	
	public void rollDice()
	{
		for(Die die: dice)
		{
			die.roll();
			//since the array is 0-5 and the dice values are 1-6, I subtract one from the dices value
			// to find its position in the array
			// Ex
			/*
			 * values[0] = amount of 1's rolled
			 * values[1] = amount of 2's rolled
			 * values[2] = amount of 3's rolled
			 * etc
			 */
			values[die.getValue()-1]++;
		}
	}
	
	public void removeDie()
	{
		dice.remove(dice.size() - 1);
	}
	
	public int[] getValues()
	{
		return values;
	}
}
