package src;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

// Extend VBox (or Pane, HBox, StackPane — whatever layout fits)
public class RestaurauntPane extends VBox {

    private Label nameLabel;
    private Label descriptionLabel;
    private Label priceLabel;
    private Label addressLabel;

    public RestaurauntPane(RestaurantData restaurant) 
    {
        // --- Configure THIS pane (we ARE the VBox) ---
        this.setSpacing(10);                          // gap between children
        this.setPadding(new Insets(15));              // inner padding
        this.setStyle("-fx-background-color: lightblue; -fx-border-color: navy;");

        nameLabel = new Label(restaurant.GetName());
        nameLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");

        addressLabel = new Label("Address: " + restaurant.GetAddress());
        addressLabel.setStyle("-fx-font-size: 18px");

        descriptionLabel = new Label("Description: " + restaurant.GetDescription());
        descriptionLabel.setStyle("-fx-font-size: 18px");

        String priceString = String.format("$%d - $%d", restaurant.GetMinPrice(), restaurant.GetMaxPrice());  
        priceLabel = new Label("Price Range: " + priceString);
        priceLabel.setStyle("-fx-font-size: 18px");
    
        Label pickupTimeLabel = new Label("Pickup Time: " + restaurant.GetPickupTime());
        pickupTimeLabel.setStyle("-fx-font-size: 18px");

        // --- Add children to THIS pane's child list ---
        this.getChildren().addAll(nameLabel, addressLabel, descriptionLabel, priceLabel, pickupTimeLabel);
    }

}