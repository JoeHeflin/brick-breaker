package breakout;

import javafx.scene.Scene;

import java.util.ArrayList;

public class Detector {

    private LevelBuilder myBricks;
    private Paddle myPaddle;
    private double myBottomBallEdge;
    private double myTopBallEdge;
    private double myLeftBallEdge;
    private double myRightBallEdge;
    private Scene myScene;
    private Ball myBall;
    private int lives = Game.INITIAL_LIVES_COUNT + 1;
    private MenuBar myMenuBar;
    private PowerUpHolder myPowerUps;

    public Detector (Scene scene, LevelBuilder bricks, Ball ball, Paddle paddle, MenuBar menuBar, PowerUpHolder powerUps) {
        myBricks = bricks;
        myPaddle = paddle;
        myScene = scene;
        myBall = ball;
        myMenuBar = menuBar;
        myPowerUps = powerUps;
    }

    void updateBoundaries(Ball ball){
        myTopBallEdge = ball.getCenterY() - ball.getRadius();
        myBottomBallEdge = ball.getCenterY() + ball.getRadius();
        myLeftBallEdge = ball.getCenterX() - ball.getRadius();
        myRightBallEdge = ball.getCenterX() + ball.getRadius();
    }

    public void detectCollisions(LevelBuilder bricks, Ball ball, MenuBar menuBar) {
        detectStage(ball);
        detectPaddle(ball);
        detectBrick(bricks, ball, menuBar);
        detectPowerUps(myPowerUps, ball, myPaddle);
    }

    private void detectStage (Ball ball) {
        updateBoundaries(ball);
        if (myLeftBallEdge < 0) {
            ball.bounceX();
            ball.setCenterX(0 + ball.getRadius());
        }
        else if (myRightBallEdge > Game.STAGE_WIDTH) {
            ball.bounceX();
            ball.setCenterX(Game.STAGE_WIDTH - ball.getRadius());
        }
        else if (myTopBallEdge < Game.MENU_BAR_HEIGHT) {
            ball.bounceY();
            ball.setCenterY(Game.MENU_BAR_HEIGHT + ball.getRadius());
        }
        else if (myBottomBallEdge > Game.STAGE_HEIGHT) {
            reset(myScene);
            myMenuBar.loseLife();
        }
    }

    private void detectPaddle (Ball ball) {
        //Checks if touching paddle and midpoint of the ball isn't beneath the top of the paddle
        if(ball.getBoundsInParent().intersects(myPaddle.getBoundsInParent()) && myBottomBallEdge > myPaddle.getY()){
            paddleBounce(ball, myPaddle);
        }

    }

    private void detectBrick(LevelBuilder bricks, Ball ball, MenuBar menuBar) { //TODO: bounce off edges of brick
        for (Brick[] brickCol : bricks.getBrickLayout()) {
            for (Brick brick : brickCol) {
                if (brick != null && ball.getBoundsInParent().intersects(brick.getBoundsInParent())) { //TODO Nested if
                    if (brick.checkIfAlive()) {
                        brick.takeDamage(ball, menuBar, bricks, myPowerUps);
                        bounce(ball, brick);
                    }
                }
            }
        }
    }



    private void bounce(Ball ball, Brick brick) {
        if (ball.getCenterX() < brick.rightEdge() && ball.getCenterX() > brick.leftEdge()) {
            ball.bounceY();
        }
        if (ball.getCenterY() < (brick.getY() + brick.getHeight()) && ball.getCenterY() > brick.getY()) {
            ball.bounceX();
        }
    }

    private void paddleBounce(Ball ball, Paddle paddle){
        double multiplier = 0.5;
        double paddleCenter = paddle.getWidth()/2;
        double distFromCenter = Math.abs(ball.getCenterX() - (paddle.getX() + paddleCenter));
//        multiplier = multiplier + ((distFromCenter/paddleCenter)*0.2);
        multiplier = multiplier*distFromCenter;
        ball.setXVel(ball.getXVel() * multiplier);
        ball.bounceY();
        ball.setCenterY(paddle.getY() - ball.getRadius() - 1);
    }

    public void detectPowerUps(PowerUpHolder powerUps, Ball ball, Paddle paddle){
        for(PowerUp p : powerUps.getActivePowerUps()){
            if(paddle.getBoundsInParent().intersects(p.getBoundsInParent())){
                p.usePower(ball, paddle);
            }
            if(p.getCenterY() > Game.STAGE_HEIGHT - 5){
                p.setInactive();
            }
        }
    }

    public double getLaunchAngle(double x, double y) {
        double angle = 90;
        double deltaX = x - myBall.getCenterX();
        double deltaY = y - myBall.getCenterY();
        angle = Math.toDegrees(Math.atan((deltaY) / (deltaX)));

        if(deltaX < 0){
            angle = 180 - angle;
        }
        return angle;
    }

    public void reset(Scene scene) {
        myBall.stop();
        myBall.setInitialPosition();
        myPaddle.freeze();
        myPaddle.setInitialPosition();
        myPaddle.setInitialSize();

        if (!myBall.isBallInMotion()){
            scene.setOnMouseClicked(e -> myBall.start(getLaunchAngle(e.getX(), e.getY()), myPaddle));
        }
    }
}
