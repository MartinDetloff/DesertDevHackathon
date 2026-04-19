package src;

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
        optionsBox = new VBox();
        
        any = new ToggleButton("Any cuisine");
        any.setToggleGroup(group);
        any.setUserData("Any cuisine");

        mexican = new ToggleButton("Mexican");
        mexican.setToggleGroup(group);
        mexican.setUserData("Mexican");
        
        american = new ToggleButton("American");
        american.setToggleGroup(group);
        american.setUserData("American");
        
        chinese = new ToggleButton("Chinese");
        chinese.setToggleGroup(group);
        chinese.setUserData("Chinese");

        italian = new ToggleButton("Italian");
        italian.setToggleGroup(group);
        italian.setUserData("Italian");
    
        optionsBox.getChildren().addAll(chinese, mexican, american, italian, any);
        m_CuisinePageRoot.getChildren().addAll(description, optionsBox);
    }
}
