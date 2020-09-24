package breakout;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PlayButton extends Button{
    private static final double BUTTON_WIDTH = 100;
    private static final double BUTTON_HEIGHT = 50;
    private static final Color BUTTON_COLOR = Color.BLACK;

    public PlayButton(FlowPane fp, Game game) {
        super("Play", fp, game);
    }

    @Override
    public void reactToClick(Game game, StackPane sp) {
        sp.setOnMouseClicked(e -> game.setUpLevelStage(0));
    }
}
