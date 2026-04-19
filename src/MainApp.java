package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application
{   
    private OwnerPage m_OwnerPage = new OwnerPage();
    private UserPage m_UserPage = new UserPage();

    public static void main(String[] args) 
    {
        launch(args);    
    }

    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setScene(new Scene(m_OwnerPage.m_OwnerRoot, 600, 400));
        primaryStage.show();
    }
}