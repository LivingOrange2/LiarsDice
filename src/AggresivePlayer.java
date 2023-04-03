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
				return new Call(getHighestRolledSide(), call.getAmountCalled() + (rand.nextInt(3) + 1));
			}
		}
		else
		{
			int sideToCall = 0;
			
			while(sideToCall != call.getCalledDie() || sideToCall < call.getCalledDie())
			{
				sideToCall = rand.nextInt(6)+1;
			}
			
			if(sideToCall == call.getCalledDie())
			{
				return new Call(sideToCall, call.getAmountCalled() + (rand.nextInt(3)+1));
			}
			else if(sideToCall > call.getCalledDie())
			{
				return new Call(sideToCall, call.getAmountCalled() + (rand.nextInt(3)));
			}
			else
			{
				return new Call(sideToCall, call.getAmountCalled() + (rand.nextInt(3)+1));
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
