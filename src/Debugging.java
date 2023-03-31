import java.util.Random;

public class Debugging {

	public static void main(String[] args) 
	{
		Random rand = new Random();
		
		for(int x = 0; x < 20; x++)
		{
			System.out.println(rand.nextBoolean());
		}
	}

}
