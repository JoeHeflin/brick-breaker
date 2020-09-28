package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
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
    public static final String TITLE = "New Game";
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int BRICK_WIDTH = 36;
    public static final int BRICK_HEIGHT = 20;
    public static final int STAGE_WIDTH = 10 * BRICK_WIDTH;
    public static final int STAGE_HEIGHT = 400;
    public static final int MENU_NUMBER_OF_ROWS = 1;
    public static final int MENU_BAR_HEIGHT = MENU_NUMBER_OF_ROWS * BRICK_HEIGHT;
    public static final int PADDLE_WIDTH = STAGE_WIDTH / 6;
    public static final int PADDLE_HEIGHT = 10;
    public static final Color PADDLE_COLOR = Color.BROWN;
    public static final double INITIAL_BALL_SPEED = 150;
    public static final int BALL_RADIUS = 5;
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final double INITIAL_PADDLE_SPEED = 15;
    public static final double INITIAL_LAUNCH_ANGLE = 60;
    public static final int INITIAL_LIVES_COUNT = 2;
    private static final String LOSER_MESSAGE = "YOU\nLOSE";
    private static final String WINNER_MESSAGE = "YOU\nWIN";
    private static final List<String> LEVELS = new ArrayList(Arrays.asList("level1.txt", "level2.txt"));
    private static final String LEVELS_DIR = "levelFormats/";
    private static final String GAME_TITLE = "BRICK\nSLAYER";
    private static final int LEVEL_BUTTON_SIZE = 20;
    private static final int GAME_TITLE_SIZE = 80;


    //TODO: Level Select class, confirming when blocks are broken / level is beaten -> loading to next level
    //TODO: Restructure level reading to be a matrix that stores Bricks, so we can keep track of them

    private Scene myScene;
    private Ball myBall;
    private Paddle myPaddle;
    private int total = 0;
    private boolean activeRound = false;
    private int paddleSpeed;
    private LevelBuilder myBricks;
    private Detector myDetector;
    private MenuBar myMenuBar;
    private Stage myStage;
    private boolean myLevelActive;
    private int myCurrentLevel = 0;

    @Override
    public void start(Stage stage) throws Exception {
        myStage = stage;
        myScene = setUpIntroScreen();
        myStage.setScene(myScene);
        myStage.show();
    }

    private Scene setUpIntroScreen() {
        FlowPane root = new FlowPane();
        addDisplayText(root, GAME_TITLE, GAME_TITLE_SIZE);
        root.setAlignment(Pos.CENTER);
        root.setVgap(30);
        Button playButton = new PlayButton(root, this);
        playButton.activateButton();
        for (int level = 1; level <= LEVELS.size(); level++) {
            Button levelButton = new LevelButton(level, level * LEVEL_BUTTON_SIZE, root, this);
            levelButton.activateButton();
        }
        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
        return scene;
    }

    public void startGamePlay() {
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

    public void setUpLevelStage(int level) {
        myScene = setUpLevelScene(level);
        myDetector.reset(myScene);
        myStage.setScene(myScene);
        myStage.setTitle(TITLE);
        myStage.show();
        startGamePlay();
    }

    public void reset(Scene scene) {
        myBall.stop();
        myBall.setInitialPosition();
        myPaddle.freeze();
        myPaddle.setInitialPosition();
        scene.setOnMouseClicked(e -> myBall.start(INITIAL_LAUNCH_ANGLE, myPaddle));
    }

    public LevelBuilder buildBrick(String filePath) {
        return new LevelBuilder(filePath);
    }

    public Scene setUpLevelScene(int level) {
        myCurrentLevel = level;
        Group root = new Group();
        myBricks = buildBrick(LEVELS_DIR + LEVELS.get(level));

        try {
            myBricks.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setBrickIds();

        displayLevelFeatures(root);

        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
        myDetector = new Detector(scene, myBricks, myBall, myPaddle, myMenuBar);
        scene.setOnKeyPressed(e -> myPaddle.handleHorizontalMovement(e.getCode(), SECOND_DELAY)); //TODO
        return scene;
    }

    public void setBrickIds() {
    }

    public void displayLevelFeatures(Group root) {
        for (Brick[] brickRow : myBricks.getBrickLayout()) {
            for (Brick brick : brickRow) {
                if (brick != null) { // TODO
                    root.getChildren().add(brick);
                }
            }
        }

        myBall = new Ball(STAGE_WIDTH / 2, STAGE_HEIGHT / 2, INITIAL_BALL_SPEED, BALL_RADIUS);
        root.getChildren().add(myBall);
        myBall.setId("Ball");

        myPaddle = new Paddle(INITIAL_PADDLE_SPEED);
        root.getChildren().add(myPaddle);
        myPaddle.setId("Paddle");

        myMenuBar = new MenuBar();
        root.getChildren().add(myMenuBar.getMenuBar());
        myMenuBar.init();
    }

    public LevelBuilder getBricks() {
        return myBricks;
    }

    /**
     * TODO: Get it working :|
     */
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
            myDetector.detectCollisions(myBricks, myBall, myMenuBar);
            myMenuBar.updateText();
            if (myBall.isBallInMotion()) {
                myBall.updatePosition(elapsedTime);
            }
            if (myBricks.noMoreBricks()) {
                nextLevel();
            }
            if (myMenuBar.noMoreLives()) {
                gameOver(LOSER_MESSAGE);
            }
        }
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void nextLevel() throws IOException {
        if (myCurrentLevel == LEVELS.size() - 1) {
            gameOver(WINNER_MESSAGE); // TODO
        } else {
            myCurrentLevel++;
            myBricks = new LevelBuilder(LEVELS_DIR + LEVELS.get(myCurrentLevel));
            setUpLevelStage(myCurrentLevel);
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

    public Ball getMyBall() {
        return myBall;
    }

    public MenuBar getMyMenuBar() {
        return myMenuBar;
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
    public static void main(String[] args) {
        launch(args);
    }

    public Scene getMyScene() {
        return myScene;
    }

    public Detector getMyDetector() {
        return myDetector;
    }
}