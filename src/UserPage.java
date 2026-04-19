package src;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserPage extends Application 
{
    private HBox boxFilters = new HBox();
    private VBox m_vbRestaurantsBox = new VBox(20);

    private static Utils m_utils = new Utils();

    private ComboBox<String> m_priceFilters = new ComboBox<>();

    private ArrayList<RestaurantData> m_aRestaurantData = m_utils.GetRestaurants();

    public static void main(String[] args) 
    {
        launch(args);
        m_utils.ClearRestaurants();
    }

    @Override
    public void start(Stage primaryStage) 
    {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));

        SetupFilters(); 
        
        m_vbRestaurantsBox.setPadding(new Insets(20));
        PopulateRestaurantsBox();

        ScrollPane scrollPane = new ScrollPane(m_vbRestaurantsBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        root.getChildren().addAll(boxFilters, scrollPane);
    
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo");
        primaryStage.show();
    }

    private void SetupFilters()
    {
        m_priceFilters.getItems().addAll("None", "Under $3", "$3-$5", "$5+");
        m_priceFilters.setValue("None");
        m_priceFilters.setOnAction( e -> { ApplyPriceFilters(); } );
    
        boxFilters.getChildren().addAll(new Label("Price Filter: "), m_priceFilters);
    }

    private void ApplyPriceFilters()
    {
        m_vbRestaurantsBox.getChildren().clear();
    
        String szPriceFilter = m_priceFilters.getValue();

        if ( szPriceFilter.equals("None") )
        {
            PopulateRestaurantsBox();
            return;
        }
    
        for ( RestaurantData restaurant : m_aRestaurantData )
        {
            if ( szPriceFilter.equals("Under $3") && restaurant.GetMinPrice() < 3 ||
                 szPriceFilter.equals("$3-$5") && restaurant.GetMinPrice() > 3 && restaurant.GetMinPrice() <= 5 || 
                 szPriceFilter.equals("$5+") && restaurant.GetMinPrice() > 5 )
            {
                RestaurauntPane restaurantPane = new RestaurauntPane(restaurant);
                m_vbRestaurantsBox.getChildren().add(restaurantPane);
            }
        }
    }

    private void PopulateRestaurantsBox()
    {
        for ( RestaurantData restaurant : m_aRestaurantData )
        {
            RestaurauntPane restaurantPane = new RestaurauntPane(restaurant);
            m_vbRestaurantsBox.getChildren().add(restaurantPane);
        }
    }
}
