package breakout;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle extends Rectangle {

    double speed;
    boolean ableToMove = true;

    Paddle(double spd){
        super(Game.STAGE_WIDTH/2 - Game.PADDLE_WIDTH/2, Game.STAGE_HEIGHT - Game.PADDLE_HEIGHT, Game.PADDLE_WIDTH, Game.PADDLE_HEIGHT);
        speed = spd;
        this.setFill(Game.PADDLE_COLOR);
    }

    public void setInitialPosition() {
        this.setX(Game.STAGE_WIDTH/2 - Game.PADDLE_WIDTH/2);
        this.setY(Game.STAGE_HEIGHT - Game.PADDLE_HEIGHT);
    }

    public void setInitialSize(){
        this.setWidth(Game.PADDLE_WIDTH);
    }

//    void handleHorizontalMovement (KeyCode code) {
    void handleHorizontalMovement (KeyCode code, double elapsedTime) {
        if (ableToMove) {
            switch (code) {
                case LEFT -> {
                    if (this.getX() >= 0) {
                        this.setX(this.getX() - (speed*elapsedTime));
                    }
                    if(this.getX() < 0){
                        this.setX(0.1);
                    }
                }
                case RIGHT -> {
                    if (this.getX() + Game.PADDLE_WIDTH <= Game.STAGE_WIDTH) {
                        this.setX(this.getX() + (speed*elapsedTime));
                    }
                    if(this.getX() + this.getWidth() > Game.STAGE_WIDTH){
                        this.setX(Game.STAGE_WIDTH - this.getWidth() - 0.1);
                    }
                }
            }
        }
    }

    public void freeze() {
        ableToMove = false;
    }

    public void unfreeze() {
        ableToMove = true;
    }

}
