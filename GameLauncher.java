package javaFx_projects;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameLauncher extends Application{

	StartPage sp=new StartPage();
	
	public static void main(String[] args) {
		launch(args);

	}
	
	@Override
	public void start(Stage stage) {
		Image image=new Image(getClass().getResourceAsStream("chainreaction.jpg"));
		stage.getIcons().add(image);
		sp.Start(stage);
		
	}

}
