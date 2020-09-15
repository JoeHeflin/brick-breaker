package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
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
    public static final int STAGE_WIDTH = 10*BRICK_WIDTH;
    public static final int STAGE_HEIGHT = 400;
    public static final Paint BRICK_COLOR = Color.RED;
    public static final int BRICK_SPACE = 2;

    private Scene myScene;
    private int total = 0;

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
                int x = col % STAGE_WIDTH;// + BRICK_SPACE/2; TODO
                int y = row;// + BRICK_SPACE/2; TODO
                if (symbol.compareTo("0") != 0) {
//                    Brick newBrick = new Brick(root, x, y, symbol); TODO

                    Rectangle newBrick = new Rectangle(x, y, BRICK_WIDTH - BRICK_SPACE/2, BRICK_HEIGHT - BRICK_SPACE/2);
                    newBrick.setFill(BRICK_COLOR);
                    root.getChildren().add(newBrick);
                }
                col += BRICK_WIDTH;
            }
            row += BRICK_HEIGHT;
        }
        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);

        return scene;
    }

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
