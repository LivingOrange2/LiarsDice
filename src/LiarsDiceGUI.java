import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LiarsDiceGUI extends Application
{
	private HBox buttons, rollBox;
	
	private Button btnCall, btnBet, btnPlay, btnExit, btnOk;
	private Label diceLeft;
	
	private Label msg;
	
	private MenuBar menuBar;
	private Menu fileMenu;
	private MenuItem exitItem;
	
	
	private OneOnOneGame game;
	
	private User player;
	
	private LiarsDiceSave data;
	
	private TextField txtDice, txtAmt;
	
	private Response response;
	
	
	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage primary) throws Exception 
	{
		primary.setScene(buildGUI(primary));
		
		primary.setTitle("Liar's Dice");
		
		primary.show();
	}
	
	public void startGame() throws Exception
	{
		player = new User();
		game = new OneOnOneGame(player);
		
		data = SaveLoader.getSave();
		
		msg.setText("Current Bet: none?");
		
		roll();
		
	}

	public void roll()
	{
		game.roll();
		
		rollBox.getChildren().clear();
		
		for(Die die: player.getDice())
		{
			rollBox.getChildren().add(new Label("" + die.getValue()));
		}
		
		diceLeft.setText("" + game.getAmountOfDice());
	}
	
	public boolean isValidCall(int side, int amt)
	{
		if(side < 1 || side > 6 || amt > game.getAmountOfDice())
		{
			return false;
		}
		else if(amt > game.getLatestCall().getAmountCalled() && side >= game.getLatestCall().getCalledDie())
		{
			return true;
		}
		else if(side > game.getLatestCall().getCalledDie() && amt >= game.getLatestCall().getAmountCalled())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void playerTurn(Call call)
	{
		player.makeCall(call);
		
		response = game.getOpponentResponse();
		
		msg.setText(response.getResponse());
		
		if(response.willCall())
		{
			buttons.getChildren().clear();
			buttons.getChildren().add(btnOk);
		}
		
		diceLeft.setText("" + game.getAmountOfDice());
	}
	
	public void continueGame()
	{
		game.setLatestCall(new Call(0, 0));
		player.makeCall(new Call(0, 0));
		
		roll();
		
		if(game.getState() == Game.gameState.PLAYER_TURN)
		{
			msg.setText("It's " + player.getName() + "'s turn\nMake your bid!");
		}
		else
		{
			response = game.getOpponentResponse();
			msg.setText(response.getResponse());
		}
	}
	
	public Scene buildGUI(Stage primary)
	{
		msg = new Label("Ready to play?");
		msg.setId("message-label");
		msg.setTextAlignment(TextAlignment.CENTER);
		msg.setAlignment(Pos.CENTER);
		
		
		HBox msgBox = new HBox(0, msg);
		msgBox.setId("msg-container");
		msgBox.setPrefSize(200, 150);
		msgBox.setAlignment(Pos.CENTER);
		
		txtDice = new TextField();
		txtAmt = new TextField();
		
		btnCall = new Button("Call It");
		btnBet = new Button("Bet");
		
		btnPlay = new Button("Play");
		btnExit = new Button("Exit");
		
		btnOk = new Button("Ok");
		
		buttons = new HBox(20, btnPlay, btnExit);
		
		buttons.setPrefHeight(45);
		
		buttons.setAlignment(Pos.CENTER);
		
		rollBox = new HBox(60);
		
		rollBox.setPrefSize(200, 30);
		
		diceLeft = new Label("0");
		
		HBox txtFields = new HBox(20, new VBox(0,
				new Label("Amt of dice"), txtAmt), new VBox(0, new Label("Dice Number"), txtDice));
		
		txtFields.setPadding(new Insets(15));
		
		HBox diceL = new HBox(15, new Label("Dice Left: "), diceLeft);
		
		diceL.setPrefHeight(30);
		
		rollBox.setAlignment(Pos.CENTER);
		
		for(int x = 0; x < 5; x++)
		{
			rollBox.getChildren().add(new Label("0"));
		}
		
		GridPane mainContainer = new GridPane();
		GridPane ps = new GridPane();
		BorderPane visual = new BorderPane();
		
		mainContainer.setAlignment(Pos.CENTER);
		
		ps.setAlignment(Pos.CENTER);
		
		ps.add(txtFields, 1, 0);
		ps.add(rollBox, 1, 1);
		ps.add(buttons, 1, 2);
		
		visual.setTop(diceL);
		visual.setCenter(msgBox);
		
		mainContainer.add(visual, 1, 0);
		mainContainer.add(ps, 1, 1);
		
		setActions(primary);
		
		Scene scene = new Scene(mainContainer, 500, 350);
		
		scene.getStylesheets().add("main.css");
		
		return scene;
	}
	
	public void setActions(Stage primary)
	{
		btnExit.setOnAction(event -> {
			try 
			{
				SaveLoader.save(data);
				
			} 
			catch (IOException e) 
			{
				System.out.println(e.getMessage());
			}
			finally
			{
				primary.close();
			}
			
			}
		);
		
		btnPlay.setOnAction(event -> {
		try 
		{
			buttons.getChildren().clear();
			buttons.getChildren().addAll(btnBet, btnCall);
			
			startGame();
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}});
		
		btnBet.setOnAction(event ->{
			try
			{
				int calledSide = Integer.parseInt(txtDice.getText());
				int amtCalled = Integer.parseInt(txtAmt.getText());
				
				if(isValidCall(calledSide, amtCalled))
				{
					playerTurn(new Call(calledSide, amtCalled));
				}
				else
				{
					msg.setText("That is not a valid bet!\nThe amount or side being" + 
				" bet\n on has to increase by at least one!\n"+ "Last Call: " + 
				 getCallText(game.getLatestCall()));
				}
			}
			catch(NumberFormatException e)
			{
				msg.setText("Please only enter numeric values\n" + "Last Call: " + 
				getCallText(game.getLatestCall()));
			}
			
		});
		
		btnOk.setOnAction(event -> {
			buttons.getChildren().clear();
			buttons.getChildren().addAll(btnBet, btnCall);
			
			continueGame();
			
		});
	}
	
	
	public String getCallText(Call call)
	{
		return call.getAmountCalled() + " " +
				call.getCalledDie() + "'s";
	}
}
