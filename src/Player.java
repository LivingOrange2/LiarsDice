import java.util.ArrayList;

public abstract class Player
{
	protected ArrayList<Die> dice;
	
	protected int[] values;
	
	private Call currentCall;
	
	public Player()
	{
		values = new int[6];
		dice = new ArrayList<Die>(5);
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
		values = new int[6];
		
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
			if(die.getValue() == 1)
			{
				for(int x = 0; x < values.length; x++)
				{
					values[x] ++;
				}
			}
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
	
	public ArrayList<Die> getDice()
	{
		return dice;
	}
	
	public boolean hasDice()
	{
		if(dice.size() == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public void makeCall(Call c)
	{
		currentCall = new Call(c.getCalledDie(), c.getAmountCalled());
	}
	
	public Call getCurrentCall()
	{
		return currentCall;
	}
}
