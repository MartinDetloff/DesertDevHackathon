package src;

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
        description = new Label("What is your preffered pickupTime?");
        optionsBox = new VBox();
        
        any = new ToggleButton("Any time");
        any.setToggleGroup(group);
        any.setUserData("Any time");

        soonest = new ToggleButton("Soonest");
        soonest.setToggleGroup(group);
        soonest.setUserData("Soonest");
        
        morning = new ToggleButton("Morning");
        morning.setToggleGroup(group);
        morning.setUserData("Morning");
        
        afternoon = new ToggleButton("Afternoon");
        afternoon.setToggleGroup(group);
        afternoon.setUserData("Afternoon");

        evening = new ToggleButton("Evening");
        evening.setToggleGroup(group);
        evening.setUserData("Evening");
    
        optionsBox.getChildren().addAll(afternoon, soonest, morning, evening, any);
        m_TimePageRoot.getChildren().addAll(description, optionsBox);
    }
}

