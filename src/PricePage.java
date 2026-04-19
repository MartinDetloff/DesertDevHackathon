package src;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PricePage {
    public VBox optionsBox;
    public Label description;
    public ToggleButton low;
    public ToggleButton med;
    public ToggleButton high;
    public ToggleButton any;
    public VBox m_PricePageRoot = new VBox();
    public ToggleGroup group = new ToggleGroup();
    public PricePage(){
        description = new Label("What is your price range?");
        description.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1A1A18;" +
                        "-fx-padding: 0 0 4 0;"
        );

        String toggleDefault =
                "-fx-font-size: 13px;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: #0F6E56;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #0F6E56;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;";

        String toggleSelected =
                "-fx-font-size: 13px;" +
                        "-fx-background-color: #E1F5EE;" +
                        "-fx-border-color: #0F6E56;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #0F6E56;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;";

        String toggleHover =
                "-fx-font-size: 13px;" +
                        "-fx-background-color: #E1F5EE;" +
                        "-fx-border-color: #0F6E56;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #0F6E56;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;";

        optionsBox = new VBox(10);
        optionsBox.setPadding(new Insets(8, 0, 0, 0));

        low = new ToggleButton("Under $3");
        low.setToggleGroup(group);
        low.setUserData("Under $3");
        low.setStyle(toggleDefault);
        low.setOnMouseEntered(e -> { if (!low.isSelected()) low.setStyle(toggleHover); });
        low.setOnMouseExited(e -> { if (!low.isSelected()) low.setStyle(toggleDefault); });
        low.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                low.setStyle(isSelected ? toggleSelected : toggleDefault));

        med = new ToggleButton("$3 - $5");
        med.setToggleGroup(group);
        med.setUserData("$3 – $5");
        med.setStyle(toggleDefault);
        med.setOnMouseEntered(e -> { if (!med.isSelected()) med.setStyle(toggleHover); });
        med.setOnMouseExited(e -> { if (!med.isSelected()) med.setStyle(toggleDefault); });
        med.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                med.setStyle(isSelected ? toggleSelected : toggleDefault));

        high = new ToggleButton("$5+");
        high.setToggleGroup(group);
        high.setUserData("$5+");
        high.setStyle(toggleDefault);
        high.setOnMouseEntered(e -> { if (!high.isSelected()) high.setStyle(toggleHover); });
        high.setOnMouseExited(e -> { if (!high.isSelected()) high.setStyle(toggleDefault); });
        high.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                high.setStyle(isSelected ? toggleSelected : toggleDefault));

        any = new ToggleButton("Any Price");
        any.setToggleGroup(group);
        any.setUserData("Any price");
        any.setStyle(toggleDefault);
        any.setOnMouseEntered(e -> { if (!any.isSelected()) any.setStyle(toggleHover); });
        any.setOnMouseExited(e -> { if (!any.isSelected()) any.setStyle(toggleDefault); });
        any.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                any.setStyle(isSelected ? toggleSelected : toggleDefault));

        optionsBox.getChildren().addAll(low, med, high, any);

        m_PricePageRoot.setSpacing(24);
        m_PricePageRoot.setPadding(new Insets(48, 40, 40, 40));
        m_PricePageRoot.setStyle("-fx-background-color: #F8F7F4;");
        m_PricePageRoot.getChildren().addAll(description, optionsBox);
    }
}