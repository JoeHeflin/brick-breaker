package breakout;

import javafx.scene.Scene;

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

    public Detector (Scene scene, LevelBuilder bricks, Ball ball, Paddle paddle, MenuBar menuBar) {
        myBricks = bricks;
        myPaddle = paddle;
        myScene = scene;
        myBall = ball;
        myMenuBar = menuBar;
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
        }
    }

    private void detectPaddle (Ball ball) {
        //Checks if touching paddle and midpoint of the ball isn't beneath the top of the paddle
        if(ball.getBoundsInParent().intersects(myPaddle.getBoundsInParent()) && myBottomBallEdge > myPaddle.getY()){
            ball.bounceY();
            ball.setCenterY(myPaddle.getY() - ball.getRadius());
        }
        //updateBoundaries(); TODO: Why necessary?

    }

    private void detectBrick(LevelBuilder bricks, Ball ball, MenuBar menuBar) { //TODO: bounce off edges of brick
        for (Brick[] brickCol : bricks.getBrickLayout()) {
            for (Brick brick : brickCol) {
                if (brick != null && ball.getBoundsInParent().intersects(brick.getBoundsInParent())) { //TODO Nested if
                    if (brick.checkIfAlive()) {
                        brick.takeDamage(ball, menuBar, bricks);
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
        else {
            ball.bounceX();
        }
    }

    public void reset(Scene scene) {//Ball ball) {
        myBall.stop();
        myBall.setInitialPosition();
        myPaddle.freeze();
        myPaddle.setInitialPosition();
        myMenuBar.loseLife();
        scene.setOnMouseClicked(e -> myBall.start(Game.INITIAL_LAUNCH_ANGLE, myPaddle));
    }
}
