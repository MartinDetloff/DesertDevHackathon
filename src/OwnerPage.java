package src;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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

public class OwnerPage
{
    Utils m_utils = new Utils();
    GridPane m_OwnerRoot = new GridPane();

    TextField m_nameField = new TextField();
    TextArea m_descField = new TextArea();
    TextField m_addressField = new TextField();
    TextField m_minPriceField = new TextField();
    TextField m_maxPriceField = new TextField();

    public OwnerPage()
    {
        m_OwnerRoot.setStyle("-fx-background-color: #FEFAE0;");
        m_OwnerRoot.setPadding(new Insets(16, 32, 16, 32));
        m_OwnerRoot.setVgap(5);
        m_OwnerRoot.setHgap(8);

        String comboStyle =
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #283618;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-font-size: 13px;" +
                        "-fx-padding: 4 8 4 8;";

        String szTextFieldStyle =
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #D3D1C7;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-font-size: 13px;" +
                        "-fx-padding: 4 8 4 8;";

        ColumnConstraints labelCol = new ColumnConstraints(120);
        labelCol.setHalignment(HPos.LEFT);

        ColumnConstraints inputCol = new ColumnConstraints();
        inputCol.setHgrow(Priority.ALWAYS);

        m_OwnerRoot.getColumnConstraints().addAll(labelCol, inputCol);

        int iRow = 0;
        AddHeader("RESTAURANT INFO", iRow++);
        AddLabel("Name *", iRow);

        m_nameField.setPromptText("e.g. Dions");
        m_OwnerRoot.add(m_nameField, 1, iRow++);
        m_nameField.setStyle(szTextFieldStyle);

        AddLabel("Description", iRow);
        m_descField.setPromptText("Describe tonight's surplus food...");
        m_descField.setPrefRowCount(4);
        m_descField.setWrapText(true);
        m_descField.setStyle(szTextFieldStyle + "-fx-control-inner-background: #FFFFFF;");
        m_OwnerRoot.add(m_descField, 1, iRow++);

        AddLabel("Address *", iRow);
        m_addressField.setPromptText("e.g. 123 Main Street");
        m_addressField.setStyle(szTextFieldStyle);
        m_OwnerRoot.add(m_addressField, 1, iRow++);

        AddLabel("Cuisine *", iRow);
        ComboBox<String> cuisineBox = new ComboBox<>();
        cuisineBox.getItems().addAll("Italian", "Mexican", "Chinese", "American");
        cuisineBox.setValue("Italian");
        cuisineBox.setStyle(comboStyle);
        m_OwnerRoot.add(cuisineBox, 1, iRow++);

        AddHeader("PRICING", iRow++);
        AddLabel("Price range *", iRow);

        HBox priceBox = new HBox(8);
        priceBox.setAlignment(Pos.CENTER_LEFT);

        m_minPriceField.setPromptText("Min $");
        m_minPriceField.setPrefWidth(90);
        m_minPriceField.setStyle(szTextFieldStyle);

        m_maxPriceField = new TextField();
        m_maxPriceField.setPromptText("Max $");
        m_maxPriceField.setPrefWidth(90);
        m_maxPriceField.setStyle(szTextFieldStyle);

        for (TextField tf : new TextField[]{m_minPriceField, m_maxPriceField}) {
            tf.textProperty().addListener((obs, old, val) -> {
                if (!val.matches("[0-9]*\\.?[0-9]*")) tf.setText(old);
            });
        }

        Label dashLabel = new Label("–");
        dashLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #5F5E5A;");
        priceBox.getChildren().addAll(m_minPriceField, dashLabel, m_maxPriceField);
        m_OwnerRoot.add(priceBox, 1, iRow++);

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

        startTime.setStyle(comboStyle);
        endTime.setStyle(comboStyle);

        Label toLabel = new Label("to");
        toLabel.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-text-fill: #5F5E5A;"
        );

        HBox timeBox = new HBox(8);
        timeBox.setAlignment(Pos.CENTER_LEFT);
        timeBox.getChildren().addAll(startTime, toLabel, endTime);
        m_OwnerRoot.add(timeBox, 1, iRow++);

        AddSeperator(iRow++);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setPadding(new Insets(2, 0, 0, 0));
        GridPane.setColumnSpan(buttonBox, 2);

        Button clearBtn = new Button("Clear");
        clearBtn.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: #D3D1C7;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #5F5E5A;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;"
        );
        clearBtn.setOnMouseEntered(e -> clearBtn.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-background-color: #FEFAE0;" +
                        "-fx-border-color: #888780;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #5F5E5A;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;"
        ));
        clearBtn.setOnMouseExited(e -> clearBtn.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: #D3D1C7;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #5F5E5A;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;"
        ));

        Button saveBtn = new Button("Save Listing");
        saveBtn.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: #283618;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #283618;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;"
        );
        saveBtn.setOnMouseEntered(e -> saveBtn.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-background-color: #E1F5EE;" +
                        "-fx-border-color: #283618;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #283618;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;"
        ));
        saveBtn.setOnMouseExited(e -> saveBtn.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: #283618;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #283618;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;"
        ));

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
                        "-fx-text-fill: #5F5E5A;" +
                        "-fx-padding: 4 0 2 0;"
        );
        header.setMaxWidth(Double.MAX_VALUE);
        GridPane.setColumnSpan(header, 2);
        m_OwnerRoot.add(header, 0, iRow);
    }

    private void AddSeperator(int iRow)
    {
        Separator sep = new Separator();
        sep.setStyle("-fx-padding: 4 0 4 0;");
        GridPane.setColumnSpan(sep, 2);
        m_OwnerRoot.add(sep, 0, iRow);
    }

    private void AddLabel(String szText, int iRow)
    {
        Label label = new Label(szText);
        label.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-text-fill: #1A1A18;" +
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