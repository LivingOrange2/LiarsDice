import javafx.scene.Scene;
import javafx.stage.Stage;

public class StatsBox extends Stage
{
	public StatsBox(LiarsDiceSave save)
	{
		
		
		Scene scene = new Scene(null, 250, 250);
		
		setScene(scene);
		setTitle(save.getName() + "'s statistics");
		
		show();
	}
}
