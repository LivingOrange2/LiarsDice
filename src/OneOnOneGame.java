
public class OneOnOneGame extends Game
{
	private Player opponent;
	private Call latestCall;
	
	public OneOnOneGame(User user)
	{
		super(user);
		newGame(user);
	}
	
	public void newGame(User user)	
	{
		this.user = user;
		
		opponent = new AggresivePlayer();
		
		players.add(user);
		players.add(opponent);
		
		latestCall = new Call(0, 0);
	}
	
	public int getAmountOfDice()
	{
		return user.getDice().size() + opponent.getDice().size();
	}
	
	public String callOpponent()
	{
		int diceBeingCalled = opponent.getCurrentCall().getCalledDie();
		
		int amountOfDice = user.getValues()[diceBeingCalled-1] + opponent.getValues()[diceBeingCalled-1];
		
		if(amountOfDice >= opponent.getCurrentCall().getAmountCalled())	
		{
			user.removeDie();
			state = gameState.PLAYER_TURN;
			return "You called the opponent!\nOpponent called " + 
			getCallText(opponent.getCurrentCall()) + "\nand there was actually "
			+ getAmountOfSide(opponent.getCurrentCall().getCalledDie()) + " "
			+ opponent.getCurrentCall().getCalledDie() + "'s\n" + user.getName()
			+ " loses a die!";
		}
		else
		{
			opponent.removeDie();
			state = gameState.OPPONENT_TURN;
			return "You called the opponent!\nOpponent called " + 
					getCallText(opponent.getCurrentCall()) + "\nand there was actually "
					+ getAmountOfSide(opponent.getCurrentCall().getCalledDie()) + " "
					+ opponent.getCurrentCall().getCalledDie() + "'s\nOpponent loses a die!";
		}
	}
	
	public String callUser()
	{
		int amountOfDice = getAmountOfSide(user.getCurrentCall().getCalledDie());
		
		if(amountOfDice >= user.getCurrentCall().getAmountCalled())	
		{
			opponent.removeDie();
			state = gameState.OPPONENT_TURN;
			return "The opponent called you!\n You called " + getCallText(user.getCurrentCall())
			+ "\nand there was actually " + getAmountOfSide(user.getCurrentCall().getCalledDie())
			 + " " + user.getCurrentCall().getCalledDie() + "'s\nOpponent loses a die!";
		}
		else
		{
			user.removeDie();
			state = gameState.PLAYER_TURN;
			return "The opponent called you!\n You called " + getCallText(user.getCurrentCall())
			+ "\nand there was actually " + getAmountOfSide(user.getCurrentCall().getCalledDie())
			 + " " + user.getCurrentCall().getCalledDie() + "'s\n" + user.getName() 
			 + " lose's a die!";
		}
	}
	
	public Response getOpponentResponse()
	{
		if(((BotPlayer)opponent).willCallUser(getAmountOfDice(), user.getCurrentCall()))
		{
			//return new Response(true);
			return new Response(true, callUser());
		}
		else
		{
			opponent.makeCall(((BotPlayer)opponent).getCall(getAmountOfDice(), user.getCurrentCall()));
			
			latestCall = opponent.getCurrentCall();
			
			return new Response(false, "Current Call: " + getCallText(latestCall));
		}
	}
	
	public boolean didPlayerWin()
	{
		if(user.getDice().size() != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void setLatestCall(Call call)
	{
		latestCall = new Call(call.getCalledDie(), call.getAmountCalled());
	}
	
	public Call getLatestCall()
	{
		return latestCall;
	}
	
	public String getCallText(Call call)
	{
		return call.getAmountCalled() + " " +
				call.getCalledDie() + "'s";
	}
	
}
