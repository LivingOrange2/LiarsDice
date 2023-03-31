import java.util.ArrayList;

public class OneOnOneGame 
{
	private User user;
	private Player opponent;
	
	public OneOnOneGame(User user)
	{
		newGame(user);
	}
	
	public void newGame(User user)	
	{
		this.user = user;
		
		opponent = new AggresivePlayer();
	}
	
	public int getAmountOfDice()
	{
		return user.getDice().size() + opponent.getDice().size();
	}
	
	public void callOpponent()
	{
		int diceBeingCalled = opponent.getCurrentCall().getCalledDie();
		
		int amountOfDice = user.getValues()[diceBeingCalled-1] + opponent.getValues()[diceBeingCalled-1];
		
		if(amountOfDice >= opponent.getCurrentCall().getAmountCalled())	
		{
			user.removeDie();
		}
		else
		{
			opponent.removeDie();
		}
	}
	
	public void callUser()
	{
		int diceBeingCalled = user.getCurrentCall().getCalledDie();
		
		int amountOfDice = user.getValues()[diceBeingCalled-1] + opponent.getValues()[diceBeingCalled-1];
		
		if(amountOfDice >= user.getCurrentCall().getAmountCalled())	
		{
			opponent.removeDie();
		}
		else
		{
			user.removeDie();
		}
	}
	
	public void opponentTurn()
	{
		if(((BotPlayer)opponent).willCallUser(getAmountOfDice(), user.getCurrentCall()))
		{
			callUser();
		}
		else
		{
			opponent.makeCall(((BotPlayer)opponent).getCall(getAmountOfDice(), user.getCurrentCall()));
		}
	}
	
	public void playerTurn(boolean callOpponent)
	{
		if(callOpponent)
		{
			callOpponent();
		}
		else
		{
			opponentTurn();
		}
	}
	
}
