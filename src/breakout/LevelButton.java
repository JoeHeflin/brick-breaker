package breakout;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

public class LevelButton extends Button {

    private int mySize;
    private int myLevel;

    public LevelButton(int level, int buttonSize, FlowPane fp, Game game) {
        super("Level " + level, fp, game);
        myLevel = level - 1;
    }

    @Override
    public void reactToClick(Game game, StackPane sp) {
        sp.setOnMouseClicked(e -> game.setUpLevelStage(myLevel));
    }
}
