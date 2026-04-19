package src;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
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
    GridPane m_root = new GridPane();

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
        ColumnConstraints labelCol = new ColumnConstraints(130);
        labelCol.setHalignment(HPos.LEFT);

        ColumnConstraints inputCol = new ColumnConstraints();
        inputCol.setHgrow(Priority.ALWAYS);
        
        m_root.getColumnConstraints().addAll(labelCol, inputCol);
        
        int iRow = 0;
        AddHeader("RESTAURANT INFO", iRow++);
        AddLabel("Name *", iRow);

        m_nameField.setPromptText("e.g. Dions");
        m_root.add(m_nameField, 1, iRow++);

        AddLabel("Description", iRow);
        m_descField.setPromptText("Describe tonight's surplus food...");
        m_descField.setPrefRowCount(3);
        m_descField.setWrapText(true);
        m_root.add(m_descField, 1, iRow++);

        AddLabel("Address *", iRow);
        m_addressField.setPromptText("e.g. 123 Main Street");
        m_root.add(m_addressField, 1, iRow++);

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
        m_root.add(priceBox, 1, iRow++);

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
        String defaultStart  = LocalTime.of(roundedHour > 23 ? 1 : roundedHour, roundedMin).format(fmt);
        String defaultEnd    = LocalTime.of(roundedHour + 1 > 23 ? 1 : roundedHour + 1, roundedMin).format(fmt);
    
        startTime.setValue(defaultStart);
        endTime.setValue(defaultEnd);

        HBox timeBox = new HBox(8);
        timeBox.setAlignment(Pos.CENTER_LEFT);
        timeBox.getChildren().addAll(startTime, new Label("to"), endTime);
        m_root.add(timeBox, 1, iRow++);

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
                    startTime.getValue() + " - " + endTime.getValue()
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
        m_root.add(buttonBox, 0, iRow);

        primaryStage.setScene(new Scene(m_root, 600, 300));
        primaryStage.show();
    }

    private void AddHeader(String szName, int iRow)
    {
        Label header = new Label(szName.toUpperCase());
        header.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #444444;" +
            "-fx-background-color: #eeeeee;" +
            "-fx-padding: 4 10 4 10;" +
            "-fx-background-radius: 4;"
        );
        GridPane.setColumnSpan(header, 2);
        m_root.add(header, 0, iRow);
    }

    private void AddSeperator(int iRow)
    {
        Separator sep = new Separator();
        sep.setStyle("-fx-padding: 6 0 6 0;");
        GridPane.setColumnSpan(sep, 2);
        m_root.add(sep, 0, iRow);
    }

    private void AddLabel(String szText, int iRow)
    {
        Label label = new Label(szText);
        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #555555;" +
                "-fx-padding: 0 8 0 0;"
        );
        label.setAlignment(Pos.CENTER_RIGHT);
        label.setMaxWidth(Double.MAX_VALUE);
        m_root.add(label, 0, iRow);
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
