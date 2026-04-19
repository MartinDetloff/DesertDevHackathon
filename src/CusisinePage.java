package src;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class CusisinePage {
    public VBox optionsBox;
    public Label description;
    public ToggleButton chinese;
    public ToggleButton mexican;
    public ToggleButton american;
    public ToggleButton italian;
    public ToggleButton any;
    public VBox m_CuisinePageRoot = new VBox();
    public ToggleGroup group = new ToggleGroup();
    public CusisinePage()
    {
        description = new Label("What is your preffered cuisine?");
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

        any = new ToggleButton("Any cuisine");
        any.setToggleGroup(group);
        any.setUserData("Any cuisine");
        any.setStyle(toggleDefault);
        any.setOnMouseEntered(e -> { if (!any.isSelected()) any.setStyle(toggleHover); });
        any.setOnMouseExited(e -> { if (!any.isSelected()) any.setStyle(toggleDefault); });
        any.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                any.setStyle(isSelected ? toggleSelected : toggleDefault));

        mexican = new ToggleButton("Mexican");
        mexican.setToggleGroup(group);
        mexican.setUserData("Mexican");
        mexican.setStyle(toggleDefault);
        mexican.setOnMouseEntered(e -> { if (!mexican.isSelected()) mexican.setStyle(toggleHover); });
        mexican.setOnMouseExited(e -> { if (!mexican.isSelected()) mexican.setStyle(toggleDefault); });
        mexican.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                mexican.setStyle(isSelected ? toggleSelected : toggleDefault));

        american = new ToggleButton("American");
        american.setToggleGroup(group);
        american.setUserData("American");
        american.setStyle(toggleDefault);
        american.setOnMouseEntered(e -> { if (!american.isSelected()) american.setStyle(toggleHover); });
        american.setOnMouseExited(e -> { if (!american.isSelected()) american.setStyle(toggleDefault); });
        american.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                american.setStyle(isSelected ? toggleSelected : toggleDefault));

        chinese = new ToggleButton("Chinese");
        chinese.setToggleGroup(group);
        chinese.setUserData("Chinese");
        chinese.setStyle(toggleDefault);
        chinese.setOnMouseEntered(e -> { if (!chinese.isSelected()) chinese.setStyle(toggleHover); });
        chinese.setOnMouseExited(e -> { if (!chinese.isSelected()) chinese.setStyle(toggleDefault); });
        chinese.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                chinese.setStyle(isSelected ? toggleSelected : toggleDefault));

        italian = new ToggleButton("Italian");
        italian.setToggleGroup(group);
        italian.setUserData("Italian");
        italian.setStyle(toggleDefault);
        italian.setOnMouseEntered(e -> { if (!italian.isSelected()) italian.setStyle(toggleHover); });
        italian.setOnMouseExited(e -> { if (!italian.isSelected()) italian.setStyle(toggleDefault); });
        italian.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                italian.setStyle(isSelected ? toggleSelected : toggleDefault));

        optionsBox.getChildren().addAll(chinese, mexican, american, italian, any);

        m_CuisinePageRoot.setSpacing(24);
        m_CuisinePageRoot.setPadding(new Insets(48, 40, 40, 40));
        m_CuisinePageRoot.setStyle("-fx-background-color: #F8F7F4;");
        m_CuisinePageRoot.getChildren().addAll(description, optionsBox);
    }
}