package breakout;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ball extends Circle{


    private static final Color BALL_COLOR = Color.BLUE;

    double up;
    double down;
    double left;
    double right;
    double speed;
    double xVel;
    double yVel;
    int damage;


    public Ball(double x, double y, double spd, int radius){
        super(x, y, radius);
        this.up = y + radius;
        this.down = y - radius;
        this.left = x - radius;
        this.right = x + radius;
        this.xVel = 0;
        this.yVel = 0;
        this.speed = spd;
        this.damage = 1;
    }

    //Makes the ball start moving // stop moving
    //void startStop(boolean go, double launchAngle){

    void start(double launchAngle) {
        this.xVel = (double) (speed * Math.cos(Math.toRadians(launchAngle)));
        this.yVel = (double) (-1 * speed * Math.sin(Math.toRadians((launchAngle))));
    }
    void stop() {
        this.xVel = 0;
        this.yVel = 0;
    }

    void updateBoundaries(){
        this.up = getCenterY() - getRadius();
        this.down = getCenterY() + getRadius();
        this.left = getCenterX() - getRadius();
        this.right = getCenterX() + getRadius();
    }

    void detectStageAndPaddle(Paddle paddle){
        updateBoundaries();
        if(left < 0){
            bounce(true);
            setCenterX(0 + getRadius());
        }
        if(right > Game.STAGE_WIDTH){
            bounce(true);
            setCenterX(Game.STAGE_WIDTH-getRadius());
        }
        if(up < 0){
            bounce(false);
            setCenterY(0 + getRadius());
        }
        if(down > Game.STAGE_HEIGHT){ //TODO Need to reset ball  position at the top of the paddle
            bounce(false);
            setCenterY(Game.STAGE_HEIGHT - getRadius());
        }
        //Checks if touching paddle and midpoint of the ball isn't beneath the top of the paddle
        if(this.getBoundsInParent().intersects(paddle.getBoundsInParent()) && this.down > paddle.getY()){
            bounce(false);
            this.setCenterY(paddle.getY() - this.getRadius());
        }
        //updateBoundaries(); TODO: Why necessary?

    }

    void detectBrick(Group bricks) {
        for (Node brick : bricks.getChildren()) {
            if (this.getBoundsInParent().intersects(brick.getBoundsInParent())) {
                bounce(false);
                //bounce(true); TODO: decide  how to have ball bounce
                // remove brick
                bricks.getChildren().remove(brick);
                //this.setCenterY(thisY() - this.getRadius());
            }
        }
    }

    void bounce(Boolean xOtherwiseY){
        if(xOtherwiseY){
            this.xVel = -this.xVel;
        }
        else{
            this.yVel = -this.yVel;
        }
    }

    void updatePosition(double elapsedTime) {
        setCenterX(getCenterX() + this.xVel * speed * elapsedTime);
        setCenterY(getCenterY() + this.yVel * speed * elapsedTime);
        System.out.println("Radius: " + getRadius() + " XPos: " + getCenterX() + "xVel: " + xVel + "yPos: " + getCenterY() + "yVel: " + yVel);
    }

}
