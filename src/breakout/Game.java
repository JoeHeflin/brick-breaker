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
    public static final int FRAMES_PER_SECOND = 60;// TODO Experiment: Ball freezes at high fps
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
    private static final double INITIAL_PADDLE_SPEED = 15;//2000;//10;//300; //TODO: Find a way to make paddle movement less jittery
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
    //private Group bricks = new Group();

    @Override
    public void start(Stage stage) throws Exception {

        myScene = setUpScene(LEVEL1_LAYOUT);
        myBall.resetBall(myScene);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();


        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    Scene setUpScene (String layoutFileName) throws IOException {
        Group root = new Group();
        //Group bricks = new Group();

//        File localStream = new File(layoutFileName);
//
//        BufferedReader br = new BufferedReader(new FileReader(localStream));
//
//        int row = 0;
//        int col = 0;
//        String line;
//        while ((line = br.readLine()) != null) {
//            String[] brickSymbols = line.split(" ");
//            for (String symbol : brickSymbols) {
//                int x = col % STAGE_WIDTH;// + BRICK_SPACE/2;
//                int y = row;// + BRICK_SPACE/2;
//                if (symbol.compareTo(BLANK_SYMBOL) != 0) {
//                    Brick newBrick = new Brick(x, y, symbol);
//                    newBrick.init();
//                    bricks.getChildren().add(newBrick);
//                }
//                col += BRICK_WIDTH;
//            }
//            row += BRICK_HEIGHT;
//        }

        bricks.init(LEVEL1_LAYOUT); //TODO

        for (Brick[] brickRow : bricks.brickLayout) {
            for (Brick brick : brickRow) {
                if (brick != null) { // TODO
                    root.getChildren().add(brick);
                }
            }
        }


        myBall = new Ball(STAGE_WIDTH/2,STAGE_HEIGHT/2,INITIAL_BALL_SPEED,BALL_RADIUS);
        root.getChildren().add(myBall);

        myPaddle = new Paddle(INITIAL_PADDLE_SPEED);
        root.getChildren().add(myPaddle);

        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
        scene.setOnKeyPressed(e -> myPaddle.handleHorizontalMovement(e.getCode(), SECOND_DELAY)); //TODO
        //myBall.start(INITIAL_LAUNCH_ANGLE);

        //scene.setOnMouseClicked(e -> handleLaunch(e.getX(), e.getY()));
        //System.out.println(root.getChildren());
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


    private void step(double elapsedTime) {
        myBall.detectStageAndPaddle(myPaddle, myScene);
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
}
