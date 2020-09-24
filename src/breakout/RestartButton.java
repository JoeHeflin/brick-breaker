package breakout;

import javafx.scene.Group;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Paint;


public class RestartButton extends Button{

    private static final double BUTTON_WIDTH = 100;
    private static final double BUTTON_HEIGHT = 50;
    private static final Color BUTTON_COLOR = Color.BLACK;

    public RestartButton(FlowPane fp, Game game) {
        super("Restart", fp, game);
    }

    @Override
    public void reactToClick(Game game, StackPane sp) {
        sp.setOnMouseClicked(e -> game.restartGame());
    }
}
