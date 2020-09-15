package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle extends Rectangle {

    double x;
    double y;
    int width;
    int height;
    double speed;

    Paddle(double spd){
        super(Game.STAGE_WIDTH/2, Game.STAGE_HEIGHT - 15, Game.PADDLE_WIDTH, Game.PADDLE_HEIGHT);
        this.x = super.getX();
        this.y = super.getY();
        width = Game.PADDLE_WIDTH;
        height = Game.PADDLE_HEIGHT;
        speed = spd;
        this.setFill(Game.PADDLE_COLOR);

    }


    void handleHorizontalMovement (KeyCode code) {
        switch (code) {
            case LEFT -> {
                if(x > 0){x = x - speed;}
            }
            case RIGHT -> {
                if(x + width < Game.STAGE_WIDTH){x = x + speed;}
            }
        }
    }

}
