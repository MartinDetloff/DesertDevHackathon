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
        optionsBox = new VBox();
        low = new ToggleButton("Under $3");
        low.setToggleGroup(group);
        low.setUserData("Under $3");
        med = new ToggleButton("$3 - $5");
        med.setToggleGroup(group);
        med.setUserData("$3 – $5");
        high = new ToggleButton("$5+");
        high.setToggleGroup(group);
        high.setUserData("$5+");
        any = new ToggleButton("Any Price");
        any.setToggleGroup(group);
        any.setUserData("Any price");
        optionsBox.getChildren().addAll(low, med, high, any);
        m_PricePageRoot.getChildren().addAll(description, optionsBox);
    }
}
