import java.text.DecimalFormat;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StatsBox extends Stage
{
	public StatsBox(LiarsDiceSave save)
	{
		DecimalFormat df = new DecimalFormat("##.00");
		
		int games = save.getWins() + save.getLosses();
		double winRatio = ((double)save.getWins() / (double)games) * 100;
		
		Button btnOk = new Button("Ok");
		
		btnOk.setOnAction(event -> {close();});
		
		VBox vbox = new VBox(10, new Label("Games Played: " + games),
				new Label("Wins: " + save.getWins()),
				new Label("Losses: " + save.getLosses()),
				new Label("Win Ratio: " + df.format(winRatio) + "%"));
		
		vbox.setAlignment(Pos.CENTER);
		
		VBox main = new VBox(20, vbox, btnOk);
		
		main.setId("stats-vbox");
		main.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(main, 350, 225);
		scene.getStylesheets().add("main.css");
		
		setScene(scene);
		setTitle(save.getName() + "'s statistics");
		
		show();
	}
}
