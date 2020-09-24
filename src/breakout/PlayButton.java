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
    public void reactToClick(Game game, FlowPane fp) {
        fp.setOnMouseClicked(e -> game.setUpLevelStage());
    }
//    public void activateButton(Game game, FlowPane root) {
//        Text restartText = new Text(myText);
//        restartText.setFont(new Font("Verdana", 20));
//        restartText.setFill(Color.WHITE);
//        Rectangle box = new Rectangle(0,0,BUTTON_WIDTH,BUTTON_HEIGHT);
//        box.setFill(BUTTON_COLOR);
//        StackPane sp = new StackPane();
//        sp.getChildren().addAll(box,restartText);
//        root.getChildren().add(sp);
//        sp.setOnMouseClicked(e -> game.setUpLevelStage());
//    }
}
