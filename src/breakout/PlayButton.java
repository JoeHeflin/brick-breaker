package breakout;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

public class PlayButton extends Button{

    public PlayButton(FlowPane fp, Game game) {
        super("Play", fp, game);
    }

    @Override
    public void reactToClick(Game game, StackPane sp) {
        sp.setOnMouseClicked(e -> game.setUpLevelStage(0));
    }
}
