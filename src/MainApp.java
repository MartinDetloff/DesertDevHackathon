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
    private TimePage m_TimePage = new TimePage();

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

        m_CusisinePage.american.setOnAction( e -> {
            m_StartPage.m_PriceOption = m_CusisinePage.american.getText();
            SetTimePage(primaryStage);
        });
        m_CusisinePage.mexican.setOnAction( e -> {
            m_StartPage.m_PriceOption = m_CusisinePage.mexican.getText();
            SetTimePage(primaryStage);
        });
        m_CusisinePage.any.setOnAction( e -> {
            m_StartPage.m_PriceOption = m_CusisinePage.any.getText();
            SetTimePage(primaryStage);
        });
        m_CusisinePage.italian.setOnAction( e -> {
            m_StartPage.m_PriceOption = m_CusisinePage.italian.getText();
            SetTimePage(primaryStage);
        });
        m_CusisinePage.chinese.setOnAction( e -> {
            m_StartPage.m_PriceOption = m_CusisinePage.chinese.getText();
            SetTimePage(primaryStage);
        });

        m_TimePage.any.setOnAction( e -> {
            m_StartPage.m_PriceOption = m_TimePage.any.getText();
            SetUserPage(primaryStage);
        });
        m_TimePage.afternoon.setOnAction( e -> {
            m_StartPage.m_PriceOption = m_TimePage.afternoon.getText();
            SetUserPage(primaryStage);
        });
        m_TimePage.morning.setOnAction( e -> {
            m_StartPage.m_PriceOption = m_TimePage.morning.getText();
            SetUserPage(primaryStage);
        });
        m_TimePage.evening.setOnAction( e -> {
            m_StartPage.m_PriceOption = m_TimePage.evening.getText();
            SetUserPage(primaryStage);
        });
        m_TimePage.soonest.setOnAction( e -> {
            m_StartPage.m_PriceOption = m_TimePage.soonest.getText();
            SetUserPage(primaryStage);
        });

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

    private void SetTimePage(Stage primaryStage)
    {
        primaryStage.setScene(new Scene(m_TimePage.m_TimePageRoot, 610, 450));
        primaryStage.show();
    }

    private void SetUserPage(Stage primaryStage)
    {
        primaryStage.setScene(new Scene(m_UserPage.m_UserRoot, 650, 450));
        primaryStage.show();
    }
}