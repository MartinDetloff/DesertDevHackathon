package src;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StartPage {
    private Label title;
    private Label description;
    private HBox lowerBox;
    private VBox ownerBox;
    private VBox userBox;
    private Label ownerDescription;
    public Button ownerButton;
    private Label userDescription;
    public Button userButton;
    public VBox m_StartPageRoot = new VBox();

    public StartPage()
    {
        title = new Label("Restof");
        title.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1A1A18;" +
                        "-fx-padding: 0 0 4 0;"
        );

        description = new Label("Connecting people to reduced-price surplus food. Less waste,  more value — for restaurants and customers alike.");
        description.setWrapText(true);
        description.setMaxWidth(560);
        description.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-text-fill: #5F5E5A;" +
                        "-fx-line-spacing: 4;"
        );

        userDescription = new Label("Looking for quality food at reduced prices? Browse nearby restaurants with surplus meals available now.");
        userDescription.setWrapText(true);
        userDescription.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-text-fill: #5F5E5A;" +
                        "-fx-line-spacing: 3;"
        );

        ownerDescription = new Label("Have excess food to sell? List your surplus items and reduce waste while reaching hungry customers.");
        ownerDescription.setWrapText(true);
        ownerDescription.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-text-fill: #5F5E5A;" +
                        "-fx-line-spacing: 3;"
        );

        userButton = new Button("Get started");
        userButton.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: #0F6E56;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #0F6E56;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;"
        );
        userButton.setOnMouseEntered(e -> userButton.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-background-color: #E1F5EE;" +
                        "-fx-border-color: #0F6E56;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #0F6E56;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;"
        ));
        userButton.setOnMouseExited(e -> userButton.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: #0F6E56;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #0F6E56;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;"
        ));

        ownerButton = new Button("List my food");
        ownerButton.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: #0F6E56;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #0F6E56;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;"
        );
        ownerButton.setOnMouseEntered(e -> ownerButton.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-background-color: #E1F5EE;" +
                        "-fx-border-color: #0F6E56;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #0F6E56;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;"
        ));
        ownerButton.setOnMouseExited(e -> ownerButton.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: #0F6E56;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #0F6E56;" +
                        "-fx-padding: 7 16 7 16;" +
                        "-fx-cursor: hand;"
        ));

        userBox = new VBox(10);
        userBox.setAlignment(Pos.TOP_LEFT);
        userBox.setPrefWidth(280);
        userBox.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #D3D1C7;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 28 24 28 24;"
        );
        userBox.setOnMouseEntered(e -> userBox.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #888780;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 28 24 28 24;"
        ));
        userBox.setOnMouseExited(e -> userBox.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #D3D1C7;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 28 24 28 24;"
        ));

        ownerBox = new VBox(10);
        ownerBox.setAlignment(Pos.TOP_LEFT);
        ownerBox.setPrefWidth(280);
        ownerBox.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #D3D1C7;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 28 24 28 24;"
        );
        ownerBox.setOnMouseEntered(e -> ownerBox.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #888780;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 28 24 28 24;"
        ));
        ownerBox.setOnMouseExited(e -> ownerBox.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #D3D1C7;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 28 24 28 24;"
        ));

        Label userCardTitle = new Label("I'm a customer");
        userCardTitle.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #1A1A18;");

        Label ownerCardTitle = new Label("I'm a restaurant owner");
        ownerCardTitle.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #1A1A18;");

        userBox.getChildren().addAll(userCardTitle, userDescription, userButton);
        ownerBox.getChildren().addAll(ownerCardTitle, ownerDescription, ownerButton);

        lowerBox = new HBox(16);
        lowerBox.setPadding(new Insets(8, 0, 0, 0));
        lowerBox.getChildren().addAll(ownerBox, userBox);

        m_StartPageRoot.setSpacing(24);
        m_StartPageRoot.setPadding(new Insets(48, 40, 40, 40));
        m_StartPageRoot.setStyle("-fx-background-color: #F8F7F4;");
        m_StartPageRoot.getChildren().addAll(title, description, lowerBox);
    }
}