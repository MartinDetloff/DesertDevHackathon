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
        m_StartPage.userButton.setOnAction( e -> {
            primaryStage.setScene(new Scene(m_UserPage.m_UserRoot, 700, 450));
            primaryStage.show();
        });

         m_StartPage.ownerButton.setOnAction( e -> {
            primaryStage.setScene(new Scene(m_OwnerPage.m_OwnerRoot, 600, 400));
            primaryStage.show();
        });

        primaryStage.setScene(new Scene(m_StartPage.m_StartPageRoot, 610, 450));
        primaryStage.show();
    }
}