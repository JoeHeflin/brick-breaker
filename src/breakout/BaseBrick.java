package breakout;

import javafx.scene.paint.Color;

public class BaseBrick extends Brick {

    BaseBrick(double x, double y) {
        super(x, y);
        color = Color.RED;
        health = 1;
    }
}
