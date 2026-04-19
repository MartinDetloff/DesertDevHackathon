package src;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private ComboBox<String> m_timeFilers = new ComboBox<>();

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
        m_priceFilters.getItems().addAll("None", "Under $3", "$3-$5", "$5+");
        m_priceFilters.setValue("None");

        m_timeFilers.getItems().addAll("None", "Soonest", "Morning", "Afternoon", "Evening");
        m_timeFilers.setValue("None");
    
        m_priceFilters.setOnAction( e -> { ApplyFilters(); } );
        m_timeFilers.setOnAction( e -> { ApplyFilters(); } );

        boxFilters.setSpacing(10);
        boxFilters.getChildren().addAll(new Label("Price Filter: "), m_priceFilters, new Label("Time Filter: "), m_timeFilers);
    }

    private void ApplyFilters()
    {
        m_vbRestaurantsBox.getChildren().clear();
    
        String szPriceFilter = m_priceFilters.getValue();
        String szTimeFilter = m_timeFilers.getValue();

        if ( szPriceFilter.equals("None") && szTimeFilter.equals("None") )
        {
            PopulateRestaurantsBox(); 
            return;
        }
    
        for ( RestaurantData restaurant : m_aRestaurantData )
        {
            if ( IsWithinPriceFilter(restaurant, szPriceFilter) && IsWithinTimeFilter(restaurant, szTimeFilter) )
            {
                RestaurauntPane restaurantPane = new RestaurauntPane(restaurant);
                m_vbRestaurantsBox.getChildren().add(restaurantPane);
            }
        }
    }

    private boolean IsWithinPriceFilter(RestaurantData restaurant, String szPriceFilter)
    {
        if ( szPriceFilter.equals("None") )
            return true;

        return szPriceFilter.equals("Under $3") && restaurant.GetMinPrice() < 3 ||
               szPriceFilter.equals("$3-$5") && restaurant.GetMinPrice() > 3 && restaurant.GetMinPrice() <= 5 || 
               szPriceFilter.equals("$5+") && restaurant.GetMaxPrice() > 5;
    }


    private boolean IsWithinTimeFilter(RestaurantData restaurant, String szTimeFilter)
    {
        if ( szTimeFilter.equals("None") )
            return true;

        String[] timeRange = restaurant.GetPickupTime().split("-");

        boolean bIsStartTimeAM = timeRange[0].toLowerCase().contains("am");
        boolean bIsEndTimeAM = timeRange[1].toLowerCase().contains("am");

        String szStartTimeNoLetters = GetTimeString(timeRange[0]);
        String szEndTimeNoLetters = GetTimeString(timeRange[1]);
               
        int iStartTime = Integer.parseInt(szStartTimeNoLetters);
        int iEndTime = Integer.parseInt(szEndTimeNoLetters);

        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm");
        String szStandardCurrentTime = now.format(formatter).replace(":", "");

        int iCurrentTime = Integer.parseInt(szStandardCurrentTime);

        switch (szTimeFilter)
        {
            case "Soonest":
                return iStartTime >= iCurrentTime || iEndTime >= iCurrentTime;
            case "Morning":
                return (bIsStartTimeAM && iStartTime >= 800 && iStartTime <= 1200) || (bIsEndTimeAM && iEndTime >= 800 && iEndTime <= 1200);
            case "Afternoon":
                return (!bIsStartTimeAM && (iStartTime >= 1200 && iStartTime <= 1259 ) || iStartTime <= 600) || 
                       (!bIsEndTimeAM && (iEndTime >= 1200 && iEndTime <= 1259 ) || iEndTime <= 600);
            case "Evening":
                return (!bIsStartTimeAM && iStartTime >= 600) || (!bIsEndTimeAM && iEndTime >= 600);
            default:
                return false;
        }
    }

    private String GetTimeString(String szTime)
    {
        szTime = szTime.replace(":", "").replace(" ", "");

        for ( int i = 0; i < szTime.length(); i++ )
        {
            if ( Character.isLetter(szTime.charAt(i)) )
                return szTime.substring(0, i);
        }

        return "";
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
