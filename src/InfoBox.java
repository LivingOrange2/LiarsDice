import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InfoBox extends Stage
{
	public InfoBox()
	{
		Label lblDirections = new Label("Opponents will take turns betting how many of a certain "
				+ "dice have been rolled\nIf you think your opponent has bet more dice than there"
				+ " actually are than you can call them\nIf you get called and lose, you lose a dice"
				+ "\nIf you call someone and they were right, you lose a dice\nLast player"
				+ " still holding a die wins");	
		VBox box = new VBox(new Label("How to play:"), lblDirections);
		

		Scene scene = new Scene(box, 500, 200);
		
		scene.getStylesheets().add("main.css");
		
		setScene(scene);
		setTitle("Information");
		show();
	}
}
