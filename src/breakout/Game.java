package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public static final double INITIAL_BALL_SPEED = 150;
    public static final int BALL_RADIUS = 5;
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final double INITIAL_PADDLE_SPEED = 15;
    public static final double INITIAL_LAUNCH_ANGLE = 60;
    public static final int INITIAL_LIVES_COUNT = 1;
    private static final int PLAY_AGAIN_LENGTH = 10;
    private static final int PLAY_AGAIN_HEIGHT = 10;
    private static final String LOSER_MESSAGE = "YOU\nLOSE";
    private static final String WINNER_MESSAGE = "YOU\nWIN";
    private static final String LEVEL2_LAYOUT = "levelFormats/level2.txt";
    private static final List<String> LEVELS = new ArrayList(Arrays.asList("level1.txt","level2.txt"));
    private static final String LEVELS_DIR = "levelFormats/";
    private static final String GAME_TITLE = "BRICK\nSLAYER";


    //TODO: Level Select class, confirming when blocks are broken / level is beaten -> loading to next level
    //TODO: Restructure level reading to be a matrix that stores Bricks, so we can keep track of them

    private Scene myScene;
    private Ball myBall;
    private Paddle myPaddle;
    private int total = 0;
    private boolean activeRound = false;
    private int paddleSpeed;
    private LevelBuilder bricks;
    private Detector myDetector;
    private Text myLivesCountText;
    private int myLivesCount;
    private MenuBar myMenuBar;
    private Stage myStage;
    private boolean myLevelActive;
    private int myCurrentLevel = 0;

    public Paddle getPaddle() {
        return myPaddle;
    }

    @Override
    public void start(Stage stage) throws Exception {
        myStage = stage;
//        bricks = new LevelBuilder(LEVELS_DIR + LEVELS.get(myCurrentLevel));
        myScene = setUpIntroScreen();
        myStage.setScene(myScene);
        myStage.show();
//        myScene = setUpLevelScene();
//        setUpLevelStage();
//        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> {
//            try {
//                step(SECOND_DELAY);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        });
//        Timeline animation = new Timeline();
//        animation.setCycleCount(Timeline.INDEFINITE);
//        animation.getKeyFrames().add(frame);
//        myLevelActive = true;
//        animation.play();
    }

    public void startGamePlay(){
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> {
            try {
                step(SECOND_DELAY);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        myLevelActive = true;
        animation.play();
    }

    private Scene setUpIntroScreen() {
        FlowPane root = new FlowPane();
        addDisplayText(root, GAME_TITLE, 80);
        root.setAlignment(Pos.CENTER);
        root.setVgap(30);
        Button playButton = new PlayButton(root, this);
        playButton.activateButton();
//        for (int level = 1; level < LEVELS.size(); level++) {
//            LevelButton levelButton = new LevelButton(level, level * LEVEL_BUTTON_SIZE);
//        }
        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
        return scene;
    }

//    private Scene setUpIntroScreen() {
//        Group root = new Group();
//        addDisplayText("Brick Slayer", GAME_TITLE_SIZE);
//        Button playButton = new PlayButton();
//        for (int level = 1; level < LEVELS.size(); level++) {
//            Button levelButton = new LevelButton(level, level * LEVEL_BUTTON_SIZE);
//        }
//    }

    public void setUpLevelStage() {
        myScene = setUpLevelScene();
        myDetector.reset(myScene);
        myStage.setScene(myScene);
        myStage.setTitle(TITLE);
        myStage.show();
        startGamePlay();
    }

//    Overload for testing
    public void reset(Scene scene) {
        myBall.stop();
        myBall.setInitialPosition();
        myPaddle.freeze();
        myPaddle.setInitialPosition();
        scene.setOnMouseClicked(e -> myBall.start(INITIAL_LAUNCH_ANGLE, myPaddle));
    }

    Scene setUpLevelScene () {
        Group root = new Group();

        bricks = new LevelBuilder(LEVELS_DIR + LEVELS.get(myCurrentLevel));
        try {
            bricks.init();
        } catch (Exception e) {e.printStackTrace();}

        for (Brick[] brickRow : bricks.getBrickLayout()) {
            for (Brick brick : brickRow) {
                if (brick != null) { // TODO
                    root.getChildren().add(brick);
                }
            }
        }

//        setBrickIds();

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
        Brick brick1 = bricks.getBrickLayout()[0][0];
        brick1.setId("brick1");
        Brick brick2 = bricks.getBrickLayout()[1][0];
        brick2.setId("brick2");
        Brick brick3 = bricks.getBrickLayout()[0][2];
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

    public void testStep() throws IOException {
        step(SECOND_DELAY);
    }

    private void step(double elapsedTime) throws IOException {
        //if (myDetector.getLives() > 0) {
        if (myLevelActive) {
            myDetector.detectCollisions(bricks, myBall, myMenuBar);
            myMenuBar.updateText();
            if (myBall.isBallInMotion()) {
                myBall.updatePosition(elapsedTime);
            }
            if (bricks.noMoreBricks()) {
                nextLevel();
            }
            if (myMenuBar.noMoreLives()) {
                gameOver(LOSER_MESSAGE);
            }
        }
    }

    private void nextLevel() throws IOException {
        if (myCurrentLevel == LEVELS.size()-1) {
            gameOver(WINNER_MESSAGE); // TODO
        }
        else {
            myCurrentLevel ++;
            bricks = new LevelBuilder(LEVELS_DIR + LEVELS.get(myCurrentLevel));
            setUpLevelStage();
        }
    }

    private void addDisplayText(FlowPane root, String text, int fontSize) {
        Text gameOverText = new Text(text);
        gameOverText.setFont(new Font("Verdana", fontSize));
        gameOverText.setFill(Color.BLACK);
        gameOverText.setTextAlignment(TextAlignment.CENTER);
        StackPane sp = new StackPane();
        sp.getChildren().add(gameOverText);
        root.getChildren().add(sp);
    }

    private void gameOver(String message) {
        FlowPane root = new FlowPane();
        addDisplayText(root, message, 100);
        root.setAlignment(Pos.CENTER);
        root.setVgap(30);

        Button restartButton = new RestartButton(root, this);
        restartButton.activateButton();

        Scene gameOverScene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
        myLevelActive = false;
        myStage.setScene(gameOverScene);
    }

    public void restartGame() {
        try {
            start(myStage);
        } catch (Exception e) {e.printStackTrace();}
    }

/** TODO: Robert - Rewrite PowerUp class (and its extensions), make powerups functional
    public void powerUpChance(double x, double y) {
        double spawnChance = Math.random() * 15;
        Math.floor(spawnChance);
        if (spawnChance == 1) {
            PowerUp power = new SlowMotion(x, y);
        }
        else if (spawnChance == 2){
            PowerUp power = new GrowPaddle (x, y);
        }
    }
 */

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}