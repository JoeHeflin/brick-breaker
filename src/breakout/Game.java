package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
    public static final int PADDLE_WIDTH = STAGE_WIDTH/4;
    public static final int PADDLE_HEIGHT = 10;
    public static final Color PADDLE_COLOR = Color.BEIGE;

    private Scene myScene;
    private Ball myBall;
//    private Paddle myPaddle; TODO
    private int total = 0;
    private boolean activeRound = false;

    @Override
    public void start(Stage stage) throws Exception {

        myScene = setUpScene(LEVEL1_LAYOUT);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

//        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
//        Timeline animation = new Timeline();
//        animation.setCycleCount(Timeline.INDEFINITE);
//        animation.getKeyFrames().add(frame);
//        animation.play();
    }
//TODO: Level Select class, confirming when blocks are broken / level is beaten
    Scene setUpScene (String layoutFileName) throws IOException {
        Group root = new Group();

        File localStream = new File(layoutFileName);

        BufferedReader br = new BufferedReader(new FileReader(localStream));

        int row = 0;
        int col = 0;
        String line;
        while ((line = br.readLine()) != null) {
            String[] brickSymbols = line.split(" ");
            for (String symbol : brickSymbols) {
                int x = col % STAGE_WIDTH;// + BRICK_SPACE/2;
                int y = row;// + BRICK_SPACE/2;
                if (symbol.compareTo(BLANK_SYMBOL) != 0) {
                    Brick newBrick = new Brick(x, y, symbol);
                    newBrick.init();
                    root.getChildren().add(newBrick);

                    Ball newBall = new Ball(STAGE_WIDTH/2,STAGE_HEIGHT,BALL_SPEED,BALL_RADIUS);
                    myBall = newBall;
                    myBall.setId("Ball");
                    root.getChildren().add(newBall);

//                    Paddle newPaddle = new Paddle(PADDLE_INIT_X, PADDLE_INIT_Y, ); TODO
//                    myPaddle.setId("Ball"); TODO
//                    root.getChildren().add(newPaddle); TODO
                }
                col += BRICK_WIDTH;
            }
            row += BRICK_HEIGHT;
        }
        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);

        return scene;
    }
/** TODO: Add a line to show the angle the ball will be launched at
    private void handleLaunch (KeyCode code) {
        double angle = 90;
        switch (code) {
            case UP ->
            case DOWN ->
            case SPACE -> {
                activeRound = true;
                myBall.startStop(activeRound, angle);
            }
        }
    }
 */

    private void step(double ellapsedTime) {
//        Ball.updatePosition(ellapsedTime);
//        Ball.checkPaddleCollision();
//        Ball.checkBlockCollision();
//        Ball.checkBoundary();
    }

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
