package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle extends Rectangle {

    double x;
    double y;
    int sizeX;
    int sizeY;
    Color color;

    Paddle(){
        super(Game.STAGE_WIDTH/2, Game.STAGE_HEIGHT + 5, Game.PADDLE_WIDTH, Game.PADDLE_HEIGHT);
        this.x = super.getX();
    }

}
