package src;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class TimePage {

    public VBox optionsBox;
    public Label description;
    public ToggleButton afternoon;
    public ToggleButton soonest;
    public ToggleButton morning;
    public ToggleButton evening;
    public ToggleButton any;
    public VBox m_TimePageRoot = new VBox();
    public ToggleGroup group = new ToggleGroup();

    public TimePage()
    {
        description = new Label("What is your preferred pickup time?");
        description.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1A1A18;" +
                        "-fx-padding: 0 0 4 0;"
        );

        String toggleDefault =
                "-fx-font-size: 13px;" +
                        "-fx-background-color: transparent;" +
                        "-fx-body-color: transparent;" +
                        "-fx-outer-border: #0F6E56;" +
                        "-fx-inner-border: transparent;" +
                        "-fx-mark-color: #0F6E56;" +
                        "-fx-border-color: #0F6E56;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #0F6E56;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;" +
                        "-fx-background-insets: 0;" +
                        "-fx-effect: null;";

        String toggleSelected =
                "-fx-font-size: 13px;" +
                        "-fx-background-color: #E1F5EE;" +
                        "-fx-body-color: #E1F5EE;" +
                        "-fx-outer-border: #0F6E56;" +
                        "-fx-inner-border: transparent;" +
                        "-fx-mark-color: #0F6E56;" +
                        "-fx-border-color: #0F6E56;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #0F6E56;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;" +
                        "-fx-background-insets: 0;" +
                        "-fx-effect: null;";

        String toggleHover =
                "-fx-font-size: 13px;" +
                        "-fx-background-color: #E1F5EE;" +
                        "-fx-body-color: #E1F5EE;" +
                        "-fx-outer-border: #0F6E56;" +
                        "-fx-inner-border: transparent;" +
                        "-fx-mark-color: #0F6E56;" +
                        "-fx-border-color: #0F6E56;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #0F6E56;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;" +
                        "-fx-background-insets: 0;" +
                        "-fx-effect: null;";

        optionsBox = new VBox(10);
        optionsBox.setPadding(new Insets(8, 0, 0, 0));

        any = new ToggleButton("Any time");
        any.setToggleGroup(group);
        any.setUserData("Any time");
        any.setStyle(toggleDefault);
        any.setOnMouseEntered(e -> { if (!any.isSelected()) any.setStyle(toggleHover); });
        any.setOnMouseExited(e -> { if (!any.isSelected()) any.setStyle(toggleDefault); });
        any.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                any.setStyle(isSelected ? toggleSelected : toggleDefault));

        soonest = new ToggleButton("Soonest");
        soonest.setToggleGroup(group);
        soonest.setUserData("Soonest");
        soonest.setStyle(toggleDefault);
        soonest.setOnMouseEntered(e -> { if (!soonest.isSelected()) soonest.setStyle(toggleHover); });
        soonest.setOnMouseExited(e -> { if (!soonest.isSelected()) soonest.setStyle(toggleDefault); });
        soonest.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                soonest.setStyle(isSelected ? toggleSelected : toggleDefault));

        morning = new ToggleButton("Morning");
        morning.setToggleGroup(group);
        morning.setUserData("Morning");
        morning.setStyle(toggleDefault);
        morning.setOnMouseEntered(e -> { if (!morning.isSelected()) morning.setStyle(toggleHover); });
        morning.setOnMouseExited(e -> { if (!morning.isSelected()) morning.setStyle(toggleDefault); });
        morning.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                morning.setStyle(isSelected ? toggleSelected : toggleDefault));

        afternoon = new ToggleButton("Afternoon");
        afternoon.setToggleGroup(group);
        afternoon.setUserData("Afternoon");
        afternoon.setStyle(toggleDefault);
        afternoon.setOnMouseEntered(e -> { if (!afternoon.isSelected()) afternoon.setStyle(toggleHover); });
        afternoon.setOnMouseExited(e -> { if (!afternoon.isSelected()) afternoon.setStyle(toggleDefault); });
        afternoon.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                afternoon.setStyle(isSelected ? toggleSelected : toggleDefault));

        evening = new ToggleButton("Evening");
        evening.setToggleGroup(group);
        evening.setUserData("Evening");
        evening.setStyle(toggleDefault);
        evening.setOnMouseEntered(e -> { if (!evening.isSelected()) evening.setStyle(toggleHover); });
        evening.setOnMouseExited(e -> { if (!evening.isSelected()) evening.setStyle(toggleDefault); });
        evening.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                evening.setStyle(isSelected ? toggleSelected : toggleDefault));

        optionsBox.getChildren().addAll(afternoon, soonest, morning, evening, any);

        m_TimePageRoot.setSpacing(24);
        m_TimePageRoot.setPadding(new Insets(48, 40, 40, 40));
        m_TimePageRoot.setStyle("-fx-background-color: #F8F7F4;");
        m_TimePageRoot.getChildren().addAll(description, optionsBox);
    }
}