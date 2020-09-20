package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.util.Duration;

//import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Game extends Application {
    //TODO: place the constants in appropriate classes
    public static final String LEVEL1_LAYOUT = "levelFormats/level1.txt";
    public static final String TITLE = "New Game";
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int BRICK_WIDTH = 36;
    public static final int BRICK_HEIGHT = 20;
    public static final int STAGE_WIDTH = 10 * BRICK_WIDTH;
    public static final int STAGE_HEIGHT = 400;
    public static final int MENU_NUMBER_OF_ROWS = 1;
    public static final int MENU_BAR_HEIGHT = MENU_NUMBER_OF_ROWS * BRICK_HEIGHT;
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
    public static final int INITIAL_LIVES_COUNT = 3;
    private static final int PLAY_AGAIN_LENGTH = 10;
    private static final int PLAY_AGAIN_HEIGHT = 10;
    private static final double PLAY_AGAIN_LEFT = STAGE_WIDTH/3;
    private static final double PLAY_AGAIN_RIGHT = STAGE_WIDTH*2/3;
    private static final double PLAY_AGAIN_BOTTOM = STAGE_HEIGHT - 50;
    private static final double PLAY_AGAIN_TOP = PLAY_AGAIN_BOTTOM - 50;


    //TODO: Level Select class, confirming when blocks are broken / level is beaten -> loading to next level
    //TODO: Restructure level reading to be a matrix that stores Bricks, so we can keep track of them

    private Scene myScene;
    private Ball myBall;
    private Paddle myPaddle;
    private int total = 0;
    private boolean activeRound = false;
    private int paddleSpeed;
    private LevelBuilder bricks = new LevelBuilder();
    private Detector myDetector;
    private Text myLivesCountText;
    private int myLivesCount;
    private MenuBar myMenuBar;
    private Stage myStage;
    private boolean myLevelActive;

    public Paddle getPaddle() {
        return myPaddle;
    }

    @Override
    public void start(Stage stage) throws Exception {

        myStage = stage;
        myScene = setUpScene(LEVEL1_LAYOUT);
        myDetector.reset(myScene);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        myLevelActive = true;
        animation.play();
    }

//    public void reset(Ball ball, Paddle paddle) {
//        ball.stop();
//        ball.setInitialPosition();
//        paddle.freeze();
//        paddle.setInitialPosition();
//        myScene.setOnMouseClicked(e -> ball.start(INITIAL_LAUNCH_ANGLE, paddle));
//    }

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

        //setBrickIds();

        myBall = new Ball(STAGE_WIDTH/2,STAGE_HEIGHT/2,INITIAL_BALL_SPEED,BALL_RADIUS);
        root.getChildren().add(myBall);
        myBall.setId("Ball");

        myPaddle = new Paddle(INITIAL_PADDLE_SPEED);
        root.getChildren().add(myPaddle);
        myPaddle.setId("Paddle");

        myMenuBar = new MenuBar();
        root.getChildren().add(myMenuBar.getMenuBar());
        myMenuBar.init();

        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
        myDetector = new Detector(scene, bricks, myBall, myPaddle, myMenuBar);
        scene.setOnKeyPressed(e -> myPaddle.handleHorizontalMovement(e.getCode(), SECOND_DELAY)); //TODO
        return scene;
    }

    private void setBrickIds() {
        Brick brick1 = bricks.brickLayout[0][0];
        brick1.setId("brick1");
        Brick brick2 = bricks.brickLayout[1][0];
        brick2.setId("brick2");
        Brick brick3 = bricks.brickLayout[0][2];
        brick3.setId("brick3");
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
        //if (myDetector.getLives() > 0) {
        if (myLevelActive) {
            detectCollisions(myBall, myMenuBar);
            myMenuBar.updateText();
            //myLivesCountText.setText(Integer.toString(myMenuBar.getLives()));
            if (myBall.isBallInMotion()) {
                myBall.updatePosition(elapsedTime);
            }
            if (bricks.noMoreBricks()) {
                nextLevel();
            }
            if (myMenuBar.noMoreLives()) {
                gameOver();
            }
        }
    }

    private void nextLevel() {

    }

    private void gameOver() {
        Group root = new Group();
        Text gameOverText = new Text(10, STAGE_HEIGHT/3, "GAME\nOVER");
        gameOverText.setFont(new Font("Verdana", 100));
       // Rectangle buttonGraphic = new Rectangle(PLAY_AGAIN_LEFT, PLAY_AGAIN_TOP,
//                PLAY_AGAIN_RIGHT - PLAY_AGAIN_LEFT, PLAY_AGAIN_BOTTOM - PLAY_AGAIN_TOP);
//        Text buttonText = new Text(buttonGraphic.get);
        //buttonGraphic.setFill(Color.GREEN);
        Text restartText = new Text(PLAY_AGAIN_LEFT + 8, PLAY_AGAIN_BOTTOM - 15, "Restart");
        restartText.setFont(new Font("Verdana", 30));
       // root.getChildren().add(buttonGraphic);
        root.getChildren().add(restartText);
//        buttonGraphic.setOnMouseClicked(e -> restartGame());
        restartText.setOnMouseClicked(e -> restartGame());

//        Button playAgainButton = new Button("Play Again", buttonGraphic);
//        myScene.setOnMouseClicked(e -> {
//            try {
//                handlePlayAgainClick(e.getX(), e.getY());
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        });
        gameOverText.setFill(Color.BLACK);
//        root.getChildren().add(playAgainButton);
        root.getChildren().add(gameOverText);
        Scene gameOverScene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
        myLevelActive = false;
        myStage.setScene(gameOverScene);
    }

    private void restartGame() {
        try {
            start(myStage);
        } catch (Exception e) {e.printStackTrace();}
    }


    private void handlePlayAgainClick(double x, double y) throws Exception {
        if ((x > PLAY_AGAIN_LEFT && x < PLAY_AGAIN_RIGHT) || (y < PLAY_AGAIN_BOTTOM && y > PLAY_AGAIN_TOP)) {
            start(myStage);
        }
    }

    private void detectCollisions(Ball ball, MenuBar menuBar) {
        myDetector.detectStage(ball);
        myDetector.detectPaddle(ball);
        myDetector.detectBrick(bricks, ball, menuBar);
    }

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
