import java.util.Random;

public interface BotPlayer
{
	public boolean willCallUser(int diceAmt, Call call);
	
	public Call getCall(int diceAmt, Call call);
	
	public int getHighestRolledSide();
	
	public default boolean willIncreaseAmtCalled()
	{
		Random rand = new Random();
		
		return rand.nextBoolean();
	}
	
	public default boolean willBluff()
	{
		Random rand = new Random();
		
		return rand.nextBoolean();	
	}
	
}
