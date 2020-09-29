package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
    public static final int POWERUP_RADIUS = 7;
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final double INITIAL_PADDLE_SPEED = 900;
    public static final int INITIAL_LIVES_COUNT = 2;
    private static final String LOSER_MESSAGE = "YOU\nLOSE";
    private static final String WINNER_MESSAGE = "YOU\nWIN";
    private static final List<String> LEVELS = new ArrayList(Arrays.asList("level1.txt", "level2.txt"));
    private static final String LEVELS_DIR = "levelFormats/";
    private static final String GAME_TITLE = "BRICK\nSLAYER";
    private static final int LEVEL_BUTTON_SIZE = 20;
    private static final int GAME_TITLE_SIZE = 80;

    private Scene myScene;
    private Ball myBall;
    private Paddle myPaddle;
    private LevelBuilder myBricks;
    private Detector myDetector;
    private Group myRoot;
    private MenuBar myMenuBar;
    private Stage myStage;
    private PowerUpHolder myPowerUps;
    private boolean myLevelActive;
    private int myCurrentLevel = 0;
    private ArrayList<PowerUp> activePowerUps = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        startAgain(stage);
        startGamePlay();
    }

    public void startAgain(Stage stage) throws Exception {
        myStage = stage;
        myScene = setUpIntroScreen();
        myStage.setScene(myScene);
        myStage.show();
    }

    public void testStartAgain(Stage stage) throws Exception {
        myStage = stage;
        myScene = setUpLevelScene(1);
        myStage.setScene(myScene);
        myStage.show();
    }



    public void startGamePlay() {
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> { step(); });
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
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

    private void handleKeyInput(KeyCode code) {
        switch (code) {
            case P -> pause();
            case N -> nextLevel();
            case LEFT, RIGHT -> myPaddle.handleHorizontalMovement(code, SECOND_DELAY);
        }
    }

    public void pause() {
        myLevelActive = !myLevelActive;
    }

    public void setUpLevelStage(int level) {
        myScene = setUpLevelScene(level);
//        myLevelActive = true;
        myDetector.reset(myScene);
        myStage.setScene(myScene);
        myStage.setTitle(TITLE);
        myStage.show();
        myPowerUps.reset();
    }

//    public void reset(Scene scene) {
//        myBall.stop();
//        myBall.setInitialPosition();
//        myPaddle.freeze();
//        myPaddle.setInitialPosition();
//        scene.setOnMouseClicked(e -> myBall.start(INITIAL_LAUNCH_ANGLE, myPaddle));
//    }

    public LevelBuilder buildBrick(String filePath) {
        return new LevelBuilder(filePath);
    }

    public Scene setUpLevelScene(int level) {
        myCurrentLevel = level;
        myLevelActive = true;
        Group root = new Group();
        myBricks = buildBrick(LEVELS_DIR + LEVELS.get(level));
        myPowerUps = new PowerUpHolder(root);

        try {
            myBricks.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setBrickIds();

        displayLevelFeatures(root);

        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
        myDetector = new Detector(scene, myBricks, myBall, myPaddle, myMenuBar,myPowerUps);
//        scene.setOnKeyPressed(e -> myPaddle.handleHorizontalMovement(e.getCode(), SECOND_DELAY)); //TODO
        myRoot = root;
        scene.setOnKeyPressed(e -> myPaddle.handleHorizontalMovement(e.getCode(), SECOND_DELAY)); //TODO
        return scene;
    }

    public void setBrickIds() {
    }

    public Scene getMyScene() {
        return myScene;
    }

    public Detector getMyDetector() {
        return myDetector;
    }

    public Group getMyRoot(){
        return myRoot;
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
        step();
    }

    private void step() {
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        if (myLevelActive) {
            testBallSpeed = myBall.getXVel();
            myDetector.detectCollisions(myBricks, myBall, myMenuBar);
            myPowerUps.checkPowerUps();
            myMenuBar.updateText();
            if (myBall.isBallInMotion()) {
                myBall.updatePosition(Game.SECOND_DELAY);
            }
            if(myPowerUps.getActivePowerUps().size() > 0){
                myPowerUps.movePowerUps(Game.SECOND_DELAY);
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
            startAgain(myStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void nextLevel(){
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

    /**
     * Start of the program.
     */
    public static void main(String[] args) {
        launch(args);
    }

}