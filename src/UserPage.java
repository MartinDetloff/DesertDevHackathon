package src;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserPage extends Application 
{
    Utils m_utils = new Utils();
    public static void main(String[] args) 
    {
        launch(args);
        System.out.println("Finished UserPage");       
    }

    @Override
    public void start(Stage primaryStage) 
    {
        VBox vbRestaurantsBox = new VBox(20);
        vbRestaurantsBox.setPadding(new Insets(20));

        ArrayList<RestaurantData> aRestaurantData = m_utils.GetRestaurants();
        for ( RestaurantData restaurant : aRestaurantData )
        {
            RestaurauntPane restaurantPane = new RestaurauntPane(restaurant);
            vbRestaurantsBox.getChildren().add(restaurantPane);
        }

        ScrollPane scrollPane = new ScrollPane(vbRestaurantsBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Scene scene = new Scene(scrollPane, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo");
        primaryStage.show();
    }
}
