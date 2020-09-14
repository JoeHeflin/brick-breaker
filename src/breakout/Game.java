package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Game extends Application {

    private static final String LEVEL1_LAYOUT = "levelFormats/level1.txt";
    private static final String TITLE = "New Game";
    private static final int BRICK_WIDTH = 36;
    private static final int BRICK_HEIGHT = 10;
    private static final int STAGE_WIDTH = 10*BRICK_WIDTH;
    private static final int STAGE_HEIGHT = 400;
    private static final Paint BRICK_COLOR = Color.RED;
    private static final int BRICK_SPACE = 2;

    private Scene myScene;

    @Override
    public void start(Stage stage) throws Exception {
        //Group root = new Group();
        myScene = setUpScene(LEVEL1_LAYOUT);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
    }

    Scene setUpScene (String layoutFileName) throws Exception {
        Group root = new Group();

        File localStream = new File(layoutFileName);
        BufferedReader br = new BufferedReader(new FileReader(localStream));

        String line;
        int row = 0;
        int col = 0;
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

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
