import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LiarsDiceGUI extends Application
{
	private HBox buttons;
	private HBox rollBox;
	
	private Button btnCall, btnBet;
	private Label totalDice;
	private Label diceLeft;
	
	private MenuBar menuBar;
	
	private User player;
	
	public static void main(String[] args) 
	{
		launch(args);

	}

	@Override
	public void start(Stage primary) throws Exception 
	{
		player = new User();
		
		primary.setScene(buildGUI());
		
		primary.show();
		
	}
	
	public Scene buildGUI()
	{
		btnCall = new Button("Call");
		btnBet = new Button("Bet");
		
		buttons = new HBox(20, btnCall, btnBet);
		buttons.setAlignment(Pos.CENTER);
		
		rollBox = new HBox(60);
		
		rollBox.setPrefSize(200, 30);
		
		diceLeft = new Label("0");
		
		HBox diceL = new HBox(30, new Label("Dice Remaining: "), diceLeft);
		
		
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		
		gp.add(buttons, 1, 1);
		gp.add(diceL, 1, 2);
		gp.add(rollBox, 1, 3);
		
		btnCall.setOnAction(event -> {roll();});
		
		return new Scene(gp, 400, 150);
	}
	
	public void roll()
	{
		player.rollDice();
		
		rollBox.getChildren().clear();
		
		for(Die die: player.getDice())
		{
			rollBox.getChildren().add(new Label("" + die.getValue()));
		}
	}
}
