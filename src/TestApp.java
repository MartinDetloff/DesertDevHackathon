package src;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.RestaurauntPane;
import javafx.scene.control.ScrollPane;

import java.util.Random;

public class TestApp extends Application {
    RestaurantData r1 = new RestaurantData("Restauraunt 1", "We are restauraunt 1", "123 Main Street", 8, 5);
    RestaurantData r2 = new RestaurantData("Restauraunt 2", "We are restauraunt 2", "125 Main Street", 10,7);
    @Override
    public void start(Stage primaryStage) {
        RestaurauntPane pane1 = new RestaurauntPane(r1);
        RestaurauntPane pane2 = new RestaurauntPane(r2);



        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        content.getChildren().addAll(pane1, pane2);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Scene scene = new Scene(scrollPane, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Custom Pane Demo");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /* --Some unfinished functions I'm working on
    private RestaurantData[] makeRestauraunts(int num) {
        Random randGen = new Random();
        RestaurantData[] restaurants = new RestaurantData[num];
        for(int i = 0; i <= num; i++){
            String name = "Restauraunt " + i+1;
            String description = "We are restauraunt " + i+1;
            int price1 = randGen.nextInt(35);
            int price2 = randGen.nextInt(35);
            int minPrice = Math.min(price1, price2);
            int maxPrice = Math.max(price1, price2);
            restaurants[i] = new RestaurantData(name,description, minPrice, maxPrice);
        }
        return restaurants;
    }
    /*
    private RestaurauntPane[] makePanes(int num){
        RestaurauntPane[] panes = new RestaurauntPane[num];
        RestaurantData[] restaurants = makeRestauraunts(num);
        for(int i = 0; i < num; i++) {

        }

    }
     */
}
