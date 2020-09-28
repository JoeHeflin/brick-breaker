package breakout;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle{

    private static final Color BALL_COLOR = Color.BLUE;
    private boolean ballInMotion;
    private double up;
    private double down;
    private double left;
    private double right;
    private double speed;
    private double xVel;
    private double yVel;
    private int damage;

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

    void start(double launchAngle, Paddle paddle) {
        ballInMotion = true;
        this.xVel = (double) (speed * Math.cos(Math.toRadians(launchAngle)));
        this.yVel = (double) (-1 * speed * Math.sin(Math.toRadians((launchAngle))));
        paddle.unfreeze();
    }
    void stop() {
        ballInMotion = false;
        this.xVel = 0;
        this.yVel = 0;
    }

//    void updateBoundaries(){
//        this.up = getCenterY() - getRadius();
//        this.down = getCenterY() + getRadius();
//        this.left = getCenterX() - getRadius();
//        this.right = getCenterX() + getRadius();
//    }

//    public boolean getUp

//    void detectStageAndPaddle (Paddle paddle, Scene scene) {
//        updateBoundaries();
//        if(left < 0){
//            bounce(true);
//            setCenterX(0 + getRadius());
//        }
//        if(right > Game.STAGE_WIDTH){
//            bounce(true);
//            setCenterX(Game.STAGE_WIDTH-getRadius());
//        }
//        if(up < 0){
//            bounce(false);
//            setCenterY(0 + getRadius());
//        }
//        if(down > Game.STAGE_HEIGHT){
//            resetBall(scene, paddle);
//            //TODO decrement lives
//        }
//        //Checks if touching paddle and midpoint of the ball isn't beneath the top of the paddle
//        if(this.getBoundsInParent().intersects(paddle.getBoundsInParent()) && this.down > paddle.getY()){
//            bounce(false);
//            this.setCenterY(paddle.getY() - this.getRadius());
//        }
//        //updateBoundaries(); TODO: Why necessary?
//
//    }

    void bounceX() {
        this.xVel = -this.xVel;
    }

    void bounceY() {
        this.yVel = -this.yVel;
    }

    void updatePosition(double elapsedTime) {
        setCenterX(getCenterX() + this.xVel * elapsedTime);
        setCenterY(getCenterY() + this.yVel * elapsedTime);
        //System.out.println("Radius: " + getRadius() + " XPos: " + getCenterX() + "xVel: " + xVel + "yPos: " + getCenterY() + "yVel: " + yVel);
    }

    public void setInitialPosition() {
        setCenterX(Game.STAGE_WIDTH/2);
        setCenterY(Game.STAGE_HEIGHT - Game.PADDLE_HEIGHT - Game.BALL_RADIUS);
    }

    public boolean isBallInMotion() { return ballInMotion;}

    public double getXVel() {
        return xVel;
    }

    public double getYVel() {
        return yVel;
    }

    public int getDamage() {
        return damage;
    }

}
