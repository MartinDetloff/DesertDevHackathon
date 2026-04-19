package src;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserPage 
{
    private HBox boxFilters = new HBox();
    private VBox m_vbRestaurantsBox = new VBox(20);
    private VBox m_UserRoot = new VBox(20);

    private static Utils m_utils = new Utils();

    private ComboBox<String> m_priceFilters = new ComboBox<>();
    private ComboBox<String> m_timeFilers = new ComboBox<>();
    private ComboBox<String> m_cuisineFilers = new ComboBox<>();

    private ArrayList<RestaurantData> m_aRestaurantData = m_utils.GetRestaurants();

    public UserPage() 
    {
        m_UserRoot.setPadding(new Insets(20));
        SetupFilters(); 
        
        m_vbRestaurantsBox.setPadding(new Insets(20));
        PopulateRestaurantsBox();

        ScrollPane scrollPane = new ScrollPane(m_vbRestaurantsBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        m_UserRoot.getChildren().addAll(boxFilters, scrollPane);
    }

    private void SetupFilters()
    {
        m_priceFilters.getItems().addAll("Any price", "Under $3", "$3 – $5", "$5+");
        m_priceFilters.setValue("Any price");

        m_timeFilers.getItems().addAll("Any time", "Soonest", "Morning", "Afternoon", "Evening");
        m_timeFilers.setValue("Any time");

        m_cuisineFilers.getItems().addAll("Any cuisine", "Italian", "Mexican", "American", "Chinese");
        m_cuisineFilers.setValue("Any cuisine");
    
        m_priceFilters.setOnAction(e -> ApplyFilters());
        m_timeFilers.setOnAction(e -> ApplyFilters());
        m_cuisineFilers.setOnAction(e -> ApplyFilters());

        String comboStyle =
            "-fx-background-color: white;" +
            "-fx-border-color: #e0e0e0;" +
            "-fx-border-radius: 6;" +
            "-fx-background-radius: 6;" +
            "-fx-font-size: 13px;" +
            "-fx-padding: 4 8 4 8;";
        m_priceFilters.setStyle(comboStyle);
        m_timeFilers.setStyle(comboStyle);
        m_cuisineFilers.setStyle(comboStyle);
        String labelStyle =
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #555555;" +
            "-fx-font-weight: bold;";

        Label priceLabel = new Label("Price");
        Label timeLabel  = new Label("Time");
        Label cuisineLabel = new Label("Cuisine");
        priceLabel.setStyle(labelStyle);
        timeLabel.setStyle(labelStyle);
        cuisineLabel.setStyle(labelStyle);

        HBox priceBox = new HBox(6);
        priceBox.setAlignment(Pos.CENTER_LEFT);
        priceBox.getChildren().addAll(priceLabel, m_priceFilters);

        HBox timeBox = new HBox(6);
        timeBox.setAlignment(Pos.CENTER_LEFT);
        timeBox.getChildren().addAll(timeLabel, m_timeFilers);

        HBox cuisineBox = new HBox(6);
        cuisineBox.setAlignment(Pos.CENTER_LEFT);
        cuisineBox.getChildren().addAll(cuisineLabel, m_cuisineFilers);

        Separator divider = new Separator(Orientation.VERTICAL);
        divider.setStyle("-fx-padding: 0 4 0 4;");

        Separator divider2 = new Separator(Orientation.VERTICAL);
        divider2.setStyle("-fx-padding: 0 4 0 4;");

        boxFilters.setSpacing(16);
        boxFilters.setAlignment(Pos.CENTER_LEFT);
        boxFilters.setPadding(new Insets(10, 16, 10, 16));
        boxFilters.setStyle(
            "-fx-background-color: #f9f9f9;" +
            "-fx-border-color: transparent transparent #e0e0e0 transparent;" +
            "-fx-border-width: 0 0 1 0;"
        );
        boxFilters.getChildren().addAll(priceBox, divider, timeBox, divider2, cuisineBox);
    }

    private void ApplyFilters()
    {
        m_vbRestaurantsBox.getChildren().clear();
    
        String szPriceFilter = m_priceFilters.getValue();
        String szTimeFilter = m_timeFilers.getValue();
        String szCuisineFilter = m_cuisineFilers.getValue();

        if ( szPriceFilter.equals("Any price") && szTimeFilter.equals("Any time") && szCuisineFilter.equals("Any cuisine") )
        {
            PopulateRestaurantsBox(); 
            return;
        }
    
        ArrayList<RestaurantData> m_aRestaurantDataTemp = new ArrayList<>();
        for ( RestaurantData restaurant : m_aRestaurantData )
        {
            if ( IsWithinPriceFilter(restaurant, szPriceFilter) && IsWithinTimeFilter(restaurant, szTimeFilter) && IsWithinCuisineFilter(restaurant, szCuisineFilter) )
                m_aRestaurantDataTemp.add(restaurant);
        }

        if ( szTimeFilter.equals("Soonest") )
        {
            Collections.sort(m_aRestaurantDataTemp, (r1, r2) -> {
                String[] timeRange1 = r1.GetPickupTime().split("-");
                String[] timeRange2 = r2.GetPickupTime().split("-");

                int iStartTime1 = Integer.parseInt(GetTimeString(timeRange1[0]));
                int iStartTime2 = Integer.parseInt(GetTimeString(timeRange2[0]));

                return Integer.compare(iStartTime1, iStartTime2);
            });
        }

        for ( RestaurantData restaurant : m_aRestaurantDataTemp )
        {
            RestaurauntPane restaurantPane = new RestaurauntPane(restaurant);
            m_vbRestaurantsBox.getChildren().add(restaurantPane);
        }
    }

    private boolean IsWithinPriceFilter(RestaurantData restaurant, String szPriceFilter)
    {
        if ( szPriceFilter.equals("Any price") )
            return true;

        return szPriceFilter.equals("Under $3") && restaurant.GetMinPrice() < 3 ||
               szPriceFilter.equals("$3 – $5") && restaurant.GetMinPrice() >= 3 && restaurant.GetMinPrice() <= 5 || 
               szPriceFilter.equals("$5+") && restaurant.GetMaxPrice() >= 5;
    }


    private boolean IsWithinTimeFilter(RestaurantData restaurant, String szTimeFilter)
    {
        if ( szTimeFilter.equals("Any time") )
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

    private boolean IsWithinCuisineFilter(RestaurantData restaurant, String szCuisineFilter)
    {
        if ( szCuisineFilter.equals("Any cuisine") )
            return true;

        return restaurant.GetCuisine().equals(szCuisineFilter);
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
    private void SetupUserPage()
    {
        m_UserRoot.setPadding(new Insets(20));
        SetupFilters(); 
        
        m_vbRestaurantsBox.setPadding(new Insets(20));
        PopulateRestaurantsBox();

        ScrollPane scrollPane = new ScrollPane(m_vbRestaurantsBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        m_UserRoot.getChildren().addAll(boxFilters, scrollPane);
    }
}
