package src;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class OwnerPage extends Application
{
    Utils m_utils = new Utils();
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) 
    {
        GridPane root = new GridPane();

        ColumnConstraints labelCol = new ColumnConstraints(130);
        labelCol.setHalignment(HPos.LEFT);

        ColumnConstraints inputCol = new ColumnConstraints();
        inputCol.setHgrow(Priority.ALWAYS);
        
        root.getColumnConstraints().addAll(labelCol, inputCol);
        
        int iRow = 0;

        AddHeader("RESTAURANT INFO", iRow++, root);

        root.add(new Label("Name *"), 0, iRow);
        TextField nameField = new TextField();
        nameField.setPromptText("e.g. Dions");
        root.add(nameField, 1, iRow++);

        root.add(new Label("Description"), 0, iRow);
        TextArea descField = new TextArea();
        descField.setPromptText("Describe tonight's surplus food...");
        descField.setPrefRowCount(3);
        descField.setWrapText(true);
        root.add(descField, 1, iRow++);

        root.add(new Label("Address *"), 0, iRow);
        TextField addressField = new TextField();
        addressField.setPromptText("e.g. 123 Main St, New York, NY 10001");
        root.add(addressField, 1, iRow++);

        AddSeperator(iRow++, root);

        AddHeader("PRICING", iRow++, root);
            
        root.add(new Label("Price range *"), 0, iRow);

        HBox priceBox = new HBox(8);
        priceBox.setAlignment(Pos.CENTER_LEFT);

        TextField minPriceField = new TextField();
        minPriceField.setPromptText("Min $");
        minPriceField.setPrefWidth(90);

        TextField maxPriceField = new TextField();
        maxPriceField.setPromptText("Max $");
        maxPriceField.setPrefWidth(90);

        for (TextField tf : new TextField[]{minPriceField, maxPriceField}) {
        tf.textProperty().addListener((obs, old, val) -> {
            if (!val.matches("[0-9]*\\.?[0-9]*")) tf.setText(old);
        });
        }

        priceBox.getChildren().addAll(minPriceField, new Label("–"), maxPriceField);
        root.add(priceBox, 1, iRow++);

        AddSeperator(iRow++, root);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setColumnSpan(buttonBox, 2);

        Button clearBtn = new Button("Clear");
        Button saveBtn  = new Button("Save Listing");
        saveBtn.setStyle("-fx-background-color: #2e7d32; -fx-text-fill: white; -fx-font-weight: bold;");
        
        clearBtn.setOnAction( e -> {
            nameField.clear();
            descField.clear();
            addressField.clear();
            minPriceField.clear();
            maxPriceField.clear();
        });

        saveBtn.setOnAction( e -> {
            if ( nameField.getText().isEmpty() ||
                 descField.getText().isEmpty() ||
                 addressField.getText().isEmpty() ||
                 minPriceField.getText().isEmpty() ||
                 maxPriceField.getText().isEmpty() ) 
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
                    nameField.getText(),
                    descField.getText(),
                    addressField.getText(),
                    Integer.parseInt(maxPriceField.getText()),
                    Integer.parseInt(minPriceField.getText())
                );

                m_utils.AddRestaurant(restaurantData);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Listing Saved");
                alert.setContentText("Your restaurant listing has been saved successfully!");

                alert.showAndWait();

                nameField.clear();
                descField.clear();
                addressField.clear();
                minPriceField.clear();
                maxPriceField.clear();
            }
        });
    
        buttonBox.getChildren().addAll(clearBtn, saveBtn);
        root.add(buttonBox, 0, iRow);

        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }

    private void AddHeader(String szName, int iRow, GridPane root)
    {
        Label pricingHeader = new Label(szName);
        pricingHeader.setStyle("-fx-font-size: 14px; -fx-text-fill: #888; -fx-padding: 0 0 4 0;");
        GridPane.setColumnSpan(pricingHeader, 2);
        root.add(pricingHeader, 0, iRow);
    }

    private void AddSeperator(int iRow, GridPane root)
    {
        root.add(new Label(""), 0, iRow);
    }
}
