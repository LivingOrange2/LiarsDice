import java.util.ArrayList;

public class Game
{
	
	enum gameState {PLAYER_TURN, OPPONENT_TURN};
	protected gameState state;
	
	protected User user;
	
	protected ArrayList<Player> players;
	
	public Game(User user)
	{
		players = new ArrayList<Player>();
		
		this.user = user;
	}
	
	public int getAmountOfDice()
	{
		int total = 0;
		
		for(Player player: players)
		{
			total += player.getDice().size();
		}
		
		return total;
	}
	
	public int getAmountOfSide(int side)
	{
		int amt = 0;
		
		for(Player player: players)
		{
			amt += player.getValues()[side-1];
		}
		
		return amt;
	}
	
	public boolean isGameOver()
	{
		for(Player player: players)
		{
			if(!player.hasDice())
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void roll()
	{
		for(Player player: players)
		{
			player.rollDice();
		}
	}
	
	public gameState getState() {
		return state;
	}

	public void setState(gameState state) {
		this.state = state;
	}
}
