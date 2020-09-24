package breakout;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class Button {
    private static final double BUTTON_WIDTH = 100;
    private static final double BUTTON_HEIGHT = 50;
    private static final Color BUTTON_COLOR = Color.BLACK;

    private String myText;
    private FlowPane myFlowPane;
    private Game myGame;

    public Button (String text, FlowPane fp, Game game) {
        myText = text;
        myFlowPane = fp;
        myGame = game;
    }

    public void activateButton() {
        Text restartText = new Text(myText);
        restartText.setFont(new Font("Verdana", 20));
        restartText.setFill(Color.WHITE);
        Rectangle box = new Rectangle(0,0,BUTTON_WIDTH,BUTTON_HEIGHT);
        box.setFill(BUTTON_COLOR);
        StackPane sp = new StackPane();
        sp.getChildren().addAll(box,restartText);
        myFlowPane.getChildren().add(sp);
        reactToClick(myGame, sp);
    }

    public void reactToClick(Game game, StackPane root) {

    }
}
