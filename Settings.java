package javaFx_projects;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Settings {

	public void setDatas(Stage stage, Scene scene) {
		stage.setTitle("Settings");
		
		GridPane gridpane=new GridPane();
		gridpane.setPadding(new Insets(10, 10, 10, 10));
		gridpane.setVgap(8);
		gridpane.setHgap(10);
		
		Button player1=new Button("Player 1");
		GridPane.setConstraints(player1, 2, 2);
		
		Button player2=new Button("Player 2");
		GridPane.setConstraints(player2, 2, 4);
		
		Button player3=new Button("Player 3");
		GridPane.setConstraints(player3, 2, 6);
		
		Button player4=new Button("Player 4");
		GridPane.setConstraints(player4, 2, 8);
		
		Button player5=new Button("Player 5");
		GridPane.setConstraints(player5, 4, 2);
		
		Button player6=new Button("Player 6");
		GridPane.setConstraints(player6, 4, 4);
		
		Button player7=new Button("Player 7");
		GridPane.setConstraints(player7, 4, 6);
		
		Button player8=new Button("Player 8");
		GridPane.setConstraints(player8, 4, 8);
		
		//Player1 settings
		player1.setOnAction(a->action());
		
		//Player2 settings
		player2.setOnAction(a->action());
		
		//Player3 settings
		player3.setOnAction(a->action());
		
		//Player4 settings
		player4.setOnAction(a->action());
				
		//Player5 settings
		player5.setOnAction(a->action());
		
		//Player6 settings
		player6.setOnAction(a->action());
			
		//Player7 settings
		player7.setOnAction(a->action());
				
		//Player8 settings
		player8.setOnAction(a->action());
		
		Button back=new Button("Back");
		GridPane.setConstraints(back, 3, 10);
		
		//For Back
		back.setOnAction(a->stage.setScene(scene));
		
		gridpane.getChildren().addAll(player1, player2, player3, player4, player5, player6, player7, player8, back);
		
		Scene settingScene=new Scene(gridpane, 225, 230);
		stage.setScene(settingScene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void action() {
		
	}
	
}
