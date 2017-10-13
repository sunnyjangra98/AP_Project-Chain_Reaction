package javaFx_projects;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StartPage{
	
	Settings setting=new Settings();
	Scene scene;
	static int numberOfPlayers=0, mSize=0, nSize=0;
	static String size=""; 

	//Not start but 'Start'
	public void Start(Stage stage) {
		GridPane grid=new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//Chain reaction image
		ImageView image=new ImageView(new Image(getClass().getResourceAsStream("logo.png"),125, 250, true, true));
		GridPane.setConstraints(image, 1, 0);
		
		//Number of Players
		Label players=new Label("Enter the number of Players:");
		GridPane.setConstraints(players, 0, 11);
		ComboBox<Integer> playerInput=new ComboBox<>(); 
		playerInput.getItems().addAll(2,3,4,5,6,7,8);
		GridPane.setConstraints(playerInput, 1, 11);
		playerInput.setValue(2);
		
		//Matrix Size
		Label matrixSize=new Label("Enter the Matrix Size:");
		GridPane.setConstraints(matrixSize, 0, 14);
		ComboBox<String> m=new ComboBox<>();
		m.getItems().addAll("9x6", "15x10");
		GridPane.setConstraints(m, 1, 14);
		m.setValue("9x6");
		
		//Start Button
		Button start=new Button("Start");
		GridPane.setConstraints(start, 1, 19);
		
		//Resume Button
		Button resumeGame=new Button("Resume");
		GridPane.setConstraints(resumeGame, 0, 21);
		
		//Settings Button
		Button settings=new Button("Player Settings");
		GridPane.setConstraints(settings, 5, 21);
		
		grid.getChildren().addAll(image, players, playerInput, matrixSize, m, start, resumeGame, settings);
		
		//For Start Button
		start.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				numberOfPlayers=playerInput.getValue();
				size=m.getValue();
				if (size.equals("9x6")) {
					mSize=9;
					nSize=6;
				}
				else {
					mSize=15;
					nSize=10;
				}
				grid.getChildren().removeAll(image, players, playerInput, matrixSize, m, start, resumeGame, settings);
				stage.setTitle("Main Game");
			}
		});
		
		//For Settings Button
		settings.setOnAction(a->setting.setDatas(stage, scene));
		
		//For Resume Button
		resumeGame.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Still working on this Button...");
				
			}
		});
		
		scene=new Scene(grid, 450, 370);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Start Page");
		stage.show();

	}

}
