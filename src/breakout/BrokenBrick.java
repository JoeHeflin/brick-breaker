package breakout;

import javafx.scene.paint.Color;

import java.util.logging.Level;

public class BrokenBrick extends Brick{
    BrokenBrick(double x, double y) {
        super(x, y);
        health = 0;
        color = Game.BACKGROUND_COLOR;
    }

    @Override
    void takeDamage(Ball ball, MenuBar menuBar, LevelBuilder layout) {

    }


}
