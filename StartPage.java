import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StartPage extends Application{

	public static void main(String[] args) {
		launch(args);

	}
	
	@Override
	public void start(Stage stage) {
		GridPane grid=new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//Number of Players
		Label players=new Label("Enter the number of Players:");
		GridPane.setConstraints(players, 0, 7);
		ComboBox playerInput=new ComboBox(); 
		playerInput.getItems().addAll(2,3,4,5,6,7,8);
		GridPane.setConstraints(playerInput, 1, 7);
		
		//Matrix Size
		Label matrixSize=new Label("Enter the Matrix Size:");
		GridPane.setConstraints(matrixSize, 0, 10);
		ComboBox m=new ComboBox();
		m.getItems().addAll(9,10,11,12,13,14,15);
		GridPane.setConstraints(m, 1, 10);
		Label x=new Label("x");
		GridPane.setConstraints(x, 2, 10);
		ComboBox n=new ComboBox();
		n.getItems().addAll(6,7,8,9,10);
		GridPane.setConstraints(n, 3, 10);
		
		//Start Button
		Button start=new Button("Start");
		GridPane.setConstraints(start, 1, 15);
		
		grid.getChildren().addAll(players, playerInput, matrixSize, m, x, n, start);
		
		start.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				grid.getChildren().removeAll(players, playerInput, matrixSize, m, x, n, start);
				
			}
		});
		
		Scene scene=new Scene(grid, 350, 300);
		stage.setScene(scene);
		stage.setTitle("First Page");
		stage.show();
	}

}
