import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoader 
{
	private static ObjectInputStream file;
	
	public static LiarsDiceSave getSave() throws IOException, ClassNotFoundException
	{
		try
		{
			file = new ObjectInputStream(new FileInputStream("data.ld"));
			
			return (LiarsDiceSave)file.readObject();
		}
		catch(FileNotFoundException e)
		{
			return new LiarsDiceSave();
		}
		finally
		{
			file.close();
		}
		
	}
	
	public static void save(LiarsDiceSave data) throws FileNotFoundException, IOException
	{
		ObjectOutputStream saveFile = new ObjectOutputStream(new FileOutputStream("data.ld"));
		saveFile.writeObject(data);
		
		saveFile.close();
	}
	
}
