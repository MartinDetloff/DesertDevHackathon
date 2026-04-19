package src;

import java.util.ArrayList;
import java.util.Comparator;
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

    private Utils m_utils = new Utils();

    private ComboBox<String> m_priceFilters = new ComboBox<>();

    private ArrayList<RestaurantData> m_aRestaurantData = m_utils.GetRestaurants();

    public static void main(String[] args) 
    {
        launch(args);
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
        m_priceFilters.getItems().addAll("None", "Ascending", "Decending");
        m_priceFilters.setValue("None");
        m_priceFilters.setOnAction( e -> { ApplyPriceFilters(); } );
    
        boxFilters.getChildren().addAll(new Label("Price Filter: "), m_priceFilters);
    }

    private void ApplyPriceFilters()
    {
        String szPriceFilter = m_priceFilters.getValue();

        if ( szPriceFilter.equals("None") )
            return;
    
        m_vbRestaurantsBox.getChildren().clear();
        
        boolean bShouldSortAscending = szPriceFilter.equals("Ascending"); 
        if ( bShouldSortAscending )
            m_aRestaurantData.sort(Comparator.comparing(RestaurantData::GetMinPrice));
        else
            m_aRestaurantData.sort(Comparator.comparing(RestaurantData::GetMinPrice).reversed());

        PopulateRestaurantsBox();
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
