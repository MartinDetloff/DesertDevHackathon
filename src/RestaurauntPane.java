package src;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class RestaurauntPane extends VBox {

    private Label nameLabel;
    private Label descriptionLabel;
    private Label priceLabel;
    private final Label addressLabel;

    public RestaurauntPane(RestaurantData restaurant)
    {
        this.setSpacing(8);
        this.setPadding(new Insets(20));
        this.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #e0e0e0;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;"
        );

        nameLabel = new Label(restaurant.GetName());
        nameLabel.setStyle(
            "-fx-font-size: 20px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #1a1a1a;"
        );

        Separator sep = new Separator();
        sep.setStyle("-fx-padding: 2 0 6 0;");

        addressLabel = new Label(restaurant.GetAddress());
        addressLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #777777;"
        );

        descriptionLabel = new Label(restaurant.GetDescription());
        descriptionLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #444444;"
        );
        descriptionLabel.setWrapText(true);

        String priceString = String.format("$%d – $%d", restaurant.GetMinPrice(), restaurant.GetMaxPrice());
        priceLabel = new Label(priceString);
        priceLabel.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #2e7d32;" +
            "-fx-background-color: #e8f5e9;" +
            "-fx-padding: 3 8 3 8;" +
            "-fx-background-radius: 12;"
        );

        Label pickupTimeLabel = new Label("⏰ " + restaurant.GetPickupTime());
        pickupTimeLabel.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #1565c0;" +
            "-fx-background-color: #e3f2fd;" +
            "-fx-padding: 3 8 3 8;" +
            "-fx-background-radius: 12;"
        );
        String cuisine = restaurant.GetCuisine();

        String textColor;
        String bgColor;
        String icon;

        switch (cuisine) {
            case "Italian":
                textColor = "#b71c1c";
                bgColor = "#ffebee";
                icon = "🍝";
                break;
            case "Mexican":
                textColor = "#1b5e20";
                bgColor = "#e8f5e9";
                icon = "🌮";
                break;
            case "American":
                textColor = "#4e342e";
                bgColor = "#efebe9";
                icon = "🍔";
                break;
            case "Chinese":
                textColor = "#b71c1c";
                bgColor = "#fff3e0";
                icon = "🍜";
                break;
            default:
                textColor = "#1565c0";
                bgColor = "#e3f2fd";
                icon = "🍽️";
        }
        Label cuisineLabel = new Label(icon + " " + restaurant.GetCuisine());
        cuisineLabel.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: " + textColor + ";" +
            "-fx-background-color: " + bgColor + ";" +
            "-fx-padding: 3 8 3 8;" +
            "-fx-background-radius: 12;"
        );
        
        HBox badges = new HBox(8);
        badges.setAlignment(Pos.CENTER_LEFT);
        badges.getChildren().addAll(priceLabel, pickupTimeLabel, cuisineLabel);

        this.getChildren().addAll(nameLabel, sep, addressLabel, descriptionLabel, badges);
    }

}