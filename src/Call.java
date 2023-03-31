
public class Call 
{
	private int calledSide;
	
	private int amtOfDice;
	
	public Call(int calledDie, int amt)
	{
		calledSide = calledDie;
		amtOfDice = amt;
	}
	
	public int getCalledDie()
	{
		return calledSide;
	}
	
	public int getAmountCalled()
	{
		return amtOfDice;
	}
	

}
