package breakout;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

public class RestartButton extends Button{

    public RestartButton(FlowPane fp, Game game) {
        super("Restart", fp, game);
    }

    @Override
    public void reactToClick(Game game, StackPane sp) {
        sp.setOnMouseClicked(e -> game.restartGame());
    }
}
