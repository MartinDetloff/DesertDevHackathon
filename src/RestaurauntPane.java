package src;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Insets;

import java.nio.charset.StandardCharsets;

// Extend VBox (or Pane, HBox, StackPane — whatever layout fits)
public class RestaurauntPane extends VBox {

    private Label nameLabel;
    private Label descriptionLabel;
    private Label priceLabel;
    private Label addressLabel;

    public RestaurauntPane(RestaurantData restaurant) {
        // --- Configure THIS pane (we ARE the VBox) ---
        this.setSpacing(10);                          // gap between children
        this.setPadding(new Insets(15));              // inner padding
        this.setStyle("-fx-background-color: lightblue; -fx-border-color: navy;");

        // --- Build child nodes ---
        String priceString1 = "Price Range: $%d - $%d";
        String priceString2 = String.format(priceString1, restaurant.GetMinPrice(), restaurant.GetMaxPrice());
        nameLabel = new Label(restaurant.GetName());
        nameLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");
        descriptionLabel = new Label(restaurant.GetDescription());
        descriptionLabel.setStyle("-fx-font-size: 18px");
        priceLabel = new Label(priceString2);
        priceLabel.setStyle("-fx-font-size: 18px");
        //addressLabel = new Label(address);
        //addressLabel.setStyle("-fx-font-size: 18px");


        // --- Add children to THIS pane's child list ---
        this.getChildren().addAll(nameLabel, descriptionLabel, priceLabel);
    }

}