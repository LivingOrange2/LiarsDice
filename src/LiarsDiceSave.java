import java.io.Serializable;

@SuppressWarnings("serial")
public class LiarsDiceSave implements Serializable
{
	private String name;
	
	private int wins;
	
	private int losses;
	
	public LiarsDiceSave(String n, int w, int l)
	{
		name = n;
		wins = w;
		losses = l;
	}
	
	public LiarsDiceSave()
	{
		name = "Player";
		wins = 0;
		losses = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}
	
	public void addWin()
	{
		wins++;
	}
	
	public void addLoss()
	{
		losses++;
	}
}
