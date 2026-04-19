package src;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserPage extends Application 
{
    private HBox boxFilters = new HBox();
    private VBox m_vbRestaurantsBox = new VBox(20);
    private VBox UserRoot = new VBox(20);

    private static Utils m_utils = new Utils();

    private ComboBox<String> m_priceFilters = new ComboBox<>();
    private ComboBox<String> m_timeFilers = new ComboBox<>();
    private ComboBox<String> m_cuisineFilers = new ComboBox<>();

    private ArrayList<RestaurantData> m_aRestaurantData = m_utils.GetRestaurants();

    // OWNER PAGE 
    GridPane m_OwnerRoot = new GridPane();

    TextField m_nameField = new TextField();
    TextArea m_descField = new TextArea();
    TextField m_addressField = new TextField();
    TextField m_minPriceField = new TextField();
    TextField m_maxPriceField = new TextField();

    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) 
    {
        SetupUserPage();
        SetupOwnerPage();
        
        Scene scene = new Scene(m_OwnerRoot, 650, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("User Waste Food Pickup");
        primaryStage.show();
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
        UserRoot.setPadding(new Insets(20));
        SetupFilters(); 
        
        m_vbRestaurantsBox.setPadding(new Insets(20));
        PopulateRestaurantsBox();

        ScrollPane scrollPane = new ScrollPane(m_vbRestaurantsBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        UserRoot.getChildren().addAll(boxFilters, scrollPane);
    }

    private void SetupOwnerPage()
    {
        ColumnConstraints labelCol = new ColumnConstraints(130);
        labelCol.setHalignment(HPos.LEFT);

        ColumnConstraints inputCol = new ColumnConstraints();
        inputCol.setHgrow(Priority.ALWAYS);
        
        m_OwnerRoot.getColumnConstraints().addAll(labelCol, inputCol);
        
        int iRow = 0;
        AddHeader("RESTAURANT INFO", iRow++);
        AddLabel("Name *", iRow);

        m_nameField.setPromptText("e.g. Dions");
        m_OwnerRoot.add(m_nameField, 1, iRow++);

        AddLabel("Description", iRow);
        m_descField.setPromptText("Describe tonight's surplus food...");
        m_descField.setPrefRowCount(3);
        m_descField.setWrapText(true);
        m_OwnerRoot.add(m_descField, 1, iRow++);

        AddLabel("Address *", iRow);
        m_addressField.setPromptText("e.g. 123 Main Street");
        m_OwnerRoot.add(m_addressField, 1, iRow++);

        AddLabel("Cuisine *", iRow);
        ComboBox<String> cuisineBox = new ComboBox<>();
        cuisineBox.getItems().addAll("Italian", "Mexican", "Chinese", "American");
        cuisineBox.setValue("Italian");
        m_OwnerRoot.add(cuisineBox, 1, iRow++);

        AddSeperator(iRow++);

        AddHeader("PRICING", iRow++);
        AddLabel("Price range *", iRow);

        HBox priceBox = new HBox(8);
        priceBox.setAlignment(Pos.CENTER_LEFT);

        m_minPriceField.setPromptText("Min $");
        m_minPriceField.setPrefWidth(90);

        m_maxPriceField = new TextField();
        m_maxPriceField.setPromptText("Max $");
        m_maxPriceField.setPrefWidth(90);

        for (TextField tf : new TextField[]{m_minPriceField, m_maxPriceField}) {
        tf.textProperty().addListener((obs, old, val) -> {
            if (!val.matches("[0-9]*\\.?[0-9]*")) tf.setText(old);
        });
        }

        priceBox.getChildren().addAll(m_minPriceField, new Label("–"), m_maxPriceField);
        m_OwnerRoot.add(priceBox, 1, iRow++);

        AddSeperator(iRow++);
        AddHeader("PICKUP TIME", iRow++);

        AddLabel("Pickup time *", iRow);

        ComboBox<String> startTime = new ComboBox<>();
        ComboBox<String> endTime   = new ComboBox<>();

        LocalTime now = LocalTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("h:mm a");
        for ( int iHour = now.getHour(); iHour < 24; iHour++ )
        {
            for ( int min : new int[]{0,30})
            {
                startTime.getItems().add(LocalTime.of(iHour, min).format(fmt));
                endTime.getItems().add(LocalTime.of(iHour, min).format(fmt));
            }
        }

        int roundedMin       = now.getMinute() < 30 ? 30 : 0;
        int roundedHour      = now.getMinute() < 30 ? now.getHour() : now.getHour() + 1;
        String defaultStart  = LocalTime.of(roundedHour > 23 ? 0 : roundedHour, roundedMin).format(fmt);
        String defaultEnd    = LocalTime.of(roundedHour > 23 ? 0 : roundedHour + 1, roundedMin).format(fmt);
    
        startTime.setValue(defaultStart);
        endTime.setValue(defaultEnd);
        String comboStyle =
        "-fx-background-color: white;" +
        "-fx-border-color: #e0e0e0;" +
        "-fx-border-radius: 6;" +
        "-fx-background-radius: 6;" +
        "-fx-font-size: 13px;" +
        "-fx-padding: 4 8 4 8;";

        startTime.setStyle(comboStyle);
        endTime.setStyle(comboStyle);

        Label toLabel = new Label("to");
        toLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #555555;"
        );

        HBox timeBox = new HBox(8);
        timeBox.setAlignment(Pos.CENTER_LEFT);
        timeBox.getChildren().addAll(startTime, toLabel, endTime);
        m_OwnerRoot.add(timeBox, 1, iRow++);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setColumnSpan(buttonBox, 2);

        Button clearBtn = new Button("Clear");
        Button saveBtn  = new Button("Save Listing");
        saveBtn.setStyle("-fx-background-color: #2e7d32; -fx-text-fill: white; -fx-font-weight: bold;");
        
        clearBtn.setOnAction( e -> {
            clearFields();
        });

        saveBtn.setOnAction( e -> {
            if ( ! ValidateInputs() )
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Missing Information");
                alert.setHeaderText("Missing Required Fields");
                alert.setContentText("Please fill in all required fields.");

                alert.showAndWait();
            }
            else
            {

                RestaurantData restaurantData = new RestaurantData(
                    m_nameField.getText(),
                    m_descField.getText(),
                    m_addressField.getText(),
                    Integer.parseInt(m_maxPriceField.getText()),
                    Integer.parseInt(m_minPriceField.getText()),
                    startTime.getValue() + " - " + endTime.getValue(),
                    cuisineBox.getValue()
                );

                m_utils.AddRestaurant(restaurantData);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Listing Saved");
                alert.setContentText("Your restaurant listing has been saved successfully!");

                alert.showAndWait();

                clearFields();
            }
        });

        buttonBox.getChildren().addAll(clearBtn, saveBtn);
        m_OwnerRoot.add(buttonBox, 0, iRow);
    }

    private void AddHeader(String szName, int iRow)
    {
        Label header = new Label(szName.toUpperCase());
        header.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #555555;" +
            "-fx-padding: 12 0 4 0;" +
            "-fx-border-color: transparent transparent #e0e0e0 transparent;" +
            "-fx-border-width: 0 0 1 0;"
        );
        header.setMaxWidth(Double.MAX_VALUE);
        GridPane.setColumnSpan(header, 2);
        m_OwnerRoot.add(header, 0, iRow);
    }

    private void AddSeperator(int iRow)
    {
        Separator sep = new Separator();
        sep.setStyle("-fx-padding: 6 0 6 0;");
        GridPane.setColumnSpan(sep, 2);
        m_OwnerRoot.add(sep, 0, iRow);
    }

    private void AddLabel(String szText, int iRow)
    {
        Label label = new Label(szText);
        label.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #4d945b;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 0 12 0 0;"
        );
        label.setAlignment(Pos.CENTER_RIGHT);
        label.setMaxWidth(Double.MAX_VALUE);
        GridPane.setValignment(label, VPos.CENTER);
        m_OwnerRoot.add(label, 0, iRow);
    }

    private boolean ValidateInputs()
    {
        return ! m_nameField.getText().isEmpty() && ! m_nameField.getText().contains(",") &&
               ! m_descField.getText().isEmpty() && ! m_descField.getText().contains(",") &&
               ! m_addressField.getText().isEmpty() && ! m_addressField.getText().contains(",") &&
               ! m_minPriceField.getText().isEmpty() && ! m_minPriceField.getText().contains(",") &&
               ! m_maxPriceField.getText().isEmpty() && ! m_maxPriceField.getText().contains(",");
    }

    private void clearFields()
    {
        m_nameField.clear();
        m_descField.clear();
        m_addressField.clear();
        m_minPriceField.clear();
        m_maxPriceField.clear();
    }
}
