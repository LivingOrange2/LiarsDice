import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NameBox extends Stage
{
	public NameBox(User user)
	{
		TextField text = new TextField();
		text.setMaxWidth(150);
		
		VBox vbox = new VBox(5, new Label("Enter your name"), text);
		
		Button btnSet = new Button("Set Name");
		btnSet.setAlignment(Pos.CENTER);
		
		VBox main = new VBox(20, vbox, btnSet);
		main.setAlignment(Pos.CENTER);
		
		btnSet.setOnAction(event -> {
			user.setName(text.getText());
			close();
		});
		
		Scene scene = new Scene(main, 200, 100);
		scene.getStylesheets().add("main.css");
		setScene(scene);
		setTitle("Change Name!");
		show();
	}
}
