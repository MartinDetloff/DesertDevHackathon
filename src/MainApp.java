package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application
{   
    private OwnerPage m_OwnerPage = new OwnerPage();
    private UserPage m_UserPage = new UserPage();
    private StartPage m_StartPage = new StartPage();

    public static void main(String[] args) 
    {
        launch(args);    
    }

    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setScene(new Scene(m_StartPage.m_StartPageRoot, 600, 450));
        primaryStage.show();
    }
}