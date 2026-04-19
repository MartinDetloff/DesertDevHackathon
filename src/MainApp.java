package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application
{   
    private OwnerPage m_OwnerPage = new OwnerPage();
    private UserPage m_UserPage = new UserPage();
    private StartPage m_StartPage = new StartPage();
    private PricePage m_PricePage = new PricePage();
    private CusisinePage m_CusisinePage = new CusisinePage();

    public static void main(String[] args) 
    {
        launch(args);    
    }

    @Override
    public void start(Stage primaryStage) 
    {
        m_StartPage.userButton.setOnAction( e -> {
            primaryStage.setScene(new Scene(m_PricePage.m_PricePageRoot, 700, 450));
            primaryStage.show();
        });

        m_PricePage.med.setOnAction( e -> {
            m_StartPage.m_PriceOption = m_PricePage.med.getText();
            SetCuisinePage(primaryStage);
        } );
        m_PricePage.low.setOnAction( e -> {
            m_StartPage.m_PriceOption = m_PricePage.low.getText();
            SetCuisinePage(primaryStage);
        } );
        m_PricePage.high.setOnAction( e -> {
            m_StartPage.m_PriceOption = m_PricePage.high.getText();
            SetCuisinePage(primaryStage);
        } );
        m_PricePage.any.setOnAction( e -> {
            m_StartPage.m_PriceOption = m_PricePage.any.getText();
            SetCuisinePage(primaryStage);
        } );


         m_StartPage.ownerButton.setOnAction( e -> {
            primaryStage.setScene(new Scene(m_OwnerPage.m_OwnerRoot, 600, 400));
            primaryStage.show();
        });

        primaryStage.setScene(new Scene(m_StartPage.m_StartPageRoot, 610, 450));
        primaryStage.show();
    }

    private void SetCuisinePage(Stage primaryStage)
    {
        primaryStage.setScene(new Scene(m_CusisinePage.m_CuisinePageRoot, 610, 450));
        primaryStage.show();
    }

}