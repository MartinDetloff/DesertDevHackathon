package src;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application
{   
    public static void main(String[] args) 
    {
        launch(args);
        System.out.println("Finished");       
    }

    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setScene(new Scene(new GridPane(), 400, 400));
        primaryStage.show();
    }
}