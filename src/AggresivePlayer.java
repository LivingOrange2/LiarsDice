import java.util.Random;

public class AggresivePlayer extends Player implements BotPlayer
{
	
	public boolean willCallUser(int diceAmt, Call call) 
	{
		Random rand = new Random();		
		
		double percentageOfDiceBeingCalled = ((double)call.getAmountCalled() / (double)diceAmt) * 100;
		
		
		if(percentageOfDiceBeingCalled > 55 )
		{
			return true;
		}
		else if(percentageOfDiceBeingCalled > 40)
		{
			return rand.nextInt(2) == 1;
		}
		else if(percentageOfDiceBeingCalled > 30)
		{
			return rand.nextInt(10) <= 7;
		}
		else
		{
			return false;
		}
		
	}

	public Call getCall(int diceAmt, Call call) 
	{
		Random rand = new Random();
		
		if(!willBluff())
		{
			if(call.getCalledDie() == getHighestRolledSide())
			{
				return new Call(call.getCalledDie(), call.getAmountCalled() + (rand.nextInt(2)+1));
			}
			else
			{
				return new Call(getHighestRolledSide(), call.getAmountCalled() + (rand.nextInt(2) + 1));
			}
		}
		else
		{
			if(call.getCalledDie() == 0)
			{
				return new Call(rand.nextInt(6)+1, rand.nextInt(2)+1);
			}
			
			boolean increaseSide = rand.nextBoolean();
			
			if(!increaseSide || call.getCalledDie() == 6)
			{
				return new Call(call.getCalledDie(), call.getAmountCalled()+1);
			}
			else
			{
				int side = 0;
				
				while(side <= call.getCalledDie())
				{
					side = rand.nextInt(6) + 1;				
				}
				
				return new Call(side, call.getAmountCalled());
			}
		}
	}

	public int getHighestRolledSide() 
	{
		int highestAmtOfDie = 0;
		
		for(int x = 0; x < values.length; x++)
		{
			if(values[x] >= highestAmtOfDie)
			{
				highestAmtOfDie = values[x];
			}
		}
		
		return highestAmtOfDie;
	}

}
