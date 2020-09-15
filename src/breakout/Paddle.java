package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle extends Rectangle {


    int width;
    int height;
    double speed;

    Paddle(double spd){
        super(Game.STAGE_WIDTH/2, Game.STAGE_HEIGHT - 15, Game.PADDLE_WIDTH, Game.PADDLE_HEIGHT);

        speed = spd;
        this.setFill(Game.PADDLE_COLOR);

    }


//    void handleHorizontalMovement (KeyCode code) {
    void handleHorizontalMovement (KeyCode code, double elapsedTime) {

        switch (code) {
            case LEFT -> {
//                if (getX() > 0) {setX(getX() - (this.speed * elapsedTime));} //ORIGINAL
                if (this.getX() >= 0) {
                    this.setX(this.getX() - speed);// * Game.SECOND_DELAY); //TODO why not multiplied by ellapsed? Probably speed
                }
            }
            case RIGHT -> {
//                if (getX() + getWidth() < Game.STAGE_WIDTH) {setX(getX() + (speed * elapsedTime));} //ORIGINAL
                if (this.getX() + Game.PADDLE_WIDTH <= Game.STAGE_WIDTH) {
                    this.setX(this.getX() + speed);// * Game.SECOND_DELAY);
                }
            }
        }
    }

}
