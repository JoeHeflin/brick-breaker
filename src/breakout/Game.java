package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Game extends Application {

    public static final String LEVEL1_LAYOUT = "levelFormats/level1.txt";
    public static final String TITLE = "New Game";
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int BRICK_WIDTH = 36;
    public static final int BRICK_HEIGHT = 10;
    public static final int STAGE_WIDTH = 10 * BRICK_WIDTH;
    public static final int STAGE_HEIGHT = 400;
    public static final Paint BRICK_COLOR = Color.RED;
    public static final int BRICK_SPACE = 2;
    public static final int PADDLE_WIDTH = STAGE_WIDTH/6;
    public static final int PADDLE_HEIGHT = 10;
    public static final Color PADDLE_COLOR = Color.BROWN;
    public static final String BLANK_SYMBOL = "0";
    public static final double INITIAL_BALL_SPEED = 15;
    public static final int BALL_RADIUS = 5;
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final double INITIAL_PADDLE_SPEED = 15;
    public static final double INITIAL_LAUNCH_ANGLE = 60;

    //TODO: Level Select class, confirming when blocks are broken / level is beaten -> loading to next level
    //TODO: Restructure level reading to be a matrix that stores Bricks, so we can keep track of them

    private Scene myScene;
    private Ball myBall;
    private Paddle myPaddle;
    private int total = 0;
    private boolean activeRound = false;
    private int paddleSpeed;
    private LevelBuilder bricks = new LevelBuilder();

    public Paddle getPaddle() {
        return myPaddle;
    }

    @Override
    public void start(Stage stage) throws Exception {

        myScene = setUpScene(LEVEL1_LAYOUT);
        reset();
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    public void reset() {
        myBall.stop();
        myBall.setInitialPosition();
        myPaddle.freeze();
        myPaddle.setInitialPosition();
        myScene.setOnMouseClicked(e -> myBall.start(INITIAL_LAUNCH_ANGLE, myPaddle));
    }

//    Overload for testing
    public void reset(Scene scene) {
        myBall.stop();
        myBall.setInitialPosition();
        myPaddle.freeze();
        myPaddle.setInitialPosition();
        scene.setOnMouseClicked(e -> myBall.start(INITIAL_LAUNCH_ANGLE, myPaddle));
    }

    Scene setUpScene (String layoutFileName) throws IOException {
        Group root = new Group();

        bricks.init(layoutFileName);

        for (Brick[] brickRow : bricks.brickLayout) {
            for (Brick brick : brickRow) {
                if (brick != null) { // TODO
                    root.getChildren().add(brick);
                }
            }
        }

        Brick brick1 = bricks.brickLayout[0][0];
        brick1.setId("brick1");
        Brick brick2 = bricks.brickLayout[1][0];
        brick2.setId("brick2");
        Brick brick3 = bricks.brickLayout[0][2];
        brick3.setId("brick3");

        myBall = new Ball(STAGE_WIDTH/2,STAGE_HEIGHT/2,INITIAL_BALL_SPEED,BALL_RADIUS);
        root.getChildren().add(myBall);
        myBall.setId("Ball");

        myPaddle = new Paddle(INITIAL_PADDLE_SPEED);
        root.getChildren().add(myPaddle);
        myPaddle.setId("Paddle");

        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
        scene.setOnKeyPressed(e -> myPaddle.handleHorizontalMovement(e.getCode(), SECOND_DELAY)); //TODO
        return scene;
    }
    /** TODO: Get it working :| */
//    private void handleLaunch (double x, double y) {
//        double angle;
//        double deltaX = x/myBall.getCenterX();
//        double deltaY = y/myBall.getCenterY();
//        if(deltaY < 0){ deltaY = 1; }
//        if(deltaX == 0) {
//            angle = 90;
//
//        }
//        if(deltaX > 0) {
//            angle = Math.toDegrees(Math.atan((y - myBall.getCenterY()) / (x - myBall.getCenterX())));
//        }
//    }
    public void testStep() {
        step(SECOND_DELAY);
    }


    private void step(double elapsedTime) {
        detectStageAndPaddle(myBall);
        myBall.detectBrick(bricks.brickLayout);
        if (myBall.isBallInMotion()) {
            myBall.updatePosition(elapsedTime);
        }
    }

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }

    void detectStageAndPaddle (Ball ball) {
        ball.updateBoundaries();
        if(ball.left < 0){
            ball.bounce(true);
            ball.setCenterX(0 + ball.getRadius());
        }
        if(ball.right > Game.STAGE_WIDTH){
            ball.bounce(true);
            ball.setCenterX(Game.STAGE_WIDTH - ball.getRadius());
        }
        if(ball.up < 0){
            ball.bounce(false);
            ball.setCenterY(0 + ball.getRadius());
        }
        if(ball.down > Game.STAGE_HEIGHT){
            reset();
            //TODO decrement lives
        }
        //Checks if touching paddle and midpoint of the ball isn't beneath the top of the paddle
        if(ball.getBoundsInParent().intersects(myPaddle.getBoundsInParent()) && ball.down > myPaddle.getY()){
            ball.bounce(false);
            ball.setCenterY(myPaddle.getY() - ball.getRadius());
        }
        //updateBoundaries(); TODO: Why necessary?

    }
}
