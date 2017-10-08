import javafx.application.Application;
import javafx.stage.Stage;

public class GameLauncher extends Application{

	StartPage sp=new StartPage();
	
	public static void main(String[] args) {
		launch(args);

	}
	
	@Override
	public void start(Stage stage) {
		sp.Start(stage);
		
	}

}
