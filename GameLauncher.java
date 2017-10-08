package javaFx_projects;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameLauncher extends Application{

	StartPage sp=new StartPage();
	
	public static void main(String[] args) {
		launch(args);
		System.out.println(StartPage.numberOfPlayers);//Printing 5 (if 5 is input as the number of players)

	}
	
	@Override
	public void start(Stage stage) {
		sp.Start(stage);
		
	}

}
